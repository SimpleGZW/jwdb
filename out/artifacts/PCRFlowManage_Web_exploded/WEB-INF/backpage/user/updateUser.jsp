<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String nowDate=format.format(calendar.getTime());
%>
<style>
    .selectpicker .pull-left{font-weight: bold;}

    .images-upload{
        width: 700px;
        height: 280px;
        display: inline-block;
        vertical-align: middle;
        border: 1px solid #ccc;
    }
    .img{
        width: 202px;
        height: 260px;
        margin: 10px 15px;
        /*border: 1px solid #ccc;*/
        float: left;
        text-align: center;
    }
    .img img{
        width: 200px;
        height: 200px;
        border: none;
    }
    .img a{
        margin-top: 10px;
        width: 200px;
        height: 50px;
        font-size: 18px;
        line-height: 50px;
    }
</style>

<div class="bjui-pageContent">
     <form id="form_user" action="${pageContext.request.contextPath}/admin/user/add" data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
         <input type="hidden" name="userid" value="${user.userid}" id="userid">
         <input type="hidden" name="valid" value="0" id="valid">
         <div class="pageFormContent" data-layout-h="0">
             <table class="table table-condensed table-hover" width="100%">
                 <tbody>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100"><i class="fa fa-asterisk" aria-hidden="true"></i>用户名：</label>
                               <input type="text" name="username" value="${user.username}" size="60" >

                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100"><i class="fa fa-asterisk" aria-hidden="true"></i>密码：</label>
                               <input type="password" name="password" value="${user.password}"  size="60" data-rule="密码:required">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100"><i class="fa fa-asterisk" aria-hidden="true"></i>确认密码：</label>
                               <input type="password" name="confirmPwd" value="${user.password}"  size="60"  data-rule="确认密码:required;match(password)">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100"><i class="fa fa-asterisk" aria-hidden="true"></i>姓名：</label>
                               <input type="text" name="name" value="${user.name}" size="60" data-rule="required">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100"><i class="fa fa-asterisk" aria-hidden="true"></i>单位：</label>
                               <input type="text" name="company" value="${user.company}" size="60" data-rule="required">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100"><i class="fa fa-asterisk" aria-hidden="true"></i>电话：</label data-rule="required">
                               <input type="text" name="tel" value="${user.tel}" size="60" data-rule="required">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100"><i class="fa fa-asterisk" aria-hidden="true"></i>地址：</label>
                               <input type="text" name="address" value="${user.address}" size="60" data-rule="required">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">代理商：</label>
                               <input type="text" name="agent" value="${user.agent}" size="60">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100">发票抬头：</label>
                               <input type="text" name="invoicetitle" value="${user.invoicetitle}" size="60">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               <label class="control-label x100"  >所属实验室：</label>
                               <%--<input type="text" name="author" value=" " size="60">--%>
                               <select data-toggle="selectpicker" class="show-tick" style="display: none;" name="labid">
                               <option value="">请选择实验室</option>
                                   <c:forEach var="lab" items="${labs}">
                                       <option value="${lab.labid}" <c:if test="${lab.labid == user.labByLabid.labid}" > selected</c:if>> ${lab.labname}</option>
                                   </c:forEach>
                           </select>

                           </td>
                       </tr>

                 </tbody>
             </table>
         </div>
         <div class="bjui-pageFooter">
             <ul>
                 <li><button type="button" class="btn-default" data-icon="">ta的订单</button></li>
                 <li><button type="submit" class="btn-default" data-icon="save">修改用户信息</button></li>

             </ul>
         </div>
     </form>
</div>
<script>
    $('#form_user').validator({
        fields: {
            'username': '用户名: required; remote[${pageContext.request.contextPath}/admin/user/checkAccount1  , userid]'
        }
    });
</script>



