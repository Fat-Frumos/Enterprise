<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 11/20/2022
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<style>
    #language {
        height: 30px;
        width: 50px;
        overflow: hidden;
        position: absolute;
        left: 300px;
        top: 25px;
    }

    #language #en {
        position: absolute;
        background: url(http://icons.iconarchive.com/icons/icons-land/vista-flags/32/English-Language-Flag-1-icon.png) center center;
        height: 20px;
        width: 30px;
        left: 0;
    }

    #language #ua {
        position: absolute;
        background: url(https://cdn3.iconfinder.com/data/icons/finalflags/256/Ukraine-Flag.png) center center;
        height: 20px;
        width: 30px;
        top: -20px;
        left: 0;
    }

</style>

<label for="flag"></label>
<input id="flag" name="language" hidden>

<div id="lang-switch" onmouseenter="normalImg()" onmouseout="bigImg()">
    <div id='language' onclick="changeLang()">
        <a onclick="enLang()" id='en' href="/?lang=en" ></a>
        <a onclick="uaLang()" id='ua' href="/?lang=ua" ></a>
    </div>
</div>

<script>
    const language = document.getElementById("language");
    const flag = document.getElementById("flag");


    if ("${user.language}" === "ua") {
        uaLang();
    }

    function bigImg() {

    }

    function normalImg(x) {
        changeLang()
    }


    function changeLang() {
        language.style.overflow = language.style.overflow === "visible" ? "hidden" : "visible";
    }

    function uaLang() {
        flag.value = 'ua';
        document.getElementById("ua").style.top = '0';
        document.getElementById("en").style.top = '-20px';

    }

    function enLang() {
        flag.value = 'en';
        document.getElementById("en").style.top = '0';
        document.getElementById("ua").style.top = '-20px';
    }
</script>
