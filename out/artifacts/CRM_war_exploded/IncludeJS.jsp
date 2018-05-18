<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all">
<link rel="shortcut icon" href="<%=basePath%>images/mainicon.png" type="image/x-icon"/>
<script src="<%=basePath%>js/jquery-3.2.1.js" charset="utf-8"></script>
<script src="<%=basePath%>layui/layui.js" charset="utf-8"></script>
<script src="<%=basePath%>js/xadmin.js" charset="utf-8"></script>





