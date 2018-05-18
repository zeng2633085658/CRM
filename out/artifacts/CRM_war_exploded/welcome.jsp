<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <!-- load css -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/font/iconfont.css" media="all">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css" media="all">

</head>
<body>


<div class="layui-fluid larry-wrapper">
    <div class="layui-row layui-col-space30">
        <div class="layui-col-xs6 layui-col-sm4 layui-col-md2">
            <section class="panel">
                <div class="symbol bgcolor-blue"> <i class="iconfont">&#xe672;</i>
                </div>
                <div class="value tab-menu">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="用户总量"><i class="iconfont " data-icon='&#xe672;'></i>
                        <h1>${usercount}</h1>
                    </a>

                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="用户总量"> <i class="iconfont " data-icon='&#xe672;'></i><span>用户总量</span></a>

                </div>
            </section>
        </div>
        <div class="layui-col-xs6 layui-col-sm4 layui-col-md2">
            <section class="panel">
                <div class="symbol bgcolor-commred"> <i class="iconfont">&#xe674;</i>
                </div>
                <div class="value tab-menu">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日注册"> <i class="iconfont " data-icon='&#xe674;'></i>
                        <h1>${cuscount}</h1>
                    </a>

                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日注册"> <i class="iconfont " data-icon='&#xe674;'></i><span>客户总量</span></a>

                </div>
            </section>
        </div>

        <div class="layui-col-xs6 layui-col-sm4 layui-col-md2">
            <section class="panel">
                <div class="symbol bgcolor-dark-green"> <i class="iconfont">&#xe6bc;</i>
                </div>
                <div class="value tab-menu">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="文章总数"> <i class="iconfont " data-icon='&#xe6bc;'></i>
                        <h1>${procount}</h1>
                    </a>
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="文章总数"> <i class="iconfont " data-icon='&#xe6bc;'></i><span>商品总数</span></a>
                </div>
            </section>
        </div>

        <div class="layui-col-xs6 layui-col-sm4 layui-col-md2">
            <section class="panel">
                <div class="symbol bgcolor-yellow-green"> <i class="iconfont">&#xe649;</i>
                </div>
                <div class="value tab-menu">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日新增"> <i class="iconfont " data-icon='&#xe649;'></i>
                        <h1>${ordcount}</h1>
                    </a>
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日新增"> <i class="iconfont " data-icon='&#xe649;'></i><span>订单总数</span></a>
                </div>
            </section>
        </div>

        <div class="layui-col-xs6 layui-col-sm4 layui-col-md2">
            <section class="panel">
                <div class="symbol bgcolor-orange"> <i class="iconfont">&#xe638;</i>
                </div>
                <div class="value tab-menu">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="评论总数"> <i class="iconfont " data-icon='&#xe638;'></i>
                        <h1>${jobcount}</h1>
                    </a>
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="评论总数"> <i class="iconfont " data-icon='&#xe638;'></i><span>派工总数</span></a>
                </div>
            </section>
        </div>

        <div class="layui-col-xs6 layui-col-sm4 layui-col-md2">
            <section class="panel">
                <div class="symbol bgcolor-yellow"> <i class="iconfont">&#xe669;</i>
                </div>
                <div class="value tab-menu">
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日评论"> <i class="iconfont " data-icon='&#xe669;'></i>
                        <h1>${weekcount}</h1>
                    </a>
                    <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日评论"> <i class="iconfont " data-icon='&#xe669;'></i><span>工作周报</span></a>
                </div>
            </section>
        </div>

    </div>
    <div class="layui-row">
        <div class="layui-xs12">

            <blockquote class="layui-elem-quote">
                系统公告:
                客户关系管理系统主要有高可控性的数据库、更高的安全性、数据实时更新等特点，提供日程管理、订单管理、发票管理、知识库管理等功能
                客户关系管理系统（CRM）是以客户数据的管理为核心，利用信息科学技术，实现市场营销、销售、服务等活动自动化，并建立一个客户信息的收集、管理、分析、利用的系统，帮助企业实现以客户为中心的管理模式。客户关系管理既是一种管理理念，又是一种软件技术。

            </blockquote>

        </div>
    </div>
    <div class="layui-row layui-col-space10">
        <div class="layui-col-xs12 layui-col-sm12 layui-col-md6">

            <section class="panel log">
                <div class="panel-heading">
                    更新日志
                    <a href="javascript:;" class="pull-right panel-toggle"><i class="iconfont">&#xe604;</i></a>
                </div>
                <div class="panel-body">
                    <h2>客户关系管理系统v2.0  2018-04-11</h2>
                    <ul>
                        <li>优化了页面交互,使用起来更加得心应手</li>
                        <li> 添加了短信服务,使您的账户更加安全</li>
                    </ul>
                    <h2>客户关系管理系统v1.6 2018-04-02</h2>
                    <ul>
                        <li>更新了欢迎界面，优化订单管理</li>
                    </ul>
                    <h2>客户关系管理系统v1.5 2018-03-30</h2>
                    <ul>
                        <li> 使用界面传值优化客户及各种管理操作</li>
                        <li> 优化了菜单</li>
                    </ul>
                    <h2>客户关系管理系统v1.3 2018-03-23</h2>
                    <ul>
                        <li> 重构代码，修复一些BUG</li>
                        <li> 使用json数据模拟真实操作</li>
                        <li> 页面更简化</li>
                        <li> 支持客户端或服务端分页</li>
                        <li> 更新的太多，不记得了，自己体验吧</li>
                    </ul>
                    <h2>客户管理系统v1.1 2018-03-22</h2>
                    <ul>
                        <li>结合使用x-admin、layui制作界面</li>
                    </ul>

                    <h2>客户管理系统v1.0 2018-03-20</h2>
                    <ul>
                        <li>开始开发项目</li>
                    </ul>

                    <p>更多帮助文档请移步到 <a href="http://www.baidu.com" target="_blank" class="layui-btn layui-btn-sm">使用手册</a></p>
                </div>
            </section>
        </div>


        <div class="layui-col-xs12 layui-col-sm12 layui-col-md6">
            <section class="panel">
                <div class="panel-heading">
                    网站信息
                    <a href="javascript:;" class="pull-right panel-toggle"><i class="iconfont">&#xe604;</i></a>
                </div>
                <div class="panel-body">
                    <table class="layui-table">
                        <tbody>
                        <tr>
                            <td>
                                <strong>软件名称</strong>：

                            </td>
                            <td>
                                <a href="http://www.baidu.com">客户关系管理系统</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>软件版本</strong>：

                            </td>
                            <td>
                                V2.0
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>开发作者</strong>：
                            </td>
                            <td>Younglim&zyp</td>
                        </tr>
                        <tr>
                            <td>
                                <strong>使用手册</strong>：
                            </td>
                            <td>
                                <a class="layui-btn  layui-btn-sm  layui-btn-primary" >使用手册1.3.4</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>下载</strong>：
                            </td>
                            <td><a href="<%=basePath%>crm.zip" download="crm.zip" target="_blank" class="layui-btn layui-btn-primary layui-btn-sm">码云下载</a>
                                <a href="<%=basePath%>crm.zip"   download="crm.zip"  target="_blank" class="layui-btn layui-btn-primary layui-btn-sm">1.7版</a>

                                <a href="https://www.baidu.com" target="_blank" class="layui-btn layui-btn-primary layui-btn-sm">其实都下不了版</a>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>QQ讨论群</strong>：
                            </td>
                            <td>
                                不存在的
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>服务器环境</strong>：
                            </td>
                            <td>windows10 tomcat 7.0.82↑ JDK 1.8 </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </section>
            <section class="panel">
                <div class="panel-heading">
                    数据统计
                    <a href="javascript:;" class="pull-right panel-toggle"><i class="iconfont">&#xe604;</i></a>
                </div>
                <div class="panel-body">
                    <div class="echarts" id="echarts"></div>
                </div>
            </section>

        </div>
    </div>
