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
</style>
<form action="/cars" method="post">
    <div class="row">
        <div class="p-3">
            <figure>
                <button type="submit" value="${auto.id}" name="id">
                    <img class="img-fluid"
                         id="main"
                         src="${auto.path}"
                         alt="${auto.id}">
                </button>
            </figure>
        </div>
        <div class="col-md-4 col-3 ps-30 my-4">
            <p class="h4 m-0">
                <input class="pe-1" style="border: none" value="${auto.brand}" name="brand"></input>
                <input class="pe-1" style="border: none" value="${auto.name}" name="name"></input>
        </div>

        <div class="col-md-4 col-3 ps-30 my-4">
            <p class="h5 m-0">Model</p>
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
            <p class="h5 m-0">Self-Drive</p>
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
            <p class="h5 m-0">Cost</p>
            <div class="d-flex align-items-end mt-4 mb-2">
                <p class="fs-14 fw-bold">
                    <input style="width: 120px"
                           type="text"
                           class="fas fa-dollar-sign pe-1 form-control"
                           value="${auto.cost}"
                           name="cost"
                           readonly="readonly"
                    >
                </p>
            </div>
        </div>

        <div class="col-md-4 col-3 ps-30 my-4">
            <p class="h5 m-0">Price</p>
            <div class="d-flex align-items-end mt-4 mb-2">
                <p class="fs-14 fw-bold">
                    <input style="width: 120px"
                           type="text"
                           class="fas fa-dollar-sign pe-1 form-control"
                           value="${auto.price}"
                           name="price"
                           readonly="readonly"
                    >
                </p>
            </div>
        </div>

        <div class="col-md-4 col-3 ps-30 my-4">
            <p class="h5 m-0">Driver</p>
            <div class="d-flex align-items-end mt-3 mb-2">
                <p class="fs-14 fw-bold">
                    <input style="width: 120px"
                           type="text"
                           class="fas fa-dollar-sign pe-1 form-control"
                           value="50"
                           name="driver"
                           readonly="readonly"
                    >
                </p>
                </p>
            </div>
        </div>
    </div>
</form>
