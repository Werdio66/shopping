<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
    <h3>购物车</h3>
<table border="1" width="60%">
    <tr>
        <a href="/commodity">去购物</a>
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
            <td>
                <a href="/shopping?pwd=delete&bookName=${shoppings.name}">删除</a>
                <a href="jsp/updateCount.jsp?bookName=${shoppings.name}&updateCount=${shoppings.count}">修改数量</a>
            </td>
        </tr>

    </c:forEach>
    <tr>
        <td>所有商品总价格</td>
        <td></td>
        <td></td>
        <td>${PRICE_IN_SESSION}</td>
        <td><a href="/shopping?pwd=removeAll">清空购物车</a> </td>
    </tr>
</table>
</body>
</html>
