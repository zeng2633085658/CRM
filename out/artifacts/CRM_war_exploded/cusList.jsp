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
    <title>客户资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <jsp:include page="/IncludeJS.jsp"></jsp:include>

</head>

<body>
<br/>
<fieldset class="layui-elem-field">
    <legend>
        <div class="demoTable">
            <button class="layui-btn" onclick="x_admin_show('添加客户资料','<%=basePath%>customerInfoServlet?action=init')"><i class="layui-icon"></i>添加</button>
            <button class="layui-btn layui-btn-primary" data-type="gohetong"><img src="images/ht.png">合同</button>
            <button class="layui-btn layui-btn-primary" data-type="godingdan"><img src="images/dd.png">订单</button>
            <button class="layui-btn layui-btn-primary" data-type="gopaigong"><img src="images/pg.png">派工</button>
            <button class="layui-btn layui-btn-primary" data-type="goqiatan"><img src="images/qt.png">商务洽谈</button>
            <button class="layui-btn layui-btn-primary" data-type="golianxi"><img src="images/lx.png">联系</button>

        </div>
    </legend>
    <div class="layui-field-box">
        <table class="layui-table" lay-size="sm"
               lay-data="{width: 1110, height:433,
       url:'<%=basePath%>customerInfoServlet', page:true, id:'idTest',limit:'3',limits:[3,10,20,50]}"
               type="checkbox" lay-filter="demo" >
            <thead>
            <tr>
                <th lay-data="{type:'checkbox', fixed: 'center'}"></th>
                <th lay-data="{field:'custId', sort: true, fixed: true}">编号</th>
                <th lay-data="{field:'custname'}">名称</th>
                <th lay-data="{field:'custtype'}">行业</th>
                <th lay-data="{field:'bankAccount'}">银行账号</th>
                <th lay-data="{field:'bankName'}">开户银行</th>
                <th lay-data="{field:'contact'}">联系人</th>
                <th lay-data="{field:'phone'}">手机</th>
                <th lay-data="{field:'taxNo'}">纳税登记号</th>
                <th lay-data="{field:'custState'}">客户状态</th>
                <th lay-data="{field:'username'}">业务员</th>
                <th lay-data="{field:'source'}">信息来源</th>
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
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                x_admin_show('修改客户资料','<%=basePath%>customerInfoServlet?action=update&custId='+data.custId);
            } else if(obj.event === 'hetong'){
                layer.msg('ID：'+ data.custId + ' 的合同操作');
            }
        });

        var $ = layui.$, active = {
            gohetong: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length==1){
                    var data1 = eval(JSON.stringify(data))
                    if(parent.ishave()){
                        layer.msg('只能打开一个客户合同');
                    }else{
                        parent.demo1(data1[0].custname+'的合同','<%=basePath%>contractList.jsp?custId='+data1[0].custId,data1[0].custId+10000)
                    }
                }else{
                    layer.msg('必须选择一个客户合同');
                }
            }
            ,godingdan: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length==1){
                    var data1 = eval(JSON.stringify(data))
                    if(parent.ishavedd()){
                        layer.msg('只能打开一个客户订单');
                    }else{
                        parent.demo1(data1[0].custname+'的订单','<%=basePath%>ordList.jsp?custid='+data1[0].custId,data1[0].custId+30000)
                    }
                }else{
                    layer.msg('必须选择一个订单');
                }
            }
            ,gopaigong: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length==1){
                    var data1 = eval(JSON.stringify(data))

                    if(parent.ishavepg()){
                        layer.msg('只能打开一个客户的派工情况');
                    }else{
                        parent.demo1(data1[0].custname+'的派工情况','<%=basePath%>jobList.jsp?custid='+data1[0].custId,data1[0].custId+40000)
                    }
                }else{
                    layer.msg('必须选择一个客户的派工情况');
                }
            }

            ,goqiatan: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length==1){
                    var data1 = eval(JSON.stringify(data))
                    if(parent.ishaveqt()){
                        layer.msg('只能打开一个客户的商业洽谈');
                    }else{
                        parent.demo1(data1[0].custname+'的商业洽谈','<%=basePath%>busList.jsp?custId='+data1[0].custId,data1[0].custId+50000)
                    }
                }else{
                    layer.msg('必须选择一个客户商业洽谈');
                }
            }
            ,golianxi: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length==1){
                    var data1 = eval(JSON.stringify(data))
                    if(parent.ishavelx()){
                        layer.msg('只能打开一个客户联系');
                    }else{
                        parent.demo1(data1[0].custname+'的联系','<%=basePath%>conList.jsp?custId='+data1[0].custId,data1[0].custId+70000)
                    }
                }else{
                    layer.msg('必须选择一个客户联系');
                }
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
