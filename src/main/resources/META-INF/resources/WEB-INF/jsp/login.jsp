<%@page contentType="text/html; ISO-8859-1" language="java" %>

<html>
<body>
Login page

<form method="post">
    <pre>${errorMessage}</pre>
    Name: <input type="text" name="name">
    Password: <input type="password" name="password">
    <input type="submit">

</form>
</body>
</html>