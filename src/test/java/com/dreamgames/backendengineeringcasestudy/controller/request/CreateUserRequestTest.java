package com.dreamgames.backendengineeringcasestudy.controller.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserRequestTest {
    @Test
    void getterAndSetterTest(){
        CreateUserRequest request = new CreateUserRequest();
        request.setAge(1);
        request.setName("value");
        request.setSurname("value");
        assertEquals(1,request.getAge());
        assertEquals("value",request.getName());
        assertEquals("value",request.getSurname());
    }


}