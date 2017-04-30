<%-- 
    Document   : reportques
    Created on : Feb 23, 2017, 1:46:21 PM
    Author     : viseshprasad
--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>

<%-- Section to display Reported Questions--%>
<div id="content">
    <div class="container">

        <c:if test="${requestScope.approved == 'true' }">
            <div class="alert alert-success" role="alert">
                <strong>Report Approved. Post Removed.</strong>
            </div>
        </c:if>
        <c:if test="${requestScope.rejected == 'true' }">
            <div class="alert alert-success" role="alert">
                <strong>Report Approved. Post Removed.</strong>
            </div>
        </c:if>

        <h3 class="text-center">Reported Questions</h3>
        <div class=table-responsive>

            <%-- Display the Reported Questions in a table --%>
            <%-- Can click buttons to approve or disapprove--%>

            <table id="posts_table" class="table table-hover table-nonfluid">
                <%--Column Names --%>
                <tr>
                    <th>Question</th>
                    <th>Action</th>
                </tr>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <c:forEach var="post" items="${post}">
                    <tr>
                        <td>${post.question}</td>
                        <td><form action="PostController?action=approve&AMP;PostCode=${post.id}" method="post">
                                <button type="submit" class="btn btn-success" value="Approve" /></form>
                            <form action="PostController?action=disapprove&AMP;PostCode=${post.id}" method="post">
                                <button type="submit" class="btn btn-danger" value="Disapprove" /></form></td>                                                
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
            </table>

        </div> </div></div>