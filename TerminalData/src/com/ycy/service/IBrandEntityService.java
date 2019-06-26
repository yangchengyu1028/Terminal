package com.ycy.service;

import com.ycy.entity.BrandEntity;

import java.util.List;

public interface IBrandEntityService {
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
