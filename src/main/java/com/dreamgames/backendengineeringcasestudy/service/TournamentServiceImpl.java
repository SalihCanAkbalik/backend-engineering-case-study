package com.dreamgames.backendengineeringcasestudy.service;

import com.dreamgames.backendengineeringcasestudy.controller.request.ClaimRewardRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.GetCountryLeaderboardRequest;
import com.dreamgames.backendengineeringcasestudy.controller.response.ClaimRewardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetCountryLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.dao.entity.GroupLeaderboard;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;
import com.dreamgames.backendengineeringcasestudy.dao.repository.GroupLeaderboardRepository;
import com.dreamgames.backendengineeringcasestudy.dao.repository.TournamentRepository;
import com.dreamgames.backendengineeringcasestudy.dao.repository.UsersRepository;
import com.dreamgames.backendengineeringcasestudy.enumeration.Country;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TournamentServiceImpl implements TournamentService {
    private final UsersRepository usersRepository;
    private final TournamentRepository tournamentRepository;
    private final GroupLeaderboardRepository groupLeaderboardRepository;

    @Autowired
    public TournamentServiceImpl(UsersRepository repository, TournamentRepository tournamentRepository, GroupLeaderboardRepository groupLeaderboardRepository) {
        this.usersRepository = repository;
        this.tournamentRepository = tournamentRepository;
        this.groupLeaderboardRepository = groupLeaderboardRepository;
    }

    @Override
    @Transactional
    public Tournament createTournament() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.withHour(20).withMinute(0);

        Optional<Tournament> existingTournament = tournamentRepository.findTournamentForToday(startOfDay, endOfDay);
        if (existingTournament.isPresent()) {
            log.info("A tournament already exists for today.");
            createTournamentGroups(existingTournament.get());
            return existingTournament.get();
        }

        Tournament tournament = new Tournament();
        tournament.setStartTime(LocalDateTime.now());
        tournament.setEndTime(endOfDay);
        tournamentRepository.save(tournament);

        createTournamentGroups(tournament);
        return tournament;
    }

    @Override
    public void resetUserStates() {
        List<Users> users = usersRepository.findAll();
        for (Users user : users) {
            user.setIsAlreadyEntered(false);
            user.setEnterTournament(false);
            user.setIsGetLastTournamentReward(false);
        }
        usersRepository.saveAll(users);
    }


    @Override
    @Transactional
    public List<GetGroupLeaderboardResponse> enterTournament(Long id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!user.getIsAlreadyEntered() && user.getUserLevel() >= 20 && user.getCoins() >= 1000){
            user.setEnterTournament(true);
            user.setGroupScores(0);
            user.setCoins(user.getCoins() - 1000);
            user.setIsGetLastTournamentReward(false);

            usersRepository.save(user);
            log.info("User {} is now set to enter the tournament.", id);

            createTournament();

            return findUserToTournamentGroup(user);
        }else {
            log.info("User {} cannot join tournament.", id);
            return null;
        }
    }

    @Override
    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    @Override
    public List<Users> allWantEnterTournament() {
        List<Users> usersList = usersRepository.findByEnterTournamentTrueAndIsAlreadyEnteredFalse().stream()
                .filter(users -> users.getUserLevel()>= 20 && users.getCoins() >= 1000)
                .toList();
        return usersList;
    }

    @Override
    public ClaimRewardResponse getReward(ClaimRewardRequest request) {
        Users user = usersRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        GroupLeaderboard groupLeaderboard = user.getGroupLeaderboard();
        Tournament tournament = groupLeaderboard.getTournament();
        ClaimRewardResponse response = new ClaimRewardResponse();

        if (LocalDateTime.now().isBefore(tournament.getEndTime()) && user.getIsGetLastTournamentReward()) {
            throw new IllegalStateException("The tournament not ended yet.");
        }

        if (user.getIsGetLastTournamentReward()) {
            throw new IllegalStateException("The user already get reward.");
        }

        List<Users> usersList = groupLeaderboard.getUsersList();

        List<Users> sortedUsers = usersList.stream()
                .sorted(Comparator.comparingInt(Users::getGroupScores).reversed())
                .toList();

        int rank = sortedUsers.indexOf(user) + 1;

        if (rank == 1) {
            user.setCoins(user.getCoins() + 10000);
        } else if (rank == 2) {
            user.setCoins(user.getCoins() + 5000);
        }

        user.setIsGetLastTournamentReward(true);
        usersRepository.save(user);

        response.setId(user.getId());
        response.setCoins(user.getCoins());
        response.setName(user.getName());

        return response;
    }


    @Transactional
    private void createTournamentGroups(Tournament tournament) {
        // Turnuvaya katılmak isteyen ve daha önce katılmamış olan tüm kullanıcılar
        List<Users> users = usersRepository.findByEnterTournamentTrueAndIsAlreadyEnteredFalse().stream()
                .filter(user -> user.getUserLevel() >= 20 && user.getCoins() >= 1000)
                .toList();

        // Mevcut tamamlanmamış gruplar
        List<GroupLeaderboard> incompleteGroups = groupLeaderboardRepository.findAll().stream()
                .filter(group -> group.getUsersList().size() < 5)
                .collect(Collectors.toList());

        // Her bir ülkeden bir kullanıcısırayla listeye ekle
        List<Users> selectedUsers = new ArrayList<>();
        Map<Country, Boolean> countryFilled = new HashMap<>();
        for (Users user : users) {
            if (!countryFilled.containsKey(user.getCountry())) {
                countryFilled.put(user.getCountry(), true); // Ülke işaretle
                selectedUsers.add(user);
                user.setIsAlreadyEntered(true); // Kullanıcı işaretle
            }
        }

        usersRepository.saveAll(selectedUsers);

        // Seçilen kullanıcıları yerleştir
        for (GroupLeaderboard group : incompleteGroups) {
            int spaceLeft = 5 - group.getUsersList().size();
            List<Users> usersToAdd = selectedUsers.stream().limit(spaceLeft).toList();
            group.getUsersList().addAll(usersToAdd);
            selectedUsers.removeAll(usersToAdd);

            for (Users user : usersToAdd) {
                user.setGroupLeaderboard(group);
            }
        }

        groupLeaderboardRepository.saveAll(incompleteGroups);
        usersRepository.saveAll(selectedUsers);

        // Eğer hala seçilen kullanıcılar varsa yeni gruplar oluşturulur
        int groupSize = 5;
        List<GroupLeaderboard> newGroupLeaderboards = new ArrayList<>();
        for (int i = 0; i < selectedUsers.size(); i += groupSize) {
            int endIndex = Math.min(i + groupSize, selectedUsers.size());
            List<Users> groupUsers = selectedUsers.subList(i, endIndex);

            GroupLeaderboard groupLeaderboard = new GroupLeaderboard();
            groupLeaderboard.setTournament(tournament);
            groupLeaderboard.setUsersList(new ArrayList<>(groupUsers));
            newGroupLeaderboards.add(groupLeaderboard);

            for (Users user : groupUsers) {
                user.setGroupLeaderboard(groupLeaderboard);
            }
        }
        groupLeaderboardRepository.saveAll(newGroupLeaderboards);
        usersRepository.saveAll(selectedUsers);
    }

    @Transactional
    private List<GetGroupLeaderboardResponse> findUserToTournamentGroup(Users user) {
        List<GroupLeaderboard> groupLeaderboards = groupLeaderboardRepository.findAll();
        for (GroupLeaderboard groupLeaderboard : groupLeaderboards) {
            List<Users> users = groupLeaderboard.getUsersList();
            if (users.contains(user)) {
                log.info("User {} found in group leaderboard {}", user.getId(), groupLeaderboard.getId());

                GroupLeaderboard userGroup = groupLeaderboardRepository.findById(groupLeaderboard.getId())
                        .orElseThrow(() -> new RuntimeException("Cannot found user group."));

                List<Users> groupUsers = userGroup.getUsersList();

                List<GetGroupLeaderboardResponse> sortedUsers = groupUsers.stream()
                        .sorted(Comparator.comparingInt(Users::getGroupScores).reversed())
                        .map(sortedUser -> {
                            GetGroupLeaderboardResponse response = new GetGroupLeaderboardResponse();
                            response.setUserId(sortedUser.getId());
                            response.setName(sortedUser.getName());
                            response.setCountry(String.valueOf(sortedUser.getCountry()));
                            response.setGroupScore(sortedUser.getGroupScores());
                            return response;
                        })
                        .collect(Collectors.toList());

                return sortedUsers;
            }
        }
        return null;
    }

    @Override
    public List<GetCountryLeaderboardResponse> getCountryLeaderboardAndScores(GetCountryLeaderboardRequest request) {
        List<GroupLeaderboard> groupLeaderboards = groupLeaderboardRepository.findByTournamentId(request.getTournamentId());

        Map<String, Integer> countryScores = new HashMap<>();
        for (GroupLeaderboard groupLeaderboard : groupLeaderboards) {
            for (Users user : groupLeaderboard.getUsersList()) {
                countryScores.put(user.getCountry().name(), countryScores.getOrDefault(user.getCountry().name(), 0) + user.getGroupScores());
            }
        }

        List<GetCountryLeaderboardResponse> responses = countryScores.entrySet().stream()
                .map(entry -> {
                    GetCountryLeaderboardResponse dto = new GetCountryLeaderboardResponse();
                    dto.setCountry(entry.getKey());
                    dto.setTotalCountryScore(entry.getValue());
                    return dto;
                })
                .sorted(Comparator.comparingInt(GetCountryLeaderboardResponse::getTotalCountryScore).reversed())
                .collect(Collectors.toList());
        return responses;
    }
}
