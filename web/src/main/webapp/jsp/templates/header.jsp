<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Car Rental</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/CarRental/list/car">Our Cars</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Add
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/CarRental/add-brand">Add new brand</a></li>
                        <li><a class="dropdown-item" href="/CarRental/add-model">Add new model</a></li>
                        <li><a class="dropdown-item" href="/CarRental/add-car">Add new car</a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        List
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/CarRental/brand-list">List all brands</a></li>
                        <li><a class="dropdown-item" href="/CarRental/model-list">List all models</a></li>
                        <li><a class="dropdown-item" href="/CarRental/car-list">List all cars</a></li>
                    </ul>
                </li>

            </ul>
            <form class="d-flex" role="search" action="/hello/search.do" method="post">
                <input class="form-control me-2" type="search" name="pname" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
