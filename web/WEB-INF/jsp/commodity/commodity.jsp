<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>商品表</title>
</head>
<body>

<script>
    function go() {
        document.forms[0].submit();
    }
</script>
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
        <c:forEach items="${pageResult.listDates}" var="commoditys">
            <tr>
                <td>${commoditys.name}</td>
                <td>${commoditys.price}</td>
                <td>${commoditys.brandName}</td>
                <td><a href="/shopping?pwd=save&bookName=${commoditys.name}">加入购物车</a></td>
            </tr>

        </c:forEach>
    <tr>
        <td colspan="4" align="center">
            <a href="/commodity?courentPage=1"> 首页 </a>
            <a href="/commodity?courentPage=${pageResult.prevPage}"> 上一页 </a>
            <a href="/commodity?courentPage=${pageResult.nextPage}"> 下一页 </a>
            <c:forEach begin="${pageResult.beginIndex}" end="${pageResult.endIndex}" var="pageNumber">
                <c:if test="${pageNumber != pageResult.courentPage}">
                    <a href="/commodity?courentPage=${pageNumber}">${pageNumber}</a>
                </c:if>
                <c:if test="${pageNumber == pageResult.courentPage}">
                    <span style="font-weight: bold">${pageNumber}</span>

                </c:if>
            </c:forEach>
            <a href="/commodity?courentPage=${pageResult.totalPage}"> 尾页 </a>
            当前第${pageResult.courentPage} /${pageResult.totalPage}页，一共${pageResult.totalCount}条数据

            跳转到 <input type="number" style="width: 45px" min="1" max="${pageResult.totalPage}"
                       value="${pageResult.courentPage}" name="courentPage">页
            <input type="button" value="GO" onclick="go();">

            每页<select name="pageSize" onchange="go();">
            <c:forEach items="${pageResult.items}" var="item">
                <option ${item == pageResult.pageSize ? "selected" : ""}>${item}</option>
            </c:forEach>
        </select>条
        </td>
    </tr>
    </table>
</form>
</body>
</html>
