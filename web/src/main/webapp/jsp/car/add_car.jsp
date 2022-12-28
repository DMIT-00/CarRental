<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="/CarRental/add-car" modelAttribute="car">
    <div class="container mt-4 col-md-12">
        <div class="row">
            <div class="col-sm">
                <form:label path="carModel.carBrand.id" class="form-label"><fmt:message key="car.brand_name"/></form:label>
                <form:select path="carModel.carBrand.id" class="form-select" onchange="this.form.submit();">
                    <form:options items="${brands}" />
                </form:select>
            </div>
            <div class="col-sm">
                <form:label path="carModel.id" class="form-label"><fmt:message key="car.model_name"/></form:label>
                <form:select path="carModel.id" class="form-select">
                    <form:options items="${models}" />
                </form:select>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm">
                <form:label path="fuelType" class="form-label"><fmt:message key="car.fuel_type"/></form:label>
                <form:select path="fuelType" class="form-select">
                    <form:options items="${FuelType}" />
                </form:select>
            </div>
            <div class="col-sm">
                <form:label path="transmission" class="form-label"><fmt:message key="car.transmission"/></form:label>
                <form:select path="transmission" class="form-select">
                    <form:options items="${TransmissionType}" />
                </form:select>
            </div>
            <div class="col-sm">
                <form:label path="bodyType" class="form-label"><fmt:message key="car.body_type"/></form:label>
                <form:select path="bodyType" class="form-select">
                    <form:options items="${BodyType}" />
                </form:select>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm">
                <form:label path="color" class="form-label"><fmt:message key="car.color"/></form:label>
                <form:input path="color" value="BLACK" class="form-control"/>
                <form:errors path="color" cssClass="text-danger"/>
            </div>
            <div class="col-sm">
                <form:label path="year" class="form-label"><fmt:message key="car.year"/></form:label>
                <form:input path="year" type="number" min="1800" max="2800" value="2000" class="form-control"/>
                <form:errors path="year" cssClass="text-danger"/>
            </div>
            <div class="col-sm">
                <form:label path="numberOfSeats" class="form-label"><fmt:message key="car.number_of_seats"/></form:label>
                <form:input path="numberOfSeats" type="number" min="0" max="400" value="5" class="form-control"/>
                <form:errors path="numberOfSeats" cssClass="text-danger"/>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm">
                <form:label path="enginePower" class="form-label"><fmt:message key="car.engine_power"/></form:label>
                <form:input path="enginePower" type="number" min="0" max="1000" step="0.1" class="form-control"/>
                <form:errors path="enginePower" cssClass="text-danger"/>
            </div>
            <div class="col-sm">
                <form:label path="fuelConsumption" class="form-label"><fmt:message key="car.fuel_consumption"/></form:label>
                <form:input path="fuelConsumption" type="number" min="0" max="1000" step="0.1" class="form-control"/>
                <form:errors path="fuelConsumption" cssClass="text-danger"/>
            </div>
            <div class="col-sm">
                <form:label path="price" class="form-label"><fmt:message key="car.price"/></form:label>
                <form:input path="price" type="number" step="any" value="100" class="form-control"/>
                <form:errors path="price" cssClass="text-danger"/>
            </div>
        </div>
    </div>

    <div class="container mt-2 col-md-12">
        <div class="row">
            <div class="col-sm">
                <form:checkbox path="abs" value="abs" class="form-check-input"/>
                <form:label path="abs" class="form-check-label"><fmt:message key="car.abs"/></form:label>
            </div>
            <div class="col-sm">
                <form:checkbox path="cruiseControl" value="cruiseControl" class="form-check-input"/>
                <form:label path="cruiseControl" class="form-check-label"><fmt:message key="car.cruise_control"/></form:label>
            </div>
            <div class="col-sm">
                <form:checkbox path="heatedSeats" value="heatedSeats" class="form-check-input"/>
                <form:label path="heatedSeats" class="form-check-label"><fmt:message key="car.heated_seats"/></form:label>
            </div>
            <div class="col-sm">
                <form:checkbox path="climateControl" value="climateControl" class="form-check-input"/>
                <form:label path="climateControl" class="form-check-label"><fmt:message key="car.climate_control"/></form:label>
            </div>
            <div class="col-sm">
                <form:checkbox path="airBags" value="airBags" class="form-check-input"/>
                <form:label path="airBags" class="form-check-label"><fmt:message key="car.air_bags"/></form:label>
            </div>
        </div>
    </div>

    <div class="container mt-4 col-md-12">
        <div class="row">
            <div class="col text-center">
                <button type="submit" style="width:100%;" name="submit-car" class="btn btn-primary btn-lg btn-block"><fmt:message key="add_car.submit"/></button>
            </div>
        </div>
    </div>


</form:form>

<%@ include file="../templates/footer.jsp" %>