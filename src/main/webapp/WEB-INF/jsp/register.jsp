<%-- 
    Document   : movies
    Created on : 15.04.2015, 20:31:03
    Author     : ilmir
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="fragments/staticFiles.jsp"/>
        <title>Регистрация</title>
    </head>
    <body>
        <jsp:include page="fragments/bodyHeader.jsp"/>

        <div class="col-lg-5">
            <div style="text-align: center"><h3>Регистрация</h3></div>

            <sf:form method="POST" commandName="user">
                <div class="panel panel-login">

                    <sf:input path="username" cssErrorClass="error" cssStyle="width:100%" placeholder="  Логин"/><br/>
                    <sf:input path="password" cssErrorClass="error" cssStyle="width:100%" placeholder="  Пароль"/><br/>
                    <sf:input path="email" cssErrorClass="error" cssStyle="width:100%" placeholder="  Email"/><br/>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3">
                                <input type="submit" name="register-submit" id="register-submit"  class="form-control btn btn-register" value="Создать аккаунт">
                            </div>
                        </div>
                    </div>
                    <sf:errors path="*" element="div" cssClass="error" cssStyle="width:100%"/>

                </div>
            </sf:form>
        </div>

        <jsp:include page="fragments/footer.jsp"/>


    </body>
</html>
