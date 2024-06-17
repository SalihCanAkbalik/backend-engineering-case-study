package com.dreamgames.backendengineeringcasestudy.controller;

import com.dreamgames.backendengineeringcasestudy.controller.request.GetGroupLeaderboardRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.GetGroupRankRequest;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupRankResponse;
import com.dreamgames.backendengineeringcasestudy.dao.entity.GroupLeaderboard;
import com.dreamgames.backendengineeringcasestudy.service.GroupLeaderboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group-leaderboards")
public class GroupLeaderboardController {

    private final GroupLeaderboardService groupLeaderboardService;

    public GroupLeaderboardController(GroupLeaderboardService groupLeaderboardService) {
        this.groupLeaderboardService = groupLeaderboardService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GroupLeaderboard>> getAllLeaderboards(){
        return new ResponseEntity<>(groupLeaderboardService.getAllLeaderboards(), HttpStatus.OK);
    }

    @GetMapping("/get-tournament-group-rank")
    public ResponseEntity<GetGroupRankResponse> getGroupRank(GetGroupRankRequest request){
        return new ResponseEntity<>(groupLeaderboardService.getGroupRank(request), HttpStatus.OK);
    }

    @GetMapping("/get-group-scores/{id}")
    public ResponseEntity<List<GetGroupLeaderboardResponse>> getGroupLeaderboardById(GetGroupLeaderboardRequest request) {
        return new ResponseEntity<>(groupLeaderboardService.getGroupList(request.getGroupId()),HttpStatus.OK);
    }
}
