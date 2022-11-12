<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/25/2022
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>

<link rel="shortcut icon" href="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/wheel.ico"
      type="image/x-icon">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
      integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

<style>
    <%@include file="../classes/templates/css/cart.css"%>
    <%@include file="../classes/templates/css/car.css"%>
</style>

<main class="">
    <div>
        </form>
        <div class="cars">
            <div class="car" id="ads">
                <c:forEach items="${cars}" var="cars">
                    <ul class="col-md-4" style="padding: 5px">
                        <li class="cart rounded">
                            <div class="modal fade" id="${cars.id}">
                                <div class="modal-dialog wrap popup">
                                    <div class="modal-content">
                                        <img onclick="popUp(${cars.id})" src="${cars.path}" alt="${cars.name}">
                                    </div>
                                </div>
                            </div>
                            <div class="cart-image fw-900"
                                 onclick="showCar(`${cars.id}`, `${cars.path}`,`${cars.brand}`, `${cars.name}`, `${cars.price}`)">
                                <span class="cart-detail-badge m-2">${cars.brand} ${cars.name} ${cars.model}</span>
                                <figure>
                                    <img class="img-fluid"
                                         src="${cars.path}"
                                         alt="${cars.id}">
                                    <figcaption></figcaption>
                                </figure>
                            </div>
                            <div class="cart-image-overlay m-auto">
                                <span class="badge">Rent:</span>
                                <span class="cart-detail-badge">$${cars.price}</span>
                                <span class="badge">| Price:</span>
                                <span class="cart-detail-badge">$${cars.cost}</span>
                            </div>
                        </li>
                    </ul>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
<script>

    window.addEventListener('contextmenu', (event) => {
        event.preventDefault()
        window.history.back();
    })

    function popUp(id) {
        $("#" + id).modal('show');
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
        // document.location.reload(true);
    }


    function showCar(id, path, brand, name, price) {
        console.log(id);
        console.log(brand);
        console.log(name);
        console.log(price);
        $("#" + id).modal('show');
        // window.location.href = "/";
    }

</script>
