<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>客户管理系统登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="<%=basePath%>css/font.css">
    <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>lib/css/verify.css">
    <link rel="shortcut icon" href="<%=basePath%>images/mainicon.png" type="image/x-icon"/>
    <script src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
    <script src="<%=basePath%>js/skel.min.js"></script>
    <script src="<%=basePath%>js/init.js"></script>

</head>
<body>
<div id="wrapper">
    <div id="bg"></div>
    <div id="overlay"></div>
    <div id="main">
        <!-- Header -->
        <header id="header">
            <div class="login">
                <div class="message">客户关系管理系统</div>
                <div id="darkbannerwrap"></div>
                <form method="post" class="layui-form" action="<%=basePath%>loginServlet" name="form1">
                    <p style="font-size: 3px;color: red">${msg}</p>
                    <input name="username" placeholder="用户名" type="text" lay-verify="username" class="layui-input" id="username">
                    <hr class="hr15">
                    <input name="password"  placeholder="密码" type="password"  lay-verify="password"  class="layui-input" id="password">
                    <hr class="hr15">
                    <div id="mpanel1"></div>
                    <hr class="hr15">
                    <button id="but1" lay-submit="" lay-filter="demo1" class="layui-btn layui-btn layui-btn-disabled" style="width: 100%;height: 100%;" disabled>登录
                    </button>
                    <hr class="hr20">
                </form>
            </div>
        </header>
        <footer id="footer">
            <span class="copyright">&copy; Younglim & zyp.</span>
        </footer>
    </div>
</div>
<script type="text/javascript" src="lib/js/jquery.min.js"></script>
<script type="text/javascript" src="lib/js/verify.js"></script>
<!--<script type="text/javascript" src="js/verify.min.js" ></script>-->
<script>
    layui.use(['form'], function () {
        var form = layui.form;

        //自定义验证规则
        form.verify({
            username: function (value) {
                if (value== "") {
                    return '请输入账号';
                }
            },
            password: function (value) {
                if (value== "") {
                    return '请输入密码';
                }
            }

        });
        //监听提交
        form.on('submit(demo1)', function(data){

            return true;
        });

    });
</script>
<script>
    $('#mpanel1').slideVerify({
        type: 1,		//类型
        vOffset: 5,	//误差量，根据需求自行调整
        barSize: {
            width: '100%',
            height: '40px',
        },
        ready: function () {
        },
        success: function () {
            $("#but1").removeClass();
            $("#but1").addClass("layui-btn");
            $("#but1").removeAttr("disabled");
            //......后续操作
        },
        error: function () {
//		        	alert('验证失败！');
        }

    });

</script>
</body>
</html>
