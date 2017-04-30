<%-- 
    Document   : reportques
    Created on : Feb 23, 2017, 1:46:21 PM
    Author     : viseshprasad
--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<div id="content">
    <c:if test="${requestScope.approved == 'true' }">
        <div class="alert alert-success" role="alert">
            <strong>Report Approved. Post Removed.</strong>
        </div>
    </c:if>
    <c:if test="${requestScope.disapproved == 'true' }">
        <div class="alert alert-success" role="alert">
            <strong>Report Denied. Post Restored.</strong>
        </div>
    </c:if>
</div>
<%-- Section to display Reported Questions--%>
<div id="content">
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
                        </div>
                        <div class="panel-footer text-primary">

                            <form style="display:inline; margin-right: 10px" action="PostController?action=approve&AMP;postId=${post.id}" method="post">
                                <button type="submit" class="btn btn-success" value="Approve" />Approve</form><br>
                            <form action="PostController?action=disapprove&AMP;postId=${post.id}" method="post">
                                <button type="submit" class="btn btn-danger" value="Disapprove" />Reject</form>

                        </div>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>