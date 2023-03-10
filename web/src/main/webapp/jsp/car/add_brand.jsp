<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="${pageContext.request.contextPath}/brand-add" modelAttribute="brand">
    <div class="mb-3">
        <form:label path="brandName" class="form-label"><fmt:message key="car.brand_name"/></form:label>
        <form:input path="brandName" class="form-control"/>
        <form:errors path="brandName" cssClass="text-danger"/>
    </div>

    <button type="submit" class="btn btn-primary"><fmt:message key="add_brand.submit"/></button>
</form:form>

<%@ include file="../templates/footer.jsp" %>
