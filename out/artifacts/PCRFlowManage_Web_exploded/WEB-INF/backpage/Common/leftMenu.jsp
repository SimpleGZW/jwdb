<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="bjui-leftside">
    <div id="bjui-sidebar-s">
        <div class="collapse"></div>
    </div>
    <div id="bjui-sidebar">
        <div class="toggleCollapse"><h2><i class="fa fa-bars"></i> 导航栏 <i class="fa fa-bars"></i></h2><a href="javascript:;" class="lock"><i class="fa fa-lock"></i></a></div>
        <div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu" data-heightbox="#bjui-sidebar" data-offsety="26">


            <div class="panel panel-default">
                <div class="panel-heading panelContent">
                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse1" class="active"><i class="fa fa-caret-square-o-down"></i>&nbsp;用户管理</a></h4>
                </div>
                <div id="bjui-collapse1" class="panel-collapse panelContent collapse ">
                    <div class="panel-body" >
                        <ul id="bjui-tree1" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">

                        	<li data-id="passRead_0"  data-url="<%=request.getContextPath()%>/admin/user/showlist" data-tabid="passRead_0" data-fresh="true" data-reloadWarn="true">用户列表</li>
                        </ul>
                    </div>
                </div>
                <div class="panelFooter"><div class="panelFooterContent"></div></div>
            </div>
            
            <div class="panel panel-default">
                <div class="panel-heading panelContent">
                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse2" class=""><i class="fa fa-caret-square-o-down"></i>&nbsp;实验室管理</a></h4>
                </div>
                <div id="bjui-collapse2" class="panel-collapse panelContent collapse">
                    <div class="panel-body" >
                        <ul id="bjui-tree2" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">

                            <li data-id="working_0"  data-url="<%=request.getContextPath()%>/admin/lab/listByPage" data-tabid="working_0" data-fresh="true" data-reloadWarn="true">实验室列表</li>
                        </ul>
                    </div>
                </div>
                <div class="panelFooter"><div class="panelFooterContent"></div></div>
            </div>

        </div>
    </div>
</div>