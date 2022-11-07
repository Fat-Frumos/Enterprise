<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/25/2022
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>date</title>
    <style>
        <%@include file="../classes/templates/css/picker.css"%>
    </style>
</head>
<body>
<div id="datepicker-container">
    <ul class="datepicker large" onwheel="monthDatepicker.onscroll(event, this)">
        <li class="date">September</li>
        <li class="date">October</li>
        <li class="date active">November</li>
        <li class="date">December</li>
        <li class="date">January</li>
        <li class="date hidden">February</li>
        <li class="date hidden">March</li>
        <li class="date hidden">April</li>
        <li class="date hidden">May</li>
        <li class="date hidden">June</li>
        <li class="date hidden">July</li>
        <li class="date hidden">August</li>
    </ul>

    <ul class="datepicker small" onwheel="dayDatepicker.onscroll(event, this)">
        <li class="date">12</li>
        <li class="date">13</li>
        <li class="date active">14</li>
        <li class="date">15</li>
        <li class="date">16</li>
    </ul>

    <ul class="datepicker medium" onwheel="yearDatepicker.onscroll(event, this)">
        <li class="date">2020</li>
        <li class="date">2021</li>
        <li class="date active">2022</li>
        <li class="date">2024</li>
        <li class="date">2025</li>
    </ul>
</div>
<script>
    function DatePicker(spec) {
        let
            currentOption = spec.current,
            itemIndex;

        function generateOptions(data, item) {
            let i, state, options = [];

            function computeState(currentIndex, itemIndex) {
                if (currentIndex === itemIndex) {
                    return 'active'
                }

                if (itemIndex >= 0 && Math.abs(itemIndex - currentIndex) < Math.ceil(spec.visible / 2)) {
                    return ''
                }

                return null;
            }

            for (i = 0; i < data.length; i += 1) {
                itemIndex = data.indexOf(item);
                state = computeState(i, data.indexOf(item));

                if (state !== null) {
                    options.push({
                        month: data[i],
                        state: computeState(i, itemIndex)
                    });
                }
            }

            return options;
        }

        function generateDom(options) {
            let i, html = '';

            for (i = 0; i < options.length; i += 1) {
                html += '<li class="date ' +
                    options[i].state + '">' +
                    options[i].month +
                    '</li>';
            }

            return html;
        }

        this.onscroll = function (event, elem) {
            let
                options,
                currentOptionIndex,
                emptyOption = {
                    month: '--',
                    state: 'hidden'
                };

            currentOptionIndex = spec.data.indexOf(currentOption) || 0;
            currentOptionIndex =
                event.wheelDeltaY < 0 ?
                    currentOptionIndex + 1 :
                    currentOptionIndex - 1;

            if (currentOptionIndex === -1) {
                currentOptionIndex = 0;
                return;
            }

            if (currentOptionIndex === spec.data.length) {
                currentOptionIndex = spec.data.length - 1;
                return;
            }

            currentOption = spec.data[currentOptionIndex];
            options = generateOptions(spec.data, currentOption);

            if (currentOptionIndex === 1 && options.length < spec.visible - 1) {
                options = [emptyOption].concat(options);
            }

            if (currentOptionIndex === 0 && options.length < spec.visible - 1) {
                options = [emptyOption, emptyOption].concat(options);
            }

            elem.innerHTML = generateDom(options);
        };
    }

    let monthDatepicker = new DatePicker({
        data: [
            'January', 'February', 'March', 'April', 'May', 'June', 'July',
            'August', 'September', 'October', 'November', 'December'
        ],
        current: 'March',
        visible: 6
    });

    let dayDatepicker = new DatePicker({
        data: Array.apply(null, {
            length: 31
        }).map(Number.call, function (n) {
            return (n || 0) + 1
        }),
        current: 14,
        visible: 6
    });

    let yearDatepicker = new DatePicker({
        data: Array.apply(null, {length: 11}).map(Number.call, function (n) {
            return (n || 0) + 2022
        }),
        current: 2022,
        visible: 6
    });
</script>
</body>
</html>
