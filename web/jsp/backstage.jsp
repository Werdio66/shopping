<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>后台管理</title>
</head>
    <body>
        <h3>商品信息</h3>
        <table border="1" width="60%">
            <tr>
                <a href="/jsp/saveCommodity.jsp">添加商品    </a>
                <a href="/jsp/management.jsp">   返回管理页面</a>
            </tr>
            <tr>
                <td>名称</td>
                <td>单价</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${COMMODITY_IN_SESSION}" var="commoditys">
                <tr>
                    <td>${commoditys.name}</td>
                    <td>${commoditys.price}</td>
                    <td><a href="/commodity?pwd=delete&bookName=${commoditys.name}">删除商品</a>
                    <a href="/jsp/updateCommodity.jsp?pwd=update&name=${commoditys.name}&price=${commoditys.price}">修改商品</a></td>
                </tr>

            </c:forEach>

         </table>
    </body>
</html>
