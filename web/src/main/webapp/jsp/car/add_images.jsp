<%@ include file="../templates/header.jsp" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="/CarRental/add-images" enctype="multipart/form-data">
    <div class="container mt-4 col-md-12">
        <div class="row">
            <div class="col-sm">
                <input type="file" name="files" class="form-control">
            </div>
            <div class="col-sm">
                <input type="file" name="files" class="form-control">
            </div>
            <div class="col-sm">
                <input type="file" name="files" class="form-control">
            </div>
            <div class="col-sm">
                <input type="file" name="files" class="form-control">
            </div>
        </div>
    </div>

    <div class="container mt-4 col-md-12">
        <div class="row">
            <div class="col text-center">
                <button type="submit" style="width:100%;" name="submit-car" class="btn btn-primary btn-lg btn-block"><fmt:message key="add_images.submit"/></button>
            </div>
        </div>
    </div>


</form:form>

<%@ include file="../templates/footer.jsp" %>