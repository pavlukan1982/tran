<%@tag description="Main Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="pageTitle"%>
<%@attribute name="username"%>

<c:set var="homePageUrl">BackOfficeAdmin</c:set>
<c:set var="logoutPageUrl">${homePageUrl}?action=logout</c:set>

<html>
<head>
    <title><c:out value="${pageTitle}"/></title>
</head>
<link rel="stylesheet" type="text/css" href="/sbweb/stylesheets/bootstrap.min.css "/>
<link rel="stylesheet" type="text/css" href="/sbweb/backofficeadmin/control.css"/>
<script src="/sbweb/scripts/boadmin/boa.js"></script>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${homePageUrl}">Back Office Admin</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${homePageUrl}">Home</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Profile <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-header"><c:out value="${username}"></c:out></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="${logoutPageUrl}">Logout</a></li>
                    </ul>
                </li>
            </ul>
            <!--
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
            -->
        </div><!--/.nav-collapse -->
    </div>
</nav>

<jsp:doBody/>

<script src="/sbweb/scripts/docjs/jquery-1.11.1.min.js"></script>
<script src="/sbweb/scripts/docjs/bootstrap.min.js"></script>

</body>
</html>
