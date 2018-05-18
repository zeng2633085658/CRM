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
    <title>修改商务洽谈</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>修改商务洽谈</h2></blockquote>

<form class="layui-form" action="<%=basePath%>businessServlet?action=updateSave" method="post" name="form1">
    <input type="hidden" name="businessIds" value="${business.businessId}">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>派工编号</label>
            <div class="layui-input-inline">
                <input type="text" name="businessId" autocomplete="off"
                       class="layui-input" value="${business.businessId}" disabled>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>商品信息</label>
            <div class="layui-input-block">
                <select name="prodid" lay-filter="aihao">
                    <c:forEach items="${product}" var="u">
                        <c:if test="${business.prodid==u.prodid}">
                            <option value="${u.prodid}" selected>${u.prodname}</option>
                        </c:if>
                        <c:if test="${business.prodid!=u.prodid}">
                            <option value="${u.prodid}" >${u.prodname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>日期</label>
            <div class="layui-input-inline">
                <input type="text" name="busDate" id="jobDate" lay-verify="required"
                       placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="${business.busDate}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>洽谈内容</label>
            <div class="layui-input-inline">
                <input type="text" name="chatContent"  autocomplete="off" lay-verify="required"
                       placeholder="请输入洽谈内容" class="layui-input" value="${business.chatContent}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>洽谈情况</label>
            <div class="layui-input-inline">
                <input type="text" name="chatResult" autocomplete="off" lay-verify="required"
                       placeholder="请输入洽谈情况" class="layui-input" value="${business.chatResult}">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>客户联系人</label>
            <div class="layui-input-inline">
                <input type="text" name="custContact" autocomplete="off" lay-verify="required"
                       placeholder="请输入客户联系人" class="layui-input" value="${business.custContact}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">客户电话</label>
            <div class="layui-input-inline">
                <input type="tel" id="phone" name="phone" placeholder="请输入手机号码" lay-verify="required|phone"
                       autocomplete="off" class="layui-input" value="${business.phone}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>员工信息</label>
            <div class="layui-input-block">
                <select name="userid" lay-filter="aihao">
                    <c:forEach items="${usersVos}" var="u">
                        <c:if test="${u.userid==business.userid}">
                            <option value="${u.userid}" selected>${u.username}</option>
                        </c:if>
                        <c:if test="${u.userid!=business.userid}">
                            <option value="${u.userid}">${u.username}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>报价模块</label>
            <div class="layui-input-inline">
                <input type="text" name="module" lay-verify="required" autocomplete="off"
                       placeholder="请输入报价模块" class="layui-input" value="${business.module}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>报价情况</label>
            <div class="layui-input-inline">
                <input type="text" name="moduleState" lay-verify="required" autocomplete="off"
                       placeholder="请输入报价情况" class="layui-input" value="${business.moduleState}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>报价金额</label>
            <div class="layui-input-inline">
                <input type="text" name="moduleMoney" lay-verify="required" autocomplete="off"
                       placeholder="请输入报价金额" class="layui-input" value="${business.moduleMoney}">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="remark">${business.remark}</textarea>
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
