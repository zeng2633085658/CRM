<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>合同扫描附件</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
</head>

<body>
<br/>
<fieldset class="layui-elem-field">
    <legend>
        <div class="demoTable">
            <button class="layui-btn" onclick="x_admin_show('添加合同扫描附件','<%=basePath%>contractAttachServlet?action=init')"><i class="layui-icon"></i>添加</button>
            <button class="layui-btn layui-btn-primary" data-type="gofj"><img src="images/fj.png">附件预览</button>
            <a class="layui-btn layui-btn-primary" href="<%=basePath%>downloadServlet" download="附件.zip" ><img src="images/fj.png">附件下载</a>
        </div>
    </legend>
    <div class="layui-field-box">
        <table class="layui-table" lay-size="sm"
               lay-data="{width: 1110, height:433,
       url:'<%=basePath%>contractAttachServlet?contract_id=${param.contract_id}', page:true, id:'idTest',limit:'50',limits:[50,100,200,500]}" type="checkbox" lay-filter="demo">
            <thead>
            <tr>
                <th lay-data="{type:'checkbox',field:'contractAttach_id' ,fixed: 'center'}"></th>
                <th lay-data="{field:'contract_name',  sort: true, fixed: true}">合同名称</th>
                <th lay-data="{field:'attachFile'}">附件名称</th>
                <th lay-data="{field:'uploadTime'}">上传时间</th>
                <th lay-data="{field:'userName'}">操作员</th>
                <th lay-data="{field:'seq', sort: true}">排序号</th>
                <th lay-data="{fixed: 'right', width:200, align:'center', toolbar: '#barDemo'}"></th>
            </tr>
            </thead>
        </table>
    </div>
</fieldset>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        //监听表格复选框选择
        table.on('checkbox(demo)', function(obj){
            console.log(obj)
        });
        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                x_admin_upload('合同附件','<%=basePath%>uploadFile/'+data.attachURL);
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    layer.close(index);
                    $.post(
                        "<%=basePath%>contractAttachServlet",
                        {
                            "action":"del",
                            "contractAttach_id":data.contractAttach_id

                        },function (data) {
                            obj.del();
                       }
                    );

                });
            } else if(obj.event === 'edit'){
                x_admin_show('修改合同附件','<%=basePath%>contractAttachServlet?action=update&contractAttach_id='+data.contractAttach_id);
            }
        });

        var $ = layui.$, active = {
            gofj: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                var data1 = eval(JSON.stringify(data))
                parent.demo1('合同附件预览','<%=basePath%>conaImages.jsp',${param.contract_id}+800)

            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
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
</body>

</html>
