package com.crm.vo;

import java.io.Serializable;

public class DegreesVo implements Serializable {
    private int degreeid;
    private String degreename; //岗位名称

    public int getDegreeid() {
        return degreeid;
    }

    public void setDegreeid(int degreeid) {
        this.degreeid = degreeid;
    }

    public String getDegreename() {
        return degreename;
    }

    public void setDegreename(String degreename) {
        this.degreename = degreename;
    }
}
