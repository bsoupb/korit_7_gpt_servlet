package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.AuthDao;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.SigninDto;
import com.korit.servlet_study.dto.SignupDto;
import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.security.jwt.JwtProvider;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private AuthDao authDao;
    private JwtProvider jwtProvider;
    private static AuthService instance;

    private AuthService() {
        authDao = new AuthDao();
        jwtProvider = JwtProvider.getInstance();
    }

    public static AuthService getInstance() {
        if(instance == null) {
            return new AuthService();
        }
        return instance;
    }


    public ResponseDto<?> signup(SignupDto dto) {
        User insertedUser = dto.toUser();

        User user = authDao.signup(insertedUser);

        if(insertedUser != null) {
            return ResponseDto.success(user);
        } else {
            return ResponseDto.fail("회원가입 실패");
        }
    }

    public ResponseDto<?> signin(SigninDto signinDto){
        User foundUser = authDao.findUserByUsername(signinDto.getUsername());
        if(foundUser == null) {
            return ResponseDto.fail("사용자 정보를 다시 확인하세요");
        }
        if(!BCrypt.checkpw(signinDto.getPassword(), foundUser.getPassword())) {
            return ResponseDto.fail("사용자 정보를 다시 확인하세요");
        }
        return ResponseDto.success(jwtProvider.generateToken(foundUser));
    }
}
