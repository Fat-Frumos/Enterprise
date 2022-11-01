<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pash
  Date: 8/28/2022
  Time: 7:47 PM
  To change this template use File | Settings | File Templates.
--%>
<div class="row">
    <div class="p-3">
        <figure>
            <img class="img-fluid"
                 id="main"
                 src="${auto.path}"
                 alt="${auto.id}">
            <figcaption>
            </figcaption>
        </figure>
    </div>
    <div class="col-md-4 col-3 ps-30 my-4">
        <p class="h4 m-0">
            <span class="pe-1">${auto.brand}</span>
            <span class="pe-1">${auto.name}</span>
    </div>

    <div class="col-md-4 col-3 ps-30 my-4">
        <p class="h5 m-0">Model</p>
        <div class="d-flex align-items-end mt-4 mb-2">
            <p class="fs-14 fw-bold">
                <span class="fas fa-dollar-sign pe-1">
                </span>${auto.model}</p>
        </div>
    </div>

    <div class="col-md-4 col-3 ps-30 my-4">
        <p class="h5 m-0">Self-Drive</p>
        <div class="d-flex align-items-end mt-4 mb-2">
            <p class="fs-14 fw-bold">
                <span class="fas fa-dollar-sign pe-1"></span>AI</p>
        </div>
    </div>

    <div class="col-md-4 col-3 ps-30 my-4">
        <p class="h5 m-0">Price</p>
        <div class="d-flex align-items-end mt-4 mb-2">
            <p class="fs-14 fw-bold">
                <span class="fas fa-dollar-sign pe-1"></span>$${auto.cost}
            </p>
        </div>
    </div>

    <div class="col-md-4 col-3 ps-30 my-4">
        <p class="h5 m-0">Cost</p>
        <div class="d-flex align-items-end mt-4 mb-2">
            <p class="fs-14 fw-bold">
                <span class="fas fa-dollar-sign pe-1"></span>$${auto.price}
            </p>
        </div>
    </div>

    <div class="col-md-4 col-3 ps-30 my-4">
        <p class="h5 m-0">Discount</p>
        <div class="d-flex align-items-end mt-3 mb-2">
            <p class="fs-14 fw-bold">
                <span class="fas fa-dollar-sign pe-1"></span>$0
            </p>
            </p>
        </div>
    </div>
</div>

