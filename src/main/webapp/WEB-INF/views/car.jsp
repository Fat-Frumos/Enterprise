<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tags/dataTag.tld" %>
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

    label {
        padding-top: 1px;
        padding-left: 5px;
        font-weight: 300;
        color: #c8c8c8;
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

<div class="col-lg-5 p-0 ps-lg-4">
    <form action="<c:url value="/order"/>" method="post">
        <div style="background: white" class="row m-0">
            <div class="col-12 px-0">
                <div class="row bg-light m-0">
                    <div class="col-12 px-4 mb-5">
                        <%--                        <jsp:include page="picker.jsp"/>--%>
                        <div class="d-flex mb-4">
                        <span class="me-5">
                            <br>
                            <span class="text-muted"><fmt:message key="span.card" bundle="${lang}"/></span>
                                <input type="text"
                                       style="width: 180px"
                                       class="form-control"
                                       value="4485 6888 2359 1498"
                                       placeholder="1234 5678 9012 3456">
                                    </span>
                            <div class="mt-4 w-100 d-flex flex-column align-items-end">

                                <span class="mt-1 text-muted">CVC</span>
                                <input class="form-control3" type="text" value="123" placeholder="XXX">
                            </div>
                        </div>


                        <div class="d-flex mb-4">
                        <span class="me-5">
                            <input id="carId" name="carId" value="${auto.id}" hidden>
                            <input id="userId" name="userId" value="${user.userId}" hidden>
                            <span class="text-muted"><fmt:message key="span.passport" bundle="${lang}"/></span>
                                    <input class="form-control"
                                           style="width: 180px"
                                           name="passport"
                                           type="text"
                                           value=""
                                           placeholder="AA 123 456 789"
                                           pattern="[a-zA-Z]{2}?[- ][0-9]{3}?[- ][0-9]{3}?[- ][0-9]{3}"
                                           required
                                    >
                                    </span>


                            <div class=" w-100 d-flex flex-column align-items-end">
                                <p class="text-muted"><fmt:message key="span.term" bundle="${lang}"/></p>
                                <input id="term"
                                       class="form-control2"
                                       style="width: 120px"
                                       type="date"
                                       name="term"
                                       pattern="yyyy-mm-dd"
                                       value="${order.term}" min=""
                                       max="2025-02-25">
                            </div>
                        </div>

                        <div class="d-flex mb-4">
                            <br>
                            <span class="me-5">
                                    <span class="text-muted"><fmt:message key="span.phone" bundle="${lang}"/></span>
                                    <input class="form-control"
                                           style="width: 180px"
                                           type="tel"
                                           name="phone"
                                           value=""
                                           pattern="\+\d{12}"
                                           placeholder="+380987654321"
                                           required
                                    >
                                    </span>
                            <div class=" w-100 d-flex flex-column align-items-end">
                                <span class="text-muted"><fmt:message key="span.driver" bundle="${lang}"/></span>
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

                        <div class="d-flex mb-4">
                        <span class="me-5">
                            <span class="text-muted"><fmt:message key="span.payment" bundle="${lang}"/></span>

<%--                        <fmt:message key="exchange.sign" bundle="${lang}"/>--%>
                            <label for="price"></label>
                            <input id="price"
                                   name="price"
                                   value="${auto.price}"
                                   style="width: 100px" hidden>

                            <label for="payment">$</label>
                            <input id="payment"
                                   class="form-control"
                                   style="width: 100px"
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
                            <div class="col-5 mb-4 p-0">
                                <input id="purchase"
                                       style="left: 220px"
                                       type="submit"
                                       name="submit"
                                       value="<fmt:message key="input.purchase" bundle="${lang}"/>"
                                       class="submit"
                                />
                                <div class="fa fa-check done"></div>
                                <div class="fa fa-close failed"></div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <br>
                </div>
            </div>
        </div>
    </form>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <script>
        // let exchangeFloat = parseFloat(document.getElementById("exchangeLabel").innerHTML).toFixed(2);
        // const pay = document.getElementById("payment");
        // pay.value *= exchangeFloat;
        let exchangeFloat = 1;
        $(document).ready(function () {
            $(".submit").click(function () {
                $(".submit").addClass("purchase");
                setTimeout(function () {
                    $(".submit").addClass("hide-loading");
                    // $(".done").addClass("finish");
                }, 1000);
                setTimeout(function () {
                    $(".submit").removeClass("purchase").removeClass("hide-loading");
                    // $(".done").removeClass("finish");
                    // $(".failed").removeClass("finish");
                }, 2000);
            })
        });

        let day = 1;
        let datePicker = document.getElementById('term');
        datePicker.min = datePicker.value = new Date().toISOString().substring(0, 10);
        let payment = document.getElementById('payment');
        let price = document.getElementById('price');
        let checkbox = document.getElementById('checkbox');

        checkbox.onchange = checkbox = (element) => {
            if (element.target.checked) {
                payment.value = (50 + parseFloat(price.value)) * day * exchangeFloat;
            } else {
                payment.value = (parseFloat(price.value)) * day * exchangeFloat;
            }
            console.log("with driver " + element.target.checked + ", payment: " + payment.value);
        }

        datePicker.onchange = datePicker = (el) => {
            let checkbox = document.getElementById('checkbox');
            let diff = new Date(el.target.value) - new Date();
            day = Math.ceil(Math.abs(diff) / (1000 * 60 * 60 * 24));
            if (checkbox.checked) {
                payment.value = (50 + parseFloat(price.value)) * day;
            } else {
                payment.value = (parseFloat(price.value)) * day;
            }
        }
    </script>
</div>
