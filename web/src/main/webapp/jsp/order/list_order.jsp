<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../templates/header.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link ${empty param.filter ? 'active' : ''}" aria-current="page" href="/CarRental/order-list">All</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${param.filter == 'payment' ? 'active' : ''}" href="?filter=payment">Payment</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${param.filter == 'paid' ? 'active' : ''}" href="?filter=paid">Paid</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${param.filter == 'car_in_use' ? 'active' : ''}" href="?filter=car_in_use">Car in use</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${param.filter == 'car_returned' ? 'active' : ''}" href="?filter=car_returned">Car returned</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${param.filter == 'closed' ? 'active' : ''}" href="?filter=closed">Closed</a>
    </li>
</ul>

<table style="width:100%" class="table" id="usertable">
    <tr>
        <th><fmt:message key="order.status"/></th>
        <th><fmt:message key="order.date"/></th>
        <th><fmt:message key="order.hours"/></th>
        <th><fmt:message key="order.total_price"/></th>
        <th><fmt:message key="order.user_id"/></th>
        <th><fmt:message key="order.car_id"/></th>
    </tr>

    <tbody class="clickable">
    <c:forEach items="${orders}" var="order">
        <tr class='table-row' data-href='order-show/${order.id}'>
            <td><c:out value="${order.orderStatus}"/></td>
            <td><c:out value="${order.startDate}"/></td>
            <td><c:out value="${order.numberOfHours}"/></td>
            <td><c:out value="${order.totalPrice}"/></td>
            <td><a href="/CarRental/user-show/${order.user.id}">${order.user.id}</a></td>
            <td><a href="/CarRental/car-show/${order.car.id}">${order.car.id}</a></td>
        </tr>
    </c:forEach>
    </tbody>

    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center position-bottom">

            <%-- Disable Previous on the first page --%>
            <c:choose>
                <c:when test="${page == 1}">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1"><fmt:message key="pagination.previous"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/CarRental/order-list?page=${page - 1}"><fmt:message key="pagination.previous"/></a></li>
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