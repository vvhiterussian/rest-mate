<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<jsp:useBean id="eventsListBean" type="com.github.vvhiterussian.restmate.web.EventsListBean" scope="request" />
<html>
<head>
    <title>Event list</title>
</head>
<body>
<h1>Hello, ${eventsListBean.name}!</h1>
<%--<c:forEach begin="1" end="3" var="index">--%>
    <%--<p>${index}</p>--%>
<%--</c:forEach>--%>

<c:forEach items="${eventsListBean.events}" var="event">
    <p>${event.name} ${event.description}</p>
</c:forEach>

</body>
</html>
