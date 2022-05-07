<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/4/26
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新</title>
</head>
<body>
<form action="BookServlet?method=update" method="post">
    <input type="hidden" name="id" value="${requestScope.book.id}">
    <table border="1px" cellspacing="0" width="60%" align="center">
        <caption>用户更新</caption>
        <tr>
            <th>图书序列号：</th>
            <th><input type="booknum" name="booknum" value="${requestScope.book.booknum}"></th>
        </tr>
        <tr>
            <th>书名：</th>
            <th><input type="bookname" name="bookname" value="${requestScope.book.bookname}"></th>
        </tr>
        <tr>
            <th>作者：</th>
            <th><input type="bookauthor" name="bookauthor" value="${requestScope.book.bookauthor}"></th>
        </tr>
        <tr>
            <th>出版社：</th>
            <th><input type="bookpublish" name="bookpublish" value="${requestScope.book.bookpublish}"></th>
        </tr>
        <tr>
            <th>日期：</th>
            <th><input type="bookdate" name="bookdate" value="${requestScope.book.bookdate}"></th>
        </tr>
        <tr>
            <th>价格：</th>
            <th><input type="bookprice" name="bookprice" value="${requestScope.book.bookprice}"></th>
        </tr>
        <tr>
            <td align="center" colspan="2">
                <input type="submit" value="更新">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
