<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../templates/header.jsp" %>

<table style="width:100%" class="table">
  <tr>
    <th>Name</th>
  </tr>
  <c:forEach items="${cars}" var="car">
  <tr>
    <td><c:out value="${car}"/></td>
  </tr>
  </c:forEach>
</table>

<%@ include file="../templates/footer.jsp" %>