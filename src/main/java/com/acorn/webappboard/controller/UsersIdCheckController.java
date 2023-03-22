package com.acorn.webappboard.controller;

import com.acorn.webappboard.dto.UsersDto;
import com.acorn.webappboard.service.UsersService;
import com.acorn.webappboard.service.UsersServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/idCheck.do")
public class UsersIdCheckController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ajax 로 요청하는 페이지 특징 에러 페이지 없이 http status 만 반환
        String uId=req.getParameter("u_id");
        if(uId==null){
            //resp.sendError(400,"잘못된 요청입니다."); //에러상태+에러페이지 반환
            resp.sendError(400);
            return;
        }
        UsersDto user=null;
        try {
            UsersService usersService=new UsersServiceImp();
            user=usersService.detail(uId);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(500);
            return;
        }
        //u_id 를 사용하는 user가 있으면 {"idCheck":true}
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().println("{\"idCheck\":"+(user!=null)+"}");

    }
}
