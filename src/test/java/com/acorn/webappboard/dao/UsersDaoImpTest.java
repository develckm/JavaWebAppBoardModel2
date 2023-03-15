package com.acorn.webappboard.dao;

import com.acorn.webappboard.WebAppBoardConn;
import com.acorn.webappboard.dto.UsersDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
//Junit 단위 테스트로 TDD 를 했어요!, Jest 단위 테스트로 TDD 를 했어요!!
class xUsersDaoImpTest {
    private static UsersDao usersDao;
    @BeforeAll //test를 시작하기 전에!!
    static void setConn()throws Exception{
        Connection conn=WebAppBoardConn.getConn();
        usersDao =new UsersDaoImp(conn);
    }

    @Test
    void findByUIdAndPw() throws Exception {
        UsersDto user=usersDao.findByUIdAndPw("user06","1234");
        System.out.println(user);
        Assertions.assertNotNull(user);
    }

    @Test
    void findByUId() throws Exception {
        UsersDto user=usersDao.findByUId("user06");
        System.out.println(user);
        Assertions.assertNotNull(user);
    }

    @Test
    void updateByPk() throws Exception {
        UsersDto user=new UsersDto();
        user.setUId("user06");
        user.setName("수정_이미라");
        user.setPw("4321");
        user.setPhone("88877770101");
        user.setImgPath("./img/users/user06.jpeg");
        user.setEmail("수정_user06@gamil.com");
        user.setGender("NONE");
        user.setAddress("경기도");
        user.setDetailAddress("이천");
        int update=usersDao.updateByPk(user);
        Assertions.assertEquals(update,1);
        System.out.println(update);
        UsersDto user06=usersDao.findByUId("user06");
        System.out.println(user06);
    }

    @Test
    void save() throws Exception {
        UsersDto user=new UsersDto();
        user.setUId("test99");
        user.setName("김재연");
        user.setPw("4321");
        user.setPhone("99977770101");
        user.setImgPath("./img/users/test99.jpeg");
        user.setEmail("test99@gamil.com");
        user.setGender("FEMALE");
        user.setAddress("서울시");
        user.setDetailAddress("압구정");
        user.setBirth("1999-12-25");
        int save=usersDao.save(user);
        Assertions.assertEquals(save,1);
        UsersDto test99=usersDao.findByUId("test99");
        System.out.println(test99);
    }

    @Test
    void updatePermissionByUIdAndPw() throws Exception {
        UsersDto user=new UsersDto();
        user.setUId("test99");
        user.setPw("4321");
        user.setPermission("PRIVATE");
        int update=usersDao.updatePermissionByUIdAndPw(user);
        Assertions.assertEquals(update,1);
    }

    @Test
    void deleteByUIdAndPw() throws Exception {
        int delete=usersDao.deleteByUIdAndPw("test99","4321");
        Assertions.assertEquals(delete,1);
    }
}