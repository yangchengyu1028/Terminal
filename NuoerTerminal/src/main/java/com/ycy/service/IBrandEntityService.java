package com.ycy.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ycy.entity.BrandEntity;

public interface IBrandEntityService extends IService<BrandEntity> {
    /**
     * 获取所有品牌并分页
     * @param page
     * @return
     */
    Page<BrandEntity> getAllBrand(Page<BrandEntity> page);
}
