package com.ycy.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ycy.entity.SymptomEntity;
import com.ycy.mapper.SymptomMapper;
import com.ycy.service.ISymptomEntityService;
import com.ycy.util.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.concurrent.TimeUnit;

@Service
public class SymptomEntityServiceImpl extends ServiceImpl<SymptomMapper, SymptomEntity> implements ISymptomEntityService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SymptomMapper symptomMapper;
    private RandomData randomData = new RandomData();

    public Page<SymptomEntity> getSymptomByLike(Page<SymptomEntity> page,String name,int supplierId){
        // 从缓存中获取药品症状
        String key = "goods_symptom_name_like_"+name+"_page_" + page.getCurrent()+ "_pageSize_" + page.getSize()+"_supplierId_"+supplierId;
        ValueOperations<String, Page<SymptomEntity>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取药品所有症状
        if ("".equals(name)){
            page.setRecords(symptomMapper.selectPage(page,new EntityWrapper<SymptomEntity>().eq("supplier_id", supplierId)));
        }else {
            page.setRecords(symptomMapper.selectPage(page,new EntityWrapper<SymptomEntity>().like("symptom_name",name).eq("supplier_id", supplierId)));
        }
        // 设置缓存时间，插入缓存
        operations.set(key, page, randomData.getRandom(10,20)*24*60*60, TimeUnit.SECONDS);
        return page;
    }




}
