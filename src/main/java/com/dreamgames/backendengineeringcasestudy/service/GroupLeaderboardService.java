package com.dreamgames.backendengineeringcasestudy.service;

import com.dreamgames.backendengineeringcasestudy.controller.request.GetGroupRankRequest;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupLeaderboardResponse;
import com.dreamgames.backendengineeringcasestudy.controller.response.GetGroupRankResponse;
import com.dreamgames.backendengineeringcasestudy.dao.entity.GroupLeaderboard;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;

import java.util.List;

public interface GroupLeaderboardService {

    List<GroupLeaderboard> getAllLeaderboards();

    List<GetGroupLeaderboardResponse> getGroupList(Long id);

    GetGroupRankResponse getGroupRank(GetGroupRankRequest request);
}
