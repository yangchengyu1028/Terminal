package com.ycy.dao;

import com.ycy.entity.BrandEntity;

import java.util.List;

public interface BrandEntityDao {
    /**
     * 存在修改,不存在插入
     * @param list
     */
    void saveOrUpdate(List<BrandEntity> list);

    /**
     * 查询易索所有品牌
     * @return
     */
    List<BrandEntity> getBrandByYS();
}
