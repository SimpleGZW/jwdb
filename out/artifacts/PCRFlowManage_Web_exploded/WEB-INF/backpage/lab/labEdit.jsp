<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bjui-pageContent">
    <form id="form_lab" action="${pageContext.request.contextPath}/admin/lab/edit" data-toggle="validate" data-reload-navtab="true">
        <input type="hidden" name="labid" value="${labentity.labid}" >
        <input type="hidden" name="isvalid" value="0">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                <tr>
                    <td colspan="2">
                        <label class="control-label ">*实验室名称：</label>
                        <input type='text' name="labname" value="${labentity.labname}" data-rule="required" />　
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <label class="control-label ">*地&nbsp;&nbsp;&nbsp;&nbsp;址：</label>
                        <input type='text'name="labaddress"  value="${labentity.labaddress}"  data-rule="required"/>　
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <label class="control-label ">*联系电话：</label>
                        <input type='text'name="labphone"  value="${labentity.labphone}" data-rule="required"/>　
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <label class="control-label ">&nbsp;&nbsp;发票抬头：</label>
                        <input type='text'name="labinvoicetitle"  value="${labentity.labinvoicetitle}" />　
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
    </ul>
</div>

<script>
    $('#form_lab').validator({
        fields: {
            'labname': '实验室名: required; remote[${pageContext.request.contextPath}/admin/lab/checkAccount1  , labid]'
        }
    });
</script>




