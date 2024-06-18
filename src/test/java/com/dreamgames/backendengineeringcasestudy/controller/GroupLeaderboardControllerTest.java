package com.dreamgames.backendengineeringcasestudy.controller;

import com.dreamgames.backendengineeringcasestudy.controller.request.GetGroupLeaderboardRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.GetGroupRankRequest;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupRankResponse;
import com.dreamgames.backendengineeringcasestudy.dao.entity.GroupLeaderboard;
import com.dreamgames.backendengineeringcasestudy.service.GroupLeaderboardService;
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
class GroupLeaderboardControllerTest {

    @Mock
    private GroupLeaderboardService groupLeaderboardService;

    private GroupLeaderboardController groupLeaderboardController;

    @BeforeEach
    void setUp(){
        groupLeaderboardController = new GroupLeaderboardController(groupLeaderboardService);
    }

    @Test
    void whenGetAllGroupLeaderboard(){
        List<GroupLeaderboard> groupLeaderboardList = new ArrayList<>();
        GroupLeaderboard groupLeaderboard = new GroupLeaderboard();
        groupLeaderboardList.add(groupLeaderboard);

        when(groupLeaderboardService.getAllLeaderboards()).thenReturn(groupLeaderboardList);
        ResponseEntity<List<GroupLeaderboard>> actual = groupLeaderboardController.getAllLeaderboards();

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    void whenGetTournamentGroupRank(){
        GetGroupRankResponse response = new GetGroupRankResponse();
        GetGroupRankRequest request = new GetGroupRankRequest();
        request.setId(123L);

        when(groupLeaderboardService.getGroupRank(any())).thenReturn(response);
        ResponseEntity<GetGroupRankResponse> actual = groupLeaderboardController.getGroupRank(request);

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    void whenGetGroupScores(){
        List<GetGroupLeaderboardResponse> response = new ArrayList<>();
        GetGroupLeaderboardRequest request = new GetGroupLeaderboardRequest();
        request.setGroupId(123L);

        when(groupLeaderboardService.getGroupList(any())).thenReturn(response);
        ResponseEntity<List<GetGroupLeaderboardResponse>> actual = groupLeaderboardController.getGroupLeaderboardById(request);

        assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

}