<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>商品表</title>
</head>
<body>
    <h3>商品列表</h3>
    <table border="1" width="60%">
        <tr>
<%--            <a href="/jsp/commodity.jsp">继续购物</a>--%>
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
                <td><a href="/shopping?pwd=save&bookName=${commoditys.name}">购买</a></td>
            </tr>

        </c:forEach>
        <tr>
            <a href="/shopping">查看购物车</a>
        </tr>
    </table>
</body>
</html>
