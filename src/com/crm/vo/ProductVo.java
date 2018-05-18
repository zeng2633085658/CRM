package com.crm.vo;

import java.io.Serializable;

//商品表
public class ProductVo implements Serializable {
    private String prodid;//	主键
    private String prodname;//商品名称（唯一）
    private String prodModel;//商品型号
    private int prodUnit;//	商品单位(关联单位表主键)
    private String prodStyle;//产品规格
    private int prodCount;//库存数量
    private float inPrice;//	进货价格
    private float salePrice;//	销售价格
    private float lowSalePrice;//×最低价格
    private String prodSerial;//商品序列号
    private String cdKey;//商品CDKEY
    private String saleStatus;//已售，未售
    private int supplierId;//	产品供应商(关联供应商表)
    private String remark;//商品描述

    private String prodUnitName; //单位名称
    private String supplierName;  //供应商信息

    public String getProdUnitName() {
        return prodUnitName;
    }

    public void setProdUnitName(String prodUnitName) {
        this.prodUnitName = prodUnitName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProdModel() {
        return prodModel;
    }

    public void setProdModel(String prodModel) {
        this.prodModel = prodModel;
    }

    public int getProdUnit() {
        return prodUnit;
    }

    public void setProdUnit(int prodUnit) {
        this.prodUnit = prodUnit;
    }

    public String getProdStyle() {
        return prodStyle;
    }

    public void setProdStyle(String prodStyle) {
        this.prodStyle = prodStyle;
    }

    public int getProdCount() {
        return prodCount;
    }

    public void setProdCount(int prodCount) {
        this.prodCount = prodCount;
    }

    public float getInPrice() {
        return inPrice;
    }

    public void setInPrice(float inPrice) {
        this.inPrice = inPrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public float getLowSalePrice() {
        return lowSalePrice;
    }

    public void setLowSalePrice(float lowSalePrice) {
        this.lowSalePrice = lowSalePrice;
    }

    public String getProdSerial() {
        return prodSerial;
    }

    public void setProdSerial(String prodSerial) {
        this.prodSerial = prodSerial;
    }

    public String getCdKey() {
        return cdKey;
    }

    public void setCdKey(String cdKey) {
        this.cdKey = cdKey;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
