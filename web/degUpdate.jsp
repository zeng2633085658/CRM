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
    <title>修改部门</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
</head>
<body>


    <!-- 内容主体区域 -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>修改岗位资料</legend>
    </fieldset>

    <form class="layui-form" action="${pageContext.request.contextPath }/degreesServlet?action=updateSave" method="post" name="form1">
        <input type="hidden" value="${degreesVo.degreeid}" name="degreeid">
        <div class="layui-form-item">
            <label class="layui-form-label">岗位名称：</label>

            <div class="layui-input-block">
                <input type="text" name="degreename" lay-verify="title" autocomplete="off" placeholder="请输入部门名称" style="width: 60%;" class="layui-input" value="${degreesVo.degreename}">
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
