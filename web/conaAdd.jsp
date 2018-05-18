<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>新增合同扫描附件</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>

    <style>
        .layer-photos-demo{margin:10px 0;}
        .layer-photos-demo img{width: 160px; height: 100px;}
    </style>
    <script type="text/javascript">
    $(function () {
        $("#btn1").click(function () {
            x_admin_upload('1','<%=basePath%>uploadFile/'+$("#test").val());
        });
    });

    function x_admin_upload(title,url,w,h){
        if (title == null || title == '') {
            title=false;
        };
        if (url == null || url == '') {
            url="404.html";
        };
        if (w == null || w == '') {
            w=($(window).width()*0.9);
        };
        if (h == null || h == '') {
            h=($(window).height() - 50);
        };
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            area: [w,h],
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: '<img style="width:'+w+'px;height:'+h+'px" src="'+url+'">'
        });
    }
</script>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>新增合同扫描附件</h2></blockquote>

<form class="layui-form" action="<%=basePath%>contractAttachServlet?action=add" method="post" name="form1">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>排序号</label>
            <div class="layui-input-inline">
                <input type="text" name="seq" lay-verify="required" autocomplete="off"
                       placeholder="请输入排序号" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>附件名称</label>
            <div class="layui-input-inline">
                <input type="text" name="attachFile" lay-verify="required" autocomplete="off"
                       placeholder="请输入附件名称" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>附件上传</label>
            <div class="layui-input-inline">
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="test1">上传图片</button>
                    <button type="button" class="layui-btn" id="btn1">查看</button>
                    <input type="hidden" id="test" name="attachURL">
                    <div class="layer-photos-demo" >
                        <p id="img1"><img class="layui-upload-img" id="demo1"></p>
                        <p id="demoText"></p>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">操作员</label>
            <div class="layui-input-block">
                <input type="text"  style="width: 32%;" name="user_name" lay-verify="title" autocomplete="off"  class="layui-input" value="${user.username}" disabled>
            </div>
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
    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: '<%=basePath%>uploadServlet'
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.fileCount > 0){
                    return layer.msg('上传失败');
                }else{
                    $("#test").val(res.fileName);
                    return layer.msg('上传成功');
                }
//                if(res.error!=""){
//                    return layer.msg('图片不能超过3MB');
//                }
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });



    });
</script>
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
