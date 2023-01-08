<%@ page import="com.enterprise.rental.dao.DBTest" %><%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 12/27/2022
  Time: 11:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>DB Test</title>
</head>
<body>

<%
    DBTest tst = new DBTest();
    tst.init();
%>

<h2>Results</h2>
Foo <%= tst.getFoo() %><br/>
Bar <%= tst.getBar() %>

</body>
</html>