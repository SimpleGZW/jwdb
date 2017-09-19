<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>降温计算流程管理平台</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/common/order-info.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/orderinfo-not-found.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/fonts/Art-Reactor-master/dist/art-reactor.min.css" rel="stylesheet">
</head>
<body>
<!--此处引入公共头部文件-->
<jsp:include page="/headerNoSearchbox"></jsp:include>

<div class="order-info">
    <div class="your-position">
        <ol class="breadcrumb">
            您的位置：
            <li><a href="${pageContext.request.contextPath}/homepage">首页</a></li>
            <li class="active">订单详情</li>
        </ol>
    </div>
    <div class="order-number">
        <p>订单编号<span>${orderDetails.orderno}</span></p>
    </div>
    <div class="order-status">
        <span>提交订单</span>
        <span>数据上传</span>
        <span>数据分析</span>
        <span>生成报告</span>
        <!--进度条为提交订单时，将id="progress-bar"的left值设置为55px，同时修改其内容为提交订单；-->
        <!--进度条为提交订单时，将id="progress-bar"的left值设置为305px，同时修改其内容为数据上传；-->
        <!--进度条为提交订单时，将id="progress-bar"的left值设置为555px，同时修改其内容为数据分析；-->
        <!--进度条为提交订单时，将id="progress-bar"的left值设置为805px，同时修改其内容为生成报告；-->
        <!--<div id="progress-bar">数据分析</div>-->
    </div>
    <div class="order-details not-found">
        <i class="ar ar-sad-b"></i>
        <div>您的订单暂未查询到任何有效信息！</div>
        <p>如果输入单号错误，请点击：<a href="${pageContext.request.contextPath}/homepage">返回首页</a></p>
    </div>
</div>

<div class="report-data">
    <div class="report">
        <h2>关于您的报告</h2>
        <p>您的这份报告是基于您提交的样本，经过古奥基因人类全基因组测序平台分析得到的结果，
            报告中仅包含我们检测到的可能会影响您健康的变异信息，古奥基因会将检测到您的所有变异信息以电子文档的形式发送给您。</p>
        <i class="ar ar-research-b"></i>
    </div>
    <div class="data">
        <h2>关于您的数据</h2>
        <ul>
            <li>
                <div><i class="ar ar-round"></i></div>
                <p>由于人类全基因组的原始数据较大（大于90G），古奥基因为您保管数据，并保证您的数据安全和隐私保护。</p>
            </li>
            <li>
                <div><i class="ar ar-round"></i></div>
                <p>若您在将来需要获得您的数据，只需告知古奥基因，我们将会将原始数据进行加密后寄送给您。</p>
            </li>
            <li>
                <div><i class="ar ar-round"></i></div>
                <p>若您得到了您的全基因组数据，为了保护您的隐私，请妥善保管，避免外泄。</p>
            </li>
            <li>
                <div><i class="ar ar-round"></i></div>
                <p>若您将来需要再次解析您的数据，我们建议您直接联系古奥基因。</p>
            </li>
        </ul>
        <i class="ar ar-envelope-open"></i>
    </div>
</div>

<!--此处引入公共尾部文件-->
<jsp:include page="/footer"></jsp:include>
</body>
</html>