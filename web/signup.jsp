<!--User signup view-->
<%@include file="header.jsp" %>

<%--Code to signup form --%>
<div class="container">

    <form class="form-signin" action="UserController?action=create" method="post">
        <h2 class="form-signin-heading">Sign up</h2>
        <input type="hidden" name="action" value="signup" />
        <label class="sr-only">Username</label>
        <input type="text" name="username" id="inputEmail" class="form-control" placeholder="Username" required autofocus>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <label for="inputPassword" class="sr-only">Confirm Password</label>
        <input type="password" name="confirm_password" id="inputPassword" class="form-control" placeholder="Confirm Password" required>
        <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
        <h1 align="center">${requestScope.msg}</h1>
    </form><br>

</div> <!-- /container -->
