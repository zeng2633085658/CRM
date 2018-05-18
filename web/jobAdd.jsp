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
    <title>新增派工单</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>新增派工单</h2></blockquote>

<form class="layui-form" action="<%=basePath%>jobRecordServlet?action=add" method="post" name="form1">
    <div class="layui-form-item">
        <div class="layui-inline">
        <label class="layui-form-label"><span style="color: red;">*</span>订单编号</label>
            <div class="layui-input-block">
                <select name="orderId" lay-filter="aihao">
                    <c:forEach items="${orders}" var="u">
                        <option value="${u.orderId}">${u.orderId}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>商品</label>
            <div class="layui-input-block">
                <select name="prodid" lay-filter="aihao">
                    <c:forEach items="${product}" var="u">
                        <option value="${u.prodid}">${u.prodname}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>日期</label>
            <div class="layui-input-inline">
                <input type="text" name="jobDate" id="jobDate" lay-verify="required"
                       placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>派工内容</label>
            <div class="layui-input-inline">
                <input type="text" name="jobContent"  autocomplete="off" lay-verify="required"
                       placeholder="请输入派工内容" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>完成情况</label>
            <div class="layui-input-inline">
                <input type="text" name="callback" autocomplete="off" lay-verify="required"
                       placeholder="请输入派工完成情况" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>业务员</label>
            <div class="layui-input-block">
                <select name="userid" lay-filter="aihao">
                    <c:forEach items="${usersVos}" var="u">
                        <option value="${u.userid}">${u.username}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>客户评价</label>
            <div class="layui-input-inline">
                <input type="text" name="custEval" lay-verify="required" autocomplete="off"
                       placeholder="请输入客户评价" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>客户签名</label>
            <div class="layui-input-block">
                <select name="custSign" lay-filter="aihao">
                    <option value="已签" selected="">已签</option>
                    <option value="未签">未签</option>
                </select>
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>开工时间</label>
            <div class="layui-input-inline">
                <input type="text" name="startTime" id="joindate" lay-verify="date"
                       placeholder="yyyy-MM-dd" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>结束时间</label>
            <div class="layui-input-inline">
                <input type="text" name="endTime" id="workdate"
                       placeholder="yyyy-MM-dd" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>工作天数</label>
            <div class="layui-input-inline">
                <input type="text" name="workDay" autocomplete="off" lay-verify="required"
                       placeholder="请输入工作天数" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">交通费</label>
            <div class="layui-input-inline">
                <input type="text" name="busMoney" autocomplete="off"
                       placeholder="请输入交通费" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">补助费</label>
            <div class="layui-input-inline">
                <input type="text" name="attachMoney" autocomplete="off"
                       placeholder="请输入补助费" class="layui-input">
            </div>
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
            elem: "#jobDate"
        });
        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 2) {
                    return '编号至少得2个字符';
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
