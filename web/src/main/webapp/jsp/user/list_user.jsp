<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../templates/header.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<table style="width:100%" class="table" id="usertable">
  <tr>
    <th><fmt:message key="user.username"/></th>
    <th><fmt:message key="user.email"/></th>
    <th><fmt:message key="user.first_name"/></th>
    <th><fmt:message key="user.last_name"/></th>
    <th><fmt:message key="user.phone_number"/></th>
    <th><fmt:message key="user.credit_card"/></th>
    <th><fmt:message key="user.birth_date"/></th>
    <th><fmt:message key="user.roles"/></th>
  </tr>

  <tbody class="clickable">
    <c:forEach items="${users}" var="user">
      <tr class='table-row' data-href='user-show/${user.id}'>
        <td><c:out value="${user.username}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.userDetail.firstName}"/></td>
        <td><c:out value="${user.userDetail.lastName}"/></td>
        <td><c:out value="${user.userDetail.phoneNumber}"/></td>
        <td><c:out value="${user.userDetail.creditCard}"/></td>
        <td><c:out value="${user.userDetail.birthDate}"/></td>
        <td><c:out value="${user.roles}"/></td>
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