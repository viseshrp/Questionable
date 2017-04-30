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
    <div class="container">

        <h3 class="text-center">Reported Questions</h3>
        <div class=table-responsive>

            <%-- Display the Reported Questions in a table --%>
            <%-- Can click buttons to approve or disapprove--%>

            <table id="posts_table" class="table table-bordered table-sm">
                <%--Column Names --%>
                <tr>
                    <th>Question</th>
                    <th>Action</th>
                </tr>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <c:forEach var="post" items="${posts}">
                    <tr>
                        <td>${post.question}</td>
                        <td><form action="PostController?action=approve&AMP;postId=${post.id}" method="post">
                                <button type="submit" class="btn btn-success" value="Approve" />Approve</form><br>
                            <form action="PostController?action=disapprove&AMP;postId=${post.id}" method="post">
                                <button type="submit" class="btn btn-danger" value="Disapprove" />Reject</form></td>                                                
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
            </table>

        </div> </div></div>