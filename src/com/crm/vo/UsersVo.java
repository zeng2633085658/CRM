package com.crm.vo;


import java.io.Serializable;
import java.util.Date;

public class UsersVo implements Serializable {
    private int userid;//主键
    private String username;    //用户名称（）
    private String password;//密码
    private int depid;    //部门编号（关联部门表主键）
    private int degreeid;//岗位编号（关联岗位表主键）
    private String jobname;//职务
    private int managerType;//管理员类别（0系统管理员，1部门经理，2员工）
    private String mobile;    //联系电话(用于找回密码必填)
    private String email;//邮箱
    private String qqcode;//QQ
    private String weixin;//微信
    private String cardno;    //身份证号码
    private String bankName;//开户银行
    private String bankCardNo;//银行账户
    private String joinDate;//入职日期
    private String workDate;    //试用转正日期
    private String levelDate;    //离职日期
    private float baseSalary;//基本工资
    private float degreeSalary;    //岗位工资
    private String addr;//联系地址
    private int status;//用户状态（1=启用，0=禁用）
    private String remark;//说明
    private String depname; //部门名称
    private String degreename; //岗位名称
    private String statusname; //用户状态（1=启用，0=禁用）
    private String managerTypename;//管理员类别

    public String getManagerTypename() {
        if(managerType==0){
            managerTypename="系统管理员";
        }else if(managerType==1){
            managerTypename="部门经理";
        }else if(managerType==2){
            managerTypename="财务";
        }else if(managerType==3){
            managerTypename="员工";
        }
        return managerTypename;
    }

    public void setManagerTypename(String managerTypename) {
        this.managerTypename = managerTypename;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getDegreename() {
        return degreename;
    }

    public void setDegreename(String degreename) {
        this.degreename = degreename;
    }

    public String getStatusname() {
        if (status==1){
            statusname="启用";
        }else{
            statusname="禁用";
        }
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDepid() {
        return depid;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }

    public int getDegreeid() {
        return degreeid;
    }

    public void setDegreeid(int degreeid) {
        this.degreeid = degreeid;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public int getManagerType() {
        return managerType;
    }

    public void setManagerType(int managerType) {
        this.managerType = managerType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqcode() {
        return qqcode;
    }

    public void setQqcode(String qqcode) {
        this.qqcode = qqcode;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getLevelDate() {
        return levelDate;
    }

    public void setLevelDate(String levelDate) {
        this.levelDate = levelDate;
    }

    public float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(float baseSalary) {
        this.baseSalary = baseSalary;
    }

    public float getDegreeSalary() {
        return degreeSalary;
    }

    public void setDegreeSalary(float degreeSalary) {
        this.degreeSalary = degreeSalary;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
