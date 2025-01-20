package com.korit.servlet_study.servlet;

import com.korit.servlet_study.entity.User;
import com.korit.servlet_study.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService;

    public UserServlet() {
        userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchValue = request.getParameter("searchValue");

        request.setAttribute("users", userService.getAllUsers(searchValue));

        request.getRequestDispatcher("WEB-INF/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.builder()
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .build();

        userService.addUser(user);

        resp.sendRedirect("http://localhost:8080/servlet_study_war/user");
    }
}
