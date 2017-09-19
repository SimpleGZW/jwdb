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
<section class="main-part">
    <link href="${pageContext.request.contextPath}/assets/font-awesome-4.6.2/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/register.css" rel="stylesheet">
    <div>
        <div class="register-status">
            <hr>
            <div id="basicInfo" class="active">
                <span>填写账号信息</span>
                <hr>
                <div class="status-num">1</div>
            </div>
            <div id="details">
                <span>完善账号资料</span>
                <hr>
                <div class="status-num">2</div>
            </div>
            <div id="finished">
                <span>完成注册</span>
                <hr>
                <div class="status-num">3</div>
            </div>
            <hr>
        </div>

        <div class="register-form">

            <form action="${pageContext.request.contextPath}/registerin" method="post" id="registerForm">
                <!--填写账号信息-->
                <div id="accountInfo">
                    <div>
                        <div class="input-box">
                            <div>
                                <p>用户名</p>
                                <input type="text" name="username" id="userName">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <!--<img src="${pageContext.request.contextPath}/assets/images/register-bg3.png">-->
                                <div>6-20位字符<br>只能包含字母或数字</div>
                            </div>
                        </div>
                        <div class="warning-info"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>用户名已被使用，请重新设置</div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>登录密码</p>
                                <input type="password" name="password1" id="password1">
                                <i class="password-toggle fa fa-eye" aria-hidden="true"></i>
                                <i class="password-toggle fa fa-eye-slash" aria-hidden="true"></i>
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <img src="${pageContext.request.contextPath}/assets/images/register-bg3.png">
                                <div>6-20位字符<br>只能包含字母、数字或符号（除空格）</div>
                            </div>
                        </div>
                        <div class="warning-info"> <i class="fa fa-shield" aria-hidden="true"></i>安全等级弱，有被盗号风险</div>
                        <div class="warning-info"> <i class="fa fa-shield" aria-hidden="true"></i>安全等级适中，可使用多种组合提升强度</div>
                        <div class="warning-info"> <i class="fa fa-shield" aria-hidden="true"></i>安全等级高</div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>确认密码</p>
                                <input type="password" name="password2" id="password2">
                                <i class="password-toggle fa fa-eye" aria-hidden="true"></i>
                                <i class="password-toggle fa fa-eye-slash" aria-hidden="true"></i>
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <img src="${pageContext.request.contextPath}/assets/images/register-bg3.png">
                                <div>确认密码<br>重复输入登录密码</div>
                            </div>
                        </div>
                    </div>
                    <div class="agreement">
                        <input type="checkbox" name="agreement" id="checkbox"><label for="checkbox">我已阅读并同意<a href="javascript:;">《古奥基因用户注册协议》</a></label>
                        <div class="warning-info"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>您必须同意用户注册协议才能进行下一步</div>
                    </div>
                    <button type="button" class="btn" id="nexStep">下一步</button>
                </div>

                <!--完善账号资料-->

                <div id="accountDetail">
                    <div class="title-info"><i class="fa fa-smile-o" aria-hidden="true"></i><p>为了更好地进行数据服务，请您完善以下账号资料</p></div>

                    <div>
                        <div class="input-box">
                            <i class="fa fa-asterisk" aria-hidden="true"></i>
                            <div>
                                <p>姓名</p>
                                <input type="text" name="name" id="name" placeholder="建议您填写真实姓名">
                            </div>
                        </div>
                        <div class="warning-info"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>姓名为必填字段，请填写后再保存</div>
                    </div>
                    <div>
                        <div class="input-box">
                            <i class="fa fa-asterisk" aria-hidden="true"></i>
                            <div>
                                <p>单位</p>
                                <input type="text" name="company" id="company" placeholder="填写您的工作单位">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <img src="${pageContext.request.contextPath}/assets/images/register-bg3.png">
                                <div>单位格式示例<br>华中科技大学生物信息学院</div>
                            </div>
                        </div>
                        <div class="warning-info"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>单位为必填字段，请填写后再保存</div>
                    </div>
                    <div>
                        <div class="input-box">
                            <i class="fa fa-asterisk" aria-hidden="true"></i>
                            <div>
                                <p>手机</p>
                                <input type="text" name="phone" id="phone" placeholder="填写您的常用手机号码">
                            </div>
                        </div>
                        <div class="warning-info"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>手机为必填字段，请填写后再保存</div>
                    </div>
                    <!--后续扩展可以将地址改为三级联动输入框-->
                    <div class="divide-info">请填写详细地址：</div>

                    <div>
                        <div class="input-box">
                            <i class="fa fa-asterisk" aria-hidden="true"></i>
                            <div>
                                <p>详细地址</p>
                                <input type="text" name="address" id="address" placeholder="请填写详细街道地址或门牌">
                            </div>
                        </div>
                        <div class="warning-info"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>地址为必填字段，请填写后再保存</div>
                    </div>
                    <div class="divide-info">请填写下列信息：以供开取发票及接收数据结果</div>

                    <div>
                        <div class="input-box">
                            <i class="fa fa-asterisk" aria-hidden="true"></i>
                            <div>
                                <p>代理商</p>
                                <input type="text" name="agent" id="agent" placeholder="请填写代理商">
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <i class="fa fa-asterisk" aria-hidden="true"></i>
                            <div>
                                <p>发票抬头</p>
                                <input type="text" name="invoiceTitle" id="invoiceTitle" placeholder="请填写发票抬头">
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <i class="fa fa-asterisk" aria-hidden="true"></i>
                            <div>
                                <p>科室负责人</p>
                                <input type="text" name="responsibleParty" id="responsibleParty" placeholder="请填写科室负责人">
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <i class="fa fa-asterisk" aria-hidden="true"></i>
                            <div>
                                <p>邮箱地址</p>
                                <input type="email" name="email" id="email" placeholder="请填写邮箱地址">
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn" id="saveSubmit">保存资料并确认</button>
                </div>

            </form>

            <!--完成注册-->
            <div id="registerFinished">
                <div class="success-info">
                    <i class="fa fa-check-circle" aria-hidden="true"></i><p>恭喜您已经注册成功！</p>
                </div>
                <div class="user-name">您的账号名为：<a href="javascript:;">zhangsan</a></div>
                <a class="btn" type="button" href="${pageContext.request.contextPath}/login">立即返回登录页</a>
            </div>

        </div>
    </div>
