<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="${pageContext.request.contextPath}/model-list" modelAttribute="brand">
    <div class="mb-3">
        <form:label path="id" class="form-label"><fmt:message key="car.brand_name"/></form:label>
        <form:select path="id" class="form-select" onchange="this.form.submit();">
                <form:options items="${brands}" />
        </form:select>
    </div>
</form:form>

<c:if test="${models != null}">

    <table class="table">
        <c:forEach items="${models}" var="carModel">
            <tr>
                <td>
                    <c:out value="${carModel.modelName}"/>
                </td>
            </tr>
        </c:forEach>
    </table>

</c:if>


<%@ include file="../templates/footer.jsp" %>