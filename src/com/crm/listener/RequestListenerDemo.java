package com.crm.listener;


import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestListenerDemo implements ServletRequestListener,ServletRequestAttributeListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+"创建request对象");

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+"销毁request对象");

    }



    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+
                "创建request变量 ,名称是:"+servletRequestAttributeEvent.getName()+
                ",值是:"+servletRequestAttributeEvent.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+
                "删除request变量 ,名称是:"+servletRequestAttributeEvent.getName()+
                ",值是:"+servletRequestAttributeEvent.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+
                "销毁request变量 ,名称是:"+servletRequestAttributeEvent.getName()+
                ",值是:"+servletRequestAttributeEvent.getValue());
    }
    //日志文件
    public void logout(String message){
        PrintWriter out=null;
        try{
            out=new PrintWriter(new FileOutputStream("E:/work/CRM/log.txt",true));
            out.print(message+"\n");
            out.close();
        }catch(Exception e){
            out.close();
            e.printStackTrace();
        }

    }






}
