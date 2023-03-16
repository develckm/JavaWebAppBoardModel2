package com.acorn.webappboard.controller;

import com.acorn.webappboard.dto.UsersDto;
import com.acorn.webappboard.service.UsersService;
import com.acorn.webappboard.service.UsersServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        UsersDto loginUser=null;
        try {
            UsersService usersService=new UsersServiceImp();
            loginUser=usersService.login(uId,pw);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //resp.getWriter().println(loginUser);
        //http Session : 인증같은 서비스를 위해 서버에 저장하는 객체 (쿠키로 클라이언트 아이디[JSESSIONID]와 만료시간(30분)을 지정)
        //요청이 들어온 클라이언트와 대응되는 session 이 있다면 요청정보에 담아준다.(없으면 새로 만들어 담아준다.)
        HttpSession session=req.getSession(); //session 의 타입은 Map 과 동일
        session.setAttribute("loginUser",loginUser);
        resp.sendRedirect(req.getContextPath()); // index.jsp 를 root 경로로 지정해 놓았다
    }
}