</div>

</div>



























<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<%--<div id="main" style="width: 600px;height:400px;"></div>--%>
<%--<script type="text/javascript">--%>
    <%--// 基于准备好的dom，初始化echarts实例--%>
    <%--var myChart = echarts.init(document.getElementById('main'));--%>

    <%--var xAxis=new Array();--%>
    <%--var series=new Array();--%>
    <%--$.post(--%>
        <%--"<%=basePath%>infoServlet",--%>
        <%--{--%>

        <%--},function (data) {--%>
            <%--var datas=data.list;--%>
            <%--for(var i=0;i<datas.length;i++){--%>
                <%--xAxis[i]=datas[i].count;--%>
                <%--series[i]=datas[i].depname;--%>
                <%--// 指定图表的配置项和数据--%>
                <%--var option = {--%>
                    <%--title: {--%>
                        <%--text: 'ECharts 入门示例'--%>
                    <%--},--%>
                    <%--tooltip: {},--%>
                    <%--legend: {--%>
                        <%--data:['部门管理']--%>
                    <%--},--%>
                    <%--xAxis: {--%>
                        <%--data:--%>
                          <%--series--%>
                    <%--},--%>
                    <%--yAxis: {},--%>
                    <%--series: [{--%>
                        <%--name: '部门管理',--%>
                        <%--type: 'bar',--%>
                        <%--data: xAxis--%>
                    <%--}]--%>
                <%--};--%>

                <%--// 使用刚指定的配置项和数据显示图表。--%>
                <%--myChart.setOption(option);--%>
            <%--}--%>
        <%--},"json"--%>
    <%--);--%>

<%--</script>--%>
</body>

<script type="text/javascript" src="<%=basePath%>js/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/version.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script>
    layui.use(['main', 'echart']);
</script>

</html>
