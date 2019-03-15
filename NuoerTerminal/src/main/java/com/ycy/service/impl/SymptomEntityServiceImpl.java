package com.ycy.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ycy.entity.SymptomEntity;
import com.ycy.mapper.SymptomMapper;
import com.ycy.service.ISymptomEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SymptomEntityServiceImpl extends ServiceImpl<SymptomMapper, SymptomEntity> implements ISymptomEntityService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SymptomMapper symptomMapper;

    public Page<SymptomEntity> getListOfSymptom(Page<SymptomEntity> page){
        // 从缓存中获取药品所有症状
        String key = "goods_symptom_all_page_" + page.getCurrent()+ "_pageSize_" + page.getSize();
        ValueOperations<String, Page<SymptomEntity>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            return operations.get(key);
        }
        // 从 DB 中获取药品所有症状
        page.setRecords(symptomMapper.selectPage(page,null));
        // 设置缓存时间，插入缓存
        operations.set(key, page, 30*24*60*60, TimeUnit.SECONDS);

        return page;
    }




}
