package com.ycy.service;

import com.baomidou.mybatisplus.service.IService;
import com.ycy.entity.UserEntity;

public interface IUserEntityService extends IService<UserEntity> {
    /**
     * 添加用户
     * @param userEntity
     */
    void addUser(UserEntity userEntity);

    /**
     * 根据手机号查询
     * @param mobile
     * @return
     */
    UserEntity query(String mobile);

    /**
     * 修改
     * @param userEntity
     */
    int update(UserEntity userEntity);
}
