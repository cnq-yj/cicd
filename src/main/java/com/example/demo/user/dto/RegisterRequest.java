package com.example.demo.user.dto;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String zipcode;
    private String baseAddress;
    private String detailAddress;
}
