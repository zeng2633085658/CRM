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
    <title>商品入库</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
</head>

<body>
<br/>
<fieldset class="layui-elem-field">
    <legend>

        <div class="demoTable">
            <button class="layui-btn" onclick="x_admin_show('添加入库资料','<%=basePath%>ordersServlet?action=countInit')"><i
                    class="layui-icon"></i>添加
            </button>
            <button class="layui-btn layui-btn-primary" data-type="goddxx"><img src="images/ddxx.png">入库详细</button>
            <button class="layui-btn layui-btn-primary" data-type="status1"><img src="images/ddxx.png">已入库</button>
            <button class="layui-btn layui-btn-primary" data-type="status2"><img src="images/ddxx.png">已审核</button>

        </div>
    </legend>
    <div class="layui-field-box">
        <table class="layui-table" lay-size="sm"
               lay-data="{width: 1110, height:433,
       url:'<%=basePath%>ordersServlet?orderType=1', page:true, id:'idTest',limit:'500',limits:[500,1000,2000,5000]}"
               type="checkbox" lay-filter="demo">
            <thead>
            <tr>
                <th lay-data="{type:'checkbox',field:'orderStatus1', fixed: 'center'}"></th>
                <th lay-data="{field:'orderId', sort: true, fixed: true,width:130}">编号</th>
                <th lay-data="{field:'custname', sort: true,width:120}">供应商</th>
                <th lay-data="{field:'username', sort: true}">业务员</th>
                <th lay-data="{field:'process'}">采购方式</th>
                <th lay-data="{field:'orderStatusName1'}">入库状态</th>
                <th lay-data="{field:'totalMoney'}">金额</th>
                <th lay-data="{field:'oprtime',width:100}">开票时间</th>
                <th lay-data="{field:'operatorName'}">开票人</th>
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
    layui.use('table', function () {
        var table = layui.table;
        //监听表格复选框选择
        table.on('checkbox(demo)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                x_admin_pwd('订单详情', '<%=basePath%>odList.jsp?orderId=' + data.orderId);
            } else if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    layer.close(index);
                    $.post(
                        "<%=basePath%>ordersServlet",
                        {
                            "action": "delSupplier",
                            "orderId": data.orderId,
                            "orderStatus": data.orderStatus
                        }, function (data) {
                            if (data) {
                                layer.msg(data);
                            } else {
                                obj.del();
                            }
                        }, "text"
                    );

                });
            } else if (obj.event === 'edit') {
                <%--x_admin_show('修改订单资料','<%=basePath%>ordersServlet?action=update&orderId='+data.orderId);--%>
            }
        });

        var $ = layui.$, active = {
            goddxx: function () { //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    , data = checkStatus.data;
                if (data.length == 1) {
                    var data1 = eval(JSON.stringify(data))
                    if (parent.ishaveddxx()) {
                        layer.msg('只能打开一个客户订单详细');
                    } else {
                        parent.demo1('入库详细', '<%=basePath%>odList.jsp?orderId=' + data1[0].orderId, data1[0].orderId);
                    }
                } else {
                    layer.msg('必须选择一个客户订单');
                }
            },
            status1: function (obj) { //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    , data = checkStatus.data;
                if (data.length == 1) {
                    var data1 = eval(JSON.stringify(data));

                    $.post(
                        "<%=basePath%>ordersServlet",
                        {
                            "action": "statusSupplier",
                            "orderId": data1[0].orderId,
                            "orderStatus": "2"
                        }, function (data) {
                            if (data) {
                                layer.msg(data);
                            } else {
                                $(".layui-laypage-btn").click();
                            }


                        }, "text"
                    );
                } else {
                    layer.msg("只能选中一个订单");
                }
            },
            status2: function () { //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    , data = checkStatus.data;
                if (data.length == 1) {
                    var data1 = eval(JSON.stringify(data));
                    $.post(
                        "<%=basePath%>ordersServlet",
                        {
                            "action": "statusSupplier",
                            "orderId": data1[0].orderId,
                            "orderStatus": "3"
                        }, function (data) {
                            if (data) {
                                layer.msg(data);
                            } else {
                                $(".layui-laypage-btn").click();
                            }
                        }, "text"
                    );
                } else {
                    layer.msg("只能选中一个订单");
                }
            }

        };

        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });


</script>

</body>

</html>
