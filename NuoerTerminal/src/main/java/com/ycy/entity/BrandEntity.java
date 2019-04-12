package com.ycy.entity;


import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 品牌
 */
@TableName("noer_brand")
public class BrandEntity implements Serializable {

    private static final long serialVersionUID = -5557866221388241633L;
    private int brand_id;
    private String brand_name;
    private String brand_letter;//英文名称
    private String brand_logo;

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_letter() {
        return brand_letter;
    }

    public void setBrand_letter(String brand_letter) {
        this.brand_letter = brand_letter;
    }

    public String getBrand_logo() {
        return brand_logo;
    }

    public void setBrand_logo(String brand_logo) {
        this.brand_logo = brand_logo;
    }
}
