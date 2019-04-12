package com.ycy.dao;

import com.ycy.entity.GoodsEntity;

import java.util.List;

public interface GoodsEntityDao {
    /**
     * 不存在则添加 存在则修改
     * @param list
     */
    void saveOrUpdate(List<GoodsEntity> list);

    /**
     * 分页获取伊索商品
     * @param page
     * @return
     */
    List<GoodsEntity> getList(int page);
}
