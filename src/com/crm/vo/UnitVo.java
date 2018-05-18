package com.crm.vo;


import java.io.Serializable;

public class UnitVo implements Serializable {
    private int unitId;
    private String unitName; //单位名称

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}

