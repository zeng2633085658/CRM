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
    <title>收款管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
</head>

<body>
<br/>
<fieldset class="layui-elem-field">
    <legend><button class="layui-btn" onclick="x_admin_show('添加收款管理','<%=basePath%>financeServlet?action=init')"><i class="layui-icon"></i>添加</button>
    </legend>
    <div class="layui-field-box">
        <table class="layui-table" lay-size="sm"
               lay-data="{width: 1110, height:433,
       url:'<%=basePath%>financeServlet', page:true, id:'idTest',limit:'3',limits:[3,10,20,50]}"
               type="checkbox" lay-filter="demo" >
            <thead>
            <tr>
                <th lay-data="{type:'checkbox', fixed: 'center'}"></th>
                <th lay-data="{field:'financeId',width:70, sort: true, fixed: true}">编号</th>
                <th lay-data="{field:'orderId',width:80, sort: true}">订单号</th>
                <th lay-data="{field:'prodid',width:80}">商品名称</th>
                <th lay-data="{field:'paidtypename',width:80}">收款方式</th>
                <th lay-data="{field:'remainMoney',width:80}">应收金额</th>
                <th lay-data="{field:'paidMoney',width:80}">收款金额</th>
                <th lay-data="{field:'orderMoney',width:80}">订单金额</th>
                <th lay-data="{field:'paidPerson',width:70}">交款人</th>
                <th lay-data="{field:'inbank',width:80}">入账银行</th>
                <th lay-data="{field:'bankAccount',width:80}">入账账号</th>
                <th lay-data="{field:'outbank',width:80}">出账银行</th>
                <th lay-data="{field:'warrant',width:90}">相关凭证号</th>
                <th lay-data="{field:'paidTime',width:80}">交款时间</th>
                <th lay-data="{field:'paidinTime',width:80}">到账日期</th>
                <th lay-data="{field:'invalid',width:80}">是否有效</th>
                <th lay-data="{field:'userid',width:70}">操作人</th>
                <th lay-data="{field:'oprtime',width:80}">录入时间</th>
                <th lay-data="{field:'oprType',width:80}">操作类别</th>
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
                layer.msg('ID：'+ data.jobId + ' 的查看操作');
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                x_admin_show('修改收款管理','<%=basePath%>financeServlet?action=update&financeId='+data.financeId);
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

</body>

</html>
