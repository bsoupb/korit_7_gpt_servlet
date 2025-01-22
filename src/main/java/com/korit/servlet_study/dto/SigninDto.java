package com.korit.servlet_study.dto;


import com.korit.servlet_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninDto {
    private String username;
    private String password;

    public User toSigninUser(){
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
