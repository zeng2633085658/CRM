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
    <title>新增合同</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>新增合同资料</h2></blockquote>

<form class="layui-form" action="<%=basePath%>contractServlet?action=add" method="post" name="form1">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>合同编号</label>
            <div class="layui-inline">
                <input type="text"  name="title" lay-verify="title" autocomplete="off"
                       placeholder="请输入合同编号" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>合同名称</label>
            <div class="layui-input-inline">
                <input type="text"  name="contract_name" lay-verify="title1" autocomplete="off"
                       placeholder="请输入合同名称" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>签订时间</label>
            <div class="layui-input-inline">
                <input type="text" name="contract_time" id="joindate" lay-verify="date"
                       placeholder="yyyy-MM-dd" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>到期时间</label>
            <div class="layui-input-inline">
                <input type="text" name="due_time" id="workdate"
                       placeholder="yyyy-MM-dd" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>合同金额</label>
            <div class="layui-input-inline">
                <input type="text" name="total_money" lay-verify="required" autocomplete="off"
                       placeholder="请输入合同金额" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>支付类别</label>
            <div class="layui-input-block">
                <select name="pay_type" lay-filter="aihao">
                    <option value="按月支付" selected="">按月支付</option>
                    <option value="按季度支付">按季度支付</option>
                    <option value="按半年支付">按半年支付</option>
                    <option value="按年支付">按年支付</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>合同状态</label>
            <div class="layui-input-block">
                <select name="status" lay-filter="aihao">
                    <option value="已付款" selected="">已付款</option>
                    <option value="已签订">已签订</option>
                    <option value="服务中">服务中</option>
                    <option value="已完成">已完成</option>
                </select>
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">业务员</label>
            <div class="layui-input-block">
                <select name="empid" lay-filter="aihao">
                    <c:forEach items="${usersVos}" var="u">
                        <c:if test="${user.userid==u.userid}">
                            <option value="${u.userid}" selected>${u.username}</option>
                        </c:if>
                        <c:if test="${user.userid!=u.userid}">
                            <option value="${u.userid}">${u.username}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">操作员</label>
            <div class="layui-input-block">
                <input type="text"  style="width: 32%;" name="operator" lay-verify="title" autocomplete="off"  class="layui-input" value="${user.username}" disabled>
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="remark"></textarea>
        </div>
    </div>

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

        //日期
        laydate.render({
            elem: '#joindate'
        });
        laydate.render({
            elem: '#workdate'
        });
        laydate.render({
            elem: "#levelDate"
        });
        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 2) {
                    return '编号至少得2个字符';
                }
            },
            title1: function (value) {
                if (value.length < 2) {
                    return '合同名称至少2个字符';
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
