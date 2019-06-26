package com.ycy.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ycy.entity.GoodsEntity;
import com.ycy.mapper.GoodsMapper;
import com.ycy.service.IGoodsEntityService;
import com.ycy.util.IsChineseOrEnglish;
import com.ycy.util.RandomData;
import com.ycy.vo.GoodsVO;
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
    private RandomData randomData = new RandomData();
    private IsChineseOrEnglish isChineseOrEnglish = new IsChineseOrEnglish();

    public Page<GoodsEntity> getGoodsBySymptomLikeName(Page<GoodsEntity> page,Integer symptomId,String name,Integer supplierId){
        // 从缓存中获取该症状所有药品
        String key = "goods_symptom_" + symptomId +"_name_like_"+name+ "_page_" + page.getCurrent()+ "_pageSize_" + page.getSize()+"_supplierId_" + supplierId;
        ValueOperations<String, Page<GoodsEntity>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        if (isChineseOrEnglish.isEnglish(name)){
            // 从 DB 中获取该症状所有药品信息
            page.setRecords(goodsMapper.selectPage(page,new EntityWrapper<GoodsEntity>().eq("symptom_id",symptomId).eq("supplier_id", supplierId).like("first_py",name)));
        }else {
            // 从 DB 中获取该症状所有药品信息
            page.setRecords(goodsMapper.selectPage(page,new EntityWrapper<GoodsEntity>().eq("symptom_id",symptomId).eq("supplier_id", supplierId).like("goods_name",name)));
        }
        // 设置缓存时间，插入缓存
        operations.set(key, page, randomData.getRandom(20,50)*60*60, TimeUnit.SECONDS);
        return page;
    }

    public Page<GoodsEntity> getGoodsByBrandLikeName(Page<GoodsEntity> page,String brand_name,String name,Integer supplierId){
        // 从缓存中获取该品牌所有药品
        String key = "goods_brand_" + brand_name +"_name_like_"+ name + "_page_" + page.getCurrent()+ "_pageSize_" + page.getSize()+"_supplierId_" + supplierId;
        ValueOperations<String, Page<GoodsEntity>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        if (isChineseOrEnglish.isEnglish(name)){
            // 从 DB 中获取该品牌所有药品信息
            page.setRecords(goodsMapper.selectPage(page,new EntityWrapper<GoodsEntity>().eq("brand_name",brand_name).eq("supplier_id",supplierId).like("first_py",name)));
        }else {
            // 从 DB 中获取该品牌所有药品信息
            page.setRecords(goodsMapper.selectPage(page,new EntityWrapper<GoodsEntity>().eq("brand_name",brand_name).eq("supplier_id",supplierId).like("goods_name",name)));
        }
        // 设置缓存时间，插入缓存
        operations.set(key, page, randomData.getRandom(20,50)*60*60, TimeUnit.SECONDS);
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
        operations.set(key, page, randomData.getRandom(20,50)*60*60, TimeUnit.SECONDS);

        return page;
    }

    @Override
    public GoodsVO getGoodsByBarCode(String barcode, Integer supplierId) {
        // 从缓存中获取该药品条形码的药品
        String key = "goods_barcode_" + barcode + "_supplierId_"+supplierId;
        ValueOperations<String, GoodsVO> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取该药品名相似的所有药品信息
        GoodsVO goodsEntity = goodsMapper.getLocalGoodsByBarCode(barcode,supplierId);
        // 设置缓存时间，插入缓存
        operations.set(key, goodsEntity, randomData.getRandom(1,5)*60*60, TimeUnit.SECONDS);

        return goodsEntity;
    }

    @Override
    public GoodsVO getGoodsByIdToVo(int goodsId) {
        // 从缓存中获取药品
        String key = "goods_goodsId_Vo" + goodsId;
        ValueOperations<String, GoodsVO> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取药品
        GoodsVO goodsEntity = goodsMapper.getGoodsByIdToVo(goodsId);
        // 设置缓存时间，插入缓存
        operations.set(key, goodsEntity, randomData.getRandom(20,100)*60, TimeUnit.SECONDS);

        return goodsEntity;
    }

    @Override
    public GoodsEntity getGoodsByIdToEntity(int goodsId) {
        // 从缓存中获取药品
        String key = "goods_goodsId_Entity" + goodsId;
        ValueOperations<String, GoodsEntity> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取药品
        GoodsEntity goodsEntity = goodsMapper.getGoodsByIdToEntity(goodsId);
        // 设置缓存时间，插入缓存
        operations.set(key, goodsEntity, randomData.getRandom(20,100)*60, TimeUnit.SECONDS);

        return goodsEntity;
    }


}
