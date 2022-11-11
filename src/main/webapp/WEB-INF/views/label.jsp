<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pash
  Date: 8/28/2022
  Time: 7:47 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
    <%@include file="../classes/templates/css/tag.css"%>
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
                <input class="pe-1" style="border: none" value="${auto.brand}" name="brand" readonly="readonly"></input>
                <input class="pe-1" style="border: none" value="${auto.name}" name="name" readonly="readonly"></input>
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
            <div class="d-flex align-items-end mt-4 mb-2">
                <p class="fs-14 fw-bold">
                    <input style="width: 120px"
                           type="text"
                           class="fas fa-dollar-sign pe-1 form-control"
                           value="50"
                           name="driver"
                           readonly="readonly"
                    >
                </p>
            </div>
        </div>
    </div>
</form>
<div class="drop-area" id="MyModal" hidden>
    <form method="post" action="/upload" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit" value="Upload"/>
    </form>
</div>

<div style="position: absolute; top: 10vh; left: -17vw;">
    <span id="tags" class="content" hidden></span>
</div>
<script src="https://cdn.jsdelivr.net/npm/TagCloud@2.2.0/dist/TagCloud.min.js"></script>
<script>
    if ("${user.role}" === "admin") {

        let main = document.getElementById("main");
        let tags = document.getElementById("tags");

        tags.hidden = false;

        tags.addEventListener("click", (event) => {
            shows(event.target);
        })

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

        let brand = document.querySelector("body > div.container > div.row.m-0 > div.col-lg-7.pb-5.pe-lg-5 > form > div > div:nth-child(2) > p > input:nth-child(1)")
        let model = document.querySelector("body > div.container > div.row.m-0 > div.col-lg-7.pb-5.pe-lg-5 > form > div > div:nth-child(2) > p > input:nth-child(2)")
        brand.readOnly = false;
        model.readOnly = false;


        for (let i = 3; i < 8; i++) {
            let query = "body > div.container > div.row.m-0 > div.col-lg-7.pb-5.pe-lg-5 > form > div > div:nth-child(" + i + ") > div > p > input";
            let input = document.querySelector(query);
            input.readOnly = false;
        }
    }

    function shows(tags) {
        

        let modal = document.getElementById("MyModal");
        console.log(modal);
        modal.hidden = modal.hidden ? false : true;
    }

    function put(id) {
        let url = '/cart' + '?id=' + id;
        // let url = '/cars' + '?id=' + id;
        console.log(url);
        fetch(url, {
            method: 'PUT',
        }).then(response => {
            console.log('Ok:', response);
            // window.location.href = url;
        }).catch(err => {
            console.error(err)
        })
    }

    const myTags = [
        'Bugatti', 'BMW', 'Ford',
        'Ferrari', 'Koenigsegg', 'Xpeng',
        'Lamborghini', 'Maserati', 'Porsche'
    ];

    const tagCloud = TagCloud('.content', myTags, {
        radius: 150,
        maxSpeed: 'fast',
        initSpeed: 'fast',
        direction: 0,
        keep: true

    });

    //To change the color of text randomly
    let colors = ['#505550', '#005c00', '#5d38df', '#000', '#C20100'];
    let random_color = colors[Math.floor(Math.random() * colors.length)];
    document.querySelector('.content').style.color = random_color;

</script>
