<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<jsp:useBean id="user" class="com.github.vvhiterussian.restmate.model.User" scope="session" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--добавлять логику залогиненности--%>
<h1>Добро пожаловать в RestMate, ${isLoggedIn ? user.login : "stranger"}!</h1>
<p>
    <c:choose>
        <c:when test="${!isLoggedIn}">
            <a href="/users/register">Зарегистрироваться</a>
            <a href="/users/login">Войти</a>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${!user.organizer}">
                    <c:choose>
                        <c:when test="${!user.hasOrganizerStatusRequest()}">
                            <%--нужно прокидывать юзера на контроллер--%>
                            <form:form action="/organizer-requests/add" method="post">
                                <button type="submit" class="btn-link">Стать организатором</button>
                            </form:form>
                        </c:when>
                        <c:otherwise>
                            <p>Ожидание утверждения статуса организатора...</p>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <a href="/events/add">Добавить событие</a>
                </c:otherwise>
            </c:choose>
            <a href="/users/logout">Выйти</a>
        </c:otherwise>
    </c:choose>

    <%--errorMessage--%>
    <c:if test="condition">

    </c:if>
</p>
</body>
</html>
