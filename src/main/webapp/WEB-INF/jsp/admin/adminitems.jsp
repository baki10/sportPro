<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../fragments/staticFiles.jsp"/>
        <title>Items</title>
    </head>
    <body>
        <jsp:include page="../fragments/adminHeader.jsp"/>

        <div class="col-md-12">
            <div class="table-responsive cart_info">
                <table class="table table-condensed">
                    <thead>
                        <tr class="cart_menu">
                            <td class="image">Товар</td>
                            <td>Название</td>
                            <td class="description">Описание</td>
                            <td class="price">Цена</td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${itemList}" var="i">
                            <tr>
                                <td style="width: 10%">
                                    <a href="javascript:{}" onclick="document.getElementById('editform${i.itemPK}').submit()">
                                        <img style="width: 100%" src="<c:url value="/jsp/resources/images/${i.image}"/>" alt="">
                                    </a>
                                </td>
                                <td style="width: 25%">
                                    <a href="javascript:{}" onclick="document.getElementById('editform${i.itemPK}').submit()">
                                        ${i.name}
                                    </a>
                                </td>
                                <td style="width: 40%">
                                    <p>
                                        ${i.description}
                                    </p>
                                </td>
                                <td style="width: 10%">
                                    ${i.unitPrice}
                                </td>
                                <td style="width: 5%">
                                    <form method="GET" id="editform${i.itemPK}" action="edit_items">
                                        <input type="hidden" name="itemNo" value="${i.itemPK.itemNo}"/>
                                        <a href="javascript:{}" onclick="document.getElementById('editform${i.itemPK}').submit()">
                                            <span class="glyphicon glyphicon-edit" aria-hidden="true"/>
                                        </a>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="2">
                                <form action="additem" method="GET">
                                    <input type="hidden" value="${categoryId}" name="categoryId"/>
                                    <button type="submit">Добавить новый товар</button>
                                </form>
                            </td>
                            <td colspan="3"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <jsp:include page="../fragments/footer.jsp"/>

    </body>
</html>
