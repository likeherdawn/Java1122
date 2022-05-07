<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/4/26
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书展示</title>
    <style>
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
<table border="1px" cellspacing="0" width="60%" align="center">
    <tr>
        <th>ID</th>
        <th>图书编号</th>
        <th>书名</th>
        <th>作者</th>
        <th>出版社</th>
        <th>日期</th>
        <th>价格</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.fa}" var="b">
        <tr>
            <td align="center">${b.id}</td>
            <td align="center">${b.booknum}</td>
            <td align="center">${b.bookname}</td>
            <td align="center">${b.bookauthor}</td>
            <td align="center">${b.bookpublish}</td>
            <td align="center">${b.bookdate}</td>
            <td align="center">${b.bookprice}</td>
            <td align="center">
                <a href="BookServlet?method=preupdate&id=${b.id}">更改</a>
                <a href="javascript:deleteBook(${b.id})">删除</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="8">
<%--            首页 第一页--%>
    <a href="BookServlet?method=findAll&currPage=1">首页&nbsp;&nbsp;</a>
<%--    上一页--%>
    <c:if test="${requestScope.page.currentPage!=1}">
        <a href="BookServlet?method=findAll&currPage=${requestScope.page.currentPage - 1}">上一页&nbsp;&nbsp;</a>
    </c:if>
<%--    页码--%>
    <c:forEach begin="1" end="${requestScope.page.totalPage}" var="n">
        <c:choose>
            <c:when test="${requestScope.page.currentPage == n}">
                <a style="color: red" href="BookServlet?method=findAll&currPage=${n}">${n}</a>
            </c:when>
            <c:otherwise>
                <a href="BookServlet?method=findAll&currPage=${n}">${n}</a>
            </c:otherwise>
        </c:choose>

    </c:forEach>
<%--    下一页--%>
    <c:if test="${requestScope.page.currentPage != requestScope.page.totalPage}">
        <a href="BookServlet?method=findAll&currPage=${requestScope.page.currentPage + 1}">下一页&nbsp;&nbsp;</a>
    </c:if>
<%--            尾页 最后一页--%>
    <a href="BookServlet?method=findAll&currPage=${requestScope.page.totalPage}">尾页&nbsp;&nbsp;</a>
        </td>
    </tr>
    <tr>
        <th colspan="8" align="center">
            <a href="index.jsp"><input type="button" value="返回首页"></a>
        </th>
    </tr>
</table>
</body>
<script>
    function deleteBook(id){
        var flag = confirm("确定删除吗");
        if (flag){
            location = "BookServlet?method=del&id=" + id;
        }
    }
</script>
</html>
