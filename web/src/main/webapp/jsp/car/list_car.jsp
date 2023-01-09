<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<table style="width:100%" class="table" id="cartable">
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

  <tbody class="clickable">
    <c:forEach items="${cars}" var="car">
      <tr class='table-row' data-href='show-car/${car.id}'>
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
    </c:forEach>
  </tbody>

  <nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">

      <%-- Disable Previous on the first page --%>
      <c:choose>
            <c:when test="${page == 1}">
              <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1"><fmt:message key="pagination.previous"/></a>
              </li>
            </c:when>
            <c:otherwise>
              <li class="page-item"><a class="page-link" href="/CarRental/car-list?page=${page - 1}"><fmt:message key="pagination.previous"/></a></li>
            </c:otherwise>
      </c:choose>


      <li class="page-item"><a class="page-link" href=""><c:out value="${page}"/></a></li>

      <c:choose>
        <c:when test="${page == pages}">
          <li class="page-item disabled">
            <a class="page-link" href="/CarRental/car-list?page=${page}" tabindex="-1"><fmt:message key="pagination.next"/></a>
          </li>
        </c:when>
        <c:otherwise>
          <li class="page-item">
            <a class="page-link" href="/CarRental/car-list?page=${page + 1}"><fmt:message key="pagination.next"/></a>
          </li>
        </c:otherwise>
      </c:choose>

    </ul>
  </nav>

  <script type="text/javascript">
        $(document).ready(function($) {
            $(".table-row").click(function() {
                window.document.location = $(this).data("href");
            });
        });
  </script>

</table>

<%@ include file="../templates/footer.jsp" %>