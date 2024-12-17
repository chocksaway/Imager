<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">
    List Gallery page for ${name}

    <hr>
    <h1>Your Gallery:</h1>

    <table class="table">
        <thead>
            <tr>
            <tr>
                <th>id</th>
                <th>Description</th>
                <th>Target Date</th>
                <th>Is Done?</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${gallery}" var="gallery">
                <tr>
                    <td>${gallery.id}</td>
                    <td><a href="photo?id=${gallery.photoId}">${gallery.description}</a></td>
                    <td>${gallery.targetDate}</td>
                    <td>${gallery.done}</td>
                    <td><a href="delete-gallery?id=${gallery.id}" class="btn btn-warning">Delete</a></td>
                    <td><a href="update-gallery?id=${gallery.id}" class="btn btn-success">Update</a></td>
                </tr>

            </c:forEach>

        </tbody>
    </table>
    <a href="add-gallery" class="btn btn-success">Add Gallery</a>
</div>
<%@include file="common/footer.jspf"%>

</body>
</html>