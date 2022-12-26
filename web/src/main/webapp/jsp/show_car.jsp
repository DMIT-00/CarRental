<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="templates/header.jsp" %>

<table style="width:100%" class="table">
    <tr>
        <th>Brand</th>
        <th>Model</th>
        <th>Transmission</th>
        <th>Seats</th>
        <th>Fuel type</th>
        <th>Fuel consumption</th>
        <th>Engine power</th>
        <th>Color</th>
        <th>Year</th>
        <th>ABS</th>
        <th>Cruise control</th>
        <th>Heated Seats</th>
        <th>Climate control</th>
        <th>Air bags</th>
        <th>Body type</th>
        <th>Price</th>
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