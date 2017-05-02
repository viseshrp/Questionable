<!--User posts list view-->
<%@ include file="header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="content">
    <c:if test="${requestScope.deleted == 'true' }">
        <div class="alert alert-success" role="alert">
            <strong>Post Removed.</strong>
        </div>
    </c:if>
</div>

<div class="row-fluid">
    <div class="span12">
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
                        <a href="PostController?action=viewpost&AMP;postId=${post.id}" class="card-link"><font color="black">Comment</font></a>
                        &nbsp;
                        <a href="PostController?action=edit&AMP;postId=${post.id}" class="card-link"><font color="black">Edit</font></a>
                        &nbsp;
                        <a href="PostController?action=delete&AMP;postId=${post.id}" class="card-link"><font color="red">Delete</font></a>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div> 
