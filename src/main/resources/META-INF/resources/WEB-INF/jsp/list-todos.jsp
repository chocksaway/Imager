<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">
    List todos page for ${name}

    <hr>
    <h1>Your Todos are:</h1>

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
            <c:forEach items="${todos}" var="todo">
                <tr>
                    <td>${todo.id}</td>
                    <td><a href="photo?id=${todo.photoId}">${todo.description}</a></td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.done}</td>
                    <td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a></td>
                    <td><a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a></td>
                </tr>

            </c:forEach>

        </tbody>
    </table>
    <a href="add-todos" class="btn btn-success">Add Todo</a>
</div>
<%@include file="common/footer.jspf"%>

</body>
</html>