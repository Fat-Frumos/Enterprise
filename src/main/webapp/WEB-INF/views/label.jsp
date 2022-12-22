<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pash
  Date: 8/28/2022
  Time: 7:47 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
    textarea:focus, input:focus {
        outline: none;
    }

    button {
        border: none;
        border-radius: 10px;
        padding: 0;
        margin: 0;
    }

    div > label {
        padding-top: 1px;
        padding-left: 2px;
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

<span id="exchangeLabel" class="cart-detail-badge" hidden>
    <fmt:message key="exchange" bundle="${lang}"/>
</span>

<section>
    <form action="${pageContext.request.contextPath}/cars" method="post">
        <div class="row">
            <div class="p-3">
                <figure>
                    <input type="submit" value="${auto.id}" name="id" hidden readonly="readonly">
                    <img class="img-fluid"
                         onclick="put(${auto.id})"
                         id="main"
                         src="${auto.path}"
                         alt="${auto.id}">
                    </input>
                </figure>
            </div>
            <div class="col-md-4 col-3 ps-30 my-4">
                <p class="h4 m-0">
                    <input class="pe-1" style="border: none; width: 180px" value="${auto.brand}" name="brand"
                           readonly="readonly">
                    <input class="pe-1" style="border: none; width: 180px" value="${auto.name}" name="name"
                           readonly="readonly">
            </div>

            <div class="col-md-4 col-3 ps-30 my-4">
                <p class="h5 m-0"><fmt:message key="p.model" bundle="${lang}"/></p>
                <div class="d-flex align-items-end mt-4 mb-2">
                    <p class="fs-14 fw-bold">
                        <input style="width: 120px"
                               type="text"
                               class="fas fa-dollar-sign pe-1 form-control"
                               value="${auto.model}"
                               name="model"
                               readonly="readonly"
                        >
                    </p>
                </div>
            </div>

            <div class="col-md-4 col-3 ps-30 my-4">
                <p class="h5 m-0"><fmt:message key="p.self" bundle="${lang}"/></p>
                <div class="d-flex align-items-end mt-4 mb-2">
                    <p class="fs-14 fw-bold">
                        <input style="width: 120px"
                               type="text"
                               class="fas fa-dollar-sign pe-1 form-control"
                               value="AI"
                               name="self"
                               readonly="readonly"
                        >
                    </p>
                </div>
            </div>

            <div class="col-md-4 col-3 ps-30 my-4">
                <p class="h5 m-0"><fmt:message key="p.cost" bundle="${lang}"/></p>
                <div class="d-flex align-items-end mt-4 mb-2">
                    <label for="cost">
                        <fmt:message key="exchange.sign" bundle="${lang}"/>
                    </label>
                    <input style="width: 120px"
                           id="cost"
                           type="text"
                           class="fas fa-dollar-sign pe-1 form-control"
                           value="${auto.cost}"
                           name="cost"
                           readonly="readonly"
                    >
                </div>
            </div>
            <input id="language"
                   name="language"
                   value="${user.language}"
                   hidden
            />
            <div class="col-md-4 col-3 ps-30 my-4">
                <p class="h5 m-0"><fmt:message key="p.price" bundle="${lang}"/></p>
                <div class="d-flex align-items-end mt-4 mb-2">
                    <label for="oldPrice">
                        <fmt:message key="exchange.sign" bundle="${lang}"/>
                    </label>
                    <input style="width: 120px"
                           id="oldPrice"
                           type="text"
                           class="fas fa-dollar-sign pe-1 form-control"
                           value="${auto.price}"
                           name="price"
                           readonly="readonly"
                    >
                </div>
            </div>

            <div class="col-md-4 col-3 ps-30 my-4">
                <p class="h5 m-0"><fmt:message key="p.driver" bundle="${lang}"/></p>
                <div class="d-flex align-items-end mt-4 mb-2">
                    <label for="driver">
                        <fmt:message key="exchange.sign" bundle="${lang}"/>
                    </label><input style="width: 120px"
                                   id="driver"
                                   type="text"
                                   class="fas fa-dollar-sign pe-1 form-control"
                                   value="50"
                                   name="driver"
                                   readonly="readonly"
                >
                </div>
            </div>
        </div>
    </form>
    <div class="drop-area" id="MyModal" hidden>
        <h4><fmt:message key="h4.upload" bundle="${lang}"/></h4>
        <form action="/upload" enctype="multipart/form-data" method="post">
            <input style="border: none" type="file" name="file2"/>
            <br>
            <input name="newBrand"
                   type="text"
                   style="border: none"
                   class="fas fa-dollar-sign pe-1 mt-2 form-control"
                   placeholder="Brand"
                   value="${auto.brand}"
            >
            <input name="newName"
                   type="text"
                   style="border: none"
                   class="fas fa-dollar-sign pe-1 mt-2 form-control"
                   placeholder="Name"
                   value="${auto.name}"
            >
            <input name="newModel"
                   type="text"
                   style="width: 120px"
                   class="fas fa-dollar-sign pe-1 mt-2 form-control"
                   placeholder="Model"
            <%--                   value="${auto.model}"--%>
            >
            <input name="newPrice"
                   type="number"
                   style="width: 120px"
                   class="fas fa-dollar-sign pe-1 mt-2 form-control"
                   placeholder="price"
                   value="${auto.price}"
            >
            <input placeholder="cost"
                   name="newCost"
                   type="number"
                   style="width: 120px"
                   class="fas fa-dollar-sign pe-1 mt-2 form-control"
                   value="${auto.cost}"
            >
            <input style="border: none; width: 100px" type="submit"
                   value="<fmt:message key="input.upload" bundle="${lang}"/>"/>
        </form>
    </div>

    <%--    <div style="position: absolute; top: 10vh; left: -17vw;">--%>
    <%--        <span id="tags" class="content"></span>--%>
    <%--    </div>--%>
</section>

<script>

     const ex = document.getElementById("exchangeLabel");
     let expf = parseFloat(ex.innerHTML).toFixed(2);

    const cost = document.getElementById("cost");
    const pp = document.getElementById("oldPrice");
    const driver = document.getElementById("driver");
    cost.value *= expf;
    pp.value *= expf;
    driver.value *= expf;

    console.log(pp.value);
    console.log(cost.value);
    console.log(driver.value);
    console.log(expf);

    for (let i = 0; i < pp.length; i++) {
        pp[i].value = "" + (pp[i].value * expf).toFixed(0);
        cost[i].value = "" + (cost[i].value * expf).toFixed(0)
        // console.log(pp[i].innerHTML);
        // console.log(cost[i].innerHTML);
    }

    if ("${user.role}" === "admin") {

        let tags = document.getElementById("tags");

        tags.hidden = false;

        tags.addEventListener("click", (event) => {
            shows(event.target);
        })

        let main = document.getElementById("main");
        main.addEventListener('contextmenu', (event) => {
                event.preventDefault()

                if (confirm("Do you want to remove the car?")) {
                    let url = '/cars' + '?id=' + ${auto.id};
                    console.log(url);

                    fetch(url, {
                        method: 'DELETE',
                    }).then(response => {
                        console.log('Ok:', response);

                    }).catch(err => {
                        console.error(err)
                    })
                    window.location.href = url;
                    // } else {
                    //     window.history.back();
                }
            }
        )
        // document.querySelector("body > div.container > div > div.col-lg-7.pe-lg-5 > section > form > div > div:nth-child(2) > p > input:nth-child(1)")
        let brand = document.querySelector("body > div.container > div > div.col-lg-7.pe-lg-5 > section > form > div > div:nth-child(2) > p > input:nth-child(1)")
        let model = document.querySelector("body > div.container > div > div.col-lg-7.pe-lg-5 > section > form > div > div:nth-child(3) > div > p > input")
        brand.readOnly = false;
        model.readOnly = false;
        document.querySelector("#oldPrice").readOnly = false;
        document.querySelector("#cost").readOnly = false;

    }

    function shows(tags) {
        let modal = document.getElementById("MyModal");
        console.log(tags);
        modal.hidden = !modal.hidden;
        // modal.hidden = modal.hidden ? false : true;
    }

    function put(id) {
        // let url = '/order' + '?id=' + id;
        let url = '/cart' + '?id=' + id;
        fetch(url, {
            method: 'put',
        }).then(response => {
            console.log('Ok:', response);
            window.location.href = url;
        }).catch(err => {
            console.error(err)
        })
    }
</script>
