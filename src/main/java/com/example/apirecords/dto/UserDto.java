package com.example.apirecords.dto;

import lombok.Data;

public @Data class UserDto {
    private String medicalId;

    private String email;

    private String password;

    private String login;

    private String birthday;

    private String gestationDate;
}
