<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/28/2022
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    a {
        font-family: Raleway, Roboto, sans-serif;
        font-weight: 600;
    }
</style>
<div class="bar">
    <a href="/">Cars</a> •
    <a href="/user">Cabinet</a> •
    <c:if test="${not empty user.name}">
        <c:if test="${empty car}">
            <a href="/cars">Cards
                <span class="cart-basket">${car}</span>
            </a> •
        </c:if>
        <c:if test="${not empty car}">
            <a href="/cart">Cards
                <span class="cart-basket">${car}</span>
            </a> •
        </c:if>
    </c:if>

    <c:if test="${empty user.name}">
        <a href="/cars">Cards
            <span class="cart-basket">${car}</span>
        </a> •
    </c:if>

    <c:if test="${not empty user.name}">
        <a style="text-transform: capitalize" href="/login">${user.name}(${user.role})</a>
    </c:if>
    <c:if test="${empty user.name}">
        <a style="text-transform: capitalize" href="/login">Login</a>
    </c:if>
</div>