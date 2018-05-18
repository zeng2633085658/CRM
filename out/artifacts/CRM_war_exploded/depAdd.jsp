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
    <title>新增部门</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
</head>
<body>




    <!-- 内容主体区域 -->
    <blockquote class="layui-elem-quote"><h2>新增部门资料</h2></blockquote>


    <form class="layui-form" action="${pageContext.request.contextPath }/depServlet?action=add" method="post" name="form1">
        <div class="layui-form-item">
            <label class="layui-form-label">部门名称：</label>

            <div class="layui-input-block">
                <input type="text" name="depname" lay-verify="title" autocomplete="off" placeholder="请输入部门名称" style="width: 60%;" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">负责人：</label>
            <div class="layui-input-block">
                <input type="text" name="chairman" lay-verify="required" placeholder="请输入负责人" style="width: 60%;" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">部门描述：</label>
            <div class="layui-input-block">
                <textarea name="description" placeholder="请输入描述内容" style="width: 60%;" class="layui-textarea"></textarea>
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
                        return '部门名称至少得2个字符啊';
                    }else{
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
