package com.ycy.entity;


import java.io.Serializable;

/**
 * 店铺
 */
public class SupplierEntity implements Serializable {

    private static final long serialVersionUID = 3942052857362546301L;
    private int supplier_id;
    private String supplier_name;
    private String mobile;

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
