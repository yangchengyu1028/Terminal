package com.ycy.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ycy.entity.SymptomEntity;

public interface ISymptomEntityService extends IService<SymptomEntity> {


    /**
     * 模糊查询症状并分页(也可以查询所有症状)
     * @param page
     * @param name
     * @return
     */
    public Page<SymptomEntity> getSymptomByLike(Page<SymptomEntity> page,String name,int supplierId);

}
