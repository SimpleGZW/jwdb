<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<section>
  <div class="img-carousel">

    <link href="${pageContext.request.contextPath}/assets/css/home-page.css" rel="stylesheet">

    <div id="myCarousel" class="carousel slide" data-ride="carousel">

      <!-- 轮播（Carousel）指标 -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>

      <!-- 轮播（Carousel）项目 -->
      <div class="carousel-inner">
        <div class="item active" id="bg1">
          <img src="${pageContext.request.contextPath}/images/homepage-bg1.jpg">
        </div>
        <div class="item" id="bg2">
          <img src="${pageContext.request.contextPath}/images/homepage-bg2.jpg">
        </div>
        <div class="item" id="bg3">
          <img src="${pageContext.request.contextPath}/images/homepage-bg3.jpg">
        </div>
      </div>
    </div>

  </div>

</section>
<!--页面开始部分结束-->
<jsp:include page="/footer"></jsp:include>
<!--公共尾部开始-->

<script>
  webRoot="${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/assets/js/common/require.min.js" rel="script" defer data-main="${pageContext.request.contextPath}/assets/js/home-page-main"></script>

</body>
</html>