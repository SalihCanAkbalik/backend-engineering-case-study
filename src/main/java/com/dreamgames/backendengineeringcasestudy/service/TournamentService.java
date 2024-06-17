package com.dreamgames.backendengineeringcasestudy.service;

import com.dreamgames.backendengineeringcasestudy.controller.request.ClaimRewardRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.GetCountryLeaderboardRequest;
import com.dreamgames.backendengineeringcasestudy.controller.response.ClaimRewardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetCountryLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.dao.entity.GroupLeaderboard;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;

import java.util.List;

public interface TournamentService {
    List<GetGroupLeaderboardResponse> enterTournament(Long id);

    List<Tournament> getAll();

    Tournament createTournament();

    void resetUserStates();

    List<Users> allWantEnterTournament();

    ClaimRewardResponse getReward(ClaimRewardRequest request);

    List<GetCountryLeaderboardResponse> getCountryLeaderboardAndScores(GetCountryLeaderboardRequest request);
}
