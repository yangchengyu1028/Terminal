package com.ycy.service;

import com.ycy.entity.SymptomEntity;

import java.util.List;

public interface ISymptomEntityService {
    /**
     * 存在修改 不存在添加
     * @param list
     */
    void saveOrUpdate(List<SymptomEntity> list);

    /**
     * 分页查询易索所有商家的症状分类
     * @param num
     * @return
     */
    List<SymptomEntity> getSymptomByYS(int num);
}
