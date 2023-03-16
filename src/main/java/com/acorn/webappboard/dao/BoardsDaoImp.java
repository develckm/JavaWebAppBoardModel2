package com.acorn.webappboard.dao;

import com.acorn.webappboard.dto.BoardsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardsDaoImp implements BoardsDao{
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BoardsDaoImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<BoardsDto> findAll() throws Exception {
        List<BoardsDto> boards=null; //null 실패냐??
        String sql="SELECT * FROM boards";
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        boards=new ArrayList<>();
        while (rs.next()){
            BoardsDto board=parseBoardsDto(rs);
            boards.add(board);
        }
        return boards;
    }

    @Override
    public BoardsDto findByBId(int bId) throws Exception {
        BoardsDto board=null;
        //JPA (==Sequelize)
        String sql="SELECT * FROM boards WHERE b_id=?";
        //mybatis
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,bId);
        rs=pstmt.executeQuery();
        if(rs.next()){
            board=parseBoardsDto(rs);
        }
        return board;
    }

    @Override
    public int save(BoardsDto board) throws Exception {
        int save=0;
        String sql="INSERT INTO boards (u_id, title, content) values (?,?,?)";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,board.getUId());
        pstmt.setString(2,board.getTitle());
        pstmt.setString(3,board.getContent());
        save=pstmt.executeUpdate();
        return save;
    }

    @Override
    public int updateByPk(BoardsDto board) throws Exception {
        int updateByPk=0;
        String sql="UPDATE boards SET status=?,title=?,content=? WHERE b_id=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,board.getStatus());
        pstmt.setString(2,board.getTitle());
        pstmt.setString(3,board.getContent());
        pstmt.setInt(4,board.getBId());
        updateByPk=pstmt.executeUpdate();
        return updateByPk;
    }

    @Override
    public int deleteByPk(int bId) throws Exception {
        int deleteByPk=0;
        String sql="DELETE FROM boards WHERE b_id=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,bId);
        deleteByPk=pstmt.executeUpdate();
        //10시 10분까지 쉬었다가 오겠습니다~ 
        return deleteByPk;
    }

    public BoardsDto parseBoardsDto(ResultSet rs)throws Exception{
        BoardsDto board=new BoardsDto();
        board.setBId(rs.getInt("b_id"));
        board.setUId(rs.getString("u_id"));
        board.setPostTime(rs.getDate("post_time"));
        board.setUpdateTime(rs.getDate("update_time"));
        board.setStatus(rs.getString("status"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setViewCount(rs.getInt("view_count"));
        return board;
    }

}
