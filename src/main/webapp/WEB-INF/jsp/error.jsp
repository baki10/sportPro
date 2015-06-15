<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="fragments/staticFiles.jsp"/>
        <title>Ошибка</title>
    </head>    
    <body>
        <jsp:include page="fragments/bodyHeader.jsp"/>

        <div class="col-lg-12">

            <div class="error">
                <h1>${msg}</h1>
                ${details}
            </div>
            <br/>
            <a href="javascript:history.back()">Назад</a>

        </div>

        <jsp:include page="fragments/footer.jsp"/>

    </body>
</html>

