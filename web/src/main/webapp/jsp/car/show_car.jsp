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
            <th><fmt:message key="order.orders"/></th>
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
                <td><a href="${pageContext.request.contextPath}/order-list?car=${car.id}">${car.orders.size()}</a></td>
            </sec:authorize>
        </tr>
    </tbody>

</table>

<div class="container mt-4 col-md-12">
    <div class="row">
        <c:forEach items="${images}" var="image">
            <div class="col-sm">
                <td><img src="${pageContext.request.contextPath}/car-image/${image}" alt="${image}" style="width:420px;"></td>
            </div>
        </c:forEach>
    </div>
</div>

<security:authorize access="isAuthenticated()">
<form:form method="POST" modelAttribute="order">
    <div class="container">
        <div class="row">
            <div class="col-sm">
                <form:label path="startDate" class="form-label"><fmt:message key="order.date"/></form:label>
                <form:input path="startDate" type="datetime-local"
                            value=''
                            class="form-control"/>
                <form:errors path="startDate" cssClass="text-danger"/>
            </div>
            <div class="col-sm">
                <form:label path="endDate" class="form-label"><fmt:message key="order.end_date"/></form:label>
                <form:input path="endDate" type="datetime-local"
                            value=''
                            class="form-control"/>
                <form:errors path="endDate" cssClass="text-danger"/>
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    <script type="text/javascript">
        function formatDate(time) {
             var d = new Date(),
                 month = '' + (d.getMonth() + 1),
                 day = '' + (d.getDate() + 1),
                 year = d.getFullYear();

             if (month.length < 2) month = '0' + month;
             if (day.length < 2) day = '0' + day;

             return [year, month, day].join('-') + " " + time;
         }

        $( document ).ready(function() {
           $("#startDate").attr("value", formatDate("10:00:00"));
        });

        $( document ).ready(function() {
           $("#endDate").attr("value", formatDate("14:00:00"));
        });
    </script>

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
                <button type="submit" style="width:100%;" name="order-car" class="btn btn-primary btn-lg btn-block"><fmt:message key="order.submit"/></button>
            </div>
        </div>
    </div>
</form:form>
</security:authorize>

<security:authorize access="isAnonymous()">
    <div class="container mt-4 col-md-12">
        <div class="alert alert-light" role="alert">
            <fmt:message key="order.login_to_order"/>
        </div>
    </div>
</security:authorize>

<%@ include file="../templates/footer.jsp" %>