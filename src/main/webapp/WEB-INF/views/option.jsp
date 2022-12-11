<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/15/2022
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="option">

    <c:choose>
        <c:when test="${user.language=='ua'}">
            <fmt:setLocale value="ua" scope="session"/>
            <fmt:setBundle basename="com.enterprise.rental.utils.locale.BungleUa" var="lang"/>
        </c:when>
        <c:otherwise>
            <fmt:setLocale value="en" scope="session"/>
            <fmt:setBundle basename="com.enterprise.rental.utils.locale.BungleEn" var="lang"/>
        </c:otherwise>
    </c:choose>

    <form action="${pageContext.request.contextPath}/?page=${page}">
        <div class="priceInput">
            <div onclick="brand()">
                <label for="brand"><fmt:message key="label.brand" bundle="${lang}"/>:</label>
                <select style="width: 180px" id="brand" name="brand">
                    <option value="" selected disabled hidden>
                        <fmt:message key="option.choose" bundle="${lang}"/>
                    </option>
                    <option value="BMW">BMW</option>
                    <option value="Bugatti">Bugatti</option>
                    <option value="Ford">Ford</option>
                    <option value="Ferrari">Ferrari</option>
                    <option value="Koenigsegg">Koenigsegg</option>
                    <option value="Lamborghini">Lamborghini</option>
                    <option value="Maserati">Maserati</option>
                    <option value="Porsche">Porsche</option>
                    <option value="Xpeng">Xpeng</option>
                </select>
            </div>
            <label for="price"><fmt:message key="label.price" bundle="${lang}"/>:</label>
            <input style="width: 195px" type="number" id="price" name="price" min="100" max="1500">
            <ul class="pagination mt-2">
                <c:if test="${page != 1}">
                    <li class="page-item">
                        <a class="page-link" onclick="send(${page-1})"><</a>
                    </li>
                </c:if>
                <c:forEach begin="${begin}" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${page eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" onclick="send(${i})">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${page lt noOfPages}">
                    <li class="page-item">
                        <a class="page-link" onclick="send(${page+1})">></a>
                    </li>
                </c:if>
            </ul>
            <div class="btn-group sort-btn mt-2">
                <button id="direction" class="btn btn-secondary" data="none">
                    <fmt:message key="button.cost" bundle="${lang}"/>
                    <input name="direction" value="cost" data="none" class="fa fa-sort">
                </button>
                <button id="search" class="btn btn-secondary" data-sort="none">
                    <input id="sort" name="sort" value="" class="fa fa-sort"><i class="fa fa-sort"></i>
                </button>
                <button id="submit" class="btn btn-primary" type="submit">
                    <fmt:message key="button.search" bundle="${lang}"/>
                    <i class="fa fa-search"></i>
                </button>
            </div>
        </div>
    </form>
</div>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script>

    function send(page) {
        let brand = document.getElementById("brand").value;
        let price = document.getElementById("price").value;
        let direction = document.getElementById("direction").value;
        let sort = document.getElementById("sort").value;
        window.location.href = "?page=" + page + "&direction=" + direction + "&sort=" + sort + "&price=" + price + "&brand=" + brand;
    }

    $("input").on("keydown", function search(e) {
        if (e.keyCode === 13) {
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
                    direction.innerHTML = "<fmt:message key="button.price" bundle="${lang}"/><input name=\"direction\" value=\"price\">";
                } else {
                    direction.innerHTML = "<fmt:message key="button.cost" bundle="${lang}"/><input name=\"direction\" value=\"cost\">";
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