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

}
