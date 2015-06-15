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
        <jsp:include page="../fragments/staticFiles.jsp"/>
        <title>${itemList[0].name}</title>
    </head>
    <body>
        <jsp:include page="../fragments/adminHeader.jsp"/>

        <div class="col-md-12">
            <div class="table-responsive cart_info">
                <form method="POST" action="save_items_changes">
                    <table class="table table-condensed">
                        <tbody>
                            <tr>
                                <td>
                                    <b>Название товара</b>
                                </td>
                                <td>
                                    <textarea rows="1" cols="90" name="name">${itemList[0].name}</textarea>  
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>Описание товара</b>
                                </td>
                                <td>
                                    <textarea rows="3" cols="90" name="description">${itemList[0].description}</textarea>                                
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>Цена товара</b>
                                </td>
                                <td>
                                    <input type="text" value="${itemList[0].unitPrice}" name="unitPrice"/>                                
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="hidden" value="${itemList[0].itemPK.itemNo}" name="itemNo"/>
                                    <button type="submit">Сохранить</button>
                                </td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>

        <div class="col-md-6">
            <table class="table table-condensed">
                <thead>
                    <tr>
                        <td>Цвет</td>
                        <td>Размер</td>
                        <td>Колличество</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${itemList}" var="i">
                        <tr>
                            <td>
                                ${i.itemPK.color}
                            </td>
                            <td>
                                ${i.itemPK.itemSize}
                            </td>
                            <td style="width: 25%">
                                <form action="save_item_changes" method="POST">
                                    <input type="hidden" name="itemNo" value="${i.itemPK.itemNo}"/>
                                    <input type="hidden" name="color" value="${i.itemPK.color}"/>
                                    <input type="hidden" name="size" value="${i.itemPK.itemSize}"/>
                                    <input type="number" name="count" value="${i.unitsOnHand}" min="1" style="width: 65px" class="input-sm"/>
                                    <button type="submit"><span><i class="glyphicon glyphicon-save"></i></span></button>
                                </form>   
                            </td>
                            <td style="width: 5%">
                                <form action="delete_item" method="POST">
                                    <input type="hidden" name="itemNo" value="${i.itemPK.itemNo}"/>
                                    <input type="hidden" name="color" value="${i.itemPK.color}"/>
                                    <input type="hidden" name="size" value="${i.itemPK.itemSize}"/>
                                    <button type="submit"><span><i class="glyphicon glyphicon-remove"></i></span></button>
                                </form> 
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form  action="createItem" method="POST">
                <input type="hidden" value="${itemList[0].categoryId.id}" name="categoryId"/>
                <input type="hidden" name="itemNo" value="${itemList[0].itemPK.itemNo}"/>
                <input type="submit" value="Добавить"/>
            </form>
        </div>

        <jsp:include page="../fragments/footer.jsp"/>
    </body>
</html>
