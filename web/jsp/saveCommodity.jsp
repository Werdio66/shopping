<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
</head>
<body>
    <form action="/commodity?pwd=save" method="post">
        商品名称：<input type="text" name="name"/>
        商品价格：<input type="text" name="price"/>
        <input type="submit" value="保存"/>
    </form>
</body>
</html>
