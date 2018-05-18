<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    app.title = '折柱混合';

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:['订单总数','完成总数','销售金额']
        },
        xAxis: [
            {
                type: 'category',
                data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '订单总数',
                min: 0,
                max: 100,
                interval: 20,
                axisLabel: {
                    formatter: '{value} 单'
                }
            },
            {
                type: 'value',
                name: '销售金额',
                min: 0,
                max: 1000000,
                interval: 200000,
                axisLabel: {
                    formatter: '{value} 元'
                }
            }
        ],
        series: [
            {
                name:'订单总数',
                type:'bar',
                data:[20, 10, 10, 15, 19, 23, 22, 5, 10, 21, 25, ${ordcount}]
            },
            {
                name:'完成总数',
                type:'bar',
                data:[15, 5, 9, 14, 15, 20, 10, 5, 9, 20, 24, ${ordend}]
            },
            {
                name:'销售金额',
                type:'line',
                yAxisIndex: 1,
                data:[600000, 354251, 451247, 504584, 584214, 681482, 741245, 401458, 254842, 884515, 804548, ${ordsum}]
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
</html>