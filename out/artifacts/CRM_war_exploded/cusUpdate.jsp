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
    <title>修改客户</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>修改客户资料</h2></blockquote>

<form class="layui-form" action="<%=basePath%>customerInfoServlet?action=updateSave" method="post" name="form1">
    <input type="hidden" name="custId" value="${cus.custId}">
    <div class="layui-form-item">
        <label class="layui-form-label"><span style="color: red;">*</span>客户名称</label>
        <div class="layui-input-block">
            <input type="text" style="width: 32%;" name="title" lay-verify="title" autocomplete="off"
                   placeholder="请输入客户名称" class="layui-input" value="${cus.custname}">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>行业</label>
            <div class="layui-input-inline">
                <input type="text" name="custtype" lay-verify="required" autocomplete="off"
                       placeholder="请输入客户行业" class="layui-input" value="${cus.custtype}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>银行账号</label>
            <div class="layui-input-inline">
                <input type="text" name="bankAccount" lay-verify="required" autocomplete="off"
                       placeholder="请输入银行账号" class="layui-input" value="${cus.bankAccount}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>开户银行</label>
            <div class="layui-input-inline">
                <input type="text" name="bankName" lay-verify="required" autocomplete="off"
                       placeholder="请输入开户银行" class="layui-input" value="${cus.bankName}">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>联系人</label>
            <div class="layui-input-inline">
                <input type="text" name="Contact" lay-verify="required" autocomplete="off"
                       placeholder="请输入联系人" class="layui-input" value="${cus.contact}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>手机</label>
            <div class="layui-input-inline">
                <input type="tel" id="Phone" name="phone" placeholder="请输入手机号码" lay-verify="required|phone"
                       autocomplete="off" class="layui-input" value="${cus.phone}">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">开票名称</label>
            <div class="layui-input-inline">
                <input type="text" name="TicketName" placeholder="请输入开票名称" autocomplete="off"
                       class="layui-input" value="${cus.ticketName}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">开票地址</label>
            <div class="layui-input-inline">
                <input type="text" name="TicketAddr" placeholder="请输入开票地址" autocomplete="off"
                       class="layui-input" value="${cus.ticketAddr}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">开票电话</label>
            <div class="layui-input-inline">
                <input type="text" name="TicketTel" placeholder="请输入开票电话" autocomplete="off"
                       class="layui-input" value="${cus.ticketTel}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">纳税登记号</label>
            <div class="layui-input-inline">
                <input type="text" name="TaxNo" placeholder="请输入纳税登记号" autocomplete="off"
                       class="layui-input" value="${cus.taxNo}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">客户状态</label>
            <div class="layui-input-block">
                <select name="custState" lay-filter="aihao">
                    <c:if test="${cus.custState=='新客户'}">
                        <option value="新客户" selected>新客户</option>
                        <option value="成交客户">成交客户</option>
                        <option value="洽谈客户">洽谈客户</option>
                    </c:if>
                    <c:if test="${cus.custState=='成交客户'}">
                        <option value="新客户">新客户</option>
                        <option value="成交客户" selected>成交客户</option>
                        <option value="洽谈客户">洽谈客户</option>
                    </c:if>
                    <c:if test="${cus.custState=='洽谈客户'}">
                        <option value="新客户">新客户</option>
                        <option value="成交客户">成交客户</option>
                        <option value="洽谈客户" selected>洽谈客户</option>
                    </c:if>

                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">业务员</label>
            <div class="layui-input-block">
                <select name="userid" lay-filter="aihao">
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
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">信息来源</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="source" value="${cus.source}">${cus.source}</textarea>
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
