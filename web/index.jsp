<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/4/25
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
    <style>
      a{
        text-decoration: none;
      }
    </style>
  </head>
  <body>
  <h1><a href="BookInsert.jsp">添加图书</a></h1>
  <h1><a href="BookServlet?method=findAll&currPage=1">查询图书</a></h1>
  <h1><a href="findBook.jsp">条件查询</a></h1>
  </body>
</html>
