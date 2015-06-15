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
        <title>Добавить товар</title>
    </head>
    <body>
        <jsp:include page="../fragments/adminHeader.jsp"/>

        <div class="col-md-12">
            <div class="table-responsive cart_info">
                <c:if test="${item!=null}">
                    <form method="POST" action="save_new_item">
                        <table class="table table-condensed">
                            <tbody>
                                <tr>
                                    <td>
                                        <b>Название товара</b>
                                    </td>
                                    <td>
                                        <textarea readonly="true" rows="1" cols="90" name="name">${item.name}</textarea>  
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Описание товара</b>
                                    </td>
                                    <td>
                                        <textarea readonly="true" rows="3" cols="90" name="description">${item.description}</textarea>                                
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Цена товара</b>
                                    </td>
                                    <td>
                                        <input readonly="true" type="text" value="${item.unitPrice}" name="unitPrice"/>                                
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td>
                                        <b>Цвет товара</b>
                                    </td>
                                    <td>
                                        <input type="text" name="color" required="true"/>                                        
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Размер товара</b>
                                    </td>
                                    <td>
                                        <input type="text" name="size" required="true"/>                                        
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Колличество товара</b>
                                    </td>
                                    <td>
                                        <input type="text" name="quantity" required="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Изображение товара</b>
                                    </td>
                                    <td>
                                        <input type="text" name="image"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" value="${categoryId}" name="categoryId"/>
                                        <input type="hidden" value="${item.itemPK.itemNo}" name="itemNo"/>
                                        <button type="submit">Сохранить</button>
                                    </td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </c:if>
                <c:if test="${item==null}">
                    <form method="POST" action="save_new_item">
                        <table class="table table-condensed">
                            <tbody>
                                <tr>
                                    <td>
                                        <b>Название товара</b>
                                    </td>
                                    <td>
                                        <textarea rows="1" cols="90" name="name" required="true"></textarea>  
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Описание товара</b>
                                    </td>
                                    <td>
                                        <textarea rows="3" cols="90" name="description" required="true"></textarea>                                
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Цена товара</b>
                                    </td>
                                    <td>
                                        <input type="text" value="" name="unitPrice" required="true"/>                                
                                    </td>
                                </tr><tr>
                                    <td>
                                        <b>Цвет товара</b>
                                    </td>
                                    <td>
                                        <input type="text" name="color" required="true"/>                                        
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Размер товара</b>
                                    </td>
                                    <td>
                                        <input type="text" name="size" required="true"/>                                        
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Колличество товара</b>
                                    </td>
                                    <td>
                                        <input type="text" name="quantity" required="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Изображение товара</b>
                                    </td>
                                    <td>
                                        <input type="text" name="image"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="hidden" value="${categoryId}" name="categoryId"/>
                                        <button type="submit">Сохранить</button>
                                    </td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </c:if>
            </div>
        </div>
        <jsp:include page="../fragments/footer.jsp"/>
    </body>
</html>
