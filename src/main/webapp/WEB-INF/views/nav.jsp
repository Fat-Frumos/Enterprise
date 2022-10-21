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
    .bar {
        position: absolute;
        top: 3vh;
        left: 2.8vw;
        font-weight: 500;
        z-index: 2;
    }

    div.bar > a:hover {
        text-decoration: none;
        color: #59238F;
    }

    .cart-basket {
        font-size: .5rem;
        position: absolute;
        top: 2px;
        left: 136px;
        width: 12px;
        height: 12px;
        color: #fff;
        background-color: #f44336;
        border-radius: 50%;
        text-align: center;
    }
</style>
<div class="bar">
    <a href="/cars">Cars</a> |
    <a href="/user">User</a> |
    <a href="/cart">Basket
        <c:if test="${not empty car}">
            <span class="cart-basket">${car}</span>
        </c:if>
    </a> |
    <c:if test="${not empty user.name}">
        <a style="text-transform: capitalize" href="/login">${user.name} (${user.role})</a>
    </c:if>
    <c:if test="${empty user.name}">
        <a style="text-transform: capitalize" href="/login">Login</a>
    </c:if>


</div>