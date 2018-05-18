package com.crm.vo;


import java.io.Serializable;
import java.util.Date;

public class ContractAttachVo implements Serializable {
    private int contractAttach_id;//主键，标识列，自动生成
    private int contract_id;//合同编号（关联合同表主键）
    private int seq;//	排序号
    private String attachFile;//附件名称
    private String uploadTime;//	上传时间
    private int user_name;//	操作员编号
    private String userName;//操作员编号名称
    private String attachURL;//附件地址
    private String contract_on; //合同编号
    private String contract_name;//合同名称

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContract_on() {
        return contract_on;
    }

    public void setContract_on(String contract_on) {
        this.contract_on = contract_on;
    }

    public int getContractAttach_id() {
        return contractAttach_id;
    }

    public void setContractAttach_id(int contractAttach_id) {
        this.contractAttach_id = contractAttach_id;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getUser_name() {
        return user_name;
    }

    public void setUser_name(int user_name) {
        this.user_name = user_name;
    }

    public String getAttachURL() {
        return attachURL;
    }

    public void setAttachURL(String attachURL) {
        this.attachURL = attachURL;
    }
}
