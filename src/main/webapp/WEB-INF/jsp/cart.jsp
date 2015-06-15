<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="fragments/staticFiles.jsp"/>
        <title>Корзина</title>
    </head>
    <body>
        <jsp:include page="fragments/bodyHeader.jsp"/>

        <div class="col-md-12">
            <div class="table-responsive cart_info">
                <table class="table table-condensed">
                    <thead>
                        <tr class="cart_menu">
                            <td class="image">Товар</td>
                            <td class="description"></td>
                            <td class="price">Цена</td>
                            <td class="quantity">Колличество</td>
                            <td class="total">Итого</td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="price" value="0"/>
                        <c:forEach items="${items}" var="i">
                            <c:set var="price" value="${price+i.item.unitPrice*i.quantityOrdered}"/>
                            <tr>
                                <td class="cart_product" style="width: 10%">
                                    <a href="<c:url value="/items/${i.item.itemPK.itemNo}?color=${i.item.itemPK.color}&&size=${i.item.itemPK.itemSize}"/>">
                                        <img style="width: 100%" src="<c:url value="/jsp/resources/images/${i.item.image}"/>" alt="">
                                    </a>
                                </td>
                                <td class="cart_description" style="width: 55%">
                                    <h4>
                                        <a href="<c:url value="/items/${i.item.itemPK.itemNo}?color=${i.item.itemPK.color}&&size=${i.item.itemPK.itemSize}"/>">
                                            ${i.item.name}
                                        </a>
                                    </h4>
                                </td>
                                <td class="cart_price" style="width: 10%">
                                    <p>
                                        ${i.item.unitPrice}
                                    </p>
                                </td>
                                <td class="cart_quantity" style="width: 10%">
                                    <form method="POST" action="update_cart">
                                        <input type="hidden" name="itemNo" value="${i.item.itemPK.itemNo}"/>
                                        <input type="hidden" name="color" value="${i.item.itemPK.color}"/>
                                        <input type="hidden" name="size" value="${i.item.itemPK.itemSize}"/>
                                        <input name="quantityOrdered" type="number" min="1" max="${i.quantityOrdered+i.item.unitsOnHand}" style="width: 65px" class="input-sm" value="${i.quantityOrdered}" >
                                    </form>
                                </td>
                                <td class="cart_total" style="width: 10%">
                                    <p>
                                        ${i.item.unitPrice*i.quantityOrdered}
                                    </p>
                                </td>
                                <td class="cart_delete" style="width: 5%">
                                    <form method="POST" id="deleteform${i.item.itemPK}" action="remove_cart">
                                        <input type="hidden" name="itemNo" value="${i.item.itemPK.itemNo}"/>
                                        <input type="hidden" name="color" value="${i.item.itemPK.color}"/>
                                        <input type="hidden" name="size" value="${i.item.itemPK.itemSize}"/>
                                        
                                        
                                        <a href="javascript:{}" onclick="document.getElementById('deleteform${i.item.itemPK}').submit()">
                                           <span class="glyphicon glyphicon-remove" aria-hidden="true"/>
                                        </a>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="2"></td>
                            <td colspan="2" style="text-align: right">
                                <b>Общий итог:</b>
                            </td>
                            <td colspan="2">
                                <b>${price} &nbsp;руб</b>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <c:if test="${sessionScope.items!=null}">
                    <div class="col-lg-offset-9">
                        <span style="color:red">${cartError}</span>
                        <c:if test="${sessionScope.user!=null}">
                            <sf:form action="buy_items" method="POST" commandName="items">
                                
                                <button style="width: 100px;" type="submit" class="btn btn-primary">
                                    <i class="glyphicon glyphicon-ok"></i>
                                    Купить
                                </button> 
                            </sf:form>
                        </c:if>
                        <c:if test="${sessionScope.user==null}">
                            <span style="color: red">необходима авторизация</span>
                            <button style="width: 100px;" type="submit" class="btn btn-primary" disabled="true">
                                <i class="glyphicon glyphicon-ok"></i>
                                Купить
                            </button> 
                        </c:if>               
                    </div>
                </c:if>

            </div>
        </div>
                            
        <jsp:include page="fragments/footer.jsp"/>

    </body>
</html>
