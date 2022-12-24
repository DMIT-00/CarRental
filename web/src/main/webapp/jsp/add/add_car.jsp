<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="/CarRental/add-car" modelAttribute="car">
    <div class="container mt-4 col-md-12">
        <div class="row">
            <div class="col-sm">
                <form:label path="carBrand.id" class="form-label">Brand name</form:label>
                <form:select path="carBrand.id" class="form-select" onchange="this.form.submit();">
                    <form:options items="${brands}" />
                </form:select>
            </div>
            <div class="col-sm">
                <form:label path="carModel.id" class="form-label">Model name</form:label>
                <form:select path="carModel.id" class="form-select">
                    <form:options items="${models}" />
                </form:select>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm">
                <form:label path="fuelType" class="form-label">Fuel type</form:label>
                <form:select path="fuelType" class="form-select">
                    <form:options items="${FuelType}" />
                </form:select>
            </div>
            <div class="col-sm">
                <form:label path="transmission" class="form-label">Transmission type</form:label>
                <form:select path="transmission" class="form-select">
                    <form:options items="${TransmissionType}" />
                </form:select>
            </div>
            <div class="col-sm">
                <form:label path="bodyType" class="form-label">Body type</form:label>
                <form:select path="bodyType" class="form-select">
                    <form:options items="${BodyType}" />
                </form:select>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm">
                <form:label path="color" class="form-label">Color</form:label>
                <form:input path="color" value="BLACK" class="form-control"/>
            </div>
            <div class="col-sm">
                <form:label path="year" class="form-label">Year of manufacture</form:label>
                <form:input path="year" type="number" min="1800" max="2800" value="2000" class="form-control"/>
            </div>
            <div class="col-sm">
                <form:label path="numberOfSeats" class="form-label">Number of seats</form:label>
                <form:input path="numberOfSeats" type="number" min="0" max="400" value="5" class="form-control"/>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm">
                <form:label path="enginePower" class="form-label">Engine power</form:label>
                <form:input path="enginePower" type="number" min="0" max="1000" step="0.1" class="form-control"/>
            </div>
            <div class="col-sm">
                <form:label path="fuelConsumption" class="form-label">Fuel consumption</form:label>
                <form:input path="fuelConsumption" type="number" min="0" max="1000" step="0.1" class="form-control"/>
            </div>
            <div class="col-sm">
                <form:label path="price" class="form-label">Price</form:label>
                <form:input path="price" type="number" step="any" value="100" class="form-control"/>
            </div>
        </div>
    </div>

    <div class="container mt-2 col-md-12">
        <div class="row">
            <div class="col-sm">
                <form:checkbox path="abs" value="abs" class="form-check-input"/>
                <form:label path="abs" class="form-check-label">ABS</form:label>
            </div>
            <div class="col-sm">
                <form:checkbox path="abs" value="abs" class="form-check-input"/>
                <form:label path="abs" class="form-check-label">Cruise control</form:label>
            </div>
            <div class="col-sm">
                <form:checkbox path="heatedSeats" value="heatedSeats" class="form-check-input"/>
                <form:label path="heatedSeats" class="form-check-label">Heated seats</form:label>
            </div>
            <div class="col-sm">
                <form:checkbox path="climateControl" value="climateControl" class="form-check-input"/>
                <form:label path="climateControl" class="form-check-label">Climate control</form:label>
            </div>
            <div class="col-sm">
                <form:checkbox path="airBags" value="airBags" class="form-check-input"/>
                <form:label path="airBags" class="form-check-label">Airbags</form:label>
            </div>
        </div>
    </div>

    <div class="container mt-4 col-md-12">
        <div class="row">
            <div class="col text-center">
                <button type="submit" style="width:100%;" name="submit-car" class="btn btn-primary btn-lg btn-block">Add this car</button>
            </div>
        </div>
    </div>


</form:form>

<%@ include file="../templates/footer.jsp" %>