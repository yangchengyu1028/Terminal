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
        page.setRecords(goodsMapper.selectPage(page,new EntityWrapper<GoodsEntity>().eq("symptom_id",symptomId).eq("supplier_id", supplierId)));
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
        page.setRecords(goodsMapper.selectPage(page,new EntityWrapper<GoodsEntity>().eq("brand_id",brandId).eq("supplier_id",supplierId)));
        // 设置缓存时间，插入缓存
        operations.set(key, page, 24*60*60, TimeUnit.SECONDS);
        return page;
    }

    @Override
    public Page<GoodsEntity> getGoodsByName(Page<GoodsEntity> page, String name, Integer supplierId) {
        // 从缓存中获取该药品名相似的所有药品
        String key = "goods_name_" + name + "_page_" + page.getCurrent()+ "_pageSize_" + page.getSize()+"_supplierId_" + supplierId;
        ValueOperations<String, Page<GoodsEntity>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取该药品名相似的所有药品信息
        page.setRecords(goodsMapper.selectPage(page,new EntityWrapper<GoodsEntity>().like("goods_name",name).eq("supplier_id",supplierId)));
        // 设置缓存时间，插入缓存
        operations.set(key, page, 7*24*60*60, TimeUnit.SECONDS);

        return page;
    }

    @Override
    public GoodsEntity getGoodsByBarCode(String barcode, Integer supplierId) {
        // 从缓存中获取该药品条形码的药品
        String key = "goods_barcode_" + barcode +"_supplierId_" + supplierId;
        ValueOperations<String, GoodsEntity> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取该药品名相似的所有药品信息
        GoodsEntity goodsEntity = goodsMapper.getGoodsByBarCode(barcode,supplierId);
        // 设置缓存时间，插入缓存
        operations.set(key, goodsEntity, 24*60*60, TimeUnit.SECONDS);

        return goodsEntity;
    }

    @Override
    public GoodsEntity getGoodsById(int goodsId,Integer supplierId) {
        // 从缓存中获取药品
        String key = "goods_goodsId_" + goodsId +"_supplierId_" + supplierId;
        ValueOperations<String, GoodsEntity> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取药品
        GoodsEntity goodsEntity = goodsMapper.getGoodsById(goodsId,supplierId);
        // 设置缓存时间，插入缓存
        operations.set(key, goodsEntity, 6*60*60, TimeUnit.SECONDS);

        return goodsEntity;
    }
}
