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
    <title>修改密码</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>


<blockquote class="layui-elem-quote"><h2>修改用户密码</h2></blockquote>

<form class="layui-form" action="<%=basePath%>usersServlet?action=pwdUpdate" method="post" name="form1">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名称</label>
        <div class="layui-input-block">
                <input type="text"  style="width: 32%;" name="title" lay-verify="title" autocomplete="off"  class="layui-input" value="${user.username}" disabled>
        </div>
    </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red;">*</span>验证手机</label>
                <div class="layui-input-inline">
                    <input type="tel" id="phone" name="phone" placeholder="请输入手机号码"  lay-verify="required|phone" autocomplete="off" class="layui-input">
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
                <label class="layui-form-label"><span style="color: red;">*</span>输入验证码</label>
                <div class="layui-input-inline">
                    <input type="text" id="number" name="number" placeholder="请输入您手机获取的验证码" lay-verify="required|number" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    <div class="layui-form-item">
        <label class="layui-form-label">
            <span style="color: red;">*</span>旧密码
        </label>
        <div class="layui-input-inline">
            <input type="password" id="oldpass" name="oldpass" lay-verify="pass"  placeholder="请输入旧密码"  required=""
                   autocomplete="off" class="layui-input" >
        </div>
        <div class="layui-form-mid layui-word-aux">
            <span><font id="tishi1" color="red"></font></span></input>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">
            <span style="color: red;">*</span>新密码
        </label>
        <div class="layui-input-inline">
            <input type="password" id="newpass" name="newpass" lay-verify="pass"  placeholder="请输入您要设置的新密码"  required=""
                   autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">
            6到16个字符
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">
            <span style="color: red;">*</span>确认密码
        </label>
        <div class="layui-input-inline">
            <input type="password" id="newpass1" name="newpass1" lay-verify="pass"  placeholder="请确认您的密码"  required=""
                   autocomplete="off" class="layui-input" onkeyup="validate()">
        </div>
        <div class="layui-form-mid layui-word-aux">
            <span id="tishi"></span></input>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-disabled" id="b1" lay-submit="" lay-filter="demo1" disabled>立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>

</form>



<script>
    function validate() {
        var pwd1 = document.getElementById("newpass").value;
        var pwd2 = document.getElementById("newpass1").value;
        <!-- 对比两次输入的密码 -->
        if(pwd1 == pwd2) {
            document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
            $("#b1").removeClass();
            $("#b1").removeAttr("disabled");
            $("#b1").addClass("layui-btn");
        }
        else {
            document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>";
            $("#b1").removeClass();
            $("#b1").attr("disabled","disabled");
            $("#b1").addClass("layui-btn layui-btn-disabled");
        }
    }

    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;


        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 2){
                    return '用户名称至少得2个字符啊';
                }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,number:function (value) {
                if(value!=code){
                    return '验证码不正确';
                }
            }

        });
    });
</script>
<script type="text/javascript">
    var code;
    $(function(){
        //发送验证码
        $("#getCode").click(function(){
            if($("#phone").val()==""){
                layer.msg("请先输入手机号!");
                $("#phone").focus();
                return;
            }
            if($("#phone").val()!=${user.mobile}){
                layer.msg("请输入正确的绑定手机");
                $("#phone").focus();
                return;
            }
            $.ajax({
                url:"codeServlet?phone="+$("#phone").val()+"&action=pwd"
                ,success:function (data) {
                    code=data;
                }
            });
            $("#getCode").removeClass("layui-btn");
            $("#getCode").addClass("layui-btn layui-btn-disabled");
            time();
        });
        $("#oldpass").keyup(function () {
            $.ajax({
                url: "<%=basePath%>usersServlet",
                meth:"post",
                data: {
                    "action": "pwdUpdates",
                    "password": $("#oldpass").val()
                },success:function (data) {
                    $("#tishi1").html(data);
                    if(data=="输入错误" ){
                        $("#b1").removeClass();
                        $("#b1").attr("disabled","disabled");
                        $("#b1").addClass("layui-btn layui-btn-disabled");
                    }else{
                        $("#tishi1").attr("color","green");
                    }
                }

            });
        });

    });
    //验证码倒计时
    var wait = 60;
    function time(obj) {
        if(wait==0) {
            $("#getCode").removeAttr("disabled");
            $("#getCode").val("获取验证码");
            $("#getCode").removeClass("layui-btn layui-btn-disabled");
            $("#getCode").addClass("layui-btn");
            wait = 60;
        }else {
            $("#getCode").attr("disabled","true");
            $("#getCode").val(wait+"秒后重试");
            wait--;
            setTimeout(function() {     //倒计时方法
                time(obj);
            },1000);    //间隔为1s
        }
    }

</script>

</body>
</html>
