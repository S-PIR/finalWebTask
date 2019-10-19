<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<body BGCOLOR="#FDF5E6" class="center">
<c:if test="${not empty errorMessage}">
    <c:out value="${errorMessage}"/><br>
    <hr>
</c:if>
<h2>Fill the form please:</h2>
<form class="header-form" name="loginForm" method="post" action="<c:url value='/login'/>">
    <input type="text" name="login" placeholder="login"><br><br>
    <input type="password" name="password" placeholder="password"><br><br>
    <input type="submit" value="Enter">
</form>
<hr>
</body>
</body>
</html>
