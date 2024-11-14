<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>register</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
<h1>register</h1>
<a href="login">login</a>
<form:form action="register" modelAttribute="user" method="post">
    <label>Email:</label>
    <form:input path="email" type="email"/><br>
    <form:errors path="email" cssClass="error"/><br>

    <label>First Name:</label>
    <form:input path="firstName"/><br>
    <form:errors path="firstName" cssClass="error"/><br>

    <label>Last Name:</label>
    <form:input path="lastName"/><br>
    <form:errors path="lastName" cssClass="error"/><br>

    <label>Birth Year:</label>
    <form:input path="birthYear" type="number" min="1900" max="2024" value="1988"/><br>
    <form:errors path="birthYear" cssClass="error"/><br>

    <label>Password:</label>
    <form:password path="password"/><br>
    <form:errors path="password" cssClass="error"/>
    <span class="error">${message}</span><br>

    <label>Confirm Password:</label>
    <form:password path="passwordConfirm"/><br>
    <form:errors path="passwordConfirm" cssClass="error"/><br>
    <form:errors path="passwordMatch" cssClass="error"/><br>
    <input type="submit" value="Submit"><br>
</form:form>
</body>
</html>