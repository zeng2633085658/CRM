<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath%>">
    <title>新增岗位</title>
    <link rel="shortcut icon" href="<%=basePath%>images/mainicon.png" type="image/x-icon" />
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

    <!-- 内容主体区域 -->
    <blockquote class="layui-elem-quote"><h2>新增岗位资料</h2></blockquote>

    <form class="layui-form" action="${pageContext.request.contextPath }/degreesServlet?action=add" method="post" name="form1">
        <div class="layui-form-item">
            <label class="layui-form-label">岗位名称：</label>

            <div class="layui-input-block">
                <input type="text" name="degreename" lay-verify="title" autocomplete="off" style="width: 60%;" placeholder="请输入岗位名称" style="width: 60%;" class="layui-input">
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
        layui.use(['form'], function(){
            var form = layui.form
                ,layer = layui.layer;


            //自定义验证规则
            form.verify({
                title: function(value){
                    if(value.length < 2){
                        return '岗位名称至少得2个字符啊';
                    }else {
                        x_admin_close();
                    }
                }
            });


        });
    </script>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
</script>


</body>
</html>
