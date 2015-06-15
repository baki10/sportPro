<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="my"  uri="/WEB-INF/tlds/mytaglib"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="fragments/staticFiles.jsp"/>
        <title>${title}</title>
    </head>
    <body>
        <jsp:include page="fragments/bodyHeader.jsp"/>

        <c:if test="${listCount!=null}">
            <div class="lead">
                <b>${listCount}</b>
            </div>
        </c:if>

        <c:forEach items="${itemList}" var="i">
            <my:item img="/soccerPro/jsp/resources/images/${i.image}"  price="${i.unitPrice}">
                <a href="${i.itemPK.itemNo}?color=${i.itemPK.color}">${i.name}</a>
            </my:item>
        </c:forEach>

        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>
