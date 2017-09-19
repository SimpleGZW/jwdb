<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${pageContext.request.contextPath}/admin/lab/listByPage" method="post" >
        <input type="hidden" name="pageSize" value="${labentityspage.size}">
        <input type="hidden" name="pageCurrent" value="${labentityspage.number+1}">
        <div class="bjui-searchBar">

            <div class="pull-right">
                <div class="btn-group">
                    <button type="button" class="btn-default " ><a href="<%=request.getContextPath()%>/admin/lab/addPage" data-toggle="dialog" data-width="900" data-height="580" data-id="dialog-mask" data-mask="true" style="text-decoration: none;"><i class="fa fa-plus"></i> 新增实验室</a></button>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="bjui-pageContent">
    <table data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
        <tr>
            <th >实验室名称</th>
            <th >用户数量</th>
            <th >订单数量</th>
            <th >状态</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${labentityspage.content}">
            <tr>
                <td>${list.labname}</td>
                <td>${list.usersByLabid.size()}</td>
                <td>${list.orderNum}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/lab/editPage?labid=${list.labid}" class="btn btn-default" data-toggle="dialog" data-width="900" data-height="580" data-id="dialog-mask" data-mask="true">查看</a>
                    <a href="${pageContext.request.contextPath}/admin/lab/delete?labid=${list.labid}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="bjui-pageFooter">
    <div class="pages">
        <span>每页&nbsp;</span>
        <div class="selectPagesize">
            <select data-toggle="selectpicker" data-toggle-change="changepagesize">
                <option value="5" <c:if test="${labentityspage.size==5}">selected</c:if>>5</option>
                <option value="10" <c:if test="${labentityspage.size==10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${labentityspage.size==20}">selected</c:if>>20</option>
                <option value="50" <c:if test="${labentityspage.size==50}">selected</c:if>>50</option>

            </select>
        </div>
        <span>&nbsp;条，共 ${labentityspage.totalElements} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${labentityspage.totalElements}" data-page-size="${labentityspage.size}" data-page-current="${labentityspage.number+1}">
    </div>
</div>
