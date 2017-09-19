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
        <a class="logo" href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/images/logo.png">
        </a>
        <div class="login">
            <%--<a class="btn btn-danger" href="${pageContext.request.contextPath}/place-order">我要下单</a>--%>

            <div class="btn-group">
                <c:if  test="${sessionScope.get('username')==null}">
                    <a class="btn btn-default" href="${pageContext.request.contextPath}/login">登录</a>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/register">注册</a>
                </c:if>
                <c:if  test="${sessionScope.get('username')!=null}">
                    <%--<a class="btn btn-default"  style="border: none" href="${pageContext.request.contextPath}/submitOrderSuccess#/1">用户：${sessionScope.get('name')}</a>
                    <a class="btn btn-default"  style="border: none" href="${pageContext.request.contextPath}/logout">Exit</a>--%>


                    <div class="user-info">
                        <i class="ar ar-remind-full">${sessionScope.get('name')}，欢迎您！</i>
                        <ul>
                            <li><i class="ar ar-envelope-b"></i>主页<a href="${pageContext.request.contextPath}/index.jsp"></a></li>
                            <li><i class="ar ar ar-set-c"></i>提交工程<a href="${pageContext.request.contextPath}/place-order"></a></li>
                            <li><i class="ar ar-user-full"></i>我的工程<a href="${pageContext.request.contextPath}/submitOrderSuccess#/1"></a></li>
                            <li><i class="ar ar-signout"></i>退出登录<a href="${pageContext.request.contextPath}/logout"></a></li>
                        </ul>
                    </div>


                </c:if>
            </div>
        </div>
    </div>

</header>

