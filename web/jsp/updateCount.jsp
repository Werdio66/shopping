<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改数量</title>
</head>
<body>
<h3>修改数量</h3>

    <form action="/shopping?pwd=update&bookName=<%=request.getParameter("bookName")%>" method="post">
        请输入数量：<input type="number" name="updateCount" value="<%=Integer.valueOf(request.getParameter("updateCount"))%>"/>
        <input type="submit" value="修改"/>
    </form>
</body>
</html>
