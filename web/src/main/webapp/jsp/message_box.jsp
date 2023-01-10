<%@ include file="templates/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container mt-4 col-md-12">
    <c:choose>
        <c:when test="${messageBox.type eq 'INFO'}"><div class="alert alert-info" role="alert"></c:when>
        <c:when test="${messageBox.type eq 'WARNING'}"><div class="alert alert-warning" role="alert"></c:when>
        <c:when test="${messageBox.type eq 'ERROR'}"><div class="alert alert-danger" role="alert"></c:when>
        <c:when test="${messageBox.type eq 'SUCCESS'}"><div class="alert alert-success" role="alert"></c:when>
        <c:otherwise><div class="alert alert-primary" role="alert"></c:otherwise>
    </c:choose>
        <h4 class="alert-heading"><fmt:message key="${messageBox.caption}"/></h4>
        <hr>
        <p class="mb-0"><fmt:message key="${messageBox.body}"/></p>
    </div>
</div>

<%@ include file="templates/footer.jsp" %>