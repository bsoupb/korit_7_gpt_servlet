package com.korit.servlet_study.dto;

import com.korit.servlet_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private String username;
    private String password;
    private String name;
    private String email;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(BCrypt.hashpw(password, BCrypt.gensalt(10)))      // 평문, 암호화 알고리즘에 사용되는 복잡도
                .name(name)
                .email(email)
                .build();
    }

}
