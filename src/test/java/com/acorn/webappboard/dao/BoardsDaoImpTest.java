package com.acorn.webappboard.dao;

import com.acorn.webappboard.WebAppBoardConn;
import com.acorn.webappboard.dto.BoardsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardsDaoImpTest {

    private static BoardsDao boardsDao;
    private static int testPk;
    @BeforeAll
    static void init() throws Exception {
        Connection conn= WebAppBoardConn.getConn();
        boardsDao=new BoardsDaoImp(conn);
        //미리 테스트할 내역을 등록 (수정 및 삭제 할 레코드)
        BoardsDto board=new BoardsDto();
        board.setUId("admin");
        board.setTitle("BoardDaoImp test 레코드");
        board.setContent("내용내용~");
        boardsDao.save(board);
        //jdbc 는 auto increment 했을 때 등록된 번호를 반환하지 않는다.
        //mysql(Auto Increment pk) : SELECT LAST_INSERT_ID()
        String sql="SELECT LAST_INSERT_ID() pk";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            testPk=rs.getInt("pk");
        }
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
    void save() throws Exception {
        BoardsDto board=new BoardsDto();
        board.setUId("user05");
        board.setTitle("등록 테스트!!");
        board.setContent("내용내용~");
        int save= boardsDao.save(board);
        Assertions.assertEquals(save,1);
    }

    @Test
    void updateByPk() throws Exception {
        BoardsDto board=new BoardsDto();
        board.setBId(testPk);
        board.setTitle("수정수정 테스트!!");
        board.setContent("내용내용 수정수정 합니다.");
        board.setStatus("PRIVATE");
        int update= boardsDao.updateByPk(board);
        Assertions.assertEquals(update,1);
    }

    @Test
    void deleteByPk() throws Exception {
        int delete= boardsDao.deleteByPk(testPk);
        Assertions.assertEquals(delete,1);
    }
}