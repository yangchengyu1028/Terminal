package com.ycy.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ycy.entity.GoodsEntity;


public interface IGoodsEntityService extends IService<GoodsEntity> {

    /**
     * 按症状查询该店铺所有商品并分页
     * @param page 
     * @param symptomId 症状id
     * @param supplierId 店铺id
     * @return
     */
    Page<GoodsEntity> getAllBySymptom(Page<GoodsEntity> page,Integer symptomId,Integer supplierId);

    /**
     * 按品牌查询该店铺所有商品并分页
     * @param page
     * @param brandId 品牌id
     * @param supplierId 店铺id
     * @return
     */
    Page<GoodsEntity> getAllByBrand(Page<GoodsEntity> page,Integer brandId,Integer supplierId);

    /**
     * 根据药品名模糊查询药品并分页
     * @param page
     * @param name
     * @param supplierId
     * @return
     */
    Page<GoodsEntity> getGoodsByName(Page<GoodsEntity> page,String name,Integer supplierId);

    /**
     *根据药品条形码查询药品
     * @param name
     * @param supplierId
     * @return
     */
    GoodsEntity getGoodsByBarCode(String name,Integer supplierId);

    /**
     * 根据id获取唯一药品
     * @param goodsId
     * @param supplierId
     * @return
     */
    GoodsEntity getGoodsById(int goodsId,Integer supplierId);

}
