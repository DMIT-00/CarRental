<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="/CarRental/add-model" modelAttribute="model">
    <div class="mb-3">
        <form:label path="carBrand.id" class="form-label">Brand name</form:label>
        <form:select path="carBrand.id" class="form-select">
            <form:options items="${brands}" />
        </form:select>
    </div>

    <div class="mb-3">
        <form:label path="modelName" class="form-label">Model Name</form:label>
        <form:input path="modelName" class="form-control"/>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</form:form>

<%@ include file="../templates/footer.jsp" %>