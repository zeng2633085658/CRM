package com.crm.servlet;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.crm.common.AesUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

//验证码servlet
public class CodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        try {
            //获取电话号码
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
            //替换成你的AK
            final String accessKeyId = "LTAInX3UKDb6N8S2";//你的accessKeyId,参考本文档步骤2
            final String accessKeySecret = "UzJsYyZdrcnrKn0czDYJ27ao1J27oX";//你的accessKeySecret，参考本文档步骤2
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest requests = new SendSmsRequest();
            //使用post提交
            requests.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            requests.setPhoneNumbers(request.getParameter("phone"));
            //必填:短信签名-可在短信控制台中找到
            requests.setSignName("宏图科技");
            //必填:短信模板-可在短信控制台中找到
            if(action.equals("reg")){
                requests.setTemplateCode("SMS_130980190");
            }else if(action.equals("pwd")){
                requests.setTemplateCode("SMS_131015184");
            }
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            //随机验证码 100000-999999
            Random ran = new Random();
            int code = Math.abs(ran.nextInt(899999)) + 100000;
            requests.setTemplateParam("{\"name\":\"sa\", \"code\":\"" + code + "\"}");
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            requests.setOutId("yourOutId");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(requests);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                System.out.println("请求成功");
                String validationCode = code + "";
                PrintWriter out = response.getWriter();
                System.out.println(validationCode);
                out.print(validationCode);
            } else {
                System.out.println("请求失败");
                System.out.println(sendSmsResponse.getCode());
            }
//                Random ran=new Random();
//                int code=Math.abs(ran.nextInt(899999))+100000;
//                System.out.println(code);
//                String validationCode=code+"";
//                PrintWriter out=response.getWriter();
//                out.print(validationCode);
        } catch (Exception e) {
            session.setAttribute("msg", "获取验证码失败,检查网络设置!");
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
