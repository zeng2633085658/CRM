<%@ page import="com.crm.common.ContextUtils" %>
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
    <title>新增商品</title>
    <jsp:include page="/IncludeJS.jsp"></jsp:include>
</head>
<body>

<blockquote class="layui-elem-quote"><h2>修改商品资料</h2></blockquote>

<form class="layui-form" action="<%=basePath%>productServlet?action=updateSave" method="post" name="form1">
    <input type="hidden" name="prodid" value="${pro.prodid}">
    <div class="layui-form-item">
        <label class="layui-form-label"><span style="color: red;">*</span>商品序号</label>
        <div class="layui-input-block">
            <input type="text" style="width: 32%;" name="prodid" lay-verify="title" autocomplete="off"
                  class="layui-input"  value="${pro.prodid}" disabled>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"><span style="color: red;">*</span>商品名称</label>
        <div class="layui-input-block">
            <input type="text" style="width: 32%;" name="title" lay-verify="title" autocomplete="off"
                   placeholder="请输入商品名称" class="layui-input"  value="${pro.prodname}">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>商品型号</label>
            <div class="layui-input-inline">
                <input type="text" name="prodmodel" lay-verify="required" autocomplete="off"
                       placeholder="请输入商品型号" class="layui-input"  value="${pro.prodModel}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>产品规格</label>
            <div class="layui-input-inline">
                <input type="text" name="prodstyle" lay-verify="required" autocomplete="off"
                       placeholder="请输入产品规格" class="layui-input" value="${pro.prodStyle}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>库存数量</label>
            <div class="layui-input-inline">
                <input type="text" name="prodcount" lay-verify="required" autocomplete="off"
                       placeholder="请输入库存数量" class="layui-input" value="${pro.prodCount}" disabled>
            </div>
        </div>
        <input type="hidden" name="prodcounts" value="${pro.prodCount}">
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>进货价格</label>
            <div class="layui-input-inline">
                <input type="text" name="inprice" lay-verify="required" autocomplete="off"
                       placeholder="请输入进货价格" class="layui-input" value="${pro.inPrice}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>销售价格</label>
            <div class="layui-input-inline">
                <input type="text" name="saleprice" lay-verify="required" autocomplete="off"
                       placeholder="请输入销售价格" class="layui-input" value="${pro.salePrice}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red;">*</span>最低价格</label>
            <div class="layui-input-inline">
                <input type="text" name="lowsaleprice" lay-verify="required" autocomplete="off"
                       placeholder="请输入最低价格" class="layui-input" value="${pro.lowSalePrice}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商品单位</label>
            <div class="layui-input-block">
                <select name="produnit" lay-filter="aihao">
                    <c:forEach items="${unitVos}" var="u">
                        <c:if test="${pro.prodUnit==u.unitId}">
                            <option value="${u.unitId}" selected>${u.unitName}</option>
                        </c:if>
                        <c:if test="${pro.prodUnit!=u.unitId}">
                            <option value="${u.unitId}">${u.unitName}</option>
                        </c:if>
                    </c:forEach>
                </select>
        </div>
    </div>
    <div class="layui-form-item">
        <br/>
        <div class="layui-inline">
            <label class="layui-form-label">商品序列号</label>
            <div class="layui-input-inline">
                <input type="text" name="prodserial" placeholder="请输入商品序列号" autocomplete="off"
                       class="layui-input" value="${pro.prodSerial}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商品CDKEY</label>
            <div class="layui-input-inline">
                <input type="text" name="cdkey" placeholder="请输入商品CDKEY" autocomplete="off"
                       class="layui-input" value="${pro.cdKey}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">产品供应商</label>
            <div class="layui-input-block">
                <select name="supplierid" lay-filter="aihao">
                    <c:forEach items="${supplierVos}" var="s">
                        <c:if test="${pro.supplierId==s.supplierId}">
                            <option value="${s.supplierId}" selected>${s.supplierName}</option>
                        </c:if>
                        <c:if test="${pro.supplierId!=s.supplierId}">
                            <option value="${s.supplierId}">${s.supplierName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="remark">${pro.remark}</textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>


<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 2) {
                    return '名称至少得2个字符';
                }
            }

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
