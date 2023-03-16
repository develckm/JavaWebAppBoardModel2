package com.acorn.webappboard.service;

import com.acorn.webappboard.dto.BoardsDto;

import java.util.List;

public interface BoardService {
    public List<BoardsDto> list();//paging+search
    public BoardsDto detail(int bId);//상세보기+이미지리스트+조회수!+댓글리스트(대댓글+좋아요)+좋아요...
    public int modify(BoardsDto board); //이미지리스트 수정(등록,삭제), PRIVATE 으로 상태만 바꾸는 것..
    public int reguster(BoardsDto board); //이미지리스트 등록
    public int remove(int bId); //이미지리스트가 참조하는 서버에 저장된 이미지들 삭제

}
