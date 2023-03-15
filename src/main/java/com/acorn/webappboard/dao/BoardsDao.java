package com.acorn.webappboard.dao;

import com.acorn.webappboard.dto.BoardsDto;

import java.util.List;

public interface BoardsDao {
    List<BoardsDto> findAll() throws Exception;

    //List<BoardsDto> findBySearchAngPaging(PageVo) throws Exception;
    BoardsDto findByBId(int bId) throws Exception;
    int save(BoardsDto board) throws Exception;
    int updateByPk(BoardsDto board) throws Exception;
    int deleteByPk(int bId) throws Exception;
    //BoardsDaoImp + BoardsDaoImpTest 구현하세요~
}
