<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>

<table border="1" width="60%">
    <tr>
        <a href="/jsp/commodity.jsp">继续购物</a>
    </tr>
    <tr>
        <td>名称</td>
        <td>单价</td>
        <td>数量</td>
        <td>总价</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${SHOPPING_IN_SESSION}" var="shoppings">
        <tr>
            <td>${shoppings.name}</td>
            <td>${shoppings.price}</td>
            <td>${shoppings.count}</td>
            <td>${shoppings.totalPrice}</td>
            <td><a href="/settlement?name=${shoppings.name}">删除</a></td>
        </tr>

    </c:forEach>
    <tr>
<%--        <td>总价 : ${TOTAL_IN_SESSION}</td>--%>
    </tr>
</table>
</body>
</html>
