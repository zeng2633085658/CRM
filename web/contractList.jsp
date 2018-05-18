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
    <title>合同资料</title>
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
            <button class="layui-btn" onclick="x_admin_show('添加合同','<%=basePath%>contractServlet?action=init')"><i class="layui-icon"></i>添加</button>
            <button class="layui-btn layui-btn-primary" data-type="gohetongfj"><img src="images/fj.png">附件</button>
        </div>
    </legend>
    <div class="layui-field-box">
        <table class="layui-table" lay-size="sm"
               lay-data="{width: 1110, height:433,
       url:'<%=basePath%>contractServlet?custId=${param.custId}', page:true, id:'idTest',limit:'50',limits:[50,100,200,500]}"
               type="checkbox" lay-filter="demo" >
            <thead>
            <tr>
                <th lay-data="{type:'checkbox', fixed: 'center'}"></th>
                <th lay-data="{field:'contract_id', sort: true, fixed: true}">ID</th>
                <th lay-data="{field:'contract_no',width:'80'}">合同编号</th>
                <th lay-data="{field:'custname',width:'80'}">客户名称</th>
                <th lay-data="{field:'contract_time',width:'80'}">签订时间</th>
                <th lay-data="{field:'due_time',width:'80'}">到期时间</th>
                <th lay-data="{field:'total_money',width:'80'}">金额</th>
                <th lay-data="{field:'deposit',width:'90'}">有效期(年)</th>
                <th lay-data="{field:'pay_type',width:'80'}">支付类别</th>
                <th lay-data="{field:'status',width:'80'}">状态</th>
                <th lay-data="{field:'empName',width:'70'}">业务员</th>
                <th lay-data="{field:'operatorName',width:'70'}">操作员</th>
                <th lay-data="{field:'oprtime',width:'80'}">操作时间</th>
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
                <%--parent.demo1(data.custname+'的合同附件预览','<%=basePath%>conaImages.jsp?contract_id='+data.contract_id,data.contract_id+10000)--%>
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    layer.close(index);
                    $.post(
                        "<%=basePath%>contractServlet",
                        {
                            "action":"del",
                            "contract_id":data.contract_id
                        },function (data) {
                            if(data){
                                layer.msg('不能删除有附件的合同');
                            }else {
                                obj.del();
                            }
                        }
                    );
                });
            } else if(obj.event === 'edit'){
                x_admin_show('修改合同','<%=basePath%>contractServlet?action=update&contract_id='+data.contract_id);
            }
        });

        var $ = layui.$, active = {
            gohetongfj: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length==1){
                    var data1 = eval(JSON.stringify(data))
                    if(parent.ishavefj()){
                        layer.msg('只能打开一个合同附件');
                    }else{
                        parent.demo1(data1[0].custname+'的合同附件','<%=basePath%>conaList.jsp?contract_id='+data1[0].contract_id,data1[0].contract_id+20000)
                    }
                }else{
                    layer.msg('只能选择一个合同附件');
                }
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
