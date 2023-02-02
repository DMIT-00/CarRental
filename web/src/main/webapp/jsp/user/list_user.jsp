<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../templates/header.jsp" %>

<ul class="nav nav-tabs">
  <li class="nav-item">
      <a class="nav-link ${empty param.filter ? 'active' : ''}" aria-current="page" href="${pageContext.request.contextPath}/user-list"><fmt:message key="user.filter.all"/></a>
  </li>
  <li class="nav-item">
      <a class="nav-link ${param.filter == 'active' ? 'active' : ''}" href="?filter=active"><fmt:message key="user.filter.active"/></a>
  </li>
  <li class="nav-item">
    <a class="nav-link ${param.filter == 'blocked' ? 'active' : ''}" href="?filter=blocked"><fmt:message key="user.filter.blocked"/></a>
  </li>
  <li class="nav-item">
      <a class="nav-link ${param.filter == 'payment' ? 'active' : ''}" href="?filter=payment"><fmt:message key="user.filter.payment"/></a>
  </li>
  <li class="nav-item">
      <a class="nav-link ${param.filter == 'paid' ? 'active' : ''}" href="?filter=paid"><fmt:message key="user.filter.paid"/></a>
  </li>
  <li class="nav-item">
    <a class="nav-link ${param.filter == 'car_in_use' ? 'active' : ''}" href="?filter=car_in_use"><fmt:message key="user.filter.car_in_use"/></a>
  </li>
  <li class="nav-item">
    <a class="nav-link ${param.filter == 'car_returned' ? 'active' : ''}" href="?filter=car_returned"><fmt:message key="user.filter.car_returned"/></a>
  </li>
  <li class="nav-item">
    <a class="nav-link ${param.filter == 'closed' ? 'active' : ''}" href="?filter=closed"><fmt:message key="user.filter.closed"/></a>
  </li>
</ul>

<table style="width:100%" class="table" id="usertable">
  <tr>
    <security:authorize access="hasRole('ROLE_ADMIN')">
      <th style="width: 40px;"></th>
    </security:authorize>
    <th><fmt:message key="user.username"/></th>
    <th><fmt:message key="user.email"/></th>
    <th><fmt:message key="user.first_name"/></th>
    <th><fmt:message key="user.last_name"/></th>
    <th><fmt:message key="user.phone_number"/></th>
    <th><fmt:message key="user.credit_card"/></th>
    <th><fmt:message key="user.birth_date"/></th>
    <th><fmt:message key="user.roles"/></th>
    <th><fmt:message key="user.locked"/></th>
    <th><fmt:message key="order.orders"/></th>
  </tr>

  <tbody class="clickable">
    <c:forEach items="${users}" var="user">
      <tr class='table-row' data-href='user-show/${user.id}'>
        <security:authorize access="hasRole('ROLE_ADMIN')">
          <td><a style="text-decoration: none" href="${pageContext.request.contextPath}/user-edit/${user.id}">&#128393;</a></td>
        </security:authorize>
        <td><c:out value="${user.username}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.userDetail.firstName}"/></td>
        <td><c:out value="${user.userDetail.lastName}"/></td>
        <td><c:out value="${user.userDetail.phoneNumber}"/></td>
        <td><c:out value="${user.userDetail.creditCard}"/></td>
        <td><c:out value="${user.userDetail.birthDate}"/></td>
        <td><c:out value="${user.roles}"/></td>
        <td><c:out value="${user.locked}"/></td>
        <td><a href="${pageContext.request.contextPath}/order-list?user=${user.id}">${user.orders.size()}</a></td>
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
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/car-list?page=${page - 1}"><fmt:message key="pagination.prev"/></a></li>
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

<%@ include file="../templates/import_jquery.jsp" %>

<script src="${pageContext.request.contextPath}/js/clickable_table.js"></script>

<%@ include file="../templates/footer.jsp" %>