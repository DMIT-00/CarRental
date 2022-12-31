<%@ include file="../templates/header.jsp" %>

<form method="POST" action="${pageContext.request.contextPath}/login">
    <div class="container mt-2 col-md-12">
        <div class="row">
            <div class="col-sm">
                <input type="text" id="username" name="username" class="form-control" placeholder="<fmt:message key="add_user.username"/>" required
                       autofocus>
            </div>
        </div>
    </div>
    <div class="container mt-2 col-md-12">
        <div class="row">
            <div class="col-sm">
                <input type="password" id="password" name="password" class="form-control" placeholder="<fmt:message key="add_user.password"/>" required>
            </div>
        </div>
    </div>


    <div class="container mt-4 col-md-12">
        <div class="row">
            <div class="col text-center">
                <button style="width:100%;" class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="navbar.login"/></button>
            </div>
        </div>
    </div>


</form>

<%@ include file="../templates/footer.jsp" %>