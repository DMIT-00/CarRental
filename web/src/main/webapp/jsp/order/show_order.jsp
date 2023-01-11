<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table style="width:100%" class="table">
    <tr>
        <th><fmt:message key="order.status"/></th>
        <th><fmt:message key="order.date"/></th>
        <th><fmt:message key="order.hours"/></th>
        <th><fmt:message key="order.total_price"/></th>
        <th><fmt:message key="order.user_id"/></th>
        <th><fmt:message key="user.username"/></th>
        <th><fmt:message key="order.car_id"/></th>
        <th><fmt:message key="car.brand_name"/></th>
        <th><fmt:message key="car.model_name"/></th>
    </tr>

    <tbody>
        <tr class='table-row'>
            <td><c:out value="${order.orderStatus}"/></td>
            <td><c:out value="${order.startDate}"/></td>
            <td><c:out value="${order.numberOfHours}"/></td>
            <td><c:out value="${order.totalPrice}"/></td>
            <td><a href="${pageContext.request.contextPath}/user-show/${order.user.id}">${order.user.id}</a></td>
            <td><c:out value="${order.user.username}"/></td>
            <td><a href="${pageContext.request.contextPath}/car-show/${order.car.id}">${order.car.id}</a></td>
            <td><c:out value="${order.car.carModel.carBrand.brandName}"/></td>
            <td><c:out value="${order.car.carModel.modelName}"/></td>
        </tr>
    </tbody>

</table>

<%@ include file="../templates/footer.jsp" %>