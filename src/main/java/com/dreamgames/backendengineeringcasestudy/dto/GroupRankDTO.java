package com.dreamgames.backendengineeringcasestudy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRankDTO {
    private Long tournamentId;
    private Long groupId;
    private Integer rank;
}
