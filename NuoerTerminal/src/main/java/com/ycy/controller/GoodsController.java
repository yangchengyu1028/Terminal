package com.ycy.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ycy.entity.GoodsEntity;
import com.ycy.service.IGoodsEntityService;
import com.ycy.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IGoodsEntityService goodsEntityService;


    /**
     * 模糊查询某个店铺某个症状下的所有商品并分页
     * @param pageNo
     * @param pageSize
     * @param symptomId
     * @return
     */
    @RequestMapping("/getLocalGoodsBySymptomLikeName")
    @ResponseBody
    public Page<GoodsEntity> getLocalGoodsBySymptomLikeName(int pageNo, int pageSize, int symptomId,String name, HttpServletRequest request) {
        Page<GoodsEntity> page = new Page<>(pageNo,pageSize);
        Page<GoodsEntity> goodsPage = goodsEntityService.getGoodsBySymptomLikeName(page,symptomId,name,(int)request.getSession(false).getAttribute("supplierId"));
        return goodsPage;
    }

    /**
     *查询某个店铺某个品牌下的所有商品并分页
     * @param pageNo
     * @param pageSize
     * @param brand_name
     * @return
     */
    @RequestMapping("/getLocalGoodsByBrandLikeName")
    @ResponseBody
    public Page<GoodsEntity> getLocalGoodsByBrandLikeName(int pageNo,int pageSize,String brand_name,String name, HttpServletRequest request) {
        Page<GoodsEntity> page = new Page<>(pageNo,pageSize);
        Page<GoodsEntity> goodsPage = goodsEntityService.getGoodsByBrandLikeName(page,brand_name,name,(int)request.getSession(false).getAttribute("supplierId"));
        return goodsPage;
    }

    /**
     * 根据药品名模糊查询该店药品并分页
     * @param pageNo
     * @param pageSize
     * @param name
     * @param request
     * @return
     */
    @RequestMapping("/getLocalGoodsByName")
    @ResponseBody
    public Page<GoodsEntity> getLocalGoodsByName(int pageNo, int pageSize,String name, HttpServletRequest request) {
        Page<GoodsEntity> page = new Page<>(pageNo,pageSize);
        Page<GoodsEntity> goodsPage = goodsEntityService.getGoodsByName(page,name,(int)request.getSession(false).getAttribute("supplierId"));
        return goodsPage;
    }

    /**
     * 根据药品条形码查询该店药品（唯一）
     * @param barCode
     * @param request
     * @return
     */
    @RequestMapping("/getLocalGoodsByBarCode")
    @ResponseBody
    public GoodsVO getLocalGoodsByBarCode(String barCode,HttpServletRequest request){

        GoodsVO goodsEntity = goodsEntityService.getGoodsByBarCode(barCode,(int)request.getSession(false).getAttribute("supplierId"));
        return goodsEntity;
    }

    /**
     * 通过id获取某个商品详情
     * @param goodsId
     * @param request
     * @return
     */
    @RequestMapping("/getGoodsByIdToVo")
    @ResponseBody
    public GoodsVO getGoodsByIdToVo(int goodsId, HttpServletRequest request){

        GoodsVO goodsEntity = goodsEntityService.getGoodsByIdToVo(goodsId);
        return goodsEntity;
    }

    @RequestMapping("/getGoodsByIdToEntity")
    @ResponseBody
    public GoodsEntity getGoodsByIdToEntity(int goodsId, HttpServletRequest request){

        GoodsEntity goodsEntity = goodsEntityService.getGoodsByIdToEntity(goodsId);
        return goodsEntity;
    }


    /**
     *查询所有店铺某个品牌下的所有商品并分页
     * @param pageNo
     * @param pageSize
     * @param brand_name
     * @return
     */
    @RequestMapping("/getOnlineGoodsByBrandLikeName")
    @ResponseBody
    public Page<GoodsEntity> getGoodsByBrand(int pageNo,int pageSize,String brand_name,String name, HttpServletRequest request) {
        Page<GoodsEntity> page = new Page<>(pageNo,pageSize);
        Page<GoodsEntity> goodsPage = goodsEntityService.getGoodsByBrandLikeName(page,brand_name,name,61);
        return goodsPage;
    }
    /**
     * 模糊查询所有店铺某个症状下的所有商品并分页
     * @param pageNo
     * @param pageSize
     * @param symptomId
     * @return
     */
    @RequestMapping("/getOnlineGoodsBySymptomLikeName")
    @ResponseBody
    public Page<GoodsEntity> getOnlineGoodsBySymptomLikeName(int pageNo, int pageSize, int symptomId,String name, HttpServletRequest request) {
        Page<GoodsEntity> page = new Page<>(pageNo,pageSize);
        Page<GoodsEntity> goodsPage = goodsEntityService.getGoodsBySymptomLikeName(page,symptomId,name,61);
        return goodsPage;
    }

    /**
     * 根据药品名模糊查询所有店药品并分页
     * @param pageNo
     * @param pageSize
     * @param name
     * @param request
     * @return
     */
    @RequestMapping("/getOnlineGoodsByName")
    @ResponseBody
    public Page<GoodsEntity> getOnlineGoodsByName(int pageNo, int pageSize,String name, HttpServletRequest request) {
        Page<GoodsEntity> page = new Page<>(pageNo,pageSize);
        Page<GoodsEntity> goodsPage = goodsEntityService.getGoodsByName(page,name,61);
        return goodsPage;
    }

    /**
     * 根据药品条形码查询所有店铺药品（唯一）
     * @param barCode
     * @param request
     * @return
     */
    @RequestMapping("/getOnlineGoodsByBarCode")
    @ResponseBody
    public GoodsVO getOnlineGoodsByBarCode(String barCode,HttpServletRequest request){

        GoodsVO goodsEntity = goodsEntityService.getGoodsByBarCode(barCode,61);
        return goodsEntity;
    }
}