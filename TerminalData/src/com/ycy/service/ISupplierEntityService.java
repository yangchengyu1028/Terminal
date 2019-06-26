package com.ycy.service;

import com.ycy.entity.SupplierEntity;

import java.util.List;

public interface ISupplierEntityService {
    /**
     * 存在修改，不存在插入
     * @param list
     */
    void saveOrUpdate(List<SupplierEntity> list);
}
