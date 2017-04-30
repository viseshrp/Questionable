<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <div class="comments-list">
                            <c:forEach var="comment" items="${comments}">
                                <div class="media">
                                    <p class="pull-right"><small style="font-size:85%;"><strong>Created on ${comment.created_date} 
                                                &nbsp;--&nbsp; Modified on ${comment.modified_date}</strong></small></p>
                                    <a class="media-left" href="#">
                                        <img src="http://lorempixel.com/40/40/people/1/">
                                    </a>
                                    <div class="media-body">
                                        <h4 class="media-heading user_name"><small style="font-size:85%;">${comment.user.user_name}</small></h4>
                                        <h4>${comment.content}</h4>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
        <form class="form-signin" action="PostController?action=comment&AMP;postId=${post.id}" method="post">
        <input type="hidden" name="action" value="comment" />
        <label for="exampleTextarea" class="sr-only">Comment</label>
        <textarea name="comment" class="form-control" id="exampleTextarea" rows="3" placeholder="Comment" maxlength="300" required=""></textarea>        <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        <h1 align="center">${requestScope.msg}</h1>

        </div>
    </div>
