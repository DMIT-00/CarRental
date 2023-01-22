<%@ include file="templates/header.jsp" %>

<div id="carouselExampleIndicators" class="carousel slide mt-5">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="${pageContext.request.contextPath}/img/slide1.jpg" class="d-block w-100" alt="...">
            <div class="position-absolute top-0 mt-5 mx-5">
                <h1 class="display-1 text-dark"><fmt:message key="advertisement.page.one"/></h1>
                <p class="text-dark"><fmt:message key="advertisement.page.one.full"/></p>
            </div>
        </div>
        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/img/slide2.jpg" class="d-block w-100" alt="...">
            <div class="position-absolute top-0 end-0 mt-5 mx-5">
                <h1 class="display-1 text-light"><fmt:message key="advertisement.page.two"/></h1>
                <p class="text-light"><fmt:message key="advertisement.page.two.full"/></p>
            </div>
        </div>
        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/img/slide3.jpg" class="d-block w-100" alt="...">
            <div class="position-absolute bottom-0 end-0 mt-5 mx-5">
                <h1 class="display-1 text-body"><fmt:message key="advertisement.page.three"/></h1>
                <p class="text-body"><fmt:message key="advertisement.page.three.full"/></p>
            </div>
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>

<%@ include file="templates/footer.jsp" %>