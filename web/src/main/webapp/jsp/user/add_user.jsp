<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="${pageContext.request.contextPath}/user-add" modelAttribute="user">
    <div class="container mt-2 col-md-12">
        <div class="row">
            <div class="col-sm">
                <form:label path="email" class="form-label"><fmt:message key="user.email"/></form:label>
                <form:input path="email" type="email" class="form-control"/>
                <form:errors path="email" cssClass="text-danger"/>
            </div>
            <div class="col-sm">
                <form:label path="username" class="form-label"><fmt:message key="user.username"/></form:label>
                <form:input path="username" type="text" class="form-control"/>
                <form:errors path="username" cssClass="text-danger"/>
            </div>
        </div>
    </div>
    <div class="container mt-2 col-md-12">
        <div class="row">
            <div class="col-sm">
                <form:label path="password" class="form-label"><fmt:message key="user.password"/></form:label>
                <form:input path="password" type="password" class="form-control"/>
                <form:errors path="password" cssClass="text-danger"/>
                <form:errors cssClass="text-danger"/>
            </div>

            <div class="col-sm">
                <label class="form-label"><fmt:message key="user.password_repeat"/></label>
                <input id="passwordRepeat" type="password" class="form-control"/>
                <span id="passwordRepeatError" class="text-danger" hidden><fmt:message key="user.password_should_match"/></span>
            </div>
        </div>
    </div>

    <div class="container mt-2 col-md-12">
        <div class="row">

            <div class="col-sm">
                <form:label path="userDetail.firstName" class="form-label"><fmt:message key="user.first_name"/></form:label>
                <form:input path="userDetail.firstName" type="text" class="form-control"/>
                <form:errors path="userDetail.firstName" cssClass="text-danger"/>
            </div>

            <div class="col-sm">
                <form:label path="userDetail.lastName" class="form-label"><fmt:message key="user.last_name"/></form:label>
                <form:input path="userDetail.lastName" type="text" class="form-control"/>
                <form:errors path="userDetail.lastName" cssClass="text-danger"/>
            </div>
        </div>
    </div>

    <div class="container mt-2 col-md-12">
        <div class="row">
            <div class="col-sm">
                <form:label path="userDetail.phoneNumber" class="form-label"><fmt:message key="user.phone_number"/></form:label>
                <form:input path="userDetail.phoneNumber" type="text" placeholder="+00000000000" class="form-control"/>
                <form:errors path="userDetail.phoneNumber" cssClass="text-danger"/>
            </div>

            <div class="col-sm">
                <form:label path="userDetail.creditCard" class="form-label"><fmt:message key="user.credit_card"/></form:label>
                <form:input path="userDetail.creditCard" type="text" class="form-control"/>
                <form:errors path="userDetail.creditCard" cssClass="text-danger"/>
            </div>

            <div class="col-sm">
                <form:label path="userDetail.birthDate" class="form-label"><fmt:message key="user.birth_date"/></form:label>
                <form:input path="userDetail.birthDate" type="date" class="form-control"/>
                <form:errors path="userDetail.birthDate" cssClass="text-danger"/>
            </div>
        </div>
    </div>

    <div class="container mt-4 col-md-12">
        <div class="row">
            <div class="col text-center">
                <button id="submit-user" type="submit" style="width:100%;" name="submit-user" class="btn btn-primary btn-lg btn-block" disabled>
                    <fmt:message key="user.submit"/>
                </button>
            </div>
        </div>
    </div>
</form:form>

<%@ include file="../templates/import_jquery.jsp" %>

<script src="${pageContext.request.contextPath}/js/password_repeat.js"></script>

<%@ include file="../templates/footer.jsp" %>