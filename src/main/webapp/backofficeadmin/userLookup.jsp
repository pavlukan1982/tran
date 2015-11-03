<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper username="${requestScope.username}">

    <div class="container">

        <form class="form-inline" method="post" action="${requestScope.serviceUrl}">
            <h2 class="form-heading">Please enter detail information</h2>

            <div class="form-group">
                <label for="producerId">Producer Id</label>
                <input type="text" id="producerId" name="producerId" class="form-control" placeholder="Producer Id"
                       size="10" value="${requestScope.producerId}" required
                       autofocus>
            </div>
            <div class="form-group">
                <label for="gaId">Agency Id</label>
                <input type="text" id="gaId" name="gaId" class="form-control" placeholder="Agency Id" size="10"
                       value="${requestScope.gaId}">
            </div>
            <div class="form-group">
                <label for="gaName">Agency Name</label>
                <input type="text" id="gaName" name="gaName" class="form-control" placeholder="Agency Name"
                       value="${requestScope.gaName}">
            </div>
            <button class="btn btn-lg btn-primary" type="submit">Lookup</button>
            <input type="hidden" name="action" value="${requestScope.action}">
        </form>

        <div id="alert-message" class="alert alert-danger" role="alert" style="display: none;">
            <c:out value="${requestScope.alertMessage}"/>
        </div>
        <div id="info-message" class="alert alert-info" role="alert" style="display: none;">
            <c:out value="${requestScope.infoMessage}"/>
        </div>
    </div>

    <c:if test="${requestScope.userInfoList!=null}">
        <div id="boxes" class="container">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>User Id</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Agency Id</th>
                    <th>Agency Name</th>
                    <th></th>
                    <th>Failed Login Counter</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:set var="count" value="0" scope="page"/>
                <c:forEach items="${requestScope.userInfoList}" var="user">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr>
                        <td><c:out value="${count}"/></td>
                        <td><c:out value="${user.userId}"/></td>
                        <td><c:out value="${user.firstName}"/></td>
                        <td><c:out value="${user.lastName}"/></td>
                        <td><c:out value="${user.gaId}"/></td>
                        <td><c:out value="${user.gaName}"/></td>
                        <td><a class="btn btn-danger" href="javascript:resetPassword(${user.userId});"
                               role="button">Reset password</a></td>
                        <td align="center">
                            <c:out value="${user.failedLoginCount}"/>
                        </td>
                        <td>
                            <c:if test="${user.failedLoginCount > 0}">
                                <a class="btn btn-warning" href="javascript:unlockAccount(${user.userId});"
                                    <%--<a class="btn btn-warning" href="${requestScope.accountUnlockUrl}&userId=${user.userId}"--%>
                                   role="button">
                                    Unlock account
                                </a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <script type="text/javascript">
        setServiceUrl("${requestScope.serviceUrl}");
        setUnlockAccountAction("${requestScope.unlockAccountAction}");
        setResetPasswordActionAction("${requestScope.resetPasswordActionAction}");
    </script>

</t:wrapper>