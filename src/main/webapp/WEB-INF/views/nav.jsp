<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/28/2022
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="../classes/templates/css/nav.css"%>

    a {
        font-family: Raleway, Roboto, sans-serif;
        font-weight: 600;
    }

</style>
<c:choose>
    <c:when test="${user.language=='ua'}">
        <fmt:setLocale value="ua" scope="session"/>
        <fmt:setBundle basename="com.enterprise.rental.service.locale.BungleUa" var="lang"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en" scope="session"/>
        <fmt:setBundle basename="com.enterprise.rental.service.locale.BungleEn" var="lang"/>
    </c:otherwise>
</c:choose>

<div class="navigation">
    <ul>
        <li class="list">
            <a href="${pageContext.request.contextPath}/">
                <span id="facebook" class="icon"><ion-icon name="car-sport-outline"></ion-icon></span>
                <span class="text">
                    <fmt:message key="a.cars" bundle="${lang}"/>
                </span>
            </a>
        </li>

        <li class="list">
            <c:choose>
                <c:when test="${empty user}">
                    <a href="${pageContext.request.contextPath}/login">
                <span class="icon">
                    <ion-icon name="document-text-outline"></ion-icon>
                </span>
                        <span class="text">
                    <fmt:message key="a.cabinet" bundle="${lang}"/>
                </span>
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="/user">
                <span class="icon">
                    <ion-icon name="document-text-outline"></ion-icon>
                </span>
                        <span class="text">
                    <fmt:message key="a.cabinet" bundle="${lang}"/>
                </span>
                    </a>
                </c:otherwise>
            </c:choose>
            <c:if test="${empty user}">
            </c:if>
        </li>


        <c:if test="${empty car}">
            <li class="list">
                <a href="${pageContext.request.contextPath}/cars">
                    <span class="icon"><ion-icon name="cart-outline"></ion-icon></span>
                    <span class="text"><fmt:message key="a.cards" bundle="${lang}"/>
                    </span>
                </a>
            </li>
        </c:if>
        <c:if test="${not empty user.name}">
            <c:if test="${not empty car}">
                <li class="list">
                    <a href="${pageContext.request.contextPath}/cart">
                        <span class="icon">
                            <ion-icon name="cart-outline"></ion-icon>
                        </span>
                        <span class="text">
                                <fmt:message key="a.cards" bundle="${lang}"/>
                            </span>
                        <span class="cart-basket">${car}</span>
                    </a>
                </li>
            </c:if>
        </c:if>

        <li class="list active">
            <a style="text-transform: capitalize" href="${pageContext.request.contextPath}/login">
                <span class="icon"><ion-icon name="person-outline"></ion-icon></span>
                <span class="text">
            <c:if test="${not empty user.name}">
                ${user.name}(${user.role})</a>
            </c:if>
            <c:if test="${empty user.name}">Login
            </c:if>
            </span>
            </a>
        </li>
    </ul>
</div>

<script>
    let list = document.querySelectorAll('.list');
    const cabinet = document.getElementById('cabinet');

    function activeLink() {
        list.forEach((item) =>
            item.classList.remove('active'));
        this.classList.add('active');
    }

    list.forEach((item) =>
        item.addEventListener('click', activeLink))
</script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
