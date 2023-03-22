package com.acorn.webappboard.filter;

import com.acorn.webappboard.dto.UsersDto;
import com.acorn.webappboard.lib.AESEncryption;
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
        Object isLogout=session.getAttribute("isLogout");
        Cookie loginId=null;
        Cookie loginPw=null;
        String path=req.getRequestURI();
        // localhost:3306/webAppBoard/users/login.do(url) =>/webAppBoard(?)/users/login.do (uri)
        System.out.println("AutoLoginFilter.doFilter uri:"+path);

        //로그인이 되어 있거나,login.do,logout.do 페이지에서 자동로그인 제외
        if(session.getAttribute("loginUser")!=null || path.endsWith("/login.do") || path.endsWith("/logout.do")){
            chain.doFilter(request,response);
            return;
        }

        Cookie [] cookies=req.getCookies(); //요청에 넘어온 모든 쿠키들
        for(Cookie c : cookies){
            if("LOGIN_ID".equals(c.getName())){
                loginId=c;
            }else if("LOGIN_PW".equals(c.getName())){
                loginPw=c;
            }
        }
        if(loginId!=null && loginPw!=null){
            String modalMsg="";
            String erroMsg=null;
            UsersDto loginUser=null;
            try {
                String uId= AESEncryption.decryptValue(loginId.getValue());
                String pw= AESEncryption.decryptValue(loginPw.getValue());
                loginUser=new UsersServiceImp().login(uId,pw);
                //8분까지 쉬었다가 오겠습니다.
            }catch (Exception e){
                e.printStackTrace();
                erroMsg=e.getMessage();
            }
            if (loginUser!=null){
                session.setAttribute("loginUser",loginUser);
                session.setAttribute("actionMsg","자동로그인 성공");
            }else{//db오류(다시시도), id와 pw가 바껴서 조회가 안될때(쿠키 삭제 및 로그인 페이지로 이동)
                if(erroMsg!=null){
                    session.setAttribute("actionMsg","자동로그인 오류 :"+erroMsg);
                }else{
                    session.setAttribute("actionMsg","자동로그인 실패 아이디와 비밀번호를 확인하세요.");
                    loginId.setMaxAge(0);
                    loginPw.setMaxAge(0);
                    resp.addCookie(loginId);
                    resp.addCookie(loginPw);
                    resp.sendRedirect(req.getContextPath()+"/users/login.do");
                    return;
                }
            }
        }
        chain.doFilter(request,response);
    }
}







