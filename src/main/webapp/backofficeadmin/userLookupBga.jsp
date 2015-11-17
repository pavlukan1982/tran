<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper username="${requestScope.username}">

    <div class="container">

        <h2 class="form-heading">Please enter detail information</h2>

        <div id="formAgency" class="panel panel-default">
            <div class="panel-heading">Lookup by Admin</div>
            <div class="panel-body">
                <form id="userLookupAgency" class="form-inline" method="post">

                    <div class="form-group">
                        <label for="gaId_agency">Agency Id</label>
                        <input type="text" id="gaId_agency" name="gaId" class="form-control"
                               placeholder="Agency Id" size="10"">
                    </div>
                    <div class="form-group">
                        <label for="gaEmail">Agency admin Email</label>
                        <input type="text" id="gaEmail" name="gaEmail" class="form-control"
                               placeholder="Agency admin Email">
                    </div>
                    <input type="hidden" name="lookupType" value="admin"/>
                    <button id="submitAgency" onclick="javascript:return submitFormAgency()"
                            class="btn btn-lg btn-primary">Lookup
                    </button>
                </form>
            </div>
        </div>

        <div id="alert-message" class="alert alert-danger" role="alert" style="display: none;">
            <c:out value="${requestScope.alertMessage}"/>
        </div>
        <div id="info-message" class="alert alert-info" role="alert" style="display: none;">
            <c:out value="${requestScope.infoMessage}"/>
        </div>
    </div>

    <div id="boxes" class="container">
        <table id="users" class="table table-hover" style="display: none;">
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
            </tbody>
        </table>
    </div>
    <table style="display: none;">
        <tbody id="resultRow-tpl">
        <tr id="#userId">
            <td><c:out value="#count"/></td>
            <td><c:out value="#userId"/></td>
            <td><c:out value="#firstName"/></td>
            <td><c:out value="#lastName"/></td>
            <td><c:out value="#gaId"/></td>
            <td><c:out value="#gaName"/></td>
            <td></td>
            <td id="tdFailedLoginCount" align="center">
                <c:out value="#failedLoginCount"/>
            </td>
            <td id="tdUnlockAccount">
                <a class="btn btn-warning" href="javascript:unlockAccount('#userId');"
                   role="button">
                    Unlock account
                </a>
            </td>
        </tr>
        </tbody>
    </table>

    <script type="text/javascript">
        setServiceUrl("${requestScope.serviceUrl}");
        setUnlockAccountPath("${requestScope.unlockAccount}");
    </script>

</t:wrapper>