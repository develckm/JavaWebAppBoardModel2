package com.acorn.webappboard.filter;

import com.acorn.webappboard.dto.UsersDto;
import com.acorn.webappboard.service.UsersServiceImp;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("*")
public class AutoLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //클라이언트 요청에 LOGIN_ID, LOGIN_PW 쿠키가 포함되어 있으면 자동 로그인 하는 필터
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        HttpSession session=req.getSession();
        Cookie loginId=null;
        Cookie loginPw=null;
        Cookie [] cookies=req.getCookies(); //요청에 넘어온 모든 쿠키들
        for(Cookie c : cookies){
            if("LOGIN_ID".equals(c.getName())){
                loginId=c;
            }else if("LOGIN_PW".equals(c.getName())){
                loginPw=c;
            }
        }
        if(loginId!=null && loginPw!=null){
            try {
                UsersDto loginUser=new UsersServiceImp().login(loginId.getValue(),loginPw.getValue());
                session.setAttribute("loginUser",loginUser);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        chain.doFilter(request,response);
    }
}
