<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event add</title>
</head>
<body>
    <h1>Добавление события</h1>

    <form action="/events/add" method="post" enctype="application/x-www-form-urlencoded">
        <p>
            Название: <input type="text" name="title">
        </p>
        <p>
            Описание: <input type="text" name="description">
        </p>
        <p>
            Дата начала: <input type="datetime-local" name="start">
        </p>
        <p>
            Дата окночания: <input type="datetime-local" name="end">
        </p>
        <p>
            <input type="submit" />
        </p>
    </form>
</body>
</html>
