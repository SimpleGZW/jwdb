<%--
  Created by IntelliJ IDEA.
  User: dushang
  Date: 08/04/16
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<header class="navbar-fixed-top">

    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/fonts/Art-Reactor-master/dist/art-reactor.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/common/header.css" rel="stylesheet">

    <div class="head-container">
        <a class="logo" href="#">
            <img src="${pageContext.request.contextPath}/images/logo.png">
        </a>
        <div class="login">

            <div class="btn-group">
                <c:if  test="${sessionScope.get('username')!=null}">
                    <a class="btn btn-default"  style="border: none" href="${pageContext.request.contextPath}/queryOrders#/1">操作员：${sessionScope.get('name')}</a>
                    <a class="btn btn-default"  style="border: none" href="${pageContext.request.contextPath}/logout">Exit</a>
                </c:if>
            </div>
        </div>
    </div>

</header>

