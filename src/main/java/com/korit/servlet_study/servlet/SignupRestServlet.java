package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dao.AuthDao;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.SignupDto;
import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/signup")
public class SignupRestServlet extends HttpServlet {
    private AuthService authService;

    public SignupRestServlet() {
        authService = AuthService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();

        try(BufferedReader reader = req.getReader()) {
            String line;
            while( (line = reader.readLine()) != null ) {
                sb.append(line);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        SignupDto signupDto = mapper.readValue(sb.toString(), SignupDto.class);

        ResponseDto<?> responseDto = authService.signup(signupDto);
        String responseJson = mapper.writeValueAsString(responseDto);

        resp.setStatus(responseDto.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().println(responseJson);
    }
}
