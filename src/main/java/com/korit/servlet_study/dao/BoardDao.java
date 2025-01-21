package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class BoardDao {
    private DBConnectionMgr mgr;
    private static BoardDao boardDao;

    private BoardDao() {
        mgr = DBConnectionMgr.getInstance();
    }

    public static BoardDao getInstance() {
        if (boardDao == null) {
            boardDao = new BoardDao();
        }
        return boardDao;
    }


    public Optional<Board> save(Board board) {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = mgr.getConnection();
            String sql = """
                        insert into
                        board_tb
                        values(default, ?, ?)                    
                    """;
            ps = con.prepareStatement(sql);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.executeUpdate();

            return Optional.ofNullable(board);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
