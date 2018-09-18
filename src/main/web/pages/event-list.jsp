<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<jsp:useBean id="eventsModelView" type="com.github.vvhiterussian.restmate.web.modelviews.EventsModelView" scope="session" />
<html>
<head>
    <title>Event list</title>
</head>
<body>
<h1>Hello, ${eventsModelView.user.login}!</h1>
<c:choose>
    <c:when test="${not empty eventsModelView.events}">
        <table border="1">
            <tbody>
                <c:forEach items="${eventsModelView.events}" var="event">
                    <tr>
                        <td><c:out value="${event.name}" escapeXml="true"/></td>
                        <td><c:out value="${event.description}" escapeXml="false"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Нет событий.</p>
    </c:otherwise>
</c:choose>

<p>
    <a href="/events/add">Добавить событие</a>
</p>

</body>
</html>
