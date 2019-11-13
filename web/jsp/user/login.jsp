<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <h3>请登录</h3>
    ${MSG}
    <form action="/login" method="post">
        账号：<input type="text" name="username"><br>
        密码：<input type="password" name="password"><br>
        <input type="submit" value="登录"><br>
    </form>
    <a href="jsp/user/management.jsp">返回管理页面</a>
</body>
</html>
