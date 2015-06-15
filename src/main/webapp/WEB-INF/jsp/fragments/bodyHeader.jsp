<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/jsp/home" />">
                <span>
                    SoccerPR<img width="20px" style="margin-top: -5px" src="<c:url value="/jsp/resources/images/ball_icon.png"/>" />
                </span>
            </a>
        </div>
        <form class="navbar-form navbar-left" role="search" action="<c:url value="/jsp/items/search"/>">
            <div class="form-group">
                <input name="text" type="text" class="form-control" placeholder="Поиск" style="width: 150px"/>                 
            </div>
        </form>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="<c:url value="/jsp/items/cart"/>">Корзина<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"/></a>
                </li>
                <li>
                    <a href="<c:url value="/jsp/users/register"/>">Регистрация</a>
                </li>
            </ul>

            <c:choose>
                <c:when test="${sessionScope.user!=null}" >
                    <div class="navbar-right" style="margin-top: 8px">
                        <a  href="<c:url value='/jsp/users/${sessionScope.user.username}'/>" class="btn btn-link">
                            <span class="glyphicon glyphicon-user"></span>
                            ${sessionScope.user.username}
                        </a>
                        |<a href="<c:url value='/jsp/users/logout'/>" class="btn btn-link"> Выйти</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <form  class="navbar-form navbar-right" method="post" action="<c:url value='/jsp/users/login'/>">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input style="width: 120px" class="form-control" name="name" value="" placeholder="Логин">                                        
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input style="width: 120px" id="password" type="password" class="form-control" name="password" value="" placeholder="Пароль">                                        
                        </div>

                        <button type="submit" class="btn btn-primary">Войти</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<!-- Page Content -->
<div class="container" rel="main">

    <div class="row">

        <div class="col-md-3">

            <p class="lead"><s:message code="sportpro.category"/></p>
            <div class="list-group">
                <a href="<c:url value="/jsp/items/boots"/>" class="list-group-item"><b>Бутсы</b></a>
                <a href="<c:url value="/jsp/items/wears"/>" class="list-group-item"><b>Одежда</b></a>
                <a href="<c:url value="/jsp/items/balls"/>" class="list-group-item"><b>Мячи</b></a>
                <a href="<c:url value="/jsp/items/defense"/>" class="list-group-item"><b>Защита</b></a>
                <a href="<c:url value="/jsp/admin/boots"/>" class="list-group-item"><i>Admin</i></a>
            </div>
        </div>

        <div class="col-md-9">
        <div class="row">

