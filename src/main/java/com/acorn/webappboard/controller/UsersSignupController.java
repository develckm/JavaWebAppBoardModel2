package com.acorn.webappboard.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/users/signup.do")
@MultipartConfig// multipart/form-data 에서 blob으로 오는 파라미터를 처리
public class UsersSignupController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/users/signup.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //8분까지
        String path=("/Users/choegyeongmin/webAppStudy20230117/JavaWebAppBoardModel2/src/main/webapp/public/img/users");
        System.out.println("path = " + path);
        String uId=req.getParameter("u_id");
        String name=req.getParameter("name");
        Part profileImg=req.getPart("img_path");
        profileImg.write(path+"/"+uId+".jpeg");
        System.out.println("name = " + name);
        System.out.println("uId = " + uId);
        System.out.println("profileImg.getSubmittedFileName() = " + profileImg.getSubmittedFileName());
    }
}
