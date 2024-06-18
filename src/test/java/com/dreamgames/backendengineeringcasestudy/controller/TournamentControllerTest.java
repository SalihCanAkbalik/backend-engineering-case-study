package com.dreamgames.backendengineeringcasestudy.controller;

import com.dreamgames.backendengineeringcasestudy.controller.request.ClaimRewardRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.EnterTournamentRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.GetCountryLeaderboardRequest;
import com.dreamgames.backendengineeringcasestudy.controller.response.ClaimRewardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetCountryLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;
import com.dreamgames.backendengineeringcasestudy.service.TournamentService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TournamentControllerTest {

    @Mock
    private TournamentService service;

    private TournamentController controller;

    @BeforeEach
    void setUp(){
        controller = new TournamentController(service);
    }

    @Test
    void whenStartTournamnet(){
        Tournament tournament = new Tournament();
        when(service.createTournament()).thenReturn(tournament);
        ResponseEntity<Tournament> actual = controller.startTournament();

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    void whenGetAllTournamnets(){
        List<Tournament> tournaments = new ArrayList<>();
        when(service.getAll()).thenReturn(tournaments);
        ResponseEntity<List<Tournament>> actual = controller.getAll();

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    void whenGetAllWantToEnterTournament(){
        List<Users> users = new ArrayList<>();
        when(service.allWantEnterTournament()).thenReturn(users);
        ResponseEntity<List<Users>> actual = controller.allWantsEnterTournament();

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    void whenGetReward(){
        ClaimRewardResponse response = new ClaimRewardResponse();
        ClaimRewardRequest request = new ClaimRewardRequest();
        when(service.getReward(any())).thenReturn(response);
        ResponseEntity<ClaimRewardResponse> actual = controller.getReward(request);

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    void whenSomeoneWantsToJoinTournament(){
        List<GetGroupLeaderboardResponse> responses = new ArrayList<>();
        EnterTournamentRequest request = new EnterTournamentRequest();
        when(service.enterTournament(any())).thenReturn(responses);
        ResponseEntity<List<GetGroupLeaderboardResponse>> actual = controller.joinTournament(request);

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    void whenReturnCountryLeaderboard(){
        List<GetCountryLeaderboardResponse> responses = new ArrayList<>();
        GetCountryLeaderboardRequest request = new GetCountryLeaderboardRequest();
        when(service.getCountryLeaderboardAndScores(any())).thenReturn(responses);
        ResponseEntity<List<GetCountryLeaderboardResponse>> actual = controller.getCountryLeaderboard(request);

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

}