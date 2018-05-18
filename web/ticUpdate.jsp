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
    <title>新增开票信息</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>新增开票信息</h2></blockquote>

<form class="layui-form" action="<%=basePath%>ticketServlet?action=updateSave" method="post" name="form1">
    <input type="hidden" name="id" value="${ticket.id}">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>开票日期</label>
            <div class="layui-input-inline">
                <input type="text" name="ticketDate" id="jobDate" lay-verify="required"
                       placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="${ticket.ticketDate}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>订单号</label>
            <div class="layui-input-block">
                <select name="orderid" lay-filter="aihao">
                    <c:forEach items="${orders}" var="u">
                        <c:if test="${u.orderId==ticket.orderid}">
                            <option value="${u.orderId}" selected="">${u.orderId}</option>
                        </c:if>
                        <c:if test="${u.orderId!=ticket.orderid}">
                            <option value="${u.orderId}">${u.orderId}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>客户</label>
            <div class="layui-input-block">
                <select name="custid" lay-filter="aihao">
                    <c:forEach items="${customerInfo}" var="u">
                        <c:if test="${u.custId==ticket.custid}">
                            <option value="${u.custId}" selected="">${u.custname}</option>
                        </c:if>
                        <c:if test="${u.custId!=ticket.custid}">
                            <option value="${u.custId}">${u.custname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>开票金额</label>
            <div class="layui-input-inline">
                <input type="text" name="ticketMoney" autocomplete="off"
                       placeholder="请输入开票金额" class="layui-input" value="${ticket.ticketMoney}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>出票公司</label>
            <div class="layui-input-inline">
                <input type="text" name="ticketComp" lay-verify="required" autocomplete="off"
                       placeholder="请输入出票公司" class="layui-input" value="${ticket.ticketComp}">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="remark">${ticket.remark}</textarea>
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
