package com.ycy.service;

import com.baomidou.mybatisplus.service.IService;
import com.ycy.entity.SupplierEntity;

public interface ISupplierEntityService extends IService<SupplierEntity> {
    /**
     * 通过手机号验证是否存在该店铺并获取店铺id
     * @param mobile
     * @return
     */
    int getSupplierByMobile(String mobile);
}
