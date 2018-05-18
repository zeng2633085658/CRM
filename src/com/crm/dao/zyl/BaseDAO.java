package com.crm.dao.zyl;


import com.crm.common.ContextUtils;
import com.crm.db.DBConn;
import com.crm.db.DBPool;
import com.crm.vo.UsersVo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class BaseDAO {

    //查找用户总数
    public int findUsers(){
        UsersVo u=new UsersVo();
        Connection conn= DBConn.openDB();
        try{
            String sql="select COUNT(*) count from users";
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("count");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  0;
    }
    //查找客户总数
    public int findcus(){
        UsersVo u=new UsersVo();
        Connection conn= DBConn.openDB();
        try{
            String sql="select COUNT(*) count from CustomerInfo";
            System.out.println(sql);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("count");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  0;
    }
    //查找商品总数
    public int findpro(){
        UsersVo u=new UsersVo();
        Connection conn= DBConn.openDB();
        try{
            String sql="select COUNT(*) count from Product";
            System.out.println(sql);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("count");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  0;
    }
    //查找订单总数
    public int findord(){
        UsersVo u=new UsersVo();
        Connection conn= DBConn.openDB();
        try{
            String sql="select COUNT(*) count from Orders where orderType='销售出库'";
            System.out.println(sql);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("count");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  0;
    }
    //查找订单完成总数
    public int findordend(){
        UsersVo u=new UsersVo();
        Connection conn= DBConn.openDB();
        try{
            String sql="select COUNT(*) count from Orders where orderStatus=4";
            System.out.println(sql);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("count");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  0;
    }
    //查找订单完成总数
    public int findordsum(){
        UsersVo u=new UsersVo();
        Connection conn= DBConn.openDB();
        try{
            String sql="select sum(totalMoney) count from Orders where orderType='销售出库'";
            System.out.println(sql);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("count");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  0;
    }
    //查找派工总数
    public int findjob(){
        UsersVo u=new UsersVo();
        Connection conn= DBConn.openDB();
        try{
            String sql="select COUNT(*) count from JobRecord";
            System.out.println(sql);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("count");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  0;
    }
    //查找工作周报
    public int findweek(){
        UsersVo u=new UsersVo();
        Connection conn= DBConn.openDB();
        try{
            String sql="select COUNT(*) count from WeeklyReport";
            System.out.println(sql);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("count");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  0;
    }
    //统计合同
    public int findcon(String s){
        UsersVo u=new UsersVo();
        Connection conn= DBConn.openDB();
        try{
            String sql="select count(*) count from Contract where status='"+s+"'";
            System.out.println(sql);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("count");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  0;
    }
    //统计派工总数
    public int findjobok(){
        UsersVo u=new UsersVo();
        Connection conn= DBConn.openDB();
        try{
            String sql="select count(*) count from JobRecord where custSign='已签'";
            System.out.println(sql);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("count");
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  0;
    }
    public static void main(String[] args){
//        BaseDAO dao = new BaseDAO();
//        System.out.println(dao.findUsers());
    }
}
