package com.dreamgames.backendengineeringcasestudy.mapper;

import com.dreamgames.backendengineeringcasestudy.controller.request.CreateUserRequest;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;
import com.dreamgames.backendengineeringcasestudy.dto.UsersDto;
import com.dreamgames.backendengineeringcasestudy.enumeration.Country;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UsersMapper {

    UsersDto toDto(Users entity);

    Users toEntity(UsersDto usersDto);

    Users toEntityWithCreateRequest(CreateUserRequest request, Country country);

}
