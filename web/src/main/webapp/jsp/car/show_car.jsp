<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<table style="width:100%" class="table">
    <tr>
        <th><fmt:message key="car.brand_name"/></th>
        <th><fmt:message key="car.model_name"/></th>
        <th><fmt:message key="car.transmission"/></th>
        <th><fmt:message key="car.number_of_seats"/></th>
        <th><fmt:message key="car.fuel_type"/></th>
        <th><fmt:message key="car.fuel_consumption"/></th>
        <th><fmt:message key="car.engine_power"/></th>
        <th><fmt:message key="car.color"/></th>
        <th><fmt:message key="car.year"/></th>
        <th><fmt:message key="car.abs"/></th>
        <th><fmt:message key="car.cruise_control"/></th>
        <th><fmt:message key="car.heated_seats"/></th>
        <th><fmt:message key="car.climate_control"/></th>
        <th><fmt:message key="car.air_bags"/></th>
        <th><fmt:message key="car.body_type"/></th>
        <th><fmt:message key="car.price"/></th>
        <sec:authorize access="hasRole('ROLE_MANAGER')">
            <th><fmt:message key="order.active_order"/></th>
        </sec:authorize>
    </tr>

    <tbody>
        <tr class='table-row'>
            <td><c:out value="${car.carModel.carBrand.brandName}"/></td>
            <td><c:out value="${car.carModel.modelName}"/></td>
            <td><c:out value="${car.transmission}"/></td>
            <td><c:out value="${car.numberOfSeats}"/></td>
            <td><c:out value="${car.fuelType}"/></td>
            <td><c:out value="${car.fuelConsumption}"/></td>
            <td><c:out value="${car.enginePower}"/></td>
            <td><c:out value="${car.color}"/></td>
            <td><c:out value="${car.year}"/></td>
            <td><c:out value="${car.abs}"/></td>
            <td><c:out value="${car.cruiseControl}"/></td>
            <td><c:out value="${car.heatedSeats}"/></td>
            <td><c:out value="${car.climateControl}"/></td>
            <td><c:out value="${car.airBags}"/></td>
            <td><c:out value="${car.bodyType}"/></td>
            <td><c:out value="${car.price}"/></td>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <c:choose>
                    <c:when test="${car.activeOrder != null}">
                        <td><a href="/CarRental/show-order/${car.activeOrder.id}">${car.activeOrder.id}</a></td>
                    </c:when>
                    <c:otherwise>
                        <td>-</td>
                    </c:otherwise>
                </c:choose>
            </sec:authorize>
        </tr>
    </tbody>

</table>

<div class="container mt-4 col-md-12">
    <div class="row">
        <c:forEach items="${images}" var="image">
            <div class="col-sm">
                <td><img src="/CarRental/car-image/${image}" alt="${image}" style="width:420px;"></td>
            </div>
        </c:forEach>
    </div>
</div>

<form:form method="POST" modelAttribute="order">
    <div class="container">
        <div class="row">
            <div class="col-sm">
                <form:label path="startDate" class="form-label"><fmt:message key="order.date"/></form:label>
                <form:input path="startDate" type="timestamp" class="form-control"/>
                <form:errors path="startDate" cssClass="text-danger"/>
            </div>
            <div class="col-sm">
                <form:label path="numberOfHours" class="form-label"><fmt:message key="order.hours"/></form:label>
                <form:input path="numberOfHours" type="number" value="1" class="form-control"/>
                <form:errors path="numberOfHours" cssClass="text-danger"/>
            </div>
        </div>
    </div>

    <div class="container mt-4 col-md-12">
        <div class="row">
            <div class="col text-center">
                <button type="submit" style="width:100%;" name="order-car" class="btn btn-primary btn-lg btn-block"><fmt:message key="order.submit"/></button>
            </div>
        </div>
    </div>


</form:form>


<%@ include file="../templates/footer.jsp" %>