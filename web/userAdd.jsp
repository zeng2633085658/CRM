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
    <title>新增用户</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
    <script type="text/javascript">
        $(function () {
            $("#username").keyup(function () {
                $.ajax({
                    url: "<%=basePath%>usersServlet",
                    meth:"post",
                    data: {
                        "action": "isUserName",
                        "username": $("#username").val()
                    },success:function (data) {
                        $("#tishi1").html(data);
                        if(data=="用户名已存在" ){
                            $("#b1").removeClass();
                            $("#b1").attr("disabled","disabled");
                            $("#tishi1").attr("color","red");
                            $("#b1").addClass("layui-btn layui-btn-disabled");
                        }else{
                            $("#tishi1").attr("color","green");
                            $("#b1").removeClass();
                            $("#b1").removeAttr("disabled");
                            $("#b1").addClass("layui-btn");
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>


<blockquote class="layui-elem-quote"><h2>新增用户资料</h2></blockquote>

<form class="layui-form" action="<%=basePath%>usersServlet?action=add" method="post" name="form1">
    <input type="hidden" name="userid" value="${user.userid}" lay-verify="userid">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名称</label>
        <div class="layui-input-block">
            <input type="text" style="width: 32%;" name="title" lay-verify="title" id="username" autocomplete="off"
                   placeholder="请输入用户名称" class="layui-input">
            <span><font id="tishi1" color="red"></font></span></input>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">验证手机</label>
            <div class="layui-input-inline">
                <input type="tel" id="phone" name="phone" placeholder="请输入手机号码" lay-verify="required|phone"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="button" autocomplete="off" value="获取验证码" class="layui-btn" id="getCode">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">输入验证码</label>
            <div class="layui-input-inline">
                <input type="text" id="number" name="number" placeholder="请输入您手机获取的验证码" lay-verify="required|number"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" lay-verify="pass" placeholder="请输入密码" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">联系地址</label>
            <div class="layui-input-inline">
                <input type="tel" name="addr" placeholder="请输入地址" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">身份证</label>
        <div class="layui-input-block">
            <input type="text" name="identity" style="width: 46%;" placeholder="请输入身份证号码"
                   lay-verify="identity" placeholder="" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">QQ</label>
            <div class="layui-input-inline">
                <input type="text" name="qqcode" placeholder="请输入QQ号码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">微信</label>
            <div class="layui-input-inline">
                <input type="text" name="weixin" placeholder="请输入微信号码" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">开户银行</label>
            <div class="layui-input-inline">
                <input type="text" name="bankname" placeholder="请输入开户银行" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">银行账户</label>
            <div class="layui-input-inline">
                <input type="text" name="bankcardno" placeholder="请输入银行账户" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">入职日期</label>
            <div class="layui-input-inline">
                <input type="text" name="joindate" id="joindate" lay-verify="date"
                       placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">转正日期</label>
            <div class="layui-input-inline">
                <input type="text" name="workdate" id="workdate"
                       placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">离职日期</label>
            <div class="layui-input-inline">
                <input type="text" name="levelDate" id="levelDate"
                       placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">基本工资</label>
            <div class="layui-input-inline">
                <input type="text" name="basesalary" placeholder="请输入基本工资" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">岗位工资</label>
            <div class="layui-input-inline">
                <input type="text" name="degreesalary" placeholder="请输入岗位工资" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">部门</label>
        <div class="layui-input-block">
            <select name="depid" lay-filter="aihao">
                <c:forEach items="${depVos}" var="d">
                    <option value="${d.depid}">${d.depname}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">岗位</label>
        <div class="layui-input-block">
            <select name="degreeid" lay-filter="aihao">
                <c:forEach items="${degreesVos}" var="d">
                    <option value="${d.degreeid}">${d.degreename}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">职务</label>
            <div class="layui-input-inline">
                <input type="text" name="jobname" placeholder="请输入职务"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">管理员类别</label>
        <div class="layui-input-block">

            <input type="radio" name="managerType" value="0" title="系统管理员工" checked="">
            <input type="radio" name="managerType" value="1" title="部门经理">
            <input type="radio" name="managerType" value="2" title="财务">
            <input type="radio" name="managerType" value="3" title="员工">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">说明</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="remark"></textarea>
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
            <button class="layui-btn layui-btn-disabled" lay-submit="" id="b1" lay-filter="demo1" disabled>立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary ">重置</button>
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
                    return '用户名称至少得2个字符';
                }
            }
            , pass: [/(.+){6,12}$/, '密码必须6到12位']
            , number: function (value) {
//                if (value != 1) {
//                    return '验证码不正确';
//                }
                if (value != code) {
                    return '验证码不正确';
                }
            }

        });
        //监听提交
        form.on('submit(demo1)', function(data){
            x_admin_close();
            return true;
        });

        //监听指定开关
        form.on('switch(switchTest)', function (data) {
            layer.msg('开关checked：' + (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
            layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });
    });
</script>
<script type="text/javascript">
    var code;
    $(function () {
        //发送验证码
        $("#getCode").click(function () {
            if ($("#phone").val() == "") {
                layer.msg("请先输入手机号!");
                $("#phone").focus();
                return ;
            }
            $.ajax({
                url: "codeServlet?phone=" + $("#phone").val()+"&action=reg"
                , success: function (data) {
                    code = data;
                }
            });
            $("#getCode").removeClass("layui-btn");
            $("#getCode").addClass("layui-btn layui-btn-disabled");
            time();
        });

    });
    //验证码倒计时
    var wait = 60;
    function time(obj) {
        if (wait == 0) {
            $("#getCode").removeAttr("disabled");
            $("#getCode").val("获取验证码");
            $("#getCode").removeClass("layui-btn layui-btn-disabled");
            $("#getCode").addClass("layui-btn");
            wait = 60;
        } else {
            $("#getCode").attr("disabled", "true");
            $("#getCode").val(wait + "秒后重试");
            wait--;
            setTimeout(function () {     //倒计时方法
                time(obj);
            }, 1000);    //间隔为1s
        }
    }

</script>

</body>
</html>
