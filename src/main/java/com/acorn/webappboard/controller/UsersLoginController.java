package com.acorn.webappboard.controller;

import com.acorn.webappboard.dto.UsersDto;
import com.acorn.webappboard.service.UsersService;
import com.acorn.webappboard.service.UsersServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/users/login.do")
public class UsersLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //인터프린트(js,python,ruby) <-> 컴파일(java,c,c++.....)
        //jsp 퍼그 처럼 html을 렌더링하는 템플릿 엔진 (문법은 자바인데 인터프린트언어로 취급)
        //html : xhtml (html4)
        //<button>  <-> <button/>
        req.getRequestDispatcher("/templates/users/login.jsp").forward(req,resp);
        //express fug 는 라우터가 뷰를 렌더링하고 끝 (대세!!)
        //톰캣 jsp 는 뷰를 렌더링하면서 요청과 응답을 jsp 페이지에서 마무리한다. (옛날 방식!! 보안에 취약)

        //jsp 는 인터린트 언어로 정적리소스 페이지와 동일한 위치에 존재한다.
        //클라이언트는 정적리소스 페이지 위치에 파일을 업로드하거나 다운로드 할수 있다.(보안에 취약)
        //jsp는 요청과 응답을 처리할 수 있는 동적페이지로 서버에 대한 주요 정보가 존재할 수 있다.
        //해커 jsp 페이지를 서버에 업로드하고 호출하면서 실행!! (db에 접속해서 중요한 정보를 취득 가능)
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uId=req.getParameter("u_id");
        String pw=req.getParameter("pw");
        String autoLogin=req.getParameter("auto_login");
        String modalMsg="";
        String errorMsg=null;
        UsersDto loginUser=null;
        try {
            UsersService usersService=new UsersServiceImp();
            loginUser=usersService.login(uId,pw);
        } catch (Exception e) {
            e.getMessage();
            errorMsg=e.getMessage();
        }
        HttpSession session=req.getSession();
        if(loginUser!=null){
            if(autoLogin!=null && autoLogin.equals("1")){
                //쿠키로 자동 로그인 구현 (login.do,loginout.do 를 제외한 모든 요청에서 id와pw 쿠키가 있으면 자동으로 로그인 시도)
                //LOGIN_ID,LOGIN_PW
                Cookie loginId=new Cookie("LOGIN_ID",loginUser.getUId());
                Cookie loginPw=new Cookie("LOGIN_PW",loginUser.getPw());
                //쿠키 만료시간과 쿠키가 유요한 url을 지정(쿠키를 만든 url default)
                loginId.setPath(req.getContextPath());
                loginPw.setPath(req.getContextPath());
                loginId.setMaxAge(7*24*60*60);
                loginPw.setMaxAge(7*24*60*60);
                resp.addCookie(loginId);
                resp.addCookie(loginPw);
                modalMsg="자동 ";
            }
            modalMsg+="로그인 성공";
            session.setAttribute("actionMsg",modalMsg);
            session.setAttribute("loginUser",loginUser);
            resp.sendRedirect(req.getContextPath());
        }else{ //로그인 실패 : db오류, pw나 id 가 잘못된것
            if(errorMsg!=null) {
                modalMsg="db 오류 다시 시도 : "+errorMsg;
            }else{
                modalMsg="아이디나 비밀번호를 확인하세요!";
            }
            session.setAttribute("actionMsg",modalMsg);
            resp.sendRedirect(req.getContextPath()+"/users/login.do");
        }
    }
}
