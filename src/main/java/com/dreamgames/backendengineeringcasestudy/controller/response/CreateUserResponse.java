package com.dreamgames.backendengineeringcasestudy.controller.response;

import com.dreamgames.backendengineeringcasestudy.enumeration.Country;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateUserResponse implements Serializable {
    private Long id;
    private Integer user_level;
    private Integer coins;
    private Country country;
}
