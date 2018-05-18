package com.crm.listener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SessionListenerDemo implements HttpSessionListener,HttpSessionAttributeListener {

    static int count=0;
    //创建session对象
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+"创建session对象");
    }

    //销毁session对象
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+"销毁session对象");
    }

        //创建session变量(属性)
        @Override
        public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
            count++;
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+
                "创建session变量 ,名称是:"+httpSessionBindingEvent.getName()+
                ",值是:"+httpSessionBindingEvent.getValue()+"\n"+"在线人数:"+count+"人");


    }

    //删除session变量(属性)
    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        count--;
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+
                "删除session变量 ,名称是:"+httpSessionBindingEvent.getName()+
                ",值是:"+httpSessionBindingEvent.getValue()+"\n"+"在线人数:"+count+"人");

    }

    //修改session变量(属性)
    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        logout("\n"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+
                "修改session变量 ,名称是:"+httpSessionBindingEvent.getName()+
                ",值是:"+httpSessionBindingEvent.getValue());
    }

    //日志文件
    public void logout(String message){
        PrintWriter out=null;
        try{
            out=new PrintWriter(new FileOutputStream("E:/work/CRM/log.txt",true));
            out.print(message);
            out.close();
        }catch(Exception e){
            out.close();
            e.printStackTrace();
        }
    }
}
