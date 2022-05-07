<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/4/26
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询图书</title>
</head>
<body>
<form action="BookServlet?method=findBookByCondition" method="post">
    <table align="center">
        <tr>
            <th>图书序列号：</th>
            <th><input type="booknum" name="booknum" placeholder="请输入序列号"></th>
        </tr>
        <tr>
            <th>书名：</th>
            <th><input type="bookname" name="bookname" placeholder="请输入书名"></th>
        </tr>
        <tr>
            <th>作者：</th>
            <th><input type="bookauthor" name="bookauthor" placeholder="请输入作者"></th>
        </tr>
        <tr>
            <th>出版社：</th>
            <th><input type="bookpublish" name="bookpublish" placeholder="请输入出版社"></th>
        </tr>
        <tr>
            <th>日期：</th>
            <th><input type="bookdate" name="bookdate" placeholder="请输入日期"></th>
        </tr>
        <tr>
            <th>价格：</th>
            <th><input type="bookprice" name="bookprice" placeholder="请输入价格"></th>
        </tr>
        <tr align="center">
            <th colspan="2">
                <input type="submit" value="查询">
            </th>
        </tr>
    </table>
</form>
</body>
</html>
