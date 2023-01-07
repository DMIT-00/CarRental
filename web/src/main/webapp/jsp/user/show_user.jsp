<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../templates/header.jsp" %>

<table style="width:100%" class="table">
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

    <tbody>
        <tr class='table-row'>
            <td><c:out value="${user.username}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.userDetail.firstName}"/></td>
            <td><c:out value="${user.userDetail.lastName}"/></td>
            <td><c:out value="${user.userDetail.phoneNumber}"/></td>
            <td><c:out value="${user.userDetail.creditCard}"/></td>
            <td><c:out value="${user.userDetail.birthDate}"/></td>
            <td><c:out value="${user.roles}"/></td>
        </tr>
    </tbody>

</table>

<%@ include file="../templates/footer.jsp" %>