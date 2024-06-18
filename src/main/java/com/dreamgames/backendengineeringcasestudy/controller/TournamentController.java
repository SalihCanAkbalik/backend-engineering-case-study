package com.dreamgames.backendengineeringcasestudy.controller;

import com.dreamgames.backendengineeringcasestudy.controller.request.ClaimRewardRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.EnterTournamentRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.GetCountryLeaderboardRequest;
import com.dreamgames.backendengineeringcasestudy.controller.response.ClaimRewardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetCountryLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.dao.entity.GroupLeaderboard;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;
import com.dreamgames.backendengineeringcasestudy.service.TournamentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournament")
public class TournamentController {
    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/start")
    public ResponseEntity<Tournament> startTournament() {
        return new ResponseEntity<>(tournamentService.createTournament(),HttpStatus.OK);
    }

    @GetMapping("/get-all-tournaments")
    public ResponseEntity<List<Tournament>> getAll(){
        return new ResponseEntity<>(tournamentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getAll-wants-to-enter-tournament")
    public ResponseEntity<List<Users>> allWantsEnterTournament(){
        return new ResponseEntity<>(tournamentService.allWantEnterTournament(), HttpStatus.OK);
    }

    @GetMapping("/get-tournament-reward")
    public ResponseEntity<ClaimRewardResponse> getReward(ClaimRewardRequest request){
        return new ResponseEntity<>(tournamentService.getReward(request), HttpStatus.OK);
    }

    @PutMapping("/enter-tournament-user/{id}")
    public ResponseEntity<List<GetGroupLeaderboardResponse>> joinTournament(@Valid @RequestBody EnterTournamentRequest request){
        return new ResponseEntity<>(tournamentService.enterTournament(request.getId()),HttpStatus.OK);
    }

    @GetMapping("/get-country-leaderboard")
    public ResponseEntity<List<GetCountryLeaderboardResponse>> getCountryLeaderboard(GetCountryLeaderboardRequest request){
        return new ResponseEntity<>(tournamentService.getCountryLeaderboardAndScores(request),HttpStatus.OK);
    }
}
