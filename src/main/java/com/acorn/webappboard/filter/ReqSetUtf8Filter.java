package com.acorn.webappboard.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter("*")
public class ReqSetUtf8Filter implements Filter {

    //app.ase("*",(req,res,next)=>{}) :express 미들웨어 ( 특정 요청 전에 )
    //Filter doFilter(req,res,chain) : 특정 요청 전에 처리하는 미들웨어(필터)
    //쿠키 + 쿠키 자동로그인(쿠키 암호화) + 회원가입
    //수요일 + board crud => 제출 (시험 점수)=>spring
    //목요일 스프링~ (Mybatis,JPA(==sequelize))

    //2 : 40 - 3 : 20  2조
    //3 : 30 - 4 : 10  1조
    //4 : 20 - 4 : 45  2조
    //3 : 05 - 5 : 40  1조

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //System.out.println("ReqSetUtf8Filter.doFilter( * : 모든 페이지)");
        //url 파라미터의 인코딩을 utf-8로 변경
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response); //==미들웨어 next()
    }
}
