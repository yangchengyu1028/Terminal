package com.ycy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ycy.entity.SupplierEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SupplierMapper extends BaseMapper<SupplierEntity> {
    /**
     * 通过手机号查询店铺
     * @param mobile
     * @return
     */
    SupplierEntity getByMobile(@Param("mobile") String mobile);

}
