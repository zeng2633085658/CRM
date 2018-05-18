package com.crm.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ServletContext监听器
 * 1.实现ServletContextListener接口
 * 2.配置监听器
 * <listener>
 <listener-class>ht.listener.ApplicationListener</listener-class>
 </listener>
 * 3.系统自动运行
 *
 * 监听属性就必须实现ServletContextAttributeListener
 * */
public class ApplicationListenerDemo implements ServletContextListener,ServletContextAttributeListener {

    //初始化ServletContext对象
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
       logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+"初始化ServletContext对象:"+servletContextEvent.getServletContext());
    }



    //销毁application对象
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("销毁ServletContext对象：");
    }

    //创建application变量(属性)
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+"创建了application变量，名称="+servletContextAttributeEvent.getName()+
                "值="+servletContextAttributeEvent.getValue()
        );
    }

    //删除application变量(属性)
    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+"删除application变量，名称="+servletContextAttributeEvent.getName()+
                "值="+servletContextAttributeEvent.getValue()
        );
    }

    //修改application变量(属性)
    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        logout(new SimpleDateFormat("\n"+"yyyy-MM-dd HH:mm:ss").format(new Date())+"修改application变量，名称="+servletContextAttributeEvent.getName()+
                "值="+servletContextAttributeEvent.getValue()
        );
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
