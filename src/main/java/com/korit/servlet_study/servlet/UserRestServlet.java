package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.security.annotation.JwtValid;
import com.korit.servlet_study.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class UserRestServlet extends HttpServlet {
    private UserService userService;

    public UserRestServlet() {
        userService = UserService.getInstance();
    }

    @JwtValid
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String userIdParam = req.getParameter("userId");
        int userId = Integer.parseInt(userIdParam);
        ResponseDto<?> responseDto = userService.getUser(userId);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(responseDto);
        System.out.println(jsonUser);

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        resp.setContentType("application/json");
        resp.getWriter().println(jsonUser);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
