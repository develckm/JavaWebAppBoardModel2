package com.acorn.webappboard.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

//import com.mysql.cj.jdbc.Driver; //mysql-connector-j
//클라이언트에서 요청이 오면 톰캣에서 web.xml 설정에 있는 동적파일(HttpServlet)을 실행해서 결과를 반환
@WebServlet("/model1/boardList.do") //컴파일시 web.xml 설정에 해당 동적리소스의 주소를 등록
public class Model1BoardsList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url="jdbc:mysql://localhost:3306/webAppBoard";
        String user="boardServerDev";
        String pw="mysql123";
        String driver="com.mysql.cj.jdbc.Driver";//jdbc 가 db에 접속할 때 필요한 driver
        resp.setContentType("text/html;charset=UTF-8");
        String html="<h1>model1+jdbc 게시글 리스트 !</h1>";
        html+="<p>model1 : Model,Controller,View 가 한페이지에 있는 것</p>";
        html+="<p>JDBC : 자바 어플(톰캣의 웹앱)에서 db 접속하는 것(모듈과 패키지 java.sql.*) </p>";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            Class.forName(driver); //동적로딩에 사용할 클래스 불러오기
            conn=DriverManager.getConnection(url,user,pw);
            String sql="SELECT * FROM boards";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            html+="<table>";
            html+="<tr><th>no</th><th>title</th><th>u_id</th><th>등록일</th><th>상세</th></tr>";
            while (rs.next()){
                   html+="<tr>";
                   html+="<td>"+rs.getInt("b_id")+"</td>";
                   html+="<td>"+rs.getString("title")+"</td>";
                   html+="<td>"+rs.getString("u_id")+"</td>";
                   html+="<td>"+rs.getString("post_time")+"</td>";
                   html+="<td><a href='./boardDetail.do?b_id="+rs.getInt("b_id")+"' >상세</a></td>";
                   html+="</tr>";
            }
            //12시 까지 쉬었다가 상세 페이지 만드세요!! (한명도 빠짐없이!!!)
        }catch (Exception e){
            e.printStackTrace();
        }
        resp.getWriter().println(html);
    }
}
