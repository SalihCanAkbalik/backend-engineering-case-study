package com.dreamgames.backendengineeringcasestudy.service;

import com.dreamgames.backendengineeringcasestudy.controller.request.GetGroupRankRequest;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupRankResponse;
import com.dreamgames.backendengineeringcasestudy.dao.entity.GroupLeaderboard;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;
import com.dreamgames.backendengineeringcasestudy.dao.repository.GroupLeaderboardRepository;
import com.dreamgames.backendengineeringcasestudy.dao.repository.TournamentRepository;
import com.dreamgames.backendengineeringcasestudy.dao.repository.UsersRepository;
import com.dreamgames.backendengineeringcasestudy.dto.GroupRankDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GroupLeaderboardServiceImpl implements GroupLeaderboardService{

    private final UsersRepository usersRepository;
    private final TournamentRepository tournamentRepository;
    private final GroupLeaderboardRepository groupLeaderboardRepository;

    @Autowired
    public GroupLeaderboardServiceImpl(UsersRepository repository, TournamentRepository tournamentRepository, GroupLeaderboardRepository groupLeaderboardRepository) {
        this.usersRepository = repository;
        this.tournamentRepository = tournamentRepository;
        this.groupLeaderboardRepository = groupLeaderboardRepository;
    }
    @Override
    public List<GroupLeaderboard> getAllLeaderboards() {
        final List<GroupLeaderboard> groupLeaderboards = groupLeaderboardRepository.findAll();
        return groupLeaderboards;
    }

    @Override
    public GetGroupRankResponse getGroupRank(GetGroupRankRequest request) {
        Users user = usersRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<GroupLeaderboard> groupLeaderboards = groupLeaderboardRepository.findAllByUserId(user.getId());

        List<GroupRankDTO> groupRanks = new ArrayList<>();

        for (GroupLeaderboard groupLeaderboard : groupLeaderboards) {
            // Lazy Initialization Exception'ı önlemek için kullanıcı listesini yükle
            List<Users> usersList = groupLeaderboard.getUsersList();

            List<Users> sortedUsers = usersList.stream()
                    .sorted(Comparator.comparingInt(Users::getGroupScores).reversed())
                    .toList();

            int rank = sortedUsers.indexOf(user) + 1;

            GroupRankDTO groupRank = new GroupRankDTO();
            groupRank.setTournamentId(groupLeaderboard.getTournament().getId());
            groupRank.setGroupId(groupLeaderboard.getId());
            groupRank.setRank(rank);
            groupRanks.add(groupRank);
        }

        GetGroupRankResponse response = new GetGroupRankResponse();
        response.setUserId(user.getId());
        response.setGroupRanks(groupRanks);

        return response;
    }


    @Override
    public List<GetGroupLeaderboardResponse> getGroupList(Long id) {
        GroupLeaderboard groupLeaderboard = groupLeaderboardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot found user group."));

        List<Users> users = groupLeaderboard.getUsersList();
        List<GetGroupLeaderboardResponse> sortedUsers = users.stream()
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
