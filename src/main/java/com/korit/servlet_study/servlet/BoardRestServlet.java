package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dao.BoardDao;
import com.korit.servlet_study.dto.InsertBoardDto;
import com.korit.servlet_study.entity.Board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/board")
public class BoardRestServlet extends HttpServlet {
    private BoardDao boardDao;

    public BoardRestServlet() {
        boardDao = BoardDao.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();

        Board board = Board.builder()
                .boardId("board_Id")
                .title("title")
                .content("content")
                .build();


        try(BufferedReader bufferedReader = request.getReader()){
            String line;
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        InsertBoardDto insertBoardDto = objectMapper.readValue(stringBuilder.toString(), InsertBoardDto.class);
        System.out.println(insertBoardDto);
    }
}
