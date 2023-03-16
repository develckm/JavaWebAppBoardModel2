package com.acorn.webappboard.dao;

import com.acorn.webappboard.WebAppBoardConn;
import com.acorn.webappboard.dto.BoardsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardsDaoImpTest {

    private static BoardsDao boardsDao;
    @BeforeAll
    static void init() throws SQLException, ClassNotFoundException {
        Connection conn= WebAppBoardConn.getConn();
        boardsDao=new BoardsDaoImp(conn);
    }
    @Test
    void findAll() throws Exception {
        List<BoardsDto> boards=boardsDao.findAll();
        System.out.println("boards = " + boards);
        Assertions.assertNotNull(boards);
    }

    @Test
    void findByBId() throws Exception {
        BoardsDto board=boardsDao.findByBId(6);
        System.out.println("board = " + board);
        Assertions.assertNotNull(board);
    }
    @Test
    void save() {
    }

    @Test
    void updateByPk() {
    }

    @Test
    void deleteByPk() {
    }
}