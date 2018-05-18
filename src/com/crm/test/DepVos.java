package com.crm.test;

import com.crm.common.AesUtils;

public class DepVos {
    private int count;
    private String depname;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public static void main(String[] args) {
        System.out.println(AesUtils.encryptStr("123456",AesUtils.SECRETKEY));
        System.out.println(AesUtils.encryptStr("123456",AesUtils.SECRETKEY));
    }

}
