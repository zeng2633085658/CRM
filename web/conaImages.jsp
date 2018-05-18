<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>合同附件预览</title>
    <style type="text/css">
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: #3e3e3e;
        }
        h3 {
            margin-bottom: 10px;
        }
        .wrapper {
            background: #333333;
            position: relative;
            font-family: Arial, Helvetica, sans-serif;
            margin: auto;
            margin-top: 90px;
            width: 900px;
            padding: 20px;
            color: #fff;
            border-radius: 30px;
            box-shadow: 0 0 5px rgba(0,0,0,.8);
        }
        span.btn {
            padding: 10px;
            display: inline-block;
            cursor: pointer;
            font: 12px/14px Arial, Helvetica, sans-serif;
            -moz-border-radius: 10px;
            -webkit-border-radius: 10px;
            background-color: #666666;
            color: #fff;
            box-shadow: inset 0 0 2px rgba(0,0,0,.8);
            border: 1px solid gray;
        }
        span.btn:hover {
            background-color: #eee;
            color: #333333;
            box-shadow: 0 0 2px rgba(0,0,0,.8);
        }
        #screen {
            width: 900px;
            height: 450px;
            border: 1px solid gray;
            background: rgba(0,0,0,1);
        }
        #thumbs{margin-top:15px;}
        #thumbs img {
            height: 67px;
            margin: 4px;
            box-shadow: 0 0 5px rgba(0,0,0,.5);
            border: 3px solid rgba(255,255,255,.5);
            cursor: url("images/xc/css/zoomin.png"), -moz-zoom-in;
        }
        #thumbs img:hover {
            border: 3px solid rgba(255,255,255,1);
        }
        #controls {
            width: 900px;
            text-align: right;
        }
        .explanation b {
            color: #999;
        }
        .explanation {
            display: none;
        }
        #showExp {
            padding: 10px;
            border-radius: 10px;
            background: #333333;
            display: inline-block;
            font-size: 18px;
            font-weight: bold;
            color: #666666;
            cursor: pointer;
            margin: 5px;
            float: right;
            box-shadow: inset 0 0 4px rgba(0,0,0,.6);
        }
        #showExp:hover {
            background: #fff;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="images/xc/css/mb.zoomify.css" media="screen" />
    <script type="text/javascript" src="images/xc/inc/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="images/xc/inc/mb.zoomify.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#thumbs").find("img").eq(0).click();
        });

            $.post(
                "<%=basePath%>contractAttachServlet",
                {
                    "action":"findImage"
                },function (data) {
                    var datas=data.img;
                    var str="";
                    for(var i=0;i<datas.length;i++){
                        str+="<img  src=\"<%=basePath%>uploadFile/"+datas[i].attachURL+"\"  data-highres=\"<%=basePath%>uploadFile/"+datas[i].attachURL+"\" onclick=\"$(this).mbZoomify({screen:\'#screen\'});\">"
                    }

                    $("#thumbs").html(str);
                },"json"
            );
    </script>
</head>
<body>
<div class="wrapper">
    <div id="screen"></div>
    <br>
    <div id="controls">
        <span class="btn" onclick="$('#screen').mbZoomify_zoom('in')">放大</span>
        <span class="btn" onclick="$('#screen').mbZoomify_zoom('out')">缩小</span>
    </div>
    <div id="thumbs">
        <img  src="images/fjmain.jpg"  data-highres="images/fjmain.jpg" onclick="$(this).mbZoomify({screen:'#screen'});">

    </div>

    <div id="showExp" onclick="if($('.explanation').is(':visible')){ $('.explanation').slideUp();} else {$('.explanation').slideDown();}">?</div>
    <br style="clear: both;">
    <div class="explanation">
        <h3>图片<img src="images/xc/css/zoomin.png"> 放大:</h3>
        1. <b>Double click</b> on the image to <b>zoom in to that point</b>. <br>
        2. Or hold down the <b></b> key (mac) or the <b>ctrl key</b> (PC) and <b>click</b> on the image. <br>
        3. Or hold down the <b>plus</b> key to increment the zoom. <br>
        4. Or hold down the <b></b> key (mac) or the <b>ctrl key</b> (PC) and use the <b>mouse wheel</b>.
        <h3>图片 <img src="images/xc/css/zoomout.png"> 缩小:</h3>
        1. <b>Double click</b> on the zoomed image to <b>zoom out</b>. <br>
        2. Or hold down the <b> + alt</b> key (mac) or the <b>ctrl + alt</b> key (PC) and <b>click</b> on the image. <br>
        3. Or hold down <b>minus</b> key to decrement the zoom. <br>
        4. Or hold down the <b></b> key (mac) or the <b>ctrl key</b> (PC) and use the <b>mouse wheel</b>. </div>
</div>
</body>
</html>
