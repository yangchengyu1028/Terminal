package com.ycy.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ycy.entity.GoodsEntity;


public interface IGoodsEntityService extends IService<GoodsEntity> {

    /**
     * 按症状查询所有
     * @param symptomId
     * @return
     */
    Page<GoodsEntity> getAllBySymptom(Page<GoodsEntity> page,Integer symptomId);
}
