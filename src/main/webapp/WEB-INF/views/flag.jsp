<%@ page import="com.enterprise.rental.entity.User" %>
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

<style>
    #language {
        height: 30px;
        width: 50px;

        overflow: hidden;
        position: absolute;
        left: 280px;
        top: 50px;
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
        top: 30px;
        left: 0;
    }

</style>

<%--<c:choose>--%>
<%--    <c:when test="${user.language=='ua'}">--%>
<%--        <fmt:setLocale value="ua" scope="session"/>--%>
<%--        <fmt:setBundle basename="com.enterprise.rental.utils.BungleUa" var="lang"/>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>
<%--        <fmt:setLocale value="en" scope="session"/>--%>
<%--        <fmt:setBundle basename="com.enterprise.rental.utils.BungleEn" var="lang"/>--%>
<%--    </c:otherwise>--%>
<%--</c:choose>--%>

<div id="lang-switch">
    <div id='language' onclick="changeLang()">
        <a onclick="enLang()" id='en' href="#"></a>
        <a onclick="uaLang()" id='ua' href="#"></a>
    </div>
</div>

<script>
    const language = document.getElementById("language");
    const flag = document.getElementById("flag");

<%--        let add_element = () => {--%>

<%--&lt;%&ndash;            &lt;%&ndash;%>--%>
<%--&lt;%&ndash;            User user = (User) request.getAttribute("user");&ndash;%&gt;--%>
<%--&lt;%&ndash;            if (user != null) {&ndash;%&gt;--%>
<%--&lt;%&ndash;            user.setLanguage("en");&ndash;%&gt;--%>
<%--&lt;%&ndash;            request.setAttribute("user", user);&ndash;%&gt;--%>
<%--&lt;%&ndash;            }&ndash;%&gt;--%>
<%--&lt;%&ndash;             %>&ndash;%&gt;--%>

<%--        const template = document.createElement('div');--%>
<%--        template.innerHTML = "${user.language}";--%>

<%--        document.body.appendChild(template);--%>
<%--    }--%>


    function changeLang() {
        language.style.overflow = language.style.overflow === "visible" ? "hidden" : "visible";
    };

    function uaLang() {
        // add_element();
        flag.value = 'ua';
<%--        <fmt:setBundle basename="com.enterprise.rental.utils.BungleUa" var="lang"/>--%>
        document.getElementById("ua").style.top = '0';
        document.getElementById("en").style.top = '30px';
    };

    function enLang() {
        flag.value = 'en';

        document.getElementById("en").style.top = '0';
        document.getElementById("ua").style.top = '30px';
    };
</script>