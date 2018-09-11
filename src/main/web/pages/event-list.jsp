<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<jsp:useBean id="eventsListBean" type="com.github.vvhiterussian.restmate.web.EventsListBean" scope="request" />
<html>
<head>
    <title>Event list</title>
</head>
<body>
<h1>Hello, ${eventsListBean.user.login}!</h1>
<c:choose>
    <c:when test="${not empty eventsListBean.events}">
        <table border="1">
            <tbody>
                <c:forEach items="${eventsListBean.events}" var="event">
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
