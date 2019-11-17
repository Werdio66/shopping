<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
</head>
<body>
    <h3>添加商品</h3>
    <form action="/commodity?pwd=save" method="post">
        商品名称：<input type="text" name="name"/>
        商品价格：<input type="text" name="price"/>
        商品分类：<select name="brandName">
            <option>电子产品</option>
            <option>书</option>
            <option selected>其他</option>
        </select>
        <input type="submit" value="保存"/>
    </form>
</body>
</html>
