<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 11/12/2022
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--<title>Animated tags sphere</title>--%>
<span id="tags" class="content" hidden>
  <!-- <h1 contenteditable data-text="Grow">Grow</h1> -->
</span>

<script src="https://cdn.jsdelivr.net/npm/TagCloud@2.2.0/dist/TagCloud.min.js"> </script>

<script>
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

    let colors = ['#2C001e','#505550', '#005c00', '#5d38df', '#000', '#C20100'];
    let random_color = colors[Math.floor(Math.random() * colors.length)];
    document.querySelector('.content').style.color = random_color;
    // var h1 = document.querySelector("h1");
    // h1.addEventListener("input", function() {
    //   this.setAttribute("data-text", this.innerText);
    // });
</script>