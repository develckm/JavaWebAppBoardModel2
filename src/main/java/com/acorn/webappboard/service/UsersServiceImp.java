package com.acorn.webappboard.service;

import com.acorn.webappboard.WebAppBoardConn;
import com.acorn.webappboard.dao.UsersDao;
import com.acorn.webappboard.dao.UsersDaoImp;
import com.acorn.webappboard.dto.UsersDto;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersServiceImp implements UsersService{
    private Connection conn;
    private UsersDao usersDao;

    //트랜잭션 : 친구한테 계좌 송금(서비스) ~ 내계좌업데이트 4000->3000,은행기록등록 (실패),친구계좌 업데이트 (dao)

    public UsersServiceImp(UsersDao usersDao) throws Exception {
        conn= WebAppBoardConn.getConn();
        this.usersDao = new UsersDaoImp(conn);
    }

    @Override
    public UsersDto login(String uId, String pw) throws Exception {
        return usersDao.findByUIdAndPw(uId,pw);
    }

    @Override
    public UsersDto detail(String uId) throws Exception {
        return usersDao.findByUId(uId);
    }

    @Override
    public UsersDto idCheck(String uId) throws Exception {
        return usersDao.findByUId(uId);
    }

    @Override
    public int modify(UsersDto user) throws Exception {
        int modify=0;
        modify=usersDao.updateByPk(user);
        return modify;
    }//11분까지 쉬었다가 올께요~
    //서비스 개념의 이해~

    @Override
    public int register(UsersDto user) throws Exception {

        return usersDao.save(user);
    }

    @Override
    public int closeAccount(UsersDto user) throws Exception {
        return 0;
    }

    @Override
    public int dropOut(String uId, String pw) throws Exception {
        return 0;
    }
}
