<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<table style="width:100%" class="table">
    <tr>
        <th><fmt:message key="order.status"/></th>
        <th><fmt:message key="order.date"/></th>
        <th><fmt:message key="order.end_date"/></th>
        <th><fmt:message key="order.total_price"/></th>
        <th><fmt:message key="order.user_id"/></th>
        <th><fmt:message key="user.username"/></th>
        <th><fmt:message key="order.car_id"/></th>
        <th><fmt:message key="car.brand_name"/></th>
        <th><fmt:message key="car.model_name"/></th>
    </tr>

    <tbody>
        <tr class='table-row'>
            <td><c:out value="${order.orderStatus}"/></td>
            <td><c:out value="${order.startDate}"/></td>
            <td><c:out value="${order.endDate}"/></td>
            <td><c:out value="${order.totalPrice}"/></td>
            <td><a href="${pageContext.request.contextPath}/user-show/${order.user.id}">${order.user.id}</a></td>
            <td><c:out value="${order.user.username}"/></td>
            <td><a href="${pageContext.request.contextPath}/car-show/${order.car.id}">${order.car.id}</a></td>
            <td><c:out value="${order.car.carModel.carBrand.brandName}"/></td>
            <td><c:out value="${order.car.carModel.modelName}"/></td>
        </tr>
    </tbody>

</table>

<form:form method="POST" modelAttribute="order">
<div class="container">
    <div class="col-sm">
        <div class="col-sm">
            <form:label path="orderStatus" class="form-label"><fmt:message key="order.status"/></form:label>
            <form:select path="orderStatus" class="form-select">
                <form:options items="${OrderStatus}" />
            </form:select>
        </div>
    </div>

    <div class="row">
        <div class="col-sm">
            <form:label path="startDate" class="form-label"><fmt:message key="order.date"/></form:label>
            <form:input path="startDate" type="datetime-local"
                        class="form-control"/>
            <form:errors path="startDate" cssClass="text-danger"/>
        </div>
        <div class="col-sm">
            <form:label path="endDate" class="form-label"><fmt:message key="order.end_date"/></form:label>
            <form:input path="endDate" type="datetime-local"
                        class="form-control"/>
            <form:errors path="endDate" cssClass="text-danger"/>
        </div>
    </div>
</div>

<c:if test="${userBusyError != null}">
    <div id="userError" class="text-danger" align="center">
        <fmt:message key="user.busy"/>
    </div>
</c:if>
<c:if test="${carBusyError != null}">
    <div id="carError" class="text-danger" align="center">
        <fmt:message key="car.busy"/>
    </div>
</c:if>

<div class="container mt-4 col-md-12">
    <div class="row">
        <div class="col text-center">
            <button type="submit" style="width:100%;" name="edit-order" class="btn btn-primary btn-lg btn-block"><fmt:message key="button.edit"/></button>
        </div>
    </div>
</div>
</form:form>

<%@ include file="../templates/footer.jsp" %>