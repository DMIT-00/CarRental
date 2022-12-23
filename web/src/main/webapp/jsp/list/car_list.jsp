<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../templates/header.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<table style="width:100%" class="table" id="cartable">
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

  <c:forEach items="${cars}" var="car">
  <tr class='table-row' data-href='show-car/${car.id}'>
    <td><c:out value="${car.brandName}"/></td>
    <td><c:out value="${car.modelName}"/></td>
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
  </c:forEach>

  </tbody>

    <script type="text/javascript">
        $(document).ready(function($) {
            $(".table-row").click(function() {
                window.document.location = $(this).data("href");
            });
        });
    </script>

</table>

<%@ include file="../templates/footer.jsp" %>