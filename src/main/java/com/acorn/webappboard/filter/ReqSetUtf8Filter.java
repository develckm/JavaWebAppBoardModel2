package com.acorn.webappboard.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter("*")
public class ReqSetUtf8Filter implements Filter {

    //app.ase("*",(req,res,next)=>{}) :express 미들웨어 ( 특정 요청 전에 )
    //Filter doFilter(req,res,chain) : 특정 요청 전에 처리하는 미들웨어(필터)

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //System.out.println("ReqSetUtf8Filter.doFilter( * : 모든 페이지)");
        //url 파라미터의 인코딩을 utf-8로 변경
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response); //==미들웨어 next()
    }
}
