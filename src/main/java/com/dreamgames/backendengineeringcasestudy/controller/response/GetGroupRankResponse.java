package com.dreamgames.backendengineeringcasestudy.controller.response;

import com.dreamgames.backendengineeringcasestudy.dto.GroupRankDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetGroupRankResponse {
    private Long userId;
    private List<GroupRankDTO> groupRanks;
}
