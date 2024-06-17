package com.dreamgames.backendengineeringcasestudy.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetGroupLeaderboardResponse {
    private Long userId;
    private String name;
    private String country;
    private Integer groupScore;
}
