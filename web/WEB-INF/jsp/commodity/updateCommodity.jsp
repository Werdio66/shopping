<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改商品信息</title>
</head>
<body>
<h3>修改商品信息</h3>
    <form action="/commodity?pwd=update&bookName=<%=request.getParameter("name")%>" method="post">
        商品名称：<input type="text" name="name" value="<%=request.getParameter("name")%>"/>
        商品价格：<input type="text" name="price" value="<%=request.getParameter("price")%>"/>
        <input type="submit" value="修改"/>
    </form>
</body>
</html>
