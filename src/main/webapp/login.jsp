<%-- 
    Document   : index
    Created on : 22.02.2011, 10:54:43
    Author     : class_a23
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <title>Login Form</title>
    </head>
       <h2>You can log in:</h2>
        <form name="loginForm" method="POST" action="j_security_check">
            <p><strong><label for="username">Please type your user name: </label></strong>
                <input id="username" type="text" name="j_username" size="25"></p>
            <p><strong><label for="password">Please type your password: </label></strong>
                <input id="password" type="password" size="15" name="j_password"></p>
            <p>
                <input type="submit" value="Submit"/>
                <input type="reset" value="Reset"/></p>
        </form>       
    
</html>
