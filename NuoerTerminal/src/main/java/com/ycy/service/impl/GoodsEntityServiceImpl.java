package com.ycy.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ycy.entity.GoodsEntity;
import com.ycy.mapper.GoodsMapper;
import com.ycy.service.IGoodsEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsEntityServiceImpl extends ServiceImpl<GoodsMapper, GoodsEntity> implements IGoodsEntityService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    public Page<GoodsEntity> getAllBySymptom(Page<GoodsEntity> page,Integer symptomId){
        Page<GoodsEntity> goodsEntityPage = new Page<>();
        // 从缓存中获取该症状所有药品
        String key = "goods_symptom_" + symptomId + "_page_" + page.getCurrent();
        ValueOperations<String, List<GoodsEntity>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            goodsEntityPage.setRecords(operations.get(key));
            return goodsEntityPage;
        }
        // 从 DB 中获取该症状所有药品信息
        goodsEntityPage.setRecords(goodsMapper.getAllBySymptom(page,symptomId));
        // 插入缓存
        operations.set(key, goodsEntityPage.getRecords());
        return goodsEntityPage;
    }
}
