<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath%>">
    <title>新增入库单</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>新增入库单</h2></blockquote>

<div class="layui-form">

    <fieldset class="layui-elem-field">
        <legend>订单信息</legend>

    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>入库编号</label>
            <div class="layui-input-block">
                <input type="text" name="orderId" lay-verify="required" autocomplete="off"
                       class="layui-input" value="${orderId}" disabled>
                <input type="hidden" name="orderId" lay-verify="required" autocomplete="off"
                       class="layui-input" value="${orderId}" id="orderId">
            </div>
        </div>



        <div class="layui-inline">
            <label class="layui-form-label">入库时间</label>
            <div class="layui-input-inline">
                <input type="text" name="oprtime" id="workdate"
                       placeholder="yyyy-MM-dd" autocomplete="off" value="${time}" class="layui-input">
            </div>
        </div>

    </div>



    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>业务人员</label>
            <div class="layui-input-block">
                <select name="userid" lay-filter="aihao" id="userid" style="width: 200px;height: 20px;margin-top: 10px">
                    <c:forEach items="${usersVos}" var="u">
                        <option value="${u.userid}">${u.username}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">

            <label class="layui-form-label">入库说明</label>
            <div class="layui-input-block">
                <input type="text" name="remark"  id="remark" autocomplete="off"
                       placeholder="请输入订单说明" class="layui-input">
            </div>

        </div>
    </div>
    </fieldset>

    <fieldset class="layui-elem-field">
        <legend>商品信息</legend>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red;">*</span>供应商名称</label>
                <div class="layui-input-block">
                    <select name="supplierId" id="supplierId" lay-filter="supplierId" lay-search >
                        <option value="0" selected>未选择</option>
                        <c:forEach items="${supplierVos}" var="u">
                            <option value="${u.supplierId}" >${u.supplierName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red;">*</span>商品名称</label>
                <div class="layui-input-block">
                    <select name="prodid" id="prodid" lay-filter="prodid" class="prodid" lay-search>
                        <option value="0">未选择</option>
                    </select>
                </div>
            </div>


            <div class="layui-inline">
                <label class="layui-form-label"><span style="color: red;">*</span>商品单位</label>
                <div class="layui-input-block">
                    <div class="layui-input-inline">
                                <input type="text"  autocomplete="off"
                                       value="未选择" class="layui-input" id="unitId" disabled>
                        <input type="hidden" name="unitId"  autocomplete="off"
                               value="0" class="layui-input" id="unitIds" disabled>

                </div>
            </div>

    </div>


    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>采购金额</label>
            <div class="layui-input-inline">
                <input type="text" name="saleMoney" lay-verify="required" autocomplete="off"
                       placeholder="请输入采购金额" class="layui-input" id="saleMoney">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>采购数量</label>
            <div class="layui-input-inline">
                <input type="text" name="prodCount" lay-verify="required" autocomplete="off"
                       placeholder="请输入采购数量" class="layui-input" id="prodCount">
            </div>
        </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">注册联系人</label>
            <div class="layui-input-inline">
                <input type="text" name="regPerson"  id="regPerson" autocomplete="off"
                       placeholder="请输入注册联系人" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>采购方式</label>
            <div class="layui-input-block">
                <select name="status" lay-filter="aihao" id="status" style="width: 200px;height: 20px;margin-top: 10px">
                    <option value="市场采购" selected="">市场采购</option>
                    <option value="其他采购">其他采购</option>
                </select>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">注册密码</label>
            <div class="layui-input-inline">
                <input type="text" name="regPassword" autocomplete="off"
                       placeholder="请输入注册密码" class="layui-input" id="regPassword">
            </div>
        </div>
    </div>


    <%--<div class="layui-form-item">--%>
        <%--<div class="layui-inline">--%>
            <%--<label class="layui-form-label">服务期限</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<input type="text" name="servicePeriod"  autocomplete="off"--%>
                       <%--placeholder="请输入服务期限" class="layui-input">--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="layui-inline">--%>
            <%--<label class="layui-form-label">服务到期日</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<input type="text" name="expireDate" id="workdate"--%>
                       <%--placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" onclick="check();" data-type="reload" >立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
        </div>
    </fieldset>
    <div class="layui-field-box">
        <table class="layui-hide" id="demo" lay-filter="user"></table>
    </div>

</div>

<script type="text/javascript">
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

//        //下拉框事件监听
        form.on('select(prodid)', function(data){
            $.post(
                "<%=basePath%>ordersServlet",
                {
                    "action":"price",
                    "prodid":$("#prodid").val()
                },function (data) {
                    $("#saleMoney").val(data.price);
                    $("#unitIds").val(data.unitId);
                    $("#unitId").val(data.unitName);
                    $("#prodCount").val(1);
                },"json"
            );
        });
        form.on('select(supplierId)', function(data){
            $.post(
                "<%=basePath%>ordersServlet",
                {
                    "action":"supplier",
                    "supplierId":$("#supplierId").val()
                },function (data) {
                    var datas=data.product;
                    if(datas.length==0){
                        $("#saleMoney").val('');
                        $("#unitIds").val(0);
                        $("#unitId").val('未选择');
                        $("#prodCount").val('');
                    }
                    var str="<option value='0'>未选择</option>";
                    for(var i=0;i<datas.length;i++){
                        str+="<option value='"+datas[i].prodid+"'>"+datas[i].prodname+"</option>"
                    }
                    $("#prodid").html(str);
                    form.render('select');
                    $("#prodid").get(0).selectedIndex=0;
                },"json"
            );
        });

    });
