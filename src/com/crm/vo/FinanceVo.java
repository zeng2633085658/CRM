package com.crm.vo;


import java.io.Serializable;
import java.util.Date;

//收款管理
public class FinanceVo implements Serializable {
    private int financeId;//编号
    private String orderId;//订单号（关联订单表）
    private String prodid;//	产品名称
    private String prodname;
    private int paidtypeid;//		收款方式类别(关联付款方式表）
    private String paidtypename;
    private double remainMoney;//	应收金额
    private double paidMoney;//	收款金额
    private double orderMoney;//	订单金额
    private String paidPerson;//	交款人
    private String inbank;//		入账银行
    private String bankAccount;//		入账账号
    private String outbank;//		出账银行
    private String warrant;//		相关凭证号
    private String paidTime;//		交款时间
    private String paidinTime;//		到账日期
    private String invalid;//	有效	是否有效（有效，作废）
    private int userid;//操作人（session取值，登录账号）
    private String oprtime;//录入时间
    private String oprType;//	操作类别（收款，付款）

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getPaidtypename() {
        return paidtypename;
    }

    public void setPaidtypename(String paidtypename) {
        this.paidtypename = paidtypename;
    }

    public int getFinanceId() {
        return financeId;
    }

    public void setFinanceId(int financeId) {
        this.financeId = financeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    public int getPaidtypeid() {
        return paidtypeid;
    }

    public void setPaidtypeid(int paidtypeid) {
        this.paidtypeid = paidtypeid;
    }

    public double getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(double remainMoney) {
        this.remainMoney = remainMoney;
    }

    public double getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(double paidMoney) {
        this.paidMoney = paidMoney;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getPaidPerson() {
        return paidPerson;
    }

    public void setPaidPerson(String paidPerson) {
        this.paidPerson = paidPerson;
    }

    public String getInbank() {
        return inbank;
    }

    public void setInbank(String inbank) {
        this.inbank = inbank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getOutbank() {
        return outbank;
    }

    public void setOutbank(String outbank) {
        this.outbank = outbank;
    }

    public String getWarrant() {
        return warrant;
    }

    public void setWarrant(String warrant) {
        this.warrant = warrant;
    }

    public String getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(String paidTime) {
        this.paidTime = paidTime;
    }

    public String getPaidinTime() {
        return paidinTime;
    }

    public void setPaidinTime(String paidinTime) {
        this.paidinTime = paidinTime;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getOprtime() {
        return oprtime;
    }

    public void setOprtime(String oprtime) {
        this.oprtime = oprtime;
    }

    public String getOprType() {
        return oprType;
    }

    public void setOprType(String oprType) {
        this.oprType = oprType;
    }
}
