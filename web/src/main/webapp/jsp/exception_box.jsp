<%@ include file="templates/header.jsp" %>

<div class="container mt-4 col-md-12">
    <div class="alert alert-danger" role="alert">
        <h4 class="alert-heading">${exceptionCaption}</h4>
        <hr>
        <p class="mb-0">${exceptionBody}</p>
    </div>
</div>

<%@ include file="templates/footer.jsp" %>