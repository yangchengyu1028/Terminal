package com.ycy.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ycy.entity.UserEntity;
import com.ycy.mapper.UserMapper;
import com.ycy.service.IUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class UserEntityServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserEntityService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void addUser(UserEntity userEntity) {
        userMapper.insert(userEntity);
    }

    @Override
    public UserEntity query(String mobile) {
        //从缓存中获取买家信息
        String key = "user_mobile_"+mobile;
        ValueOperations<String, UserEntity> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取买家信息
        UserEntity userEntity = userMapper.queryByMobile(mobile);
        // 设置缓存时间，插入缓存
        operations.set(key, userEntity);
        return userEntity;
    }

    @Override
    public int update(UserEntity userEntity) {
        String key = "user_mobile_"+userEntity.getMobile();
        //修改数据库数据
        int num = userMapper.update(userEntity,new EntityWrapper<UserEntity>().eq("mobile",userEntity.getMobile()));
        //删除该缓存
        redisTemplate.delete(key);

        return num;
    }
}
