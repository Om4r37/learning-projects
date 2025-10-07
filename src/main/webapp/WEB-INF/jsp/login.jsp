<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>login</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2.1.1/css/pico.classless.min.css"/>
</head>
<body>
  <header>
  <nav>
    <ul>
      <li><a href="register">register</a></li>
    </ul>
  </nav>
</header>
<main>
<h1>login</h1>
<form action = "login" method = "POST">
  email: <input type = "email" name = "email"><br>
  password: <input type = "password" name = "password"><br>
  <input type = "submit" value = "Submit"><br>
  <span id="error" style="display: none;">invalid email or password.</span>
</form>
<script>
  if (${error}) document.getElementById("error").style.display = "inline";
</script>
</main>
</body>
</html>