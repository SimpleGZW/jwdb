<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/icon.png" type="image/x-icon"/>
    <title>降温计算流程管理平台</title>
    <script>
        //提示信息全局变量
        var msg = "${message}";
    </script>
</head>
<body>
<!--公共头部开始-->
<header class="navbar-fixed-top">

    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/fonts/Art-Reactor-master/dist/art-reactor.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/common/header.css" rel="stylesheet">

    <div class="head-container">
        <a class="logo" href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/images/logo.png">
        </a>
    </div>

</header>
<!--公共头部结束-->
    <section class="main-part jumbotron">
        <link href="${pageContext.request.contextPath}/assets/font-awesome-4.6.2/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/login.css" rel="stylesheet">
        <div>
            <div class="login-form">
                <div class="title"><div class="login-role role-active" data-role="user">用户登录</div>
                </div>
                <div class="alert alert-danger">
                    <!--<a href="#" class="close" data-dismiss="alert">-->
                    <!--&times;-->
                    <!--</a>-->
                    <strong id="warning-info"></strong>
                </div>
                <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/loginin">
                    <div class="input-group input1">
                        <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                        <input type="text" id="username" name="username" class="form-control" placeholder="请输入您的用户名">
                        <img src="${pageContext.request.contextPath}/images/login-img-2.png" class="button delete" id="delete1">
                    </div>
                    <div class="input-group input2">
                        <span class="input-group-addon"><i class="fa fa-lock" aria-hidden="true"></i></span>
                        <input type="password" id="password" name="password" class="form-control" placeholder="请输入您的密码">
                        <img src="${pageContext.request.contextPath}/images/login-img-2.png" class="button delete" id="delete2">
                    </div>
                    <div class="role">
                        <input type="radio" name="role" value="0" id="user" checked>普通用户
                        <input type="radio" name="role" value="1" id="manager">管理员
                    </div>
                    <input type="submit" class="btn btn-block" style="width: 240px;" value="确&nbsp;认">
                </form>
                <div class="option">
                    <a href="${pageContext.request.contextPath}/register">没有账号 ? 立即申请</a>
                </div>
            </div>
        </div>
    </section>

    <!--公共尾部开始-->
    <footer style="background-color: #fff;color: #333;">
        <link href="${pageContext.request.contextPath}/assets/css/common/footer.css" rel="stylesheet">
        <div class="row foot-container">
            <div class="col-lg-3 col-xs-3">
                <div class="links">
                    <i class="ar ar-info-b"></i><span>关于我们</span>
                </div>

            </div>
            <div class="col-lg-3 col-xs-3">
                <div class="links">
                    <i class="ar ar-envelope"></i><span>联系我们</span>
                </div>
                <ul>
                    <li><a href="#" style="color: #333;"></a></li>
                </ul>
            </div>
            <div class="col-lg-3 col-xs-3">
                <div class="links">
                    <i class="ar ar-release-a"></i><span>帮助/反馈</span>
                </div>
                <ul>
                    <li><a href="#" style="color: #333;"></a></li>
                </ul>
            </div>
            <div class="col-lg-3 col-xs-3">
                <div class="links">
                    <i class="ar ar-flag-angle-full"></i><span>友情链接</span>
                </div>
            </div>
        </div>
        <p id="copyright">
            TIA隔热结构仿真软件计算平台  &copy; 2017 版权所有.保留所有权利.
        </p>
    </footer>
    <!--公共尾部结束-->
<script>
    webRoot="${pageContext.request.contextPath}";
</script>
    <script src="${pageContext.request.contextPath}/assets/js/common/require.min.js" rel="script" defer data-main="${pageContext.request.contextPath}/assets/js/login-main"></script>
</body>
</html>