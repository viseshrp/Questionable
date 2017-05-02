<%@ include file="header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${requestScope.reported != null}">
        <div id="content">
            <div class="alert alert-success" role=alert> <strong>Question Reported.</strong> The admin will review this to take action. </div>
        </div>
    </c:when>
    <c:when test="${sessionScope.theUser != null}">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:forEach var="post" items="${posts}">
            <div id="content">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="PostController?action=viewpost&AMP;postId=${post.id}" title="${post.question}">${post.question}</a></h3>
                    </div>
                    <div class="panel-body">
                        ${post.content}<br><br>
                        <p class="card-subtitle mb-2 text-muted"><small style="font-size:85%;"><strong>Created on ${post.created_date} &nbsp;--&nbsp; Modified on ${post.modified_date} &nbsp;by&nbsp; ${post.user.user_name}</strong></small></p>
                        <span class="label label-primary">${post.category.name}</span>
                    </div>
                    <div class="panel-footer text-primary">
                        &nbsp; <a href="PostController?action=report&AMP;postId=${post.id}" class="card-link"><font color="red">Report</font></a>&nbsp;
                        <a href="PostController?action=viewpost&AMP;postId=${post.id}" class="card-link"><font color="black">Comment</font></a></div>     

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
