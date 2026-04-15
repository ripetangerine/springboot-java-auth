package com.ripetangerine.auth.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    Long id;
    String username;
    String password;
}
