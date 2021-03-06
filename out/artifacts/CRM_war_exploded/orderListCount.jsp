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
    <title>订单报表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
</head>

<body>
<br/>
<fieldset class="layui-elem-field"><br/>
    <div class="demoTable">
        <form class="layui-form">
            <div class="layui-inline">
                <label class="layui-form-label">客户名称</label>
                <div class="layui-input-block" style="width: 150px">
                    <select name="customerInfoVos" id="customerInfoVos" lay-search>
                        <option value="0">未选择</option>
                        <c:forEach items="${customerInfoVos}" var="c">
                            <option value="${c.custId}">${c.custname}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">员工</label>
                <div class="layui-input-block" style="width: 150px">
                    <select name="usersVos" id="usersVos" lay-search>
                        <option value="0">未选择</option>
                        <c:forEach items="${usersVos}" var="u">
                            <option value="${u.userid
                            }">${u.username}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">下单时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="expireDate" id="workdate"
                           autocomplete="off" class="layui-input">
                </div>
                <button class="layui-btn" data-type="reload" type="button">搜索</button>
            </div>
        </form>

    </div>

    <div class="layui-field-box">
        <table class="layui-table" lay-size="sm" id="LAY_table_user" lay-filter="user" ></table>
    </div>
</fieldset>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript">
    layui.use('table', function(){
        var table = layui.table;

        //方法级渲染
        table.render({
            elem: '#LAY_table_user'
            ,url: '<%=basePath%>ordersCountServlet?action=select'
            ,cols: [[
                {checkbox: true, fixed: true}
                ,{field:'orderId', title: '订单编号', width:150, sort: true, fixed: true}
                ,{field:'custname', title: '客户名称', width:80}
                ,{field:'username', title: '业务员', width:80, sort: true}
                ,{field:'orderType', title: '订单类别', width:80}
                ,{field:'orderStatusName', title: '订单状态', width:80}
                ,{field:'totalMoney', title: '金额',width:80}
                ,{field:'oprtime', title: '开票时间', sort: true, width:80}
                ,{field:'operatorName', title: '开票人', sort: true, width:80}
                ,{field:'remark', title: '备注', width:100}
                ,{fixed: 'right', width:200, align:'center', toolbar: '#barDemo'}
            ]]
            ,id: 'testReload'
            ,page: true
            ,height: 433
            ,width:1100
            ,size:"sm"
            ,limit:500
            ,limits:['500','1000','1500']

        });
        //监听工具条
        table.on('tool(user)', function(obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                x_admin_pwd('订单明细', '<%=basePath%>odList.jsp?orderId=' + data.orderId);
            }
        });

        var $ = layui.$, active = {
            reload: function(){

                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        customerInfoVos:$("#customerInfoVos").val(),
                        usersVos:$("#usersVos").val(),
                        workdate:$("#workdate").val(),
                        action:"select"

                    }
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
<script>
    layui.use(['form', 'laydate'], function () {
        var form = layui.form
            , laydate = layui.laydate;


        laydate.render({
            elem: '#workdate',
            range: '~'
            , calendar: true
        });
    });
</script>

</body>

</html>
