<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file='/WEB-INF/views/css/login_page.css' %>
</style>
<head>
    <title>Poker</title>
</head>
<body>
<div class="formed">
    <div class="action">
        <h1>Poker</h1>
        <c:if test="${param.error != null}">
            <p class="invalid">Invalid username and password.</p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p class="access">You have been logged out.</p>
        </c:if>
        <c:if test="${param.registered != null}">
            <h3>You have successfully registered!</h3>
        </c:if>
        <h2>Enter your credentials</h2>
        <form action="<c:url value='/login' />" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="text" name="username" placeholder="login" autofocus/><br>
            <input type="password" name="password" placeholder="password"/><br>
            <button type="submit" name="btn">Log in</button>
        </form>
        <form action="/poker/register" method="GET">
            <button class="registration" type="submit">Registration</button>
        </form>
    </div>
</div>
</body>
</html>