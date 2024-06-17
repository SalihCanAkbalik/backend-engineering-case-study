package com.dreamgames.backendengineeringcasestudy.dto;

import com.dreamgames.backendengineeringcasestudy.enumeration.Country;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsersDto implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private Integer userLevel;
    private Integer coins;
    private Country country;
    private Boolean enterTournament;
}
