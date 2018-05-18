package com.crm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//数据库连接类负责与数据库的连接和断开
public class DBConn {
	static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String url="jdbc:sqlserver://localhost:1433;databasename=CRM";
	static String user="sa";
	static String password="123456";

	static Connection conn = null;

	//静态语块,一运行就执行
	static{
		try{
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//进行数据库连接
	public static Connection openDB(){
		try{
			if(conn==null||conn.isClosed()){
				conn=DriverManager.getConnection(url, user, password);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	//关闭数据库连接
	public static void closeDb(){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){
		new DBConn();
	}
}
