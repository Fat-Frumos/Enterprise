<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/15/2022
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="option">
    <form action="${pageContext.request.contextPath}/cars?page=${page}">
        <div class="priceInput">
            <div onclick="brand()">
                <label for="brand">Brand:</label>
                <select id="brand" name="brand">
                    <option value="" selected disabled hidden>Choose auto</option>
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
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" min="100" max="1500">

            <div class="buttons">
                <br>
                <a class="button" href="?page=${page-1}"><</a>
                <a class="button" href="?page=${page+1}">${page+1}</a>
                <a class="button" href="?page=${page+2}">${page+2}</a>
                <a class="button" href="?page=${page+3}">${page+3}</a>
                <a class="button" href="?page=${page+4}">${page+4}</a>
                <a class="button" href="?page=${page+1}">></a>
            </div>
            <br>
            <div class="btn-group sort-btn">
                <button id="direction" class="btn btn-secondary" data="none">Cost
                    <input name="direction" value="cost" data="none" class="fa fa-sort">
                </button>
                <button id="search" class="btn btn-secondary" data-sort="none">
                    <input name="sort" value="" class="fa fa-sort"><i class="fa fa-sort"></i>
                </button>
                <button id="submit" class="btn btn-primary" type="submit">
                    Search
                    <i class="fa fa-search"></i>
                </button>
            </div>
        </div>
    </form>
</div>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
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