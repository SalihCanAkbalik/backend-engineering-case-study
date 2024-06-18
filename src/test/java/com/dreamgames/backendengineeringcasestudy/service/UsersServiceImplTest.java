package com.dreamgames.backendengineeringcasestudy.service;

import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;
import com.dreamgames.backendengineeringcasestudy.dao.repository.UsersRepository;
import com.dreamgames.backendengineeringcasestudy.mapper.UsersMapper;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {
    @InjectMocks
    private UsersServiceImpl service;

    @Mock
    private UsersRepository repository;

    @Mock
    private UsersMapper mapper;

    private static final Random random = new Random();
    private static final Integer START_LEVEL = 1;
    private static final Integer START_COIN = 5000;

    @BeforeEach
    void setUp(){
        service = Mockito.spy(new UsersServiceImpl(repository,mapper));
    }

    @Test
    void whenGetAllUsers(){
        List<Users> usersList = new ArrayList<>();
        Users entity = new Users();
        entity.setId(123L);
        usersList.add(entity);

        when(repository.findAll()).thenReturn(usersList);
        verify(repository,times(1)).findById(123L);
    }
}