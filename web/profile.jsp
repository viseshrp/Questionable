<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
<!--User profile View-->
    <div id="content">
        <div class="container">
            <h1>Profile</h1><br>
            <div class=table-responsive>
                    <table class="table table-condensed table-hover table-nonfluid">
                        <tbody>
                        <tr>
                            <th scope=row>Username:</th>
                            <td>${sessionScope.theUser.user_name}</td>
                        </tr>
                        <tr>
                            <th scope=row>Email:</th>
                            <td>${sessionScope.theUser.email}</td>
                        </tr>
                        <tr>
                            <th scope=row>Type:</th>
                            <td>${sessionScope.theUser.type}</td>
                        </tr>
                        <tr>
                            <th scope=row>Join Date:</th>
                            <td>${sessionScope.theUser.reg_date}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                        </tr>

                        </tbody>
                    </table>
            </div>
        </div>
    </div>
