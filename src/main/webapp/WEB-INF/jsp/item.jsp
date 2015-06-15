<%-- 
    Document   : movies
    Created on : 15.04.2015, 20:31:03
    Author     : ilmir
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="fragments/staticFiles.jsp"/>
        <title>${itemList[0].name}</title>
    </head>
    <body>
        <jsp:include page="fragments/bodyHeader.jsp"/>

        <div class="col-md-7">
            <div class="thumbnail">
                <c:set var="imageSet" value="false"/>
                <c:forEach items="${itemList}" var="i">
                    <c:if test="${!imageSet && i.itemPK.color.equals(param['color'])}">
                        <img style="width: 100%" src="<c:url value="/jsp/resources/images/${i.image}"/>" alt="">
                        <c:set var="imageSet" value="true"/>    
                    </c:if>
                </c:forEach>
            </div>
        </div>

        <div class="col-md-5">
            <div class="caption-full">
                <h3>${itemList[0].name}</h3>
                <div style="color: red; font-size:xx-large">
                    ${itemList[0].unitPrice}
                    <span style="font-size: x-large" class="glyphicon glyphicon-rub"></span>
                </div>

                <div class="well well-sm">
                    <div>
                        <b>Описание:</b><p style="text-align: justify">${itemList[0].description}</p>
                    </div>
                    <form action="tocart" method="GET">
                        <table>
                            <tr>
                                <td style="text-align: right;padding-right: 20px"><b>Цвет:</b></td>
                                <td style="text-align: left">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                            ${param['color']} <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <c:forEach items="${itemColor}" var="c">
                                                <li><a href="?color=${c}">${c}</a></li>
                                                </c:forEach>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right;padding-right: 20px"><b>Размеры:</b></td>
                                <td style="text-align: left">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                            <c:if test="${param['size']!=null}">
                                                ${param['size']}
                                                <input type="hidden" name="size" value="${param['size']}"/>
                                            </c:if>
                                            <c:if test="${param['size']==null}">
                                                ${itemSize[0]}
                                                <input type="hidden" name="size" value="${itemSize[0]}"/>
                                            </c:if>
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <c:forEach items="${itemSize}" var="c">
                                                <li><a href="?color=${param['color']}&&size=${c}">${c}</a></li>
                                                </c:forEach>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right;padding-right: 20px"> 
                                    <b>Доступно:</b>
                                </td>
                                <td style="text-align: left">
                                    <input type="number" readonly="true" style="width: 65px" class="input-sm" value="${availableItems}"/>
                                </td>

                            </tr>
                            <tr>
                                <td style="text-align: right;padding-right: 20px"> 
                                    <b>Колличество:</b>
                                </td>
                                <td style="text-align: left">
                                    <input type="number" min="1" max="${availableItems}" name="count" style="width: 65px" class="input-sm" value="1"/>
                                    <!--обновляет при каждом переходе на страницу-->
                                    <input type="hidden" id="refreshed" value="no">
                                    <script type="text/javascript">
                                        onload = function () {
                                            var e = document.getElementById("refreshed");
                                            if (e.value == "no")
                                                e.value = "yes";
                                            else {
                                                e.value = "no";
                                                location.reload();
                                            }
                                        }
                                    </script>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="hidden" name="color" value="${param['color']}"/>
                                    <input type="hidden" name="itemNo" value="${itemList[0].itemPK.itemNo}"/>
                                    <!--<input style="width: 100%" class="btn btn-primary" type="submit" value="Добавить в корзину">-->
                                    <button style="width: 200px;" type="submit" class="btn btn-primary">
                                        <i class="glyphicon glyphicon-shopping-cart"></i>
                                        Добавить в корзину
                                    </button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>
