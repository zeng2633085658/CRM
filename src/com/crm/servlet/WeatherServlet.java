package com.crm.servlet;


import com.alibaba.fastjson.JSONObject;
import com.crm.common.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//天气servlet

public class WeatherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(getJson());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    public String getJson() {
            String json="";
            String host = "http://jisutqybmf.market.alicloudapi.com";
            String path = "/weather/query";
            String method = "GET";
            String appcode = "34be9a47dcc5420e9c87778bb73c68e3";
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE " + appcode);
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("city", "赣州");
            querys.put("citycode", "citycode");
            querys.put("cityid", "cityid");
            querys.put("ip", "ip");
            querys.put("location", "location");

            try {
                HttpResponse  response = HttpUtils.doGet(host, path, method, headers, querys);
                json=EntityUtils.toString(response.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        return json;
    }
}
