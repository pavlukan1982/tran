<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="ticketDatabase" type="java.util.Map<Integer, com.wrox.Ticket>"--%>

<%@ page import="com.farata.SecurityServiceDAO" %>
<%@ page import="com.farata.dto.UserInfoFirstLastNameDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.farata.dto.UserInfoDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    private static final String USERID_NOT_FOUND = "UserId not found";
    private static final String USERID_UNLOCK = "User unlocked";
    private static final SecurityServiceDAO securityService = new SecurityServiceDAO();
%>

<%
    pageContext.setAttribute("message", "");

    String type = request.getParameter("type");
    if (type != null) {
        session.setAttribute("unlock_type", type);
    } else {
        type = (String) session.getAttribute("unlock_type");
    }

    List<UserInfoFirstLastNameDTO> userInfoNameList = new ArrayList<UserInfoFirstLastNameDTO>();
    String action = request.getParameter("action");
    if ("search".equals(action)) {
        session.setAttribute("unlock_producerId", request.getParameter("producerId"));
        session.setAttribute("unlock_bgaId", request.getParameter("bgaId"));
        session.setAttribute("unlock_bgaName", request.getParameter("bgaName"));
        session.setAttribute("unlock_action", request.getParameter("action"));
        session.setAttribute("unlock_userEmail", request.getParameter("userEmail"));
        String bgaIdReq = request.getParameter("bgaId");
        long bgaIdLong = (bgaIdReq == null || bgaIdReq.isEmpty()) ? 0 : Long.parseLong(bgaIdReq);
        if ("producer".equals(type)) {
            String bgaNameReq = request.getParameter("bgaName");
            bgaNameReq = (bgaNameReq == null) ? "" : bgaNameReq;
            String producerIdReq = request.getParameter("producerId");
            Long producerIdLong = ((producerIdReq == null || producerIdReq.isEmpty()) ? 0 : Long.parseLong(producerIdReq));
            userInfoNameList = securityService.getUserInfoByProducerBgaId(producerIdLong, bgaIdLong, bgaNameReq);}
        if ("admin".equals(type)) {
            String userEmailReq = request.getParameter("userEmail");
            userEmailReq = (userEmailReq == null) ? "" : userEmailReq;
            userInfoNameList = securityService.getUserInfoByUserBgaId(userEmailReq, bgaIdLong);
        }

        if (userInfoNameList.size() == 0){
            pageContext.setAttribute("message", USERID_NOT_FOUND);
        }

    }
    pageContext.setAttribute("userInfo", userInfoNameList);


    if ("unlock".equals(action)) {

        String userIdReq = request.getParameter("userId");
        long userIdLong = (userIdReq == null || userIdReq.isEmpty()) ? 0 : Long.parseLong(userIdReq);



        List<UserInfoDTO> userInfoList = securityService.getUserInfo(userIdLong);
        if (userInfoList.size() == 1) {
            UserInfoDTO userInfo = userInfoList.get(0);
            userInfo.setfailedLoginCount(0L);
            securityService.getUserInfo_update(userInfo, "failedLoginCount");
            pageContext.setAttribute("message", USERID_UNLOCK);
        }
    }

%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="stylesheets/bootstrap/bootstrap.css">
    <title>Unlock user</title>
</head>
<body>

<c:if test="${not empty unlock_type}">
    <div class="container">
        <h2>Search ${unlock_type}</h2>
        <form role="form" method="post" action="unlock_user.jsp?action=search">

            <c:if test='${"producer" eq unlock_type}'>
                <div class="form-group">
                    <label for="producerId">Producer Id</label>
                    <input type="number" name="producerId" class="form-control" value="${unlock_producerId}" id="producerId">
                </div>
                <div class="form-group">
                    <label for="bgaId">BGA Id</label>
                    <input type="number" name="bgaId" class="form-control" value="${unlock_bgaId}" id="bgaId">
                </div>
            </c:if>
            <c:if test='${"admin" eq unlock_type}'>
                <div class="form-group">
                    <label for="userEmail">User email</label>
                    <input type="email" name="userEmail" class="form-control" value="${unlock_userEmail}" id="userEmail">
                </div>
            </c:if>
            <div class="form-group">
                <label for="bgaName">BGA name</label>
                <input type="text" name="bgaName" class="form-control" value="${unlock_bgaName}" id="bgaName">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-default">Search</button>
            </div>
        </form>
    </div>
    <c:if test="${userInfo.size() != 0}">
        <div class="container">
            <form role="form" method="post" action="unlock_user.jsp?action=unlock">
                <div class="form-group">
                    <label for="userId">User Id</label>
                    <select required name="userId" id="userId" class="form-control">
                        <c:if test="${userInfo.size()>1}">
                            <option selected disabled hidden>Select user</option>
                        </c:if>
                        <c:forEach items="${userInfo}" var="user">
                            <option value="${user.id}">${user.firstName} ${user.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Unlock</button>
                </div>
            </form>
        </div>
    </c:if>

</c:if>

<c:if test="${not empty message}">
    <div class="container">
        <h2>${message}</h2>
    </div>
</c:if>

</body>
</html>