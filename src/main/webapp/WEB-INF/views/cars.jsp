<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/9/2022
  Time: 9:28 PM
  Custom Tag  | Jspl Templates.
--%>
<%@ taglib uri="/WEB-INF/dataTag.tld" prefix="ct" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ taglib uri="/WEB-INF/lib/customTag.jar" prefix="ct" %>--%>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
      integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>
<title>Cars</title>
<style>
    <%@include file="../classes/templates/css/card.css"%>
    <%@include file="../classes/templates/css/car.css"%>

    .day {
        position: absolute;
        top: 5vh;
        left: 10vw;
    }
</style>
</head>

<body>
<div class="cars">
    <%--<jsp:include page="sorting.jsp"/>--%>
    <jsp:include page="card.jsp"/>
        <div class="day">
            <h5><ct:today format="MMMM dd yyyy"/></h5>
        </div>

</div>
</body>
</html>
