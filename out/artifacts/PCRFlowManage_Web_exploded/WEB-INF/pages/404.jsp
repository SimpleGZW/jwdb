<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <title>降温计算流程管理平台-404</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/common/404.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/fonts/Art-Reactor-master/dist/art-reactor.min.css" rel="stylesheet">
</head>
<body>
<!--此处引入公共头部文件-->
<jsp:include page="/headerNoSearchbox"></jsp:include>

    <div class="page-404">
        <img src="${pageContext.request.contextPath}/images/page-404.jpg">
        <div class="error-info">
            <span><i class="ar ar-warning-b"></i>未查询到任何有效信息，请您：</span>
            <ol>
                <li>输入关键字重新搜索</li>
                <li>点击返回<a href="${pageContext.request.contextPath}/homepage">主页</a></li>
            </ol>
        </div>
    </div>

<!--此处引入公共尾部文件-->
<jsp:include page="/footer"></jsp:include>
</body>
</html>