<%@ include file="../templates/header.jsp" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<form method="POST" action="${pageContext.request.contextPath}/user-login">
    <div class="container mt-2 col-md-12">
        <c:if test="${param.error != null}">
            <div id="error" class="text-danger" align="center">
                <fmt:message key="user.bad_credentials"/>
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>
    </div>

    <div class="container mt-2 col-md-12">
        <div class="row">
            <div class="col-sm">
                <input type="text" id="username" name="username" class="form-control" placeholder="<fmt:message key="user.username"/>" required
                       autofocus>
            </div>
        </div>
    </div>
    <div class="container mt-2 col-md-12">
        <div class="row">
            <div class="col-sm">
                <input type="password" id="password" name="password" class="form-control" placeholder="<fmt:message key="user.password"/>" required>
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