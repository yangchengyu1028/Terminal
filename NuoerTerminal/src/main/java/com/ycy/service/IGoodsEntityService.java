package com.ycy.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ycy.entity.GoodsEntity;
import com.ycy.vo.GoodsVO;


public interface IGoodsEntityService extends IService<GoodsEntity> {

    /**
     * 按症状模糊查询该店铺商品并分页
     * @param page 
     * @param symptomId 症状id
     * @param supplierId 店铺id
     *
     * @return
     */
    Page<GoodsEntity> getGoodsBySymptomLikeName(Page<GoodsEntity> page,Integer symptomId,String name,Integer supplierId);

    /**
     * 按品牌查询该店铺所有商品并分页
     * @param page
     * @param brandId 品牌id
     * @param supplierId 店铺id
     * @return
     */
    Page<GoodsEntity> getGoodsByBrandLikeName(Page<GoodsEntity> page,String brand_name,String name,Integer supplierId);

    /**
     * 根据药品名模糊查询本店铺药品并分页
     * @param page
     * @param name
     * @param supplierId
     * @return
     */
    Page<GoodsEntity> getGoodsByName(Page<GoodsEntity> page,String name,Integer supplierId);

    /**
     *根据药品条形码查询该店药品
     * @param name
     * @param supplierId
     * @return
     */
    GoodsVO getGoodsByBarCode(String name, Integer supplierId);

    /**
     * 根据id获取唯一药品
     * @param goodsId
     * @return
     */
    GoodsVO getGoodsByIdToVo(int goodsId);

    /**
     * 根据id获取唯一药品
     * @param goodsId
     * @return
     */
    GoodsEntity getGoodsByIdToEntity(int goodsId);


}
