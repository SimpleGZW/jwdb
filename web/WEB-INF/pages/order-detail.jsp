<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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


<section class="main-part">
    <link href="${pageContext.request.contextPath}/assets/font-awesome-4.6.2/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/order-detail.css" rel="stylesheet">
    <div>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">提示信息</h4>
                    </div>
                    <div class="modal-body"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="order-info">

            <div class="order-number">
                工程编号<span>${orderDetails.orderno}</span>
            </div>
            <div class="order-status">
                <c:forEach items="${orderStatusByType}" var="satus">
                    <div
                            <c:if test="${orderDetails.orderstatus==satus.currstatus}">class="current-status"</c:if>>${satus.currstatus.statusdescription}</div>
                </c:forEach>
            </div>
            <div class="status-info">
                <div>您的工程历史状态信息：</div>
                <p></p>
                <c:forEach items="${orderDetails.instancelist}" var="instance">
                    <fmt:formatDate value="${instance.opttime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;
                    <c:if test="${instance.optrole==1}">操作员</c:if>
                    <c:if test="${instance.optrole==0}">用户</c:if>&nbsp;&nbsp;
                    ${instance.optname}&nbsp;&nbsp;${instance.orderstatus.statusdescription}</p>
                    <p></p>
                </c:forEach>
            </div>
        </div>

        <div class="order-table">
            <div class="table-title">
                <p>工程信息</p>
            </div>

            <table class="table table-bordered">
                <tbody>
                <tr>
                    <td><label>计算工程名称</label></td>
                    <td>${orderDetails.projectname}</td>
                    <td><label>计算类型</label></td>
                    <td>${orderDetails.projecttype}</td>

                </tr>
                <tr>

                    <td><label>载荷步数量</label></td>
                    <td>${orderDetails.loadstepnum}</td>
                    <td><label>基体初始温度</label></td>
                    <td>${orderDetails.temperature}</td>
                </tr>
                <tr>
                    <td><label>基体列数</label></td>
                    <td>${orderDetails.materialnum}</td>
                    <td><label>基体行数</label></td>
                    <td>${orderDetails.nodenum}</td>
                </tr>
                <tr>
                    <td><label>基体列间距</label></td>
                    <td>${orderDetails.temExamplePath}</td>
                    <td><label>基体行间距</label></td>
                    <td>${orderDetails.outputPPath}</td>
                </tr>
                <tr>
                    <td><label>基体隔板材料</label></td>
                    <td>${orderDetails.matdataPath}</td>
                    <td><label>隔板厚度（m）</label></td>
                    <td>${orderDetails.picturenum}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <p/>
        <c:if test="${orderDetails.orderstatus.orderstatus == 2}">
        <div class="tabledivcss"><table class="tablecss">
            <thead>
            <tr>
                <td rowspan="2" style="width: 20%">序号</td>
                <td colspan="3">Nodes信息</td>
                <td rowspan="2" style="width: 20%">操作</td>
            </tr>
            <tr>
                <td>X</td>
                <td>Y</td>
                <td>Z</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                <td><input type="text" name=""></td>
                <td><input type="text" name=""></td>
                <td><input type="text" name=""></td>
                <td><i class="fa fa-trash" aria-hidden="true"></i></td>
            </tr>
            <tr>
                <td colspan="18"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
            </tr>

            </tbody>
        </table>
        <div class="tabledivcss"><table class="tablecss">
                <thead>
                <tr>
                    <td rowspan="2" style="width: 20%">序号</td>
                    <td colspan="3">Pic信息</td>
                    <td rowspan="2" style="width: 20%">操作</td>
                </tr>
                <tr>
                    <td>Dire</td>
                    <td>Off</td>
                    <td>Time</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                    <td><input type="text" name=""></td>
                    <td><input type="text" name=""></td>
                    <td><input type="text" name=""></td>
                    <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                </tr>
                <tr>
                    <td colspan="18"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                </tr>

                </tbody>
            </table><br/>
        </c:if>
        <c:if test="${orderDetails.isfinished == 0}">
            <div class="option-button-handler">
                <button type="button" class="btn btn-primary"
                        onclick="window.location.href='${pageContext.request.contextPath}/handleOrder?orderid=${orderDetails.orderid}'">
                    &nbsp;${orderNextStatue}&nbsp;</button>
            </div>
        </c:if>
        <c:if test="${orderDetails.isfinished == 1}">
            <div class="order-table">
                <div class="table-title">
                    <p>结果信息</p>
                </div>
            <div class="plant-img">
                <div class="img-carousel">
                    <i class="fa fa-angle-left" aria-hidden="true"></i>
                    <div class="image-container">
                     <img src="${pageContext.request.contextPath}/images/result.png" height="250px"
                                         data-src="${pageContext.request.contextPath}/images/result.png">
                        <img src="${pageContext.request.contextPath}/images/file002.jpg" height="250px"
                             data-src="${pageContext.request.contextPath}/images/file002.jpg">
                        <img src="${pageContext.request.contextPath}/images/file004.jpg" height="250px"
                             data-src="${pageContext.request.contextPath}/images/file004.jpg">
                    </div>
                    <i class="fa fa-angle-right" aria-hidden="true"></i>

                </div>
            </div></div>
        </c:if>
        <p/>
    </div>
</section>

<!--公共尾部开始-->
<jsp:include page="/footer"></jsp:include>
<!--公共尾部结束-->
<script>
    webRoot = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/assets/js/common/require.min.js" rel="script" defer
        data-main="${pageContext.request.contextPath}/assets/js/admin-main"></script>
</body>
</html>