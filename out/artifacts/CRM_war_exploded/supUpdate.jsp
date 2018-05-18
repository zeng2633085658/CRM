<%@ page import="com.crm.common.ContextUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath%>">
    <title>修改用户</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>


<blockquote class="layui-elem-quote"><h2>修改供应商资料</h2></blockquote>

<form class="layui-form" action="<%=basePath%>supplierServlet?action=updateSave" method="post" name="form1">
    <input type="hidden" name="supplierId" value="${sup.supplierId}">
    <div class="layui-form-item">
        <label class="layui-form-label"><span style="color: red;">*</span>供应商名称</label>
        <div class="layui-input-block">
            <input type="text" style="width: 32%;" name="title" lay-verify="title" autocomplete="off"
                   placeholder="请输入供应商名称" class="layui-input" value="${sup.supplierName}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"><span style="color: red;">*</span>联系人</label>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="text" name="contact" lay-verify="title" autocomplete="off"
                       placeholder="请输入联系人名称" class="layui-input"  value="${sup.contact}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>手机</label>
            <div class="layui-input-inline">
                <input type="tel" id="phone" name="phone" placeholder="请输入手机号码" lay-verify="required|phone"
                       autocomplete="off" class="layui-input"  value="${sup.phone}">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">银行账户</label>
            <div class="layui-input-inline">
                <input type="text" name="bankcardno" placeholder="请输入银行账户" autocomplete="off" class="layui-input"  value="${sup.bankAccount}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">开户银行</label>
            <div class="layui-input-inline">
                <input type="text" name="bankname" placeholder="请输入开户银行" autocomplete="off" class="layui-input"  value="${sup.bankName}">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-inline">
                <input type="tel" name="addr" placeholder="请输入地址" autocomplete="off" class="layui-input"  value="${sup.addr}">
            </div>
        </div>

    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="remark"  value="${sup.remark}">${sup.remark}</textarea>
        </div>
    </div>

    <%--<div class="layui-form-item layui-form-text">--%>
    <%--<label class="layui-form-label" name="remark">说明</label>--%>
    <%--<div class="layui-input-block">--%>
    <%--<textarea class="layui-textarea layui-hide" name="content" lay-verify="content" id="LAY_demo_editor"></textarea>--%>
    <%--</div>--%>
    <%--</div>--%>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>



<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 2) {
                    return '名称至少得2个字符';
                }
            }

        });
        //监听提交
        form.on('submit(demo1)', function(data){
            x_admin_close();
            return true;
        });

    });
</script>

</body>
</html>