</script>


<script>
    $("#prodid").change(function () {

    });
    $("#supplierId").change(function () {
        $("#prodid").html("<option value=\"0\" selected>未选择</option>");
        $.post(
            "<%=basePath%>ordersServlet",
            {
                "action":"supplier",
                "supplierId":$("#supplierId").val()
            },function (data) {
                var datas=data.product;
                var str="";
                for(var i=0;i<datas.length;i++){
                    str+="<option value='"+datas[i].prodid+"'>"+datas[i].prodname+"</option>"
                }
                if(str==""){
                    $("#prodid").html("<option value=\"0\" selected>未选择</option>");
                }else {
                    $("#prodid").html(str);
                }
                $.post(
                    "<%=basePath%>ordersServlet",
                    {
                        "action":"prices",
                        "prodid":datas[0].prodid
                    },function (data) {
                        $("#saleMoney").val(data.price);
                        $("#unitIds").val(data.unitId);
                        $("#unitId").val(data.unitName);
                        $("#prodCount").val(1);
                    },"json"
                );

            },"json"
        );
    });
    function check() {
        if($("#prodid").val()=="0"){
            layer.msg("请选择一个商品");
            $("#prodid").focus();
            return ;
        }
        if($("#saleMoney").val()==""){
            layer.msg("请输入金额");
            $("#saleMoney").focus();
            return ;
        }
        if($("#prodCount").val()==""){
            layer.msg("请输入数量");
            $("#prodCount").focus();
            return ;
        }
        if(isNaN($("#saleMoney").val())){
            layer.msg("请输入数字");
            $("#saleMoney").focus();
            return ;
        }
        if(isNaN($("#prodCount").val())){
            layer.msg("请输入数字");
            $("#prodCount").focus();
            return ;
        }

        if($("#prodCount").val().indexOf('.')!="-1"){
            layer.msg("请输入整数");
            $("#prodCount").focus();
            return ;
        }
        $.ajax({
            type: 'POST',
            url: '<%=basePath%>ordersServlet',
            data: {
                action:"addCount",
                orderId:$("#orderId").val(),
                oprtime:$("#oprtime").val(),
                userid:$("#userid").val(),
                remark:$("#remark").val(),
                prodid:$("#prodid").val(),
                supplierId:$("#supplierId").val(),
                unitId1:$("#unitIds").val(),
                status:$("#status").val(),
                saleMoney:$("#saleMoney").val(),
                prodCount:$("#prodCount").val(),
                regPerson:$("#regPerson").val(),
                regPassword:$("#regPassword").val()
            },
            dataType:  'text',
            success: function(data){
                layui.use('table', function(){
                    var table = layui.table;
                    //方法级渲染
                    table.render({
                        elem: '#demo'
                        ,url: '<%=basePath%>orderDetailServlet?orderType=1'
                        ,cols: [[
                            {field:'detailId', title: 'ID', width:80, sort: true, fixed: true}
                            ,{field:'prodname', title: '商品名称', width:80}
                            ,{field:'unitName', title: '单位', width:80, sort: true}
                            ,{field:'prodCount', title: '数量', width:80}
                            ,{field:'saleMoney', title: '单价' ,width:80}
                            ,{field:'orderStatusName', title: '销售类别',  width:80}
                            ,{field:'regPerson', title: '注册联系人',  width:80}
                            ,{field:'regPassword', title: '注册密码', width:80}
                        ]]
                        ,id: 'testReload'
                        ,page: true
                        ,height: 315
                    });

                    var $ = layui.$, active = {
                        reload: function(){
                            var demoReload = $('#demoReload');

                            //执行重载
                            table.reload('testReload', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                                ,where: {
                                    key: {
                                        id: demoReload.val()
                                    }
                                }
                            });
                        }
                    };

                    $('.demoTable .layui-btn').on('click', function(){
                        var type = $(this).data('type');
                        active[type] ? active[type].call(this) : '';
                    });
                });
            }
        })


    }
</script>

</body>
</html>
