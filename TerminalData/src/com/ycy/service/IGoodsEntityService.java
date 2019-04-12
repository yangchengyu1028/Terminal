package com.ycy.service;

import com.ycy.entity.GoodsEntity;

import java.util.List;

public interface IGoodsEntityService {
    /**
     * 不存在则添加 存在则修改
     * @param list
     */
    void saveOrUpdate(List<GoodsEntity> list);
}
