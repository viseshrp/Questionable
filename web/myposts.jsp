<%@ include file="header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div class="row-fluid">
            <div class="span12">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:forEach var="post" items="${posts}">
    <div id="content">
                <a href="PostController?action=viewpost&AMP;postId=${post.id}" title="${post.question}">${post.question}</a>
                <br><small style="font-size:85%;"><strong>Created on ${post.created_date} &nbsp;--&nbsp; Modified on ${post.modified_date} &nbsp;--&nbsp; ${post.user.user_name}</strong></small>
                <br>${post.content}
                <div><span class="label">${post.category.name}</span></div>
    </div>
                <br> &nbsp;
        </c:forEach>
            </div>
        </div> 
