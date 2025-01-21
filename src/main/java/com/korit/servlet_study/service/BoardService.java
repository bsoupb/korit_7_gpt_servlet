package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.BoardDao;
import com.korit.servlet_study.dto.InsertBoardDto;
import com.korit.servlet_study.entity.Board;

public class BoardService {
    private BoardDao boardDao;

    private static BoardService instance;

    private BoardService() {
        boardDao = BoardDao.getInstance();
    }

    public static BoardService getInstance() {
        if(instance == null) {
            instance = new BoardService();
        }
        return instance;
    }


    public void insertBoard(InsertBoardDto dto) {
        Board board = dto.toBoard();
        boardDao.save(board);
    }
}
