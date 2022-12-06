<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tags/dataTag.tld" prefix="ct" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<c:choose>
    <c:when test="${user.language=='ua'}">
        <fmt:setLocale value="ua" scope="session"/>
        <fmt:setBundle basename="com.enterprise.rental.utils.BungleUa" var="lang"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en" scope="session"/>
        <fmt:setBundle basename="com.enterprise.rental.utils.BungleEn" var="lang"/>
    </c:otherwise>
</c:choose>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/9/2022
  Time: 9:28 PM
  Custom Tag  | Jspl Templates.
--%>

<!DOCTYPE html>
<html lang="${user.language}">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Pasha">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv=Content-Type content="text/html; charset=windows-1251">
    <link rel="shortcut icon" href="<c:url value="/upload?wheel.ico"/>" type="image/x-icon">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

    <title><fmt:message key="title.cars" bundle="${lang}"/></title>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Raleway:100,600,400);


        <%@include file="../classes/templates/css/option.css"%>

        * {
            user-select: none;
            padding: 0;
            margin: 0;
            font-family: Raleway, Roboto, sans-serif;
            font-weight: 600;
        }
    </style>
</head>
<body>

<jsp:include page="option.jsp"/>
<jsp:include page="cart.jsp"/>
<jsp:include page="nav.jsp"/>

<%--<jsp:include page="burger.jsp"/>--%>
<%--<jsp:include page="flag.jsp"/>--%>

<script>

    $("input").on("keydown", function search(e) {
        if (e.keyCode == 13) {
            $('#submit').click();
        }
    });
    (function (fn) {
        'use strict';
        fn(window.jQuery, window, document);
    }(function ($) {
        'use strict';

        $(function () {
            $('.sort-btn').on('click', '[data]', function (event) {
                event.preventDefault();
                let direction = document.querySelector("#direction");
                let $this = $(this), param = 'cost';

                if ($this.data('direction') !== 'price') {
                    param = 'price';
                    direction.innerHTML = "Price<input name=\"direction\" value=\"price\">";
                } else {
                    direction.innerHTML = "Cost<input name=\"direction\" value=\"cost\">";
                }
                $this.data('direction', param).find('.fa').attr('value', '' + param);
            });
        });

        $(function () {
            $('.sort-btn').on('click', '[data-sort]', function (event) {
                event.preventDefault();
                let $this = $(this), dir = 'asc';
                if ($this.data('sort') !== 'desc') {
                    dir = 'desc';
                }

                $this.data('sort', dir).find('.fa').attr('class', 'fa fa-sort-' + dir);
                $this.data('sort', dir).find('.fa').attr('value', '' + dir);
            });
        });
    }));
</script>
</body>
</html>
