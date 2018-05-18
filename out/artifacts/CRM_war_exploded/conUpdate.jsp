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
    <title>新增客户联系</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>新增客户联系情况</h2></blockquote>

<form class="layui-form" action="<%=basePath%>contactServlet?action=updateSave" method="post" name="form1">
    <input type="hidden" name="contactId" value="${con.contactId}">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>日期</label>
            <div class="layui-input-inline">
                <input type="text" name="talkDate" id="jobDate" lay-verify="required"
                       placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="${con.talkDate}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>客户联系人</label>
            <div class="layui-input-inline">
                <input type="text" name="custContact"  autocomplete="off" lay-verify="required"
                       placeholder="请输入客户联系人" class="layui-input" value="${con.custContact}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>手机1</label>
            <div class="layui-input-inline">
                <input type="tel" id="phone1" name="phone1" placeholder="请输入手机号码1" lay-verify="required|phone"
                       autocomplete="off" class="layui-input" value="${con.phone1}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>手机2</label>
            <div class="layui-input-inline">
                <input type="tel" id="phone2" name="phone2" placeholder="请输入手机号码2" lay-verify="required|phone"
                       autocomplete="off" class="layui-input" value="${con.phone2}">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>QQ</label>
            <div class="layui-input-inline">
                <input type="text" name="qqCode" placeholder="请输入QQ号码" lay-verify="required"
                       autocomplete="off" class="layui-input" value="${con.qqCode}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" placeholder="请输入邮箱"
                       autocomplete="off" class="layui-input" value="${con.email}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">微信</label>
            <div class="layui-input-inline">
                <input type="text" name="weixin" placeholder="请输入微信号码" autocomplete="off"
                       class="layui-input" value="${con.weixin}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>员工信息</label>
            <div class="layui-input-block">
                <select name="userid" lay-filter="aihao">
                    <c:forEach items="${users}" var="u">
                        <c:if test="${con.userid==u.userid}">
                            <option value="${u.userid}" selected>${u.username}</option>
                        </c:if>
                        <c:if test="${con.userid!=u.userid}">
                            <option value="${u.userid}">${u.username}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>生日</label>
            <div class="layui-input-inline">
                <input type="text" name="birthday" id="birthday" lay-verify="required" autocomplete="off"
                       placeholder="请输入客户生日" class="layui-input" value="${con.birthday}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">喜爱</label>
            <div class="layui-input-inline">
                <input type="text" name="hobbit" autocomplete="off"
                       placeholder="请输入喜爱" class="layui-input" value="${con.hobbit}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">岗位</label>
            <div class="layui-input-inline">
                <input type="text" name="jobName" autocomplete="off"
                       placeholder="请输入岗位" class="layui-input"  value="${con.jobName}">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="remark">${con.remark}</textarea>
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
        laydate.render({
            elem:"#birthday"
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
