package com.ycy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ycy.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface GoodsMapper extends BaseMapper<GoodsEntity> {


    GoodsEntity getGoodsByBarCode(@Param("barcode") String barcode,@Param("supplierId")int supplierId);

    GoodsEntity getGoodsById(@Param("goodsId") int goodsId,@Param("supplierId")int supplierId);

}
