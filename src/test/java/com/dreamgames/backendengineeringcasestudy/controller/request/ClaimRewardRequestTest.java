package com.dreamgames.backendengineeringcasestudy.controller.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClaimRewardRequestTest {
    @Test
    void getterAndSetterTest(){
        ClaimRewardRequest request = new ClaimRewardRequest();
        request.setId(123L);
        assertEquals(123L,request.getId());
    }

}