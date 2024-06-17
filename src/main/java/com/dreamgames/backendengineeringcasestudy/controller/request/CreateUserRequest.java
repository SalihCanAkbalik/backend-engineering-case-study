package com.dreamgames.backendengineeringcasestudy.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateUserRequest implements Serializable {
    private String name;
    private String surname;
    private Integer age;
}
