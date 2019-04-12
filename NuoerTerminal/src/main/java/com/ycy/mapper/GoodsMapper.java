package com.ycy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ycy.entity.GoodsEntity;
import com.ycy.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface GoodsMapper extends BaseMapper<GoodsEntity> {


    GoodsVO getLocalGoodsByBarCode(@Param("barcode") String barcode,@Param("supplierId") Integer supplierId);

    GoodsVO getOnlineGoodsByBarCode(@Param("barcode") String barcode);

    GoodsVO getGoodsByIdToVo(@Param("goodsId") int goodsId);


    GoodsEntity getGoodsByIdToEntity(@Param("goodsId") int goodsId);

}
