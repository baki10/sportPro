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
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="<c:url value="/users/register"/>">Зарегистровать пользователя</a>
                </li>
            </ul>
        </div>

        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<!-- Page Content -->
<div class="container" rel="main">

    <div class="row">

        <div class="col-md-2">

            <p class="lead"><s:message code="sportpro.category"/></p>
            <div class="list-group">
                <a href="<c:url value="/jsp/admin/boots"/>" class="list-group-item">Бутсы</a>
                <a href="<c:url value="/jsp/admin/wears"/>" class="list-group-item">Одежда</a>
                <a href="<c:url value="/jsp/admin/balls"/>" class="list-group-item">Мячи</a>
                <a href="<c:url value="/jsp/admin/defense"/>" class="list-group-item">Защита</a>
            </div>
        </div>

        <div class="col-md-10">
            <div class="row">

