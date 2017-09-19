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
<section class="main-part">
    <link href="${pageContext.request.contextPath}/assets/font-awesome-4.6.2/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/placeorder.css" rel="stylesheet">
    <div>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
        <div class="register-status"><hr>
            <div id="basicInfo" class="active">
                <span>计算参数信息</span>
                <hr>
                <div class="status-num">1</div>
            </div>
            <div id="details">
                <span>基体参数信息</span>
                <hr>
                <div class="status-num">2</div>
            </div>
            <div id="constructinfo">
                <span>结构参数信息</span>
                <hr>
                <div class="status-num">3</div>
            </div>
            <div id="details_css">
                <span>载荷步信息</span>
                <hr>
                <div class="status-num">4</div>
            </div>
            <div id="details_materia">
                <span>材料参数信息</span>
                <hr>
                <div class="status-num">5</div>
            </div>
            <div id="finished">
                <span>完成</span>
                <hr>
                <div class="status-num">6</div>
            </div><hr>
        </div>
        <div class="promptImg">

        </div>
        <div class="register-form">

            <form action="${pageContext.request.contextPath}/registerin" method="post" id="registerForm">
                <div id="accountInfo" >
                    <div>
                        <div class="input-box">
                            <div>
                                <p>计算工程名称</p>
                                <input type="text" name="projectname" id="projectname" autofocus>
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div>由数字、字母、下划线组成，如: TIA_170901</div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>计算类型</p>
                                <input type="text" name="projecttype">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div>0.稳态；1.瞬态</div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>载荷步数量</p>
                                <input type="text" name="loadstepnum">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>基体初始温度</p>
                                <input type="text" name="temperature">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn nextStep">下一步</button>
                </div>

                <div class = "showcss" id="accountDetail">
                    <div>
                        <div class="input-box">
                            <div>
                                <p>基体列数</p>
                                <input type="text" name="materialnum">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div>测试提示信息！</div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>基体行数</p>
                                <input type="text" name="nodenum">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>基体列间距</p>
                                <input type="text" name="temExamplePath">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>基体行间距</p>
                                <input type="text" name="outputPPath">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>基体隔板材料</p>
                                <input type="text" name="matdataPath">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>隔板厚度（m）</p>
                                <input type="text" name="picturenum">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div class="tabledivcss"><table class="tablecss">
                        <thead>
                        <tr>
                            <td >序号</td>
                            <td >基体层材料</td>
                            <td >基体层厚(m)</td>
                            <td >操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td align="center"><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td align="center"><i class="fa fa-trash" aria-hidden="true"></i></td>
                        </tr>
                        <tr>
                            <td colspan="4"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                        </tr>

                        </tbody>
                    </table></div>
                    <button type="button" class="btn preStep">上一步</button>
                    <button type="button" class="btn nextStep">下一步</button>
                </div>

                <div class = "showcss" id="constructDetail_A">
                    <div>
                        <div class="input-box">
                            <div>
                                <p>A结构参数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>A结构个数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>总高</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>

                    <div>
                        <div class="input-box" style="display: none">
                            <div>
                                <p></p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>

                    <div>
                        <div >
                            <div>
                                <p>&nbsp;&nbsp;详细数据:</p>
                            </div>
                        </div>
                    </div>

                    <div class="tabledivcss">
                        <table class="tablecss">
                        <thead>
                        <tr>
                            <td rowspan="2">序号</td>
                            <td colspan="4">本体定位</td>
                            <td colspan="3">本体尺寸</td>
                            <td colspan="2">大胶棒</td>
                            <td colspan="5">小胶棒</td>
                            <td colspan="2">材料</td>
                            <td rowspan="2" style="width: 50%">操作</td>
                        </tr>
                        <tr>
                            <td>单元位置X</td>
                            <td>单元位置Y</td>
                            <td>圆心X（m）</td>
                            <td>圆心Z（m）</td>
                            <td>跑道矩形长度</td>
                            <td>小圆半径</td>
                            <td>大圆半径</td>
                            <td>大圆圆心定位</td>
                            <td>大圆半径</td>
                            <td>小圆圆心定位X</td>
                            <td>小圆圆心定位Z</td>
                            <td>小圆间距X</td>
                            <td>小圆间距Z</td>
                            <td>小圆半径</td>
                            <td>跑道材料</td>
                            <td>橡胶棒材料</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
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
                    </div>
                    <button type="button" class="btn preStep">上一步</button>
                    <button type="button" class="btn nextStep">下一步</button>
                </div>

                <div class = "showcss" id="constructDetail_B">
                    <div>
                        <div class="input-box">
                            <div>
                                <p>B结构参数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>B结构个数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box" style="display: none">
                            <div>
                                <p></p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box" style="display: none">
                            <div>
                                <p></p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>

                    <div>
                        <div >
                            <div>
                                <p>&nbsp;&nbsp;详细数据:</p>
                            </div>
                        </div>
                    </div>
                    <div class="tabledivcss"><table class="tablecss">
                        <thead>
                        <tr>
                            <td rowspan="2">序号</td>
                            <td colspan="4">本体定位</td>
                            <td colspan="2">本体尺寸</td>
                            <td colspan="1">材料</td>
                            <td rowspan="2">操作</td>
                        </tr>
                        <tr>
                            <td>单元位置X</td>
                            <td>单元位置Y</td>
                            <td>圆心X（m）</td>
                            <td>圆心Z（m）</td>
                            <td>半径（m）</td>
                            <td>隔板厚度(m)</td>
                            <td>管壁材料</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                        </tr>
                        <tr>
                            <td colspan="9"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                        </tr>

                        </tbody>
                    </table></div>
                    <button type="button" class="btn preStep">上一步</button>
                    <button type="button" class="btn nextStep">下一步</button>
                </div>

                <div class = "showcss" id="constructDetail_C">
                    <div>
                        <div class="input-box">
                            <div>
                                <p>C结构参数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>C结构个数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box" style="display: none">
                            <div>
                                <p></p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box" style="display: none">
                            <div>
                                <p></p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>

                    <div>
                        <div >
                            <div>
                                <p>&nbsp;&nbsp;详细数据:</p>
                            </div>
                        </div>
                    </div>
                    <div class="tabledivcss"><table class="tablecss">
                        <thead>
                        <tr>
                            <td rowspan="2">序号</td>
                            <td colspan="4">本体定位</td>
                            <td colspan="2">本体尺寸</td>
                            <td colspan="1">材料</td>
                            <td rowspan="2">操作</td>
                        </tr>
                        <tr>
                            <td>单元位置X</td>
                            <td>单元位置Y</td>
                            <td>圆心X（m）</td>
                            <td>圆心Z（m）</td>
                            <td>半径（m）</td>
                            <td>隔板厚度(m)</td>
                            <td>管壁材料</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                        </tr>
                        <tr>
                            <td colspan="9"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                        </tr>

                        </tbody>
                    </table></div>
                    <button type="button" class="btn preStep">上一步</button>
                    <button type="button" class="btn nextStep">下一步</button>
                </div>

                <div class = "showcss" id="constructDetail_D">
                    <div>
                        <div class="input-box">
                            <div>
                                <p>D结构参数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>D结构个数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box" style="display: none">
                            <div>
                                <p></p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box" style="display: none">
                            <div>
                                <p></p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div >
                            <div>
                                <p>&nbsp;&nbsp;详细数据:</p>
                            </div>
                        </div>
                    </div>
                    <div class="tabledivcss"><table class="tablecss">
                        <thead>
                        <tr>
                            <td rowspan="2">序号</td>
                            <td colspan="4">本体定位</td>
                            <td colspan="4">本体尺寸</td>
                            <td colspan="3">材料</td>
                            <td rowspan="2">操作</td>
                        </tr>
                        <tr>
                            <td>单元位置X</td>
                            <td>单元位置Y</td>
                            <td>圆心定位X（m）</td>
                            <td>圆心定位Z（m）</td>
                            <td>圆半径</td>
                            <td>间距X</td>
                            <td>间距Z</td>
                            <td>隔板厚度(m)</td>
                            <td>管壁材料1</td>
                            <td>管壁材料2</td>
                            <td>材料2位置</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                        </tr>
                        <tr>
                            <td colspan="13"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                        </tr>

                        </tbody>
                    </table></div>
                    <button type="button" class="btn preStep">上一步</button>
                    <button type="button" class="btn nextStep">下一步</button>
                </div>

                <div class = "showcss" id="constructDetail_E">
                    <div>
                        <div class="input-box">
                            <div>
                                <p>E结构参数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>E结构个数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box" style="display: none">
                            <div>
                                <p></p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box" style="display: none">
                            <div>
                                <p></p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>

                    <div>
                        <div >
                            <div>
                                <p>&nbsp;&nbsp;详细数据:</p>
                            </div>
                        </div>
                    </div>
                    <div class="tabledivcss"><table class="tablecss">
                        <thead>
                        <tr>
                            <td rowspan="2">序号</td>
                            <td colspan="4">本体定位</td>
                            <td colspan="3">本体尺寸</td>
                            <td colspan="2">材料</td>
                            <td rowspan="2">操作</td>
                        </tr>
                        <tr>
                            <td>单元位置X</td>
                            <td>单元位置Y</td>
                            <td>圆心X（m）</td>
                            <td>圆心Z（m）</td>
                            <td>小圆半径</td>
                            <td>中圆半径</td>
                            <td>大圆半径</td>
                            <td>内层材料</td>
                            <td>外层材料</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                        </tr>
                        <tr>
                            <td colspan="11"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                        </tr>

                        </tbody>
                    </table></div>
                    <button type="button" class="btn preStep">上一步</button>
                    <button type="button" class="btn nextStep">下一步</button>
                </div>

                <div class = "showcss" id="cssDetail">
                    <div>
                        <div class="input-box">
                            <div>
                                <p>载荷步号</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>载荷步持续时间s</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>加载方式</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div>瞬态有效：0.线性；1.阶跃</div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>基体底面温度</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>基体底对流换热系数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>基体顶面温度</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="input-box">
                            <div>
                                <p>基体顶对流换热系数</p>
                                <input type="text" name="">
                            </div>
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            <div class="prompt">
                                <div></div>
                            </div>
                        </div>
                    </div>
                    <div class="tabledivcss"><table class="tablecss">
                        <thead>
                        <tr>
                            <td rowspan="2">序号</td>
                            <td colspan="7">A区</td>
                            <td rowspan="2">操作</td>
                        </tr>
                        <tr>
                            <td>内壁对流换热系数</td>
                            <td>外壁对流换热系数</td>
                            <td>橡胶棒外壁对流换热系数</td>
                            <td>内壁换热温度</td>
                            <td>外壁换热温度</td>
                            <td>橡胶棒外壁换热温度</td>
                            <td>橡胶棒发热温度</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                        </tr>
                        <tr>
                            <td colspan="9"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                        </tr>

                        </tbody>
                    </table></div>
                    <div class="tabledivcss"><table class="tablecss">
                        <thead>
                        <tr>
                            <td rowspan="2">序号</td>
                            <td colspan="2">B区</td>
                            <td rowspan="2">操作</td>
                        </tr>
                        <tr>
                            <td>内壁对流换热系数</td>
                            <td>内壁温度</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                        </tr>
                        <tr>
                            <td colspan="11"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                        </tr>

                        </tbody>
                    </table></div>
                    <div class="tabledivcss"><table class="tablecss">
                        <thead>
                        <tr>
                            <td rowspan="2">序号</td>
                            <td colspan="2">C区</td>
                            <td rowspan="2">操作</td>
                        </tr>
                        <tr>
                            <td>内壁对流换热系数</td>
                            <td>内壁温度</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                        </tr>
                        <tr>
                            <td colspan="11"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                        </tr>

                        </tbody>
                    </table></div>
                    <div class="tabledivcss"><table class="tablecss">
                        <thead>
                        <tr>
                            <td rowspan="2">序号</td>
                            <td colspan="2">D区</td>
                            <td rowspan="2">操作</td>
                        </tr>
                        <tr>
                            <td>内壁对流换热系数</td>
                            <td>内壁温度</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                        </tr>
                        <tr>
                            <td colspan="11"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                        </tr>

                        </tbody>
                    </table></div>
                    <div class="tabledivcss"><table class="tablecss">
                        <thead>
                        <tr>
                            <td rowspan="2">序号</td>
                            <td colspan="4">E区</td>
                            <td rowspan="2">操作</td>
                        </tr>
                        <tr>
                            <td>内壁对流换热系数</td>
                            <td>外壁对流换热系数</td>
                            <td>内壁温度</td>
                            <td>外壁温度</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                        </tr>
                        <tr>
                            <td colspan="6"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                        </tr>

                        </tbody>
                    </table></div>
                    <button type="button" class="btn preStep">上一步</button>
                    <button type="button" class="btn nextStep">下一步</button>
                </div>

                <div class = "showcss" id="materialDetail">
                    <div class="tabledivcss"><table class="tablecss">
                        <thead>
                        <tr>
                            <td rowspan="2">序号</td>
                            <td colspan="8">材料参数信息</td>
                            <td rowspan="2">操作</td>
                        </tr>
                        <tr>
                            <td>Title</td>
                            <td>Description</td>
                            <td>C(J/kgC)</td>
                            <td>KXX(W/mK)</td>
                            <td>KEXP(/C)</td>
                            <td>EX(Pa)</td>
                            <td>PRXY</td>
                            <td>DEN(kg/m3)</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="sampleId" style="background-color: #ffffff" value="1" disabled ></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><input type="text" name=""></td>
                            <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                        </tr>
                        <tr>
                            <td colspan="10"><a><img src="${pageContext.request.contextPath}/images/add.jpg" width="20px" style="margin: 10px"></a></td>
                        </tr>

                        </tbody>
                    </table></div>
                    <button type="button" class="btn preStep">上一步</button>
                    <button type="button" class="btn nextStep" id="submitBtn">提交</button>
                </div>
            </form>

            <!--完成注册-->
            <div id="registerFinished">
                <div class="success-info">
                    <i class="fa fa-check-circle" aria-hidden="true"></i><p>您已经成功提交计算工程！</p>
                </div>
                <div class="user-name">您的工程编号：<a href="javascript:;">111111111</a></div>
                <a class="btn" type="button" href="${pageContext.request.contextPath}/submitOrderSuccess#/1">查看我的工程信息</a>
            </div>
        </div>
    </div>
</section>

<!--公共尾部开始-->
<jsp:include page="/footer"></jsp:include>
<!--公共尾部结束-->
<script>
    webRoot="${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/assets/js/common/require.min.js" rel="script" defer data-main="${pageContext.request.contextPath}/assets/js/place-order-main"></script>
</body>
</html>