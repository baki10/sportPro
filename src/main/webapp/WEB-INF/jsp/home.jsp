<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="fragments/staticFiles.jsp"/>
        <title>SoccerPro онлайн магазин</title>
    </head>
    <body>
        <jsp:include page="fragments/bodyHeader.jsp"/>

        <div class="col-lg-12">

            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                    <li data-target="#myCarousel" data-slide-to="4"></li>
                    <li data-target="#myCarousel" data-slide-to="5"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="<c:url value="/jsp/resources/images/slide/7.jpg"/>" alt="James Rodrigez">
                    </div>

                    <div class="item">
                        <img src="<c:url value="/jsp/resources/images/slide/8.jpg"/>" alt="Nike">
                    </div>

                    <div class="item">
                        <img src="<c:url value="/jsp/resources/images/slide/9.jpg"/>" alt="Just do it">
                    </div>

                    <div class="item">
                        <img src="<c:url value="/jsp/resources/images/slide/10.jpg"/>" alt="Just do it">
                    </div>

                    <div class="item">
                        <img src="<c:url value="/jsp/resources/images/slide/11.jpg"/>" alt="Nike">
                    </div>

                    <div class="item">
                        <img src="<c:url value="/jsp/resources/images/slide/13.jpg"/>" alt="Just do it">
                    </div>

                </div>

                <!-- Left and right controls -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Prev</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

        </div>
        <div class="col-lg-12">
            ${hello}
        </div>

        <jsp:include page="fragments/footer.jsp"/>

    </body>
</html>
