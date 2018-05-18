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
    <title>商品资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
</head>

<body>
<br/>
<fieldset class="layui-elem-field">
    <legend><button class="layui-btn" onclick="x_admin_show('添加商品','<%=basePath%>productServlet?action=init')"><i class="layui-icon"></i>添加</button>
    </legend>
    <div class="layui-field-box">
        <table class="layui-table" lay-size="sm"
               lay-data="{width: 1110, height:433,
       url:'<%=basePath%>productServlet', page:true, id:'idTest',limit:'10',limits:[10,20,50,100]}"
               type="checkbox" lay-filter="demo" >
            <thead>
            <tr>
                <th lay-data="{type:'checkbox', fixed: 'center'}"></th>
                <th lay-data="{field:'prodid', sort: true, fixed: true,width:150}">ID</th>
                <th lay-data="{field:'prodname',width:130}">名称</th>
                <th lay-data="{field:'prodModel',width:130}">型号</th>
                <th lay-data="{field:'prodUnitName'}">单位</th>
                <th lay-data="{field:'prodStyle',width:130}">规格</th>
                <th lay-data="{field:'prodCount',width:100}">库存</th>
                <th lay-data="{field:'inPrice', sort: true,width:100}">进价</th>
                <th lay-data="{field:'salePrice', sort: true}">售价</th>
                <th lay-data="{field:'prodSerial', width:70, align:'center'}">序列号</th>
                <th lay-data="{field:'cdKey', width:80, align:'center'}">CDKEY</th>
                <th lay-data="{field:'saleStatus',event: 'setSign'}">状态</th>
                <th lay-data="{field:'supplierName', width:120, align:'center'}">供应商</th>
                <th lay-data="{field:'remark'}">描述</th>
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
                layer.msg('ID：'+ data.id + ' 的查看操作');
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    layer.close(index);
                    $.post(
                        "<%=basePath%>productServlet",
                        {
                            "action":"del",
                            "prodid":data.prodid
                        },function (data) {
                            if(data){
                                layer.msg('不能删除已被下单的商品资料');
                            }else {
                                obj.del();
                            }
                        }
                    );
                });
            } else if(obj.event === 'edit'){
                x_admin_show('修改供应商','<%=basePath%>productServlet?action=update&prodid='+data.prodid);
            }else  if(obj.event === 'setSign') {
                layer.confirm('是否确定修改当前状态', function (index) {
                    layer.close(index);
                    //向服务端发送删除指令
                    $.post(
                        "<%=basePath%>productServlet",
                        {
                            "action": "uodateSaleStatus",
                            "prodid": data.prodid,
                            "saleStatus": data.saleStatus
                        },
                        function (data) {
                            obj.update({
                                saleStatus: data
                            });
                        }
                    );

                });
            }
        });

        var $ = layui.$, active = {
            getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ,getCheckLength: function(){ //获取选中数目
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });


</script>

<script>
    layui.use('table', function(){
        var table = layui.table
            ,form = layui.form;

        //监听性别操作
        form.on('switch(sexDemo)', function(obj){
            layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
        });

    });
</script>
</body>

</html>
