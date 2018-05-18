<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:if test="${user==null}">
  <jsp:forward page="login.jsp" />
</c:if>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>CRM管理系统</title>
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <meta http-equiv="Cache-Control" content="no-siteapp" />

  <link rel="shortcut icon" href="<%=basePath%>images/mainicon.png" type="image/x-icon" />
  <link rel="stylesheet" href="<%=basePath%>css/font.css">
  <link rel="stylesheet" href="<%=basePath%>css/xadmin.css">
  <script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>lib/layui/layui.js" charset="utf-8"></script>
  <script type="text/javascript" src="<%=basePath%>js/xadmin.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/index.js"></script>
  <script type="text/javascript">
    var weather;
    var img;
    var temp;
    var city;
    var week;
    var templow;
    var temphigh;
    $(function () {
        $.post(
            "<%=basePath%>weatherServlet",
            {
                
            },
            function (data) {
                var datas=data.result;
                weather=datas.weather;
                img=datas.img;
                temp=datas.temp;
                week=datas.week;
                templow=datas.templow;
                temphigh=datas.temphigh;
                city=datas.city;
                $("#img1").attr("src","<%=basePath%>images/weathercn/"+img+".png");
                $("#font1").text(city+":"+weather+": 温度(实时): "
                    +temp+"℃"+"  /  "+templow+"~"+temphigh+"℃"+" :"+week);
            }
            ,"json"
        );
    });



    function tab1 (title,url,id){
        //新增一个Tab项
        element.tabAdd('xbs_tab', {
            title: title
            ,content: '<iframe tab-id="'+id+'" frameborder="0" src="'+url+'" scrolling="yes" class="x-iframe"></iframe>'
            ,id: id
        })
    }


    function  tab2(id) {
        element.tabChange('xbs_tab', id); //切换到：用户管理
    }
    function demo1 (title,url,id){
         for (var i = 0; i <$('.x-iframe').length; i++) {
             if($('.x-iframe').eq(i).attr('tab-id')==id+1){
                 tab2(id+1);
                 event.stopPropagation();
                 return;
             }
         };
         tab1(title,url,id+1);
         tab2(id+1);
    }


    function  ishave (){
        for (var i = 0; i <$('.x-iframe').length; i++) {
            if($('.x-iframe').eq(i).attr('tab-id')>10000&&$('.x-iframe').eq(i).attr('tab-id')<20000){
                return true;
            }
        };
        return false;
    }
    function  ishavefj (){
        for (var i = 0; i <$('.x-iframe').length; i++) {
            if($('.x-iframe').eq(i).attr('tab-id')>20000&&$('.x-iframe').eq(i).attr('tab-id')<30000){
                return true;
            }
        };
        return false;
    }
    function  ishavedd (){
        for (var i = 0; i <$('.x-iframe').length; i++) {
            if($('.x-iframe').eq(i).attr('tab-id')>30000&&$('.x-iframe').eq(i).attr('tab-id')<40000){
                return true;
            }
        };
        return false;
    }
    function  ishavepg (){
        for (var i = 0; i <$('.x-iframe').length; i++) {
            if($('.x-iframe').eq(i).attr('tab-id')>40000&&$('.x-iframe').eq(i).attr('tab-id')<50000){
                return true;
            }
        };
        return false;
    }
    function  ishaveqt (){
        for (var i = 0; i <$('.x-iframe').length; i++) {
            if($('.x-iframe').eq(i).attr('tab-id')>50000&&$('.x-iframe').eq(i).attr('tab-id')<60000){
                return true;
            }
        };
        return false;
    }
    function  ishaveddxx (){
        for (var i = 0; i <$('.x-iframe').length; i++) {
            if($('.x-iframe').eq(i).attr('tab-id')>700000000){
                return true;
            }
        };
        return false;
    }
    function  ishavelx (){
        for (var i = 0; i <$('.x-iframe').length; i++) {
            if($('.x-iframe').eq(i).attr('tab-id')>70000&&$('.x-iframe').eq(i).attr('tab-id')<80000){
                return true;
            }
        };
        return false;
    }
  </script>

