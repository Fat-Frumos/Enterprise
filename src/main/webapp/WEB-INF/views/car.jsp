<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/dataTag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/24/2022
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<style>

    <%@include file="../classes/templates/css/form.css"%>
    <%@include file="../classes/templates/css/check-box.css"%>
    <%@include file="../classes/templates/css/rental.css"%>

</style>
<div class="col-lg-5 p-0 ps-lg-4">
    <form action="/order" method="post">
        <div style="background: white" class="row m-0">
            <div class="col-12 px-0">
                <div class="row bg-light m-0">
                    <div class="col-12 px-4 mb-5">
                        <div class="d-flex mb-4">
                        <span class="me-5">
                            <br>
                                    <span class="text-muted">Passport</span>
                                    <input class="form-control"
                                           style="width: 180px"
                                           name="passport" type="text"
                                           value=""
                                           placeholder="AA 123 456 789"
                                           required
                                    >
                                    </span>
                            <div class=" w-100 d-flex flex-column align-items-end">
                                <br>
                                <p class="text-muted">Term</p>
                                <input id="e"
                                       class="form-control2"
                                       style="width: 110px"
                                       type="date"
                                       name="term"
                                       value="" min=""
                                       max="2025-02-25">
                            </div>
                        </div>

                        <div class="d-flex mb-4">
                        <span class="me-5">
                                    <span class="text-muted">Phone</span>
                                    <input class="form-control"
                                           style="width: 180px"
                                           type="tel"
                                           name="phone"
                                           value=""
                                           pattern="+[0-9]{2}[0-9]{3}-[0-9]{3}-[0-9]{4}"
                                           placeholder="+__(___)___-__-__"
                                           required
                                    >
                                    </span>
                            <div class=" w-100 d-flex flex-column align-items-end">
                                <span class="text-muted">Driver</span>
                                <div class="w-100 d-flex flex-column align-items-end">
                                    <div class="toggle">
                                        <input id="checkbox" type="checkbox" name="driver">
                                        <label class="onButton" value=""></label>
                                    </div>
                                </div>
                                <span class="me-5">
                                </span>
                            </div>
                        </div>
                        <jsp:include page="picker.jsp"/>
                        <div class="d-flex mb-4">
                        <span class="me-5">
                                    <span class="text-muted">Payment</span>
                                                    <td><fmt:formatDate pattern="yyyy-MM-dd"
                                                                        value="${order.created}"/></td>


                                    <input class="form-control"
                                           style="width: 100px"
                                           id="payment"
                                           name="payment"
                                           type="number"
                                           min="0"
                                           pattern="0.00"
                                           step=".01"
                                           readonly="readonly"
                                           value="${auto.price}"/>

                                    </span>
                            <div class=" w-100 d-flex flex-column align-items-end">
                                <p class="text-muted"></p>
                            </div>
                        </div>
                        <div class="d-flex mb-4">
                            <span class="me-5">
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row m-0">
                    <div class="col-12 mb-4 p-0">
                        <input type="submit" id="purchase" name="submit" value="Purchase" class="submit">
                        <div class="fa fa-check done"></div>
                        <div class="fa fa-close failed"></div>
                    </div>
                </div>
                <br>
                <br>
            </div>
        </div>
    </form>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>

        $(document).ready(function () {
            $(".submit").click(function () {
                $(".submit").addClass("loading");
                setTimeout(function () {
                    $(".submit").addClass("hide-loading");
                    $(".done").addClass("finish");
                }, 1000);
                setTimeout(function () {
                    $(".submit").removeClass("loading").removeClass("hide-loading");
                    $(".done").removeClass("finish");
                    $(".failed").removeClass("finish");
                }, 2000);
            })
        });

        let payment = document.getElementById('payment');
        let checkbox = document.getElementById('checkbox');
        let datePicker = document.getElementById('e');
        let day = 1;
        datePicker.min = datePicker.value = new Date().toISOString().substring(0, 10);

        checkbox.onchange = checkbox = (element) => {
            console.log("with driver " + element.target.checked);
            if (element.target.checked) {
                payment.value = 50 * day + parseFloat(payment.value);
            } else {
                payment.value -= 50 * day;
            }
        }

        datePicker.onchange = datePicker = (e) => {
            let diff = new Date(e.target.value) - new Date();
            day = Math.ceil(Math.abs(diff) / (1000 * 60 * 60 * 24));
            payment.value *= day;
        }

    </script>
</div>
