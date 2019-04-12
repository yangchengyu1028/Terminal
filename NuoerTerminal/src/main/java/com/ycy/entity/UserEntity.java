package com.ycy.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * 用户（买家）
 */
@TableName("noer_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 7908488742137360850L;

    @TableId(value = "id",type = IdType.INPUT)
    private int id;//终端机系统自增id
    private String name;
    private String mobile;
    private String province;//省
    private String city;//市
    private String district;//区
    private String address;//详细地址
    private int ys_id;//用户在伊索平台的自增id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYs_id() {
        return ys_id;
    }

    public void setYs_id(int ys_id) {
        this.ys_id = ys_id;
    }
}
