<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper username="${requestScope.username}">

    <div class="container">
        <h1><c:out value="${param.pageTitle}"/></h1>
    </div>

    <div id="boxes" class="container">
        <c:forEach items="${requestScope.panelsData.panelBoxDataList}" var="box">
            <div id="${box.id}" class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <c:out value="${box.header}"></c:out>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <c:forEach items="${box.panelItemList}" var="boxItem">
                                <div id="${boxItem.id}" class="item">
                                    <div class="itemContentWrapper">
                                        <div class="itemImageWrapper">
                                            <a href="${boxItem.url}" id="${boxItem.id}_image"
                                               class="icon spriteicon_img"></a>
                                        </div>
                                        <div class="itemTextWrapper">
                                            <a href="${boxItem.url}" class="link"><c:out
                                                    value="${boxItem.header}"></c:out></a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

            </div>
        </c:forEach>
    </div>

</t:wrapper>