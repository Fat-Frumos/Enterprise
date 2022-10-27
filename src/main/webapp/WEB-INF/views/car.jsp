<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/dataTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/24/2022
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
    <%@include file="../classes/templates/css/users.css"%>
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
                                    <input class="form-control" style="width: 180px"
                                           name="passport" type="text"
                                           value="AA 123 456 789"
                                           placeholder="AA 123 456 789">
                                    </span>
                            <div class=" w-100 d-flex flex-column align-items-end">
                                <br>
                                <p class="text-muted">Term</p>
                                <input class="form-control2" style="width: 120px"
                                       name="term"
                                       type="date"
                                       value="24/02/2022"
                                       placeholder="MM/YYYY">
                            </div>
                        </div>

                        <div class="d-flex mb-4">
                        <span class="me-5">
                                    <span class="text-muted">Card</span>
                                    <input class="form-control" style="width: 180px"
                                           name="card" type="text"
                                           value="4485 6888 2359 1498"
                                           placeholder="1234 5678 9012 3456">
                                    </span>
                            <div class=" w-100 d-flex flex-column align-items-end">
                                <p class="text-muted">Expires</p>
                                <input class="form-control2" style="width: 80px"
                                       name="expires"
                                       type="text" value="22/02/2022"
                                       placeholder="MM/dd">
                            </div>
                        </div>
                        <jsp:include page="picker.jsp"/>
                        <div class="d-flex mb-4">
                        <span class="me-5">
                                    <span class="text-muted">Payment</span>
                                    <input class="form-control" style="width: 100px"
                                           name="card" type="number"
                                           value="${auto.price}
                                           placeholder="${auto.price}">
                                    </span>
                            <div class=" w-100 d-flex flex-column align-items-end">
                                <p class="text-muted"></p>
                            </div>
                        </div>
                        <%--                        <div class="d-flex mb-5">--%>
                        <%--                                    <span class="me-5">--%>
                        <%--                                        <span class="me-5">--%>
                        <%--                                        <span class="text-muted">Payment</span>--%>
                        <%--                                        <input class="form-control" style="width: 180px"--%>
                        <%--                                               name="payment" type="number"--%>
                        <%--                                               value="${auto.price}" placeholder="100"--%>
                        <%--                                               autocomplete="on">--%>
                        <%--                                    </span>--%>
                        <%--                                        --%>

                        <%--                        </div>--%>
                        <div class="d-flex mb-4">
                            <span class="me-5">
                                <span class="text-muted">Driver</span>
                            <div class="w-100 d-flex flex-column align-items-end">
                                <input name="driver" type="checkbox">
                                <label class="onButton"></label>
                            </div>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row m-0">
                    <div class="col-12 mb-4 p-0">
                        <input type="submit" id="purchase" name="submit" value="Purchase" class="btn btn-primary">
                        <span class="fas fa-arrow-right ps-2"></span>
                    </div>
                </div>
                <br>
                <br>
            </div>
        </div>
    </form>
</div>
