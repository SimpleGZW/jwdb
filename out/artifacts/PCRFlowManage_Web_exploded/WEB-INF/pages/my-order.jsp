<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/icon.png" type="image/x-icon"/>
    <title>降温计算流程管理平台</title>
</head>
<body>
<!--公共头部开始-->
<jsp:include page="/headerNoSearchbox"></jsp:include>
<!--公共头部结束-->

<!--页面主体部分开始-->
<section class="main-part">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jPages/css/jPages.css">
    <link href="${pageContext.request.contextPath}/assets/font-awesome-4.6.2/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/my-order.css" rel="stylesheet">
    <div>
        <div id="myOrder"></div>

        <div class="order-list">
            <div>
                <div class="table-caption">待处理工程</div>
                <table class="table table-bordered text-center">
                    <thead>
                    <tr><td>工程编号</td><td>计算工程名称</td><td>计算类型</td><td>状态</td><td>操作时间</td><td>操作</td></tr>
                    </thead>
                    <tbody id="itemContainer2">
                    <c:forEach items="${processedOrders}" var="processedOrder">
                        <tr>
                            <td>${processedOrder.orderno}</td>
                            <td>${processedOrder.projectname}</td>
                            <td>${processedOrder.projecttype}</td>
                            <td>${processedOrder.orderstatus.statusdescription}</td>
                            <td>
                                <fmt:formatDate value="${processedOrder.opttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td><a href="${pageContext.request.contextPath}/getOrderDetails?orderid=${processedOrder.orderid}">处理</a>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="holder2"></div>
            </div>

            <div>
                <!--订单结果下载-->
                <div class="table-caption">已完成工程</div>
                <table class="table table-bordered text-center">
                    <thead>
                    <tr><td>工程编号</td><td>计算工程名称</td><td>计算类型</td><td>完成时间</td><td>操作</td></tr>
                    </thead>
                    <tbody id="itemContainer3">
                    <c:forEach items="${finishedOrders}" var="finishedOrder">
                        <tr>
                            <td>${finishedOrder.orderno}</td>
                            <td>${finishedOrder.projectname}</td>
                            <td>${finishedOrder.projecttype}</td>
                            <td><fmt:formatDate value="${finishedOrder.opttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td><a href="${pageContext.request.contextPath}/orderInfo?orderid=${finishedOrder.orderid}">查看详情</a>
                        </tr>
                    </c:forEach></tbody>
                </table>

                <div class="holder3"></div>
            </div>
        </div>
    </div>

</section>
<!--页面主体部分结束-->

<!--公共尾部开始-->
<jsp:include page="/footer"></jsp:include>
<!--公共尾部结束-->
<script>
    webRoot="${pageContext.request.contextPath}";
</script>
    <script src="${pageContext.request.contextPath}/assets/js/common/require.min.js" rel="script" defer data-main="${pageContext.request.contextPath}/assets/js/my-order-main"></script>
</body>
</html>