</section>

<!--公共尾部开始-->
<footer>
    <link href="${pageContext.request.contextPath}/assets/css/common/footer.css" rel="stylesheet">
    <div class="row foot-container">
        <div class="col-lg-3 col-xs-3">
            <div class="links">
                <i class="ar ar-info-b"></i><span>关于我们</span>
            </div>
            <ul>
                <li><a href="#">网站免责声明</a></li>
                <li><a href="#">网站使用条款</a></li>
            </ul>
        </div>
        <div class="col-lg-3 col-xs-3">
            <div class="links">
                <i class="ar ar-envelope"></i><span>联系我们</span>
            </div>
            <ul>
                <li><a href="#">service@geneapps.cn</a></li>
            </ul>
        </div>
        <div class="col-lg-3 col-xs-3">
            <div class="links">
                <i class="ar ar-release-a"></i><span>帮助/反馈</span>
            </div>
            <ul>
                <li><a href="#">service@geneapps.cn</a></li>
            </ul>
        </div>
        <div class="col-lg-3 col-xs-3">
            <div class="links">
                <i class="ar ar-flag-angle-full"></i><span>友情链接</span>
            </div>
            <ul>
                <li><a href="#">Geneapps</a></li>
                <li><a href="#">高通量数据网站</a></li>
            </ul>
        </div>
    </div>
    <p id="copyright">
        古奥基因 GooalGene &copy; 2015 版权所有.保留所有权利.
    </p>
</footer>
<!--公共尾部结束-->
<script>
    webRoot="${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/assets/js/common/require.min.js" rel="script" defer data-main="${pageContext.request.contextPath}/assets/js/register-main"></script>
</body>
</html>