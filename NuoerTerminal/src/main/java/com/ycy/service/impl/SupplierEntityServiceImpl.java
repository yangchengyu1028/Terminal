package com.ycy.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ycy.entity.SupplierEntity;
import com.ycy.mapper.SupplierMapper;
import com.ycy.service.ISupplierEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class SupplierEntityServiceImpl extends ServiceImpl<SupplierMapper, SupplierEntity> implements ISupplierEntityService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public int getSupplierByMobile(String mobile) {
        int m = 0;
        // 从缓存中获取该手机号对应的店铺id
        String key = "supplier_mobile_"+mobile;
        ValueOperations<String, SupplierEntity> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            m = operations.get(key).getSupplier_id();
            return m;
        }
        // 从 DB 中获取该店铺所有信息
        SupplierEntity supplierEntity = supplierMapper.getByMobile(mobile);
        // 插入缓存
        operations.set(key, supplierEntity);
        m = supplierEntity.getSupplier_id();

        return m;
    }
}
