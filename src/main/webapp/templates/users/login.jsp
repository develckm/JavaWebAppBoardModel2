<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <% //java
        int i=0;
    %>
    <%-- pug -let i=0--%>
    <%-- h2=++i--%>
    <h1>로그인 폼 <%=++i%></h1>
    <form action="./login.do" method="post">
        <p>
            <label>
                u_id :
                <input type="text" name="u_id" value="user05">
            </label>
        </p>
        <p>
            <label>
                pw :
                <input type="password" name="pw" value="1234">
            </label>
        </p>
        <p>
            <button>로그인</button>
            <a href="./signup.do">회원가입</a>
        </p>
    </form>
    <%--   10분까지 쉬었다가 오세요~  --%>
</body>
</html>
