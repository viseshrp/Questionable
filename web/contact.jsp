<!--Contact Us View to get user feedback
-->
<%@include file="header.jsp" %>

<%--Code to signup form --%>
<div class="container">

    <c:if test="${requestScope.contacted == 'true' }">
        <div class="alert alert-success" role="alert">
        <strong>Sent!</strong> We'll get back to you soon :)
      </div>
    </c:if>
    
    <form class="form-signin" action="UserController?action=contact" method="post">
        <h2 class="form-signin-heading">Contact Us</h2>
        <input type="hidden" name="action" value="contact" />
        <label class="sr-only">Username</label>
        <input type="text" name="username" id="inputEmail" class="form-control" placeholder="Username" required autofocus>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="exampleTextarea" class="sr-only">Message</label>
        <textarea name="message" class="form-control" id="exampleTextarea" rows="3" placeholder="Message"></textarea>        <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        <h1 align="center">${requestScope.msg}</h1>
    </form><br>

</div> <!-- /container -->
