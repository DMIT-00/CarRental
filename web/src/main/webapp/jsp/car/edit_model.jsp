<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="${pageContext.request.contextPath}/model-edit/${model.id}" modelAttribute="model">
    <div class="mb-3">
        <form:label path="carBrand.id" class="form-label"><fmt:message key="car.brand_name"/></form:label>
        <form:select path="carBrand.id" class="form-select">
            <form:options items="${brands}" />
        </form:select>
        <form:errors path="carBrand.id" cssClass="text-danger"/>
    </div>

    <div class="mb-3">
        <form:label path="modelName" class="form-label"><fmt:message key="car.model_name"/></form:label>
        <form:input path="modelName" class="form-control"/>
        <form:errors path="modelName" cssClass="text-danger"/>
    </div>

    <button type="submit" class="btn btn-primary"><fmt:message key="button.edit"/></button>
</form:form>

<%@ include file="../templates/footer.jsp" %>