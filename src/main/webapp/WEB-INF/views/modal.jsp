<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/27/2022
  Time: 12:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <%--    <link rel="stylesheet" href="style.css">--%>
    <title>Drag And Drop Upload Image</title>
    <style>
        <%@include file="../classes/templates/css/modal.css"%>
    </style>
</head>
<body>
<!-- HTML5 Input Form Elements -->


<%--<input id="ajaxfile" type="file"/> <br/>--%>
<%--<button onclick="uploadFile()"> Upload</button>--%>

<div class="container">

    <div class="modal fade" id="Mymodal">
        <div class="modal-dialog wrap">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"></button>
                </div>
                <form class="popup" id="MyForm" action="/cart" method="post">
                    <label>Enter Name</label>
                    <input type="text" name="name" placeholder="Enter your name" pattern="[a-zA-Z0-9]+"
                           autocomplete="on"/><br>
                    <label>Enter Password</label>
                    <input type="password" name="password" placeholder="Enter your password" pattern="[a-zA-Z0-9]+"
                           autocomplete="on"/><br>
                    <input type="number" name="price" value="${auto.price}"/>
                    <input type="hidden" name="path" value=""/>
                    <input type="hidden" name="brand" value="Jeep"/><br>
                    <input type="hidden" name="model" value="Hammer"/><br>
                    <input type="hidden" name="year" value="2020"/><br>
                    <input type="submit" class="btn btn-default" name="upload" value="Upload"/>
                </form>
            </div>
        </div>
    </div>

    <div class="drop-area">
        <i class='bx bxs-cloud-upload icon'></i>
        <h3>Drag and drop or click here to select image</h3>
        <p>Image size mus be less than <span>2MB</span></p>
        <input type="file" accept="image/*" id="input-file" hidden>
    </div>
</div>

<script>
    const dropArea = document.querySelector('.drop-area');
    const inputFile = document.getElementById('input-file');

    dropArea.addEventListener('click', function () {
        inputFile.click()
    })

    inputFile.addEventListener('change', function () {
        const file = this.files[0];

        if (file.type.startsWith('image/')) {
            if (file.size < 2000000) {
                create_thumbnail(file);
            } else {
                alert('Image size must be less than 2MB');
            }
        } else {
            alert('Must be image');
        }
    })


    dropArea.addEventListener('dragover', function (e) {
        e.preventDefault();
        this.style.borderStyle = 'solid';

        const h3 = this.querySelector('h3');
        h3.textContent = 'Release here to upload image';
    })


    dropArea.addEventListener('drop', function (e) {
        e.preventDefault();

        inputFile.files = e.dataTransfer.files;
        const file = e.dataTransfer.files[0];

        if (file.type.startsWith('image/')) {
            if (file.size < 2000000) {
                create_thumbnail(file);
            } else {
                alert('Image size must be less than 2MB');
            }
        } else {
            alert('Must be image');
        }
    })


    const command = ['dragleave', 'dragend']

    command.forEach(item => {
        dropArea.addEventListener(item, function () {
            this.style.borderStyle = 'dashed';

            const h3 = this.querySelector('h3');
            h3.textContent = 'Drag and drop or click here to select image';
        })
    })


    function create_thumbnail(file) {
        const img = document.querySelectorAll('.thumbnail');
        const imgName = document.querySelectorAll('.img-name');
        img.forEach(item => item.remove());
        imgName.forEach(item => item.remove());

        const reader = new FileReader();
        reader.onload = () => {
            const url = reader.result;
            const img = document.createElement('img');
            img.src = url;
            img.className = 'thumbnail'
            const span = document.createElement('span');
            span.className = 'img-name';
            span.textContent = file.name;
            dropArea.appendChild(img);
            dropArea.appendChild(span);
            dropArea.style.borderColor = 'transparent';
        }
        reader.readAsDataURL(file);
    }

</script>

<!-- Ajax to Java File Upload Logic -->
<script>
    async function uploadFile() {
        let formData = new FormData();
        formData.append("file", ajaxfile.files[0]);
        await fetch('/car', {
            method: "PUT",
            body: formData
        });
        alert('The file upload with Ajax and Java was a success!');
    }
</script>
</body>
</html>