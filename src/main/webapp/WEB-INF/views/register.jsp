<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
    <%@include file='/WEB-INF/views/css/register.css' %>
</style>
<head>
    <title>Registration</title>
</head>
<body>
<div class="back">
    <container class="main">
        <c:if test="${param.invalid_email != null}">
            <h3>Invalid email or others credentials. Please, try again</h3>
        </c:if>
        <h2 class="login">Enter your login</h2>
        <form action="/poker/register" method="POST">
            <input type="text" name="login">
            <h2 class="password">Enter your password</h2>
            <input type="password" name="password">
            <h2 class="name">Enter your name</h2>
            <input type="text" name="name">
            <h2 class="lastname">Enter your last name</h2>
            <input type="text" name="lastname">
            <h2 class="email">Enter your Email</h2>
            <input type="text" name="email">
            <button type="submit">Registration</button>
        </form>
    </container>
</div>
</body>
</html>
