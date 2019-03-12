package com.ycy.entity;


import java.io.Serializable;


public class GoodsEntity implements Serializable {

    private static final long serialVersionUID = -5592750933100318863L;
    private int goods_id;//商品id
    private int symptom_id;//症状id
    private int supplier_id;//店铺id
    private int brand_id;//品牌id
    private String goods_sn;//商品货号
    private String bar_code;//条形码
    private String goods_name;//商品名称
    private int goods_number;//库存
    private String goods_weight;//商品重量
    private String shop_price;//商品价格
    private String goods_brief;//商品简单描述
    private String goods_img;//商品图片
    private String common_name;//通用名
    private String manufacturer;//生产厂家
    private String goods_attr;//规格
    private String license_number;//批准文号

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getSymptom_id() {
        return symptom_id;
    }

    public void setSymptom_id(int symptom_id) {
        this.symptom_id = symptom_id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(int goods_number) {
        this.goods_number = goods_number;
    }

    public String getGoods_weight() {
        return goods_weight;
    }

    public void setGoods_weight(String goods_weight) {
        this.goods_weight = goods_weight;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getGoods_brief() {
        return goods_brief;
    }

    public void setGoods_brief(String goods_brief) {
        this.goods_brief = goods_brief;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGoods_attr() {
        return goods_attr;
    }

    public void setGoods_attr(String goods_attr) {
        this.goods_attr = goods_attr;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }
}
