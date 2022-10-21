<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <title>Cars</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    <style>
        <%@include file="../classes/templates/css/cart.css"%>
        <%@include file="../classes/templates/css/car.css"%>
        figure {
            overflow: hidden;
        }

        .modal-content:hover {
            cursor: pointer;
        }
        img {
            overflow: hidden;
        }
    </style>
    <link rel="shortcut icon" href="https://iconarchive.com/download/i18444/iconshock/global-warming/wheel.ico" type="image/x-icon">
</head>
<body>
<main class="m-3">
    <div>
        </form>
        <div class="cars">
            <div class="car" id="ads">
                <c:forEach items="${cars}" var="cars">
                    <ul class="col-md-4">
                        <li class="cart rounded">
                            <div class="modal fade" id="${cars.id}">
                                <div class="modal-dialog wrap popup">
                                    <div class="modal-content">
                                        <img onclick="toCard(${cars.id})" src="${cars.path}" alt="${cars.name}">
                                        <div class="modal-header">
<%--                                            <button type="button" class="add" data-dismiss="modal">V</button>--%>
<%--                                            <button type="button" class="close" data-dismiss="modal">x</button>--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="cart-image fw-900"
                                 onclick="showCar(`${cars.id}`, `${cars.path}`,`${cars.brand}`, `${cars.name}`, `${cars.price}`)">
                                <span class="cart-detail-badge m-2">${cars.brand} ${cars.name} ${cars.model}</span>
                                <figure>
                                    <img class="img-fluid"
                                         src="${cars.path}"
                                         alt="`${cars.id}`, `${cars.path}`,`${cars.brand}`, `${cars.name}`, `${cars.price}`">
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

    function toCard(id) {
        let url = '/cart' + '?id=' + id;
        fetch(url, {
            method: 'GET',
        }).then(response => {
            console.log('Ok:', response);
        }).catch(err => {
            console.error(err)
        })
        window.location.href = url;
    }

    function showCar(id, path, brand, name, price) {
        console.log(id + path  + brand +  name  +  price);
        $("#" + id).modal('show');
        // window.location.href = "/";
    }

</script>
</body>
</html>