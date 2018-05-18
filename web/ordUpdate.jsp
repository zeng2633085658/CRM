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
    <title>修改订单</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>修改订单资料</h2></blockquote>

<form class="layui-form" action="<%=basePath%>ordersServlet?action=updateSave" method="post" name="form1">
    <input type="hidden" name="orderId" value="${orders.orderId}">
    <div class="layui-form-item">
        <div class="layui-inline">
        <label class="layui-form-label"><span style="color: red;">*</span>客户</label>
            <div class="layui-input-block">
                <select name="custid" lay-filter="aihao">
                    <c:forEach items="${customerInfo}" var="u">
                        <c:if test="${u.custId==orders.custid}" >
                            <option value="${u.custId}" selected="">${u.custname}</option>
                        </c:if>
                        <c:if test="${u.custId!=orders.custid}" >
                            <option value="${u.custId}">${u.custname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>业务员</label>
            <div class="layui-input-block">
                <select name="userid" lay-filter="aihao">
                    <c:forEach items="${usersVos}" var="u">
                        <c:if test="${u.userid==orders.userid}" >
                            <option value="${u.userid}" selected="">${u.username}</option>
                        </c:if>
                        <c:if test="${u.userid!=orders.userid}" >
                            <option value="${u.userid}">${u.username}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>订单类别</label>
            <div class="layui-input-block">
                <select name="orderType" lay-filter="aihao">

                    <c:if test="${orders.orderType=='采购入库'}" >
                        <option value="采购入库" selected="">采购入库</option>
                        <option value="销售出库">销售出库</option>
                    </c:if>
                    <c:if test="${orders.orderType=='销售出库'}" >
                        <option value="采购入库">采购入库</option>
                        <option value="销售出库" selected="">销售出库</option>
                    </c:if>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>订单状态</label>
            <div class="layui-input-block">
                <select name="orderStatus" lay-filter="aihao">

                    <c:if test="${orders.orderStatus=='已出货'}" >
                        <option value="已出货" selected="">已出货</option>
                        <option value="已收款">已收款</option>
                        <option value="实施中">实施中</option>
                    </c:if>
                    <c:if test="${orders.orderStatus=='已收款'}" >
                        <option value="已出货">已出货</option>
                        <option value="已收款" selected="">已收款</option>
                        <option value="实施中">实施中</option>
                    </c:if>
                    <c:if test="${orders.orderStatus=='实施中'}" >
                        <option value="已出货">已出货</option>
                        <option value="已收款">已收款</option>
                        <option value="实施中" selected="">实施中</option>
                    </c:if>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>进度</label>
            <div class="layui-input-inline">
                <input type="text" name="process" lay-verify="required" autocomplete="off"
                       placeholder="请输入进度" class="layui-input" value="${orders.process}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>订单金额</label>
            <div class="layui-input-inline">
                <input type="text" name="totalMoney" lay-verify="required" autocomplete="off"
                       placeholder="请输入合同金额" class="layui-input" value="${orders.totalMoney}">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>开票人</label>
            <div class="layui-input-inline">
                <input type="text" name="operator" lay-verify="required" autocomplete="off"
                       placeholder="请输入开票人" class="layui-input" value="${orders.operator}">
            </div>
        </div>
    </div>


    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="remark" value="${orders.remark}">${orders.remark}</textarea>
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
