<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="templates/header.jsp" %>

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


<%@ include file="templates/footer.jsp" %>