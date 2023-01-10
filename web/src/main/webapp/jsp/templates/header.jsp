<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Car Rental</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- My CSS -->
    <link href="/CarRental/css/main.css" rel="stylesheet">
</head>
<body>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/CarRental/"><fmt:message key="navbar.home"/></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/CarRental/car-list"><fmt:message key="navbar.our_cars"/></a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <fmt:message key="navbar.add"/>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/CarRental/brand-add"><fmt:message key="navbar.add_brand"/></a></li>
                        <li><a class="dropdown-item" href="/CarRental/model-add"><fmt:message key="navbar.add_model"/></a></li>
                        <li><a class="dropdown-item" href="/CarRental/car-add"><fmt:message key="navbar.add_car"/></a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <fmt:message key="navbar.list"/>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/CarRental/brand-list"><fmt:message key="navbar.list_brands"/></a></li>
                        <li><a class="dropdown-item" href="/CarRental/model-list"><fmt:message key="navbar.list_models"/></a></li>
                        <li><a class="dropdown-item" href="/CarRental/car-list"><fmt:message key="navbar.list_cars"/></a></li>
                        <div class="dropdown-divider"></div>
                        <li><a class="dropdown-item" href="/CarRental/user-list"><fmt:message key="navbar.list_user"/></a></li>
                        <div class="dropdown-divider"></div>
                        <li><a class="dropdown-item" href="/CarRental/order-list"><fmt:message key="navbar.list_order"/></a></li>
                    </ul>
                </li>

            </ul>

            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="d-flex nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <fmt:message key="language.language"/>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="?lang=en"><fmt:message key="language.en"/></a></li>
                        <li><a class="dropdown-item" href="?lang=ru"><fmt:message key="language.ru"/></a></li>
                    </ul>
                </li>

                <security:authorize access="isAnonymous()">
                    <a class="nav-link disabled"><fmt:message key="navbar.welcome"/>,&nbsp;<fmt:message key="navbar.guest"/>&nbsp;</a>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <a class="nav-link disabled"><fmt:message key="navbar.welcome"/>,&nbsp;<security:authentication property="name"/>&nbsp;</a>
                </security:authorize>

                <li class="nav-item">
                    <security:authorize access="isAnonymous()">
                        <a class="nav-link" href="${pageContext.request.contextPath}/user-login"><fmt:message key="navbar.login"/></a>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
                        <a class="nav-link" href="${pageContext.request.contextPath}/user-logout"><fmt:message key="navbar.logout"/></a>
                    </security:authorize>
                </li>

                <security:authorize access="isAnonymous()">
                    <a class="nav-link" href="/CarRental/user-add"><fmt:message key="navbar.register"/></a>
                </security:authorize>

            </ul>

            <form class="d-flex" role="search" action="/hello/search.do" method="post">
                <input class="form-control me-2" type="search" name="pname" placeholder="<fmt:message key="navbar.search"/>" aria-label="<fmt:message key="navbar.search"/>">
                <button class="btn btn-outline-success" type="submit"><fmt:message key="navbar.search"/></button>
            </form>


        </div>
    </div>
</nav>
