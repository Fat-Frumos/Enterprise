<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/15/2022
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <style>
        <%@include file="../classes/templates/css/option.css"%>
        span > i {
            color: white;
        }

        span > input {
            background: none;
            color: white;
            border: 0;
            padding: 0;
        }

        #records {
            width: 10vw;
        }

        select {
            border-radius: 5px;
            width: 7.2vw;
        }

        body > div.option > form > div > div.btn-group.sort-btn > button > input {
            display: none;
        }
    </style>

    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css'>
</head>
<body>
<div class="option">
    <form action="/cars?recordsPerPage=${recordsPerPage}&page=${page}">
        <div class="priceInput">
            <label for="price">Price: </label>
            <input type="number" id="price" name="price" min="100" max="1000000">
            <br>
            <div onclick="brand()">
                <label for="brand">Brand:</label>
                <select id="brand" name="brand">
                    <option value="" hidden></option>
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
            <select behavior="listBox" class="form-control" id="records" name="recordsPerPage">
                <option value="0" selected></option>
                <option value="4">3</option>
                <option value="7">6</option>
                <option value="10">9</option>
                <option value="13">12</option>
            </select>
            <br>
            <%--            <c:if test="${page != 1}">--%>
            <a class="button" href="?recordsPerPage=${recordsPerPage}&page=${page-1}"><</a>
            <a class="button" href="?page=1">1</a>
            <a class="button" href="?page=2">2</a>
            <a class="button" href="?page=3">3</a>
            <a class="button" href="?recordsPerPage=${recordsPerPage}&page=${page+1}">></a>
            <br>
            <%--            </c:if>--%>
            <br>
            <div class="btn-group sort-btn">
                <button class="btn btn-primary" type="submit"><i class="fa fa-search"></i> Search</button>
                <button class="btn btn-secondary" data-sort="none">
                    <input name="sort" value="" class="fa fa-sort"><i class="fa fa-sort"></i></button>

                <button id ="direction" class="btn btn-secondary" data="none">Price
                <input name="direction" value="" data="none" class="fa fa-sort"></button>
            </div>
            <br>
            <br>
        </div>
    </form>
</div>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script>

    (function (fn) {
        'use strict';
        fn(window.jQuery, window, document);
    }(function ($) {
        'use strict';


        $(function () {
            $('.sort-btn').on('click','[data]', function (event) {
                event.preventDefault();

                let $this = $(this), sortDir = 'price';
                document.querySelector("#direction").innerHTML = "Price<input name=\"direction\" value=\"price\">";

                if ($this.data('direction') !== 'name') {
                    sortDir = 'name';
                    document.querySelector("#direction").innerHTML = "Name<input name=\"direction\" value=\"name\">";
                }

                $this.data('direction', sortDir).find('.fa').attr('value', '' + sortDir);

                // let btn = document.querySelector("#direction");
                // let input = document.querySelector("#dir");
                //
                // if (btn.value === "price") {
                //     btn.value = 'name';
                //     btn.innerText = 'Name';
                //     input.value = 'name';
                // } else {
                //     input.value = 'price';
                //     btn.value = 'price';
                //     btn.innerText = 'Price';
                // }

            });
        });

        $(function () {
            $('.sort-btn').on('click', '[data-sort]', function (event) {
                event.preventDefault();

                let $this = $(this), sortDir = 'asc';

                if ($this.data('sort') !== 'desc') {
                    sortDir = 'desc';
                }

                $this.data('sort', sortDir).find('.fa').attr('class', 'fa fa-sort-' + sortDir);
                $this.data('sort', sortDir).find('.fa').attr('value', '' + sortDir);
            });
        });
    }));
</script>
<script>

    function brand() {
        const $select = document.querySelector('#brand');
        let text = $select.options[$select.selectedIndex].value;
        const $options = Array.from($select.options);
        const optionToSelect = $options.find(item => item.text === text);
        optionToSelect.selected = true;

        console.log(": " + text);
    }
</script>
</body>
</html>
