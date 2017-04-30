<%@ include file="header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${sessionScope.theUser != null}">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:forEach var="post" items="${posts}">
            <div id="content">
                <div class="card card-inverse card-danger card-outline-primary mb-3">
                    <div class="card-block">
                        <h4 class="card-title"><a href="PostController?action=viewpost&AMP;postId=${post.id}" title="${post.question}">${post.question}</a></h4>
                        <h6 class="card-subtitle mb-2 text-muted"><strong>Created on ${post.created_date} &nbsp;--&nbsp; Modified on ${post.modified_date} &nbsp;--&nbsp; ${post.user.user_name}</strong></h6>
                        <p class="card-text">${post.content}</p>
                        <a href="#" class="card-link">Report</a>&nbsp;
                        <a href="#" class="card-link">Comment</a>
                    </div>
                </div>
                <br> &nbsp;
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="container">
                <p>
                <p class="text-center"><h1 class="text-center">Welcome to Questionable!</h1>
                <br>

                <img src="images/rsz_quora_1.png" alt="..." class="img-responsive center-block" />
                <br>
                <h3>Our mission is to share and grow the world’s knowledge. 
                    A vast amount of the knowledge that would be valuable to many people is currently only available 
                    to a few — either locked in people’s heads, or only accessible to select groups. 
                    We want to connect the people who have knowledge to the people who need it, 
                    to bring together people with different perspectives so they can understand each other better, 
                    and to empower everyone to share their knowledge for the benefit of the rest of the world.</h3>
                <br>

                <br>
            </div>
        </c:otherwise>
    </c:choose>
