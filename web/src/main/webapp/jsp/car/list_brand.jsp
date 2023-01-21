<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../templates/header.jsp" %>

<table style="width:100%" class="table">
  <tr>
    <sec:authorize access="hasRole('ROLE_MANAGER')">
      <th style="width: 40px;"></th>
    </sec:authorize>
    <th><fmt:message key="car.brand_name"/></th>
  </tr>
  <tbody>
    <c:forEach items="${brands}" var="carBrand">
      <tr class='table-row' data-href='${carBrand.id}'>
        <sec:authorize access="hasRole('ROLE_MANAGER')">
          <td><a style="text-decoration: none" href="${pageContext.request.contextPath}/brand-edit/${carBrand.id}">&#128393;</a></td>
        </sec:authorize>
        <td><c:out value="${carBrand.brandName}"/></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="../templates/footer.jsp" %>