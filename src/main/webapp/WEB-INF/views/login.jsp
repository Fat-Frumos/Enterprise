<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/24/2022
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>My First JSP</title>
</head>
<body>

<p><span style="color: red;">${errorMessage}</span></p>
<form action="/login" method="POST">
    Name : <label>
    <input name="name" type="text"/>
</label> Password : <label>
    <input name="password" type="password"/>
</label> <input type="submit"/>
</form>
</body>
</html>
