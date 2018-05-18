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
    <title>派工报表</title>
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
                <label class="layui-form-label">派工时间</label>
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
            ,url: '<%=basePath%>jobCountServlet?action=select'
            ,cols: [[
                {checkbox: true, fixed: true}
                ,{field:'jobId', title: '派工编号', width:80, sort: true, fixed: true}
                ,{field:'orderId', title: '订单号', width:80}
                ,{field:'jobDate', title: '日期', width:80, sort: true}
                ,{field:'prodName', title: '商品名称', width:80}
                ,{field:'custname', title: '客户名称',width:80}
                ,{field:'jobContent', title: '派工内容', sort: true, width:80}
                ,{field:'username', title: '员工信息', sort: true, width:80}
                ,{field:'custEval', title: '客户评价', width:80}
                ,{field:'custSign', title: '客户签名', sort: true, width:80}
                ,{field:'startTime', title: '开工时间', sort: true, width:80}
                ,{field:'endTime', title: '结束时间', sort: true, width:80}
                ,{field:'workDay', title: '人工天数', sort: true, width:80}
                ,{field:'busMoney', title: '交通费', sort: true, width:80}
                ,{field:'attachMoney', title: '补助费', sort: true, width:80}
                ,{fixed: 'right', width:200, align:'center', toolbar: '#barDemo'}
            ]]
            ,id: 'testReload'
            ,page: true
            ,height: 433
            ,width:1100
            ,size:"sm"
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
