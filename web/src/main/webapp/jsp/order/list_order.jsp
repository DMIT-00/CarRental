<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../templates/header.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link ${empty param.filter ? 'active' : ''}" aria-current="page" href="${pageContext.request.contextPath}/order-list"><fmt:message key="order.filter.all"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${param.filter == 'payment' ? 'active' : ''}" href="?filter=payment"><fmt:message key="order.filter.payment"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${param.filter == 'paid' ? 'active' : ''}" href="?filter=paid"><fmt:message key="order.filter.paid"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${param.filter == 'car_in_use' ? 'active' : ''}" href="?filter=car_in_use"><fmt:message key="order.filter.car_in_use"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${param.filter == 'car_returned' ? 'active' : ''}" href="?filter=car_returned"><fmt:message key="order.filter.car_returned"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${param.filter == 'closed' ? 'active' : ''}" href="?filter=closed"><fmt:message key="order.filter.closed"/></a>
    </li>
</ul>

<table style="width:100%" class="table" id="usertable">
    <tr>
        <sec:authorize access="hasRole('ROLE_MANAGER')">
            <th style="width: 40px;"></th>
        </sec:authorize>
        <th><fmt:message key="order.status"/></th>
        <th><fmt:message key="order.date"/></th>
        <th><fmt:message key="order.end_date"/></th>
        <th><fmt:message key="order.total_price"/></th>
        <th><fmt:message key="order.user_id"/></th>
        <th><fmt:message key="user.username"/></th>
        <th><fmt:message key="order.car_id"/></th>
        <th><fmt:message key="car.brand_name"/></th>
        <th><fmt:message key="car.model_name"/></th>
    </tr>

    <tbody class="clickable">
    <c:forEach items="${orders}" var="order">
        <tr class='table-row' data-href='order-show/${order.id}'>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <td><a style="text-decoration: none" href="${pageContext.request.contextPath}/order-edit/${order.id}">&#128393;</a></td>
            </sec:authorize>
            <td><c:out value="${order.orderStatus}"/></td>
            <td><c:out value="${order.startDate}"/></td>
            <td><c:out value="${order.endDate}"/></td>
            <td><c:out value="${order.totalPrice}"/></td>
            <td><a href="${pageContext.request.contextPath}/user-show/${order.user.id}">${order.user.id}</a></td>
            <td><c:out value="${order.user.username}"/></td>
            <td><a href="${pageContext.request.contextPath}/car-show/${order.car.id}">${order.car.id}</a></td>
            <td><c:out value="${order.car.carModel.carBrand.brandName}"/></td>
            <td><c:out value="${order.car.carModel.modelName}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center position-bottom">

        <%-- Disable Previous on the first page --%>
        <c:choose>
            <c:when test="${page == 1}">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1"><fmt:message key="pagination.prev"/></a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order-list?page=${page - 1}"><fmt:message key="pagination.prev"/></a></li>
            </c:otherwise>
        </c:choose>


        <li class="page-item"><a class="page-link" href=""><c:out value="${page}"/></a></li>

        <c:choose>
            <c:when test="${page == pages}">
                <li class="page-item disabled">
                    <a class="page-link" href="${pageContext.request.contextPath}/car-list?page=${page}" tabindex="-1"><fmt:message key="pagination.next"/></a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/car-list?page=${page + 1}"><fmt:message key="pagination.next"/></a>
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

<%@ include file="../templates/footer.jsp" %>