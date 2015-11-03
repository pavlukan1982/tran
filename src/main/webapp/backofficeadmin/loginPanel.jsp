<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><c:out value="${requestScope.pageTitle}"/></title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/bootstrap.min.css "/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backofficeadmin/control.css"/>
<body>

<div class="container">

    <form class="form-signin" method="post" action="${pageContext.request.contextPath}/BackOfficeAdmin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" name="emailValue" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="passwordValue" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <input type="hidden" name="action" value="login">
    </form>

    <c:if test="${requestScope.alertMessage!=null}">
        <div class="alert alert-danger" role="alert"><c:out value="${requestScope.alertMessage}"/></div>
    </c:if>
</div>

<script src="/sbweb/scripts/docjs/jquery-1.11.1.min.js"></script>
<script src="/sbweb/scripts/docjs/bootstrap.min.js"></script>

</body>
</html>
