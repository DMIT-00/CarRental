<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Car Rental</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <!-- My CSS -->
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg" style="background-color: rgba(0, 0, 0, 0.2);">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/"><fmt:message key="navbar.home"/></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/car-list"><fmt:message key="navbar.our_cars"/></a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <fmt:message key="navbar.add"/>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/brand-add"><fmt:message key="navbar.add_brand"/></a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/model-add"><fmt:message key="navbar.add_model"/></a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/car-add"><fmt:message key="navbar.add_car"/></a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <fmt:message key="navbar.list"/>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/brand-list"><fmt:message key="navbar.list_brands"/></a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/model-list"><fmt:message key="navbar.list_models"/></a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/car-list"><fmt:message key="navbar.list_cars"/></a></li>
                        <div class="dropdown-divider"></div>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user-list"><fmt:message key="navbar.list_user"/></a></li>
                        <div class="dropdown-divider"></div>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/order-list"><fmt:message key="navbar.list_order"/></a></li>
                    </ul>
                </li>

            </ul>

            <ul class="navbar-nav mb-2 mb-lg-0">
                <li class="d-flex nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <fmt:message key="language.language"/>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="?lang=en"><fmt:message key="language.en"/></a></li>
                        <li><a class="dropdown-item" href="?lang=ru"><fmt:message key="language.ru"/></a></li>
                    </ul>
                </li>

                <li class="d-flex nav-item dropdown">
                    <security:authorize access="isAnonymous()">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false"><fmt:message key="navbar.guest"/></a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user-login">
                                <fmt:message key="navbar.login"/></a>
                            </li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user-add">
                                <fmt:message key="navbar.register"/></a>
                            </li>
                        </ul>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false"><security:authentication property="name"/></a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user-profile">
                                <fmt:message key="navbar.profile"/></a>
                            </li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user-logout">
                                <fmt:message key="navbar.logout"/></a>
                            </li>
                        </ul>
                    </security:authorize>
                </li>
            </ul>
        </div>
    </div>
</nav>
