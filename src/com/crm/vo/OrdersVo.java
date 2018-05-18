package com.crm.vo;


import java.io.Serializable;
import java.util.Date;

public class OrdersVo implements Serializable {
    private String orderId;//订单编号
    private int custid;//客户编号（关联客户资料表）
    private String custname;//客户名称
    private String username;//用户名称
    private int userid;//业务员（关联用户表id）
    private String orderType;//订单类别（采购入库，销售出库）
    private String orderStatus;//	订单状态（已下单，已收款，实施中,已完成） 1,2,3,4
    private String orderStatusName;//	订单状态（已下单，已收款，实施中,已完成） 1,2,3,4
    private String process;//进度
    private float totalMoney;//订单金额
    private String oprtime;//开票时间
    private String operator;//	开票人
    private String operatorName;//	开票人
    private String remark;//	描述

    private String orderStatus1;//	入库状态（已采购，已入库，已审核） 1,2,3,
    private String orderStatusName1;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOrderStatus1() {
        return orderStatus1;
    }

    public void setOrderStatus1(String orderStatus1) {
        this.orderStatus1 = orderStatus1;
    }

    public String getOrderStatusName1() {
        if(orderStatus1!=null){
            if(orderStatus1.equals("1")){
                orderStatusName1="已采购";
            }else if(orderStatus1.equals("2")){
                orderStatusName1="已入库";
            }else if(orderStatus1.equals("3")){
                orderStatusName1="已审核";
            }
        }

        return orderStatusName1;
    }

    public void setOrderStatusName1(String orderStatusName1) {
        this.orderStatusName1 = orderStatusName1;
    }

    public String getOrderStatusName() {
        if(orderStatus1!=null) {
            if (orderStatus.equals("1")) {
                orderStatusName = "已下单";
            } else if (orderStatus.equals("2")) {
                orderStatusName = "已收款";
            } else if (orderStatus.equals("3")) {
                orderStatusName = "实施中";
            } else if (orderStatus.equals("4")) {
                orderStatusName = "已完成";
            }
        }
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getOprtime() {
        return oprtime;
    }

    public void setOprtime(String oprtime) {
        this.oprtime = oprtime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
