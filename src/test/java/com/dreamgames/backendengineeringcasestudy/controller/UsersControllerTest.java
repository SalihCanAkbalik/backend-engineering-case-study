package com.dreamgames.backendengineeringcasestudy.controller;

import com.dreamgames.backendengineeringcasestudy.controller.request.CreateUserRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.UpdateLevelRequest;
import com.dreamgames.backendengineeringcasestudy.controller.response.CreateUserResponse;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;
import com.dreamgames.backendengineeringcasestudy.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    @Mock
    private UsersService service;

    private UsersController controller;

    @BeforeEach
    void setUp(){
        controller = new UsersController(service);
    }

    @Test
    void whenGetAllUsers(){
        List<Users> usersList = new ArrayList<>();
        when(service.getAll()).thenReturn(usersList);

        ResponseEntity<List<Users>> actual = controller.getAll();

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    void whenUserGetById() throws Exception {
        Users user = new Users();
        user.setId(123L);
        when(service.getById(anyLong())).thenReturn(user);

        ResponseEntity<Users> actual = controller.getById(user.getId());

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    void whenCreateNewUser() throws Exception {
        CreateUserResponse response = new CreateUserResponse();
        CreateUserRequest request = new CreateUserRequest();
        request.setName("value");
        request.setSurname("value");
        request.setAge(1);
        when(service.createNewUser(any())).thenReturn(response);

        ResponseEntity<CreateUserResponse> actual = controller.create(request);

        assertEquals(HttpStatus.CREATED,actual.getStatusCode());
    }

    @Test
    void whenUserLevelUp() throws Exception {
        Users user = new Users();
        UpdateLevelRequest request = new UpdateLevelRequest();

        when(service.updateLevelProgress(any())).thenReturn(user);

        ResponseEntity<Users> actual = controller.update(request);

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

}