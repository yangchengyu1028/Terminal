package com.ycy.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ycy.entity.BrandEntity;
import com.ycy.mapper.BrandMapper;
import com.ycy.service.IBrandEntityService;
import com.ycy.util.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BrandEntityServiceImpl extends ServiceImpl<BrandMapper, BrandEntity> implements IBrandEntityService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BrandMapper brandMapper;
    private RandomData randomData = new RandomData();
    @Override
    public Page<BrandEntity> getBrandByLike(Page<BrandEntity> page,String name) {
        // 从缓存中获取药品所有品牌
        String key = "goods_brand_name_like_" + name +"_page_"+ page.getCurrent()+ "_pageSize_" + page.getSize();
        ValueOperations<String, Page<BrandEntity>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取药品所有品牌
        page.setRecords(brandMapper.selectPage(page,new EntityWrapper<BrandEntity>().like("brand_name",name)));
        // 设置缓存时间，插入缓存
        operations.set(key, page, randomData.getRandom(20,40)*24*60*60, TimeUnit.SECONDS);

        return page;
    }
}
