package com.crm.vo;


import java.io.Serializable;

public class CustomerInfoVo implements Serializable {
    private int custId;//客户编号
    private String custname;//	客户名称
    private String custtype;//行业
    private String bankAccount;//银行账号
    private String bankName;//开户银行
    private String contact;//联系人
    private String phone;//	电话
    private String ticketName;//开票名称
    private String ticketAddr;//开票地址
    private String ticketTel;//开票电话
    private String taxNo;//纳税登记号
    private String custState;//客户状态（新客户，成交客户）
    private int userid;//业务员（关联用户表）
    private String source;//信息来源
    private String username;//业务员名称

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCusttype() {
        return custtype;
    }

    public void setCusttype(String custtype) {
        this.custtype = custtype;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getTicketAddr() {
        return ticketAddr;
    }

    public void setTicketAddr(String ticketAddr) {
        this.ticketAddr = ticketAddr;
    }

    public String getTicketTel() {
        return ticketTel;
    }

    public void setTicketTel(String ticketTel) {
        this.ticketTel = ticketTel;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getCustState() {
        return custState;
    }

    public void setCustState(String custState) {
        this.custState = custState;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
