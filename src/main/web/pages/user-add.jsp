<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <form action="/users/register" method="post" enctype="application/x-www-form-urlencoded">
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
