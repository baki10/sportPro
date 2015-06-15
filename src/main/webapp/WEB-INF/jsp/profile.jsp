<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
    <head>
        <jsp:include page="fragments/staticFiles.jsp"/>
        <title>Profile</title>
    </head>
    <body>
        <jsp:include page="fragments/bodyHeader.jsp"/>

        <div class="col-md-5">
            <div class="well">
                <h2>Личный кабинет</h2>
                <c:if test="${sessionScope.user!=null}">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#home" data-toggle="tab">Профиль</a></li>
                        <li><a href="#profile" data-toggle="tab">Пароль</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane active in" id="home">
                            <sf:form id="tab" method="POST" commandName="user">
                                <br/>
                                <table style="text-align: right">
                                    <tr>
                                        <td>
                                            <label>Логин</label>        
                                            <input readonly name="username" style="margin-left: 15px" type="text" value="${user.username}" class="input-xlarge">      
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <label>Email</label>        
                                            <input name="email" style="margin-left: 15px" type="text" value="${user.email}" class="input-xlarge">      
                                        </td>
                                    </tr>

                                </table>
                                <input type="hidden" name="password" value="${user.password}"/>
                                <br/>
                                <div>
                                    <button class="btn btn-primary">Сохранить</button>
                                </div>
                            </sf:form>

                        </div>
                        <div class="tab-pane fade" id="profile">
                            <sf:form id="tab2" method="POST" commandName="user">
                                <br/>
                                <label>Новый пароль</label>
                                <input name="password" type="password" class="input-xlarge">
                                <br/><br/>
                                <input type="hidden" name="username" value="${customer.username}"/>
                                <input type="hidden" name="email" value="${customer.email}"/>
                                <div>
                                    <button class="btn btn-primary">Сохранить</button>
                                </div>
                            </sf:form>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>