</head>
<body>
<!-- 顶部开始 -->
<div class="container" style="background-color: #008175" >
  <div class="logo"><a href="JavaScript:void(0);">管理系统1.7</a></div>
  <div class="left_open">
    <i title="展开左侧栏" class="iconfont">&#xe699;</i>
  </div>
  <ul class="layui-nav left "  >
    <li class="layui-nav-item">
        <img src="" id="img1">
        <font id="font1"></font>
    </li>
  </ul>
  <ul class="layui-nav right" lay-filter="">
    <li class="layui-nav-item">
      <a href="javascript:;">${user.username}</a>
      <dl class="layui-nav-child"> <!-- 二级菜单 -->
        <dd><a onclick="demo1('个人信息','<%=basePath%>pwdUpdate.jsp',5000)">个人信息</a></dd>
        <dd><a onclick="x_admin_pwd('修改密码','<%=basePath%>pwdUpdate.jsp')">修改密码</a></dd>
        <dd><a href="<%=basePath%>exit.jsp">退出</a></dd>
      </dl>
    </li>
    <%--<li class="layui-nav-item to-index"><a href="/">前台首页</a></li>--%>
  </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
  <div id="side-nav">
    <ul id="nav">
      <c:if test="${user.managerType==0}">
        <li>
          <a href="javascript:;">
            <i class="layui-icon">&#xe613;</i>
            <cite>员工管理</cite>
            <i class="iconfont nav_right">&#xe697;</i>
          </a>
          <ul class="sub-menu">
            <li>
              <a _href="<%=basePath%>userList.jsp">
                <i class="iconfont">&#xe6a7;</i>
                <cite>员工管理</cite>
              </a>
            </li >
          </ul>
        </li>
      </c:if>
      <li>
        <a href="javascript:;">
          <i class="iconfont">&#xe6b8;</i>
          <cite>客户关系管理</cite>
          <i class="iconfont nav_right">&#xe697;</i>
        </a>
        <ul class="sub-menu">
          <li>
            <a _href="<%=basePath%>cusList.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>客户资料管理</cite>
            </a>
          </li>
          <li>
            <a _href="<%=basePath%>finList.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>收款管理</cite>
            </a>
          </li>
          <li>
            <a _href="<%=basePath%>ticList.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>发票管理</cite>
            </a>
          </li>

        </ul>
      </li>

      <li>
        <a href="javascript:;">
          <i class="iconfont">&#xe723;</i>
          <cite>商品管理</cite>
          <i class="iconfont nav_right">&#xe697;</i>
        </a>
        <ul class="sub-menu">
          <li>
            <a _href="<%=basePath%>supList.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>供应商管理</cite>
            </a>
          </li >
          <li>
            <a _href="<%=basePath%>proList.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>商品管理</cite>
            </a>
          </li >
          <li>
            <a _href="<%=basePath%>proListCount.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>入库管理</cite>
            </a>
          </li >
        </ul>
      </li>
      <li>
        <a href="javascript:;">
          <i class="layui-icon">&#xe65e;</i>
          <cite>销售管理</cite>
          <i class="iconfont nav_right">&#xe697;</i>
        </a>
        <ul class="sub-menu">
          <li>
            <a _href="<%=basePath%>ordersCountServlet?action=init">
              <i class="iconfont">&#xe6a7;</i>
              <cite>订单管理</cite>
            </a>
          </li >
        </ul>
      </li>
      <c:if test="${user.managerType==0}">
      <li>
        <a href="javascript:;">
          <i class="layui-icon">&#xe614;</i>
          <cite>系统设置</cite>
          <i class="iconfont nav_right">&#xe697;</i>
        </a>
        <ul class="sub-menu">
          <li>
            <a _href="<%=basePath%>depList.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>部门管理</cite>
            </a>
          </li >
          <li>
            <a _href="<%=basePath%>degList.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>岗位管理</cite>
            </a>
          </li >
          <li>
            <a _href="<%=basePath%>unitList.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>单位管理</cite>
            </a>
          </li >
          <li>
            <a _href="<%=basePath%>paidList.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>收款方式</cite>
            </a>
          </li >
        </ul>
      </li>
      </c:if>
      <li>
        <a href="javascript:;">
          <i class="layui-icon">&#xe632;</i>
          <cite>工作周报</cite>
          <i class="iconfont nav_right">&#xe697;</i>
        </a>
        <ul class="sub-menu">
          <li>
            <a _href="<%=basePath%>weekList.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>工作周报</cite>
            </a>
          </li >
        </ul>
      </li>
      <li>
        <a href="javascript:;">
          <i class="iconfont">&#xe6ce;</i>
          <cite>系统统计</cite>
          <i class="iconfont nav_right">&#xe697;</i>
        </a>
        <ul class="sub-menu">
          <li>
            <a _href="countServlet?action=xs">
              <i class="iconfont">&#xe6a7;</i>
              <cite>销售统计</cite>
            </a>
          </li >
          <li>
            <a _href="skcount.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>收款统计</cite>
            </a>
          </li>
          <li>
            <a _href="countServlet?action=ht">
              <i class="iconfont">&#xe6a7;</i>
              <cite>合同统计</cite>
            </a>
          </li>
          <li>
            <a _href="<%=basePath%>jobCountServlet?action=init">
              <i class="iconfont">&#xe6a7;</i>
              <cite>派工报表</cite>
            </a>
          </li>
          <li>
            <a _href="countServlet?action=pg">
              <i class="iconfont">&#xe6a7;</i>
              <cite>派工统计</cite>
            </a>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
  <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
    <ul class="layui-tab-title">
      <li>首页</li>
    </ul>
    <div class="layui-tab-content">
      <div class="layui-tab-item layui-show">
        <iframe src='<%=basePath%>comeServlet' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
      </div>
    </div>
  </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer" style="background-color: #008175">
  <div align="center" class="copyright">© 2018 Younglim&zyp,inc. 🤣 (๑•̀ㅂ•́)و✧</div>
</div>
</body>
</html>