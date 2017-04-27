<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Questionable</title>

        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="styles/main.css">
    </head>
    <body>
        <br>
        <div class="container">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                                aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="home.jsp">Questionable</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <c:choose>
                            <c:when test="${sessionScope.theUser != null}">
                                <ul class="nav navbar-nav">
                                    <li><a href="">Home</a></li>
                                </ul>

                                <c:choose>
                                    <c:when test="${sessionScope.theUser.type == 'user' }">
                                        <ul class="nav navbar-nav">
                                            <li><a href="">Profile</a></li>
                                            <li><a href="">My Posts</a></li>
                                        </ul>
                                    </c:when>
                                    <c:otherwise>
                                        <ul class="nav navbar-nav">
                                            <li><a href="UserController?action=moderate">Moderate</a></li>
                                        </ul>
                                    </c:otherwise>
                                </c:choose>

                                <ul class="nav navbar-nav navbar-right">
                                    <li><a href="UserController?action=logout">Log out</a></li>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <ul class="nav navbar-nav navbar-right">
                                    <li><a href="login.jsp">Login</a></li>
                                    <li><a href="signup.jsp">Signup</a></li>
                                    <li><a href="about.jsp">About Us</a></li>
                                    <li><a href="contact.jsp">Contact Us</a></li>
                                </ul>
                            </c:otherwise>
                        </c:choose>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>
        </div>