<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <h1>${isRegister ? "Регистрация" : "Вход"}</h1>
    <form action=${isRegister ? "/users/register" : "/users/login"} method="post" enctype="application/x-www-form-urlencoded">
        <p>
            Логин: <input type="text" name="login">
        </p>
        <p>
            Пароль: <input type="text" name="password">
        </p>
        <p>
            <input type="submit" />
        </p>
    </form>
</body>
</html>


<%--<form:form action="${isRegister ? \"/users/register\" : \"/users/login\"}"
               method="post"
               enctype="application/x-www-form-urlencoded"
               modelAttribute="formBean">
        <p>
            Логин: <form:input type="text" path="login" />
        </p>
        <p>
            Пароль: <form:input type="text" path="password" />
        </p>
        <p>
            <input type="submit" />
        </p>
    </form:form>--%>