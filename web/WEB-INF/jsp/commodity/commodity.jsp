<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>商品表</title>
</head>
<body>
<h3>按条件查询</h3>
<form action="/commodity" method="post">
    名称：<input type="text" name="name" value="${commodityQuery.name}">
    价格：<input type="text" name="minPrice" value="${commodityQuery.minPrice}" placeholder="最低价"> -
    <input type="text" name="maxPrice" value="${commodityQuery.maxPrice}" placeholder="最高价">
    <select name="brandName">
                <option selected>全部</option>
                <option ${commodityQuery.brandName == "电子产品" ? "selected" : ""}>电子产品</option>
                <option ${commodityQuery.brandName == "书" ? "selected" : ""}>书</option>
                <option ${commodityQuery.brandName == "其他" ? "selected" : ""}>其他</option>
            </select>
    关键字：<input type="text" name="keyword" value="${commodityQuery.keyword}" placeholder="商品名称或者商品类型">
    <input type="submit" value="查询" style="background-color: cadetblue">
</form>
<h3>商品列表</h3>
<table border="1" width="60%">
        <tr>
            <a href="/shopping">查看购物车    </a>
            <a href="/jsp/user/management.jsp">   返回管理页面</a>
        </tr>
        <tr>
            <td>名称</td>
            <td>单价</td>
            <td>分类</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${COMMODITY_IN_SESSION}" var="commoditys">
            <tr>
                <td>${commoditys.name}</td>
                <td>${commoditys.price}</td>
                <td>${commoditys.brandName}</td>
                <td><a href="/shopping?pwd=save&bookName=${commoditys.name}">加入购物车</a></td>
            </tr>

        </c:forEach>
    </table>
</body>
</html>
