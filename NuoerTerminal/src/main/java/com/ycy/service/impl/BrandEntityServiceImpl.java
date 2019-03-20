package com.ycy.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ycy.entity.BrandEntity;
import com.ycy.mapper.BrandMaapper;
import com.ycy.service.IBrandEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BrandEntityServiceImpl extends ServiceImpl<BrandMaapper, BrandEntity> implements IBrandEntityService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BrandMaapper brandMaapper;
    @Override
    public Page<BrandEntity> getAllBrand(Page<BrandEntity> page) {
        // 从缓存中获取药品所有品牌
        String key = "goods_brand_all_page_" + page.getCurrent()+ "_pageSize_" + page.getSize();
        ValueOperations<String, Page<BrandEntity>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取药品所有品牌
        page.setRecords(brandMaapper.selectPage(page,null));
        // 设置缓存时间，插入缓存
        operations.set(key, page, 30*24*60*60, TimeUnit.SECONDS);

        return page;
    }
}
