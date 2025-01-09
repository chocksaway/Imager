<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<h1>View Video</h1>

<video width="320" height="240" controls>
    <source src="data:video/mp4;base64,${video}" type="video/mp4">
    Your browser does not support the video tag.
</video>

<%@include file="common/footer.jspf"%>
</body>
</html>