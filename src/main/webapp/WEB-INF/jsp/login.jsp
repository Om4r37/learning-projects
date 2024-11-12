<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<h1>login</h1>
<a href="/register">register</a>
<form action = "login" method = "POST">
    user name: <input type = "text" name = "username"><br>
    password: <input type = "password" name = "password"><br>
    <input type = "submit" value = "Submit"><br>
    <span id="error" style="display: none;">wrong username or password.</span>
</form>
<script>
    if (${error}) document.getElementById("error").style.display = "inline";
</script>
</body>
</html>