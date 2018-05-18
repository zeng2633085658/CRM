package com.crm.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
/**
 * 创建连接池的步骤：
 * 1.把连接数据库的jar包拷贝到tomcat的lib文件夹(sqljdbc4.jar或者sqljdbc41.jar)
 * 2.配置tomcat里面conf文件夹下面的server.xml文件
 * docBase：项目名称及路径（如果项目是发布在webapps里面，路径可以省略）
 * path:在地址栏使用的项目名称,配置连接池以后必须用配置的名称(study)访问，否则连接池不起作用
 * <Context docBase="JSP2017" path="/study" debug="0" crosscontext="true" reloadable="true">
 * 		连接池的配置信息
 <Resource name="jdbc/dbsample"	可以自定义
 type="javax.sql.DataSource"	固定不变，连接成功以后返回一个DataSource对象
 auth="Container"	容器，固定不变
 driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver"	驱动，根据jar包情况定
 url="jdbc:sqlserver://localhost\\SQL2005:1433;databasename=Test"	连接字符串，根据jar包和数据库情况定
 username="sa"	登录名称
 password="123456"	登录密码
 maxActive="20"	最大连接数
 maxIdle="5"		最小连接数
 maxWait="5000"	连接超时的等待时间
 />
 </Context>
 *3.在当前项目WebRoot\WEB-INF\web.xml文件里面配置如下代码
 <resource-ref>
 <description>描述</description>
 <res-ref-name>jdbc/sample_db</res-ref-name>
 <res-type> javax.sql.DataSource </res-type>
 <res-auth> Container </res-auth>
 </resource-ref>
 *4.写连接池的连接代码
 *
 *
 * */
public class DBPool {
    //返回连接对象
    static Connection conn=null;
    public static Connection openDB(){
        try {
            if (conn == null || conn.isClosed()) {
            //初始化上下文环境,以便读取server.xml文件中的配置信息
            Context init = new InitialContext();
            //查找与连接池有关的配置信息
            Context context = (Context) init.lookup("java:/comp/env");
            //找到连接池对应的名称,返回创建成功后的数据源
            DataSource ds = (DataSource) context.lookup("jspDB");
            //从数据源中返回连接对象
            conn = ds.getConnection();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
