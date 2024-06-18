package com.dreamgames.backendengineeringcasestudy.dao.entity;

import com.dreamgames.backendengineeringcasestudy.enumeration.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {

    @Test
    void getterAndSetterTest(){
        Users entity = new Users();
        GroupLeaderboard groupLeaderboard = new GroupLeaderboard();
        Tournament tournament = new Tournament();
        entity.setId(123L);
        entity.setName("value");
        entity.setSurname("value");
        entity.setAge(1);
        entity.setUserLevel(1);
        entity.setCoins(1);
        entity.setCountry(Country.Turkey);
        entity.setEnterTournament(true);
        entity.setIsAlreadyEntered(true);
        entity.setIsGetLastTournamentReward(true);
        entity.setGroupScores(1);
        entity.setTournament(tournament);
        entity.setGroupLeaderboard(groupLeaderboard);
        assertEquals(123L,entity.getId());
        assertEquals("value",entity.getName());
        assertEquals("value",entity.getSurname());
        assertEquals(1,entity.getAge());
        assertEquals(1,entity.getUserLevel());
        assertEquals(1,entity.getCoins());
        assertEquals(Country.Turkey,entity.getCountry());
        assertEquals(true,entity.getEnterTournament());
        assertEquals(true,entity.getIsAlreadyEntered());
        assertEquals(true,entity.getIsGetLastTournamentReward());
        assertEquals(1,entity.getGroupScores());
        assertEquals(tournament,entity.getTournament());
        assertEquals(groupLeaderboard,entity.getGroupLeaderboard());
    }

}