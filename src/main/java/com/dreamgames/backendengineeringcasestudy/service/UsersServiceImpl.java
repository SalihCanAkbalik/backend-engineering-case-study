package com.dreamgames.backendengineeringcasestudy.service;

import com.dreamgames.backendengineeringcasestudy.controller.response.CreateUserResponse;
import com.dreamgames.backendengineeringcasestudy.controller.request.CreateUserRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.UpdateLevelRequest;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;
import com.dreamgames.backendengineeringcasestudy.dao.repository.UsersRepository;
import com.dreamgames.backendengineeringcasestudy.enumeration.Country;
import com.dreamgames.backendengineeringcasestudy.mapper.UsersMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;
    private final UsersMapper mapper;

    private static final Random random = new Random();
    private static final Integer START_LEVEL = 1;
    private static final Integer START_COIN = 5000;

    public UsersServiceImpl(UsersRepository repository, UsersMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Users> getAll() {
        List<Users> users = repository.findAll().stream().toList();
        log.info("Fetched {} users from the database.", users.size());
        return users;
    }

    @Transactional
    public CreateUserResponse createNewUser(CreateUserRequest request) throws RuntimeException{
        try {
            CreateUserResponse response = new CreateUserResponse();
            Country[] countries = Country.values();
            Country country = countries[random.nextInt(countries.length)];
            Users createdUser = new Users();

            createdUser.setName(request.getName());
            createdUser.setSurname(request.getSurname());
            createdUser.setAge(request.getAge());
            createdUser.setUserLevel(START_LEVEL);
            createdUser.setCoins(START_COIN);
            createdUser.setCountry(country);
            createdUser.setIsAlreadyEntered(false);
            createdUser.setEnterTournament(false);
            createdUser.setIsGetLastTournamentReward(false);
            createdUser.setGroupScores(0);

            repository.save(createdUser);

            //Users newUser = repository.save(mapper.toEntityWithCreateRequest(request,country));

            response.setId(createdUser.getId());
            response.setUser_level(createdUser.getUserLevel());
            response.setCoins(createdUser.getCoins());
            response.setCountry(createdUser.getCountry());
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Users updateLevelProgress(UpdateLevelRequest request) throws Exception {
        Users levelUpUser = getById(request.getId());

        levelUpUser.setUserLevel(levelUpUser.getUserLevel() + 1);
        levelUpUser.setCoins(levelUpUser.getCoins() + 25);

        if (levelUpUser.getIsAlreadyEntered()){
            levelUpUser.setGroupScores(levelUpUser.getGroupScores() + 1);
        }

        return repository.save(levelUpUser);
    }

    @Override
    public Users getById(Long id) throws Exception{
        return repository.findById(id).orElseThrow(() -> new Exception("data Not Found"));
    }
}
