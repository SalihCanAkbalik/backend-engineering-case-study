package com.dreamgames.backendengineeringcasestudy.service;

import com.dreamgames.backendengineeringcasestudy.controller.response.CreateUserResponse;
import com.dreamgames.backendengineeringcasestudy.controller.request.CreateUserRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.UpdateLevelRequest;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;
import com.dreamgames.backendengineeringcasestudy.dto.UsersDto;

import java.util.List;

public interface UsersService {
    List<Users> getAll();

    CreateUserResponse createNewUser(CreateUserRequest request) throws Exception;

    Users updateLevelProgress(UpdateLevelRequest request) throws Exception;

    Users getById(Long id) throws Exception;

}
