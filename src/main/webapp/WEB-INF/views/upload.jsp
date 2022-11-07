<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 11/7/2022
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>HTML5 drag'n'drop file upload with Servlet</title>
    <script>
        window.onload = function () {
            let dropbox = document.getElementById("dropbox");
            dropbox.addEventListener("dragenter", noop, false);
            dropbox.addEventListener("dragexit", noop, false);
            dropbox.addEventListener("dragover", noop, false);
            dropbox.addEventListener("drop", dropUpload, false);
        }

        function noop(event) {
            event.stopPropagation();
            event.preventDefault();
        }

        function dropUpload(event) {
            noop(event);
            let files = event.dataTransfer.files;

            for (let i = 0; i < files.length; i++) {
                upload(files[i]);
            }
        }

        function upload(file) {
            document.getElementById("status").innerHTML = "Uploading " + file.name;

            let formData = new FormData();
            formData.append("file", file);

            let xhr = new XMLHttpRequest();
            xhr.upload.addEventListener("progress", uploadProgress, false);
            xhr.addEventListener("load", uploadComplete, false);
            xhr.open("GET", "upload", true); // If async=false, then you'll miss progress bar support.
            xhr.send(formData);
        }

        function uploadProgress(event) {
            // Note: doesn't work with async=false.
            let progress = Math.round(event.loaded / event.total * 100);
            document.getElementById("status").innerHTML = "Progress " + progress + "%";
        }

        function uploadComplete(event) {
            document.getElementById("status").innerHTML = event.target.responseText;
        }

        // function dropUpload(event) {
        //     event.stopPropagation();
        //     event.preventDefault();
        //
        //     var formData = new FormData();
        //     formData.append("file", event.dataTransfer.files[0]);
        //
        //     var xhr = new XMLHttpRequest();
        //     xhr.open("PUT", "cars");
        //     xhr.send(formData);
        // }
    </script>
    <style>
        #dropbox {
            width: 300px;
            height: 200px;
            border: 1px solid gray;
            border-radius: 5px;
            padding: 5px;
            color: gray;
        }
    </style>
</head>
<body>
<div id="dropbox">Drag and drop a file here...</div>
<div id="status"></div>
</body>
</html>
