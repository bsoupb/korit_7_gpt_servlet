package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class BoardDao {
    private DBConnectionMgr mgr;
    private static BoardDao instance;

    private BoardDao() {
        mgr = DBConnectionMgr.getInstance();
    }

    public static BoardDao getInstance() {
        if (instance == null) {
            instance = new BoardDao();
        }
        return instance;
    }


    public Board save(Board board) {
        Board insertedBoard = null;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = mgr.getConnection();
            String sql = """
                        insert into
                        board_tb
                        values(default, ?, ?)                    
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                insertedBoard = Board.builder()
                        .boardId(rs.getInt(1))
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            mgr.freeConnection(con, ps);
        }

        return insertedBoard;
    }

}
