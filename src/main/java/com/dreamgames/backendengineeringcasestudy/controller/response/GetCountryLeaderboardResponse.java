package com.dreamgames.backendengineeringcasestudy.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCountryLeaderboardResponse {
    private String country;
    private Integer totalCountryScore;
}
