<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../templates/header.jsp" %>

<table style="width:100%" class="table">
  <tr>
    <th><fmt:message key="car.brand_name"/></th>
  </tr>
  <tbody>
    <c:forEach items="${brands}" var="carBrand">
      <tr class='table-row' data-href='${carBrand.id}'>
        <td><c:out value="${carBrand.brandName}"/></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="../templates/footer.jsp" %>