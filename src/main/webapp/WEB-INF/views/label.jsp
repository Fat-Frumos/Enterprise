<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pash
  Date: 8/28/2022
  Time: 7:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%--https://raw.githubusercontent.com/Fat-Frumos/Cars/master/main.jpg--%>
<div class="row">
    <div class="p-3">
        <figure>
            <img class="img-fluid"
                 id="main"
                 src="${auto.path}"
                 alt="main">
            <figcaption>
            </figcaption>
        </figure>
    </div>
    <div class="col-md-4 col-3 ps-30 my-4">
        <p class="text-muted">Mileage</p>
        <p class="h5">25000<span class="ps-1">Km</span></p>
    </div>
    <div class="col-md-4 col-3 ps-30 my-4">
        <p class="text-muted">Autopilot</p>
        <p class="h5 m-0">AI</p>
    </div>
    <div class="col-md-4 col-3 ps-30 my-4">
        <p class="text-muted">Color</p>
        <p class="h5 m-0">Black</p>
    </div>
</div>
