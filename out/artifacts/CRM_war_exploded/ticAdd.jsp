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
    <title>新增开票信息</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>新增开票信息</h2></blockquote>

<form class="layui-form" action="<%=basePath%>ticketServlet?action=add" method="post" name="form1">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>开票日期</label>
            <div class="layui-input-inline">
                <input type="text" name="ticketDate" id="jobDate" lay-verify="required"
                       placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="${time}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>客户</label>
            <div class="layui-input-block">
                <select name="custid" lay-filter="custid" id="custid">
                    <option value='0'>未选择</option>
                    <c:forEach items="${customerInfo}" var="u">
                        <option value="${u.custId}">${u.custname}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>订单</label>
            <div class="layui-input-block">
                <select name="orderid" id="orderid" lay-search lay-filter="orderid">
                   <option value="0">未选择</option>
                </select>
            </div>
        </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">开票金额</label>
            <div class="layui-input-inline">
                <input type="text" name="ticketMoney" autocomplete="off"
                       placeholder="请输入开票金额" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>出票公司</label>
            <div class="layui-input-inline">
                <input type="text" name="ticketComp" lay-verify="required" autocomplete="off"
                       placeholder="请输入出票公司" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="remark"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit=""  lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary" id="btn" >重置</button>
        </div>
    </div>
</form>
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
            elem: "#jobDate"
        });
        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //下拉框事件监听
        form.on('select(custid)', function(data){
            $.post(
                "<%=basePath%>ticketServlet",
                {
                    "action":"select",
                    "custid":$("#custid").val()
                },function (data) {
                    $.each(data, function(key, val) {
                        if(val.length=="0"){
                            $("#orderid").html("<option value='0'>未选择</option>");
                            form.render('select');
                        }else{
                            $("#orderid").html("<option value='0'>未选择</option>");
                            var option1="<option value='0'>未选择</option>";
                            for(var i=0;i<val.length;i++){
                                option1+="<option value='"+val[i].orderId+"'>"+val[i].orderId+"</option>"
                            }
                            $("#orderid").html(option1);
                            form.render('select');
                            $("#orderid").get(0).selectedIndex=0;
                        }

                    });

                },"json"
            );

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
