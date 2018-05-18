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
    <title>新增收款管理</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>新增收款管理</h2></blockquote>

<form class="layui-form" action="<%=basePath%>financeServlet?action=add" method="post" name="form1">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>订单号</label>
            <div class="layui-input-block">
                <select name="orderId" lay-filter="orderId" id="orderId" lay-search>
                    <c:forEach items="${orders}" var="u">
                        <option value="${u.orderId}">${u.orderId}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>商品名称</label>
            <div class="layui-input-block">
                <select name="prodid" lay-filter="aihao" lay-search>
                    <c:forEach items="${product}" var="u">
                        <option value="${u.prodid}">${u.prodname}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>收款方式</label>
            <div class="layui-input-block">
                <select name="paidtypeid" lay-filter="aihao" lay-search>
                    <c:forEach items="${paidType}" var="f">
                        <option value="${f.paidtypeid}">${f.paidtypename}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>应收金额</label>
            <div class="layui-input-inline">
                <input type="text" name="remainMoney"  autocomplete="off" lay-verify="required"
                       placeholder="请输入应收金额" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>收款金额</label>
            <div class="layui-input-inline">
                <input type="text" name="paidMoney" autocomplete="off" lay-verify="required"
                       placeholder="请输入收款金额" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>订单金额</label>
            <div class="layui-input-inline">
                <input type="text" name="orderMoney" autocomplete="off" lay-verify="required"
                       placeholder="请输入订单金额" class="layui-input">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>交款人</label>
            <div class="layui-input-inline">
                <input type="text" name="paidPerson" autocomplete="off" lay-verify="required"
                       placeholder="请输入交款人" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>入账银行</label>
            <div class="layui-input-inline">
                <input type="text" name="inbank" autocomplete="off" lay-verify="required"
                       placeholder="请输入入账银行" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>入账账号</label>
            <div class="layui-input-inline">
                <input type="text" name="bankAccount" autocomplete="off" lay-verify="required"
                       placeholder="请输入入账账号" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>出账银行</label>
            <div class="layui-input-inline">
                <input type="text" name="outbank" lay-verify="required" autocomplete="off"
                       placeholder="请输入出账银行" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">相关凭证号</label>
            <div class="layui-input-inline">
                <input type="text" name="warrant" autocomplete="off"
                       placeholder="请输入相关凭证号" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>交款时间</label>
            <div class="layui-input-inline">
                <input type="text" name="paidTime" id="jobDate" lay-verify="required"
                       placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>到账日期</label>
            <div class="layui-input-inline">
                <input type="text" name="paidinTime" id="workdate" lay-verify="required"
                       placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>是否有效</label>
            <div class="layui-input-block">
                <select name="invalid" lay-filter="aihao">
                    <option value="有效">有效</option>
                    <option value="作废">作废</option>
                </select>
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>操作类别</label>
            <div class="layui-input-block">
                <select name="oprType" lay-filter="aihao">
                    <option value="收款">收款</option>
                    <option value="付款">付款</option>
                </select>
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
