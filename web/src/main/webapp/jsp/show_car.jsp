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
            <td><c:out value="${carDto.carBrand.brandName}"/></td>
            <td><c:out value="${carDto.carModel.modelName}"/></td>
            <td><c:out value="${carDto.transmission}"/></td>
            <td><c:out value="${carDto.numberOfSeats}"/></td>
            <td><c:out value="${carDto.fuelType}"/></td>
            <td><c:out value="${carDto.fuelConsumption}"/></td>
            <td><c:out value="${carDto.enginePower}"/></td>
            <td><c:out value="${carDto.color}"/></td>
            <td><c:out value="${carDto.year}"/></td>
            <td><c:out value="${carDto.abs}"/></td>
            <td><c:out value="${carDto.cruiseControl}"/></td>
            <td><c:out value="${carDto.heatedSeats}"/></td>
            <td><c:out value="${carDto.climateControl}"/></td>
            <td><c:out value="${carDto.airBags}"/></td>
            <td><c:out value="${carDto.bodyType}"/></td>
            <td><c:out value="${carDto.price}"/></td>
        </tr>
    </tbody>

</table>

<div class="container mt-4 col-md-12">
    <div class="row">
        <c:forEach items="${images}" var="image">
            <div class="col-sm">
                <td><img src="/CarRental/car-image/${image.id}" alt="${image.id}" style="width:420px;"></td>
            </div>
        </c:forEach>
    </div>
</div>


<%@ include file="templates/footer.jsp" %>