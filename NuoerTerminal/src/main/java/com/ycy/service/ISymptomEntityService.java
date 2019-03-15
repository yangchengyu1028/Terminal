package com.ycy.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ycy.entity.SymptomEntity;

public interface ISymptomEntityService extends IService<SymptomEntity> {

     /**
     * 获取所有症状类型并分页
     * @param page 分页
     * @return
     */
    Page<SymptomEntity> getListOfSymptom(Page<SymptomEntity> page);

}
