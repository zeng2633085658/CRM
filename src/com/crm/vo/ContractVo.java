package com.crm.vo;


import java.io.Serializable;

public class ContractVo implements Serializable {
    private int contract_id;//主键
    private String contract_no;//合同编号
    private int custId;//客户编号（关联客户表）
    private String custname;//客户名称
    private String contract_time;//合同签订时间
    private String due_time;//合同到期时间
    private float total_money;//合同金额
    private String deposit;//合同有效期（年）
    private String pay_type;//	支付类别（按季度支付、按月支付、按半年支付，按年支付）
    private String status;//合同状态（已签订，已付款，已完成，服务中）
    private int empid;//	业务员（关联用户表）
    private String empName;//业务员名称
    private int operator;//	操作员（关联用户表）
    private String operatorName;//操作员名称
    private String oprtime;//	操作时间
    private String contract_name;//名称
    private String remark;

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public String getContract_no() {
        return contract_no;
    }

    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getContract_time() {
        return contract_time;
    }

    public void setContract_time(String contract_time) {

        this.contract_time = contract_time;
    }

    public String getDue_time() {
        return due_time;
    }

    public void setDue_time(String due_time) {
        this.due_time = due_time;
    }

    public float getTotal_money() {
        return total_money;
    }

    public void setTotal_money(float total_money) {
        this.total_money = total_money;
    }

    public String getDeposit() {
        int year1=0;
        int year2=0;
        year1=Integer.parseInt(contract_time.substring(0,4));
        year2=Integer.parseInt(due_time.substring(0,4));
        deposit=(year2-year1)+"";
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public String getOprtime() {
        return oprtime;
    }

    public void setOprtime(String oprtime) {
        this.oprtime = oprtime;
    }
}
