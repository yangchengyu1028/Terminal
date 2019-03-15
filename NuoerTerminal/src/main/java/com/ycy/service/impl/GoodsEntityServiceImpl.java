package com.ycy.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ycy.entity.GoodsEntity;
import com.ycy.mapper.GoodsMapper;
import com.ycy.service.IGoodsEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class GoodsEntityServiceImpl extends ServiceImpl<GoodsMapper, GoodsEntity> implements IGoodsEntityService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    public Page<GoodsEntity> getAllBySymptom(Page<GoodsEntity> page,Integer symptomId,Integer supplierId){
        // 从缓存中获取该症状所有药品
        String key = "goods_symptom_" + symptomId + "_page_" + page.getCurrent()+ "_pageSize_" + page.getSize()+"_supplierId_" + supplierId;
        ValueOperations<String, Page<GoodsEntity>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取该症状所有药品信息
        //page.setRecords(goodsMapper.selectPage(page,new EntityWrapper<GoodsEntity>().eq(symptomId+"", supplierId)));
        page.setRecords(goodsMapper.getAllBySymptom(page,symptomId,supplierId));
        // 设置缓存时间，插入缓存
        operations.set(key, page, 24*60*60, TimeUnit.SECONDS);
        return page;
    }

    public Page<GoodsEntity> getAllByBrand(Page<GoodsEntity> page,Integer brandId,Integer supplierId){
        // 从缓存中获取该症状所有药品
        String key = "goods_brand_" + brandId + "_page_" + page.getCurrent()+ "_pageSize_" + page.getSize()+"_supplierId_" + supplierId;
        ValueOperations<String, Page<GoodsEntity>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取该症状所有药品信息
        page.setRecords(goodsMapper.selectPage(page,new EntityWrapper<GoodsEntity>().eq(brandId+"", supplierId)));
        // 设置缓存时间，插入缓存
        operations.set(key, page, 24*60*60, TimeUnit.SECONDS);
        return page;
    }
}
