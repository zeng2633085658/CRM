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
    <title>新增订单明细</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>新增订单明细</h2></blockquote>

<form class="layui-form" action="<%=basePath%>orderDetailServlet?action=updateSave" method="post" name="form1">
    <input type="hidden" name="detailId" value="${ordd.detailId}" >
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>订单明细编号</label>
            <div class="layui-input-inline">
                <input type="text" name="detailId" lay-verify="required" autocomplete="off"
                        class="layui-input" value="${ordd.detailId}" disabled>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>商品</label>
            <div class="layui-input-block">
                <select name="prodid" lay-filter="aihao">
                    <c:forEach items="${product}" var="u">
                        <c:if test="${u.prodid==ordd.prodid}" >
                            <option value="${u.prodid}" selected="">${u.prodname}</option>
                        </c:if>
                        <c:if test="${u.prodid!=ordd.prodid}" >
                            <option value="${u.prodid}">${u.prodname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>销售类别</label>
            <div class="layui-input-block">
                <select name="status" lay-filter="aihao">
                    <c:if test="${ordd.status=='销售'}" >
                        <option value="销售" selected="">销售</option>
                        <option value="赠送">赠送</option>
                        <option value="配套">配套</option>
                    </c:if>
                    <c:if test="${ordd.status=='赠送'}" >
                        <option value="销售">销售</option>
                        <option value="赠送" selected="">赠送</option>
                        <option value="配套">配套</option>
                    </c:if>
                    <c:if test="${ordd.status=='配套'}" >
                        <option value="销售">销售</option>
                        <option value="赠送">赠送</option>
                        <option value="配套" selected="">配套</option>
                    </c:if>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>销售金额</label>
            <div class="layui-input-inline">
                <input type="text" name="saleMoney" lay-verify="required" autocomplete="off"
                       placeholder="请输入销售金额" class="layui-input" value="${ordd.saleMoney}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>单位</label>
            <div class="layui-input-block">
                <select name="unitId" lay-filter="aihao">
                    <c:forEach items="${unit}" var="u">
                        <c:if test="${u.unitId==ordd.unitId}" >
                            <option value="${u.unitId}" selected="">${u.unitName}</option>
                        </c:if>
                        <c:if test="${u.unitId!=ordd.unitId}" >
                            <option value="${u.unitId}">${u.unitName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">注册联系人</label>
            <div class="layui-input-inline">
                <input type="text" name="regPerson"  autocomplete="off"
                       placeholder="请输入注册联系人" class="layui-input" value="${ordd.regPerson}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">注册密码</label>
            <div class="layui-input-inline">
                <input type="text" name="regPassword" autocomplete="off"
                       placeholder="请输入注册密码" class="layui-input" value="${ordd.regPassword}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">服务期限</label>
            <div class="layui-input-inline">
                <input type="text" name="servicePeriod"  autocomplete="off"
                       placeholder="请输入服务期限" class="layui-input" value="${ordd.servicePeriod}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">服务到期日</label>
            <div class="layui-input-inline">
                <input type="text" name="expireDate" id="workdate"
                       placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="${ordd.expireDate}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>销售数量</label>
            <div class="layui-input-inline">
                <input type="text" name="prodCount" lay-verify="required" autocomplete="off"
                       placeholder="请输入销售数量" class="layui-input" value="${ordd.prodCount}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>总金额</label>
            <div class="layui-input-inline">
                <input type="text" name="totalMoney" lay-verify="required" autocomplete="off"
                       placeholder="请输入总金额" class="layui-input" value="${ordd.totalMoney}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>开票人</label>
            <div class="layui-input-inline">
                <input type="text" name="operator" lay-verify="required" autocomplete="off"
                       placeholder="请输入开票人" class="layui-input" value="${ordd.operator}">
            </div>
        </div>
    </div>


    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="remark" value="${ordd.remark}">${ordd.remark}</textarea>
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
