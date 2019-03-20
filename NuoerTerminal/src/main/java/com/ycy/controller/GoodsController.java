package com.ycy.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ycy.entity.GoodsEntity;
import com.ycy.entity.SymptomEntity;
import com.ycy.service.IGoodsEntityService;
import com.ycy.service.ISymptomEntityService;
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
    @Autowired
    private ISymptomEntityService symptomEntityService;

    /**
     * 查询某个店铺某个症状下的所有商品并分页
     * @param pageNo
     * @param pageSize
     * @param symptomId
     * @return
     */
    @RequestMapping("/getGoodsBySymptom")
    @ResponseBody
    public Page<GoodsEntity> getGoodsBySymptom(int pageNo, int pageSize, int symptomId, HttpServletRequest request) {
        Page<GoodsEntity> page = new Page<>(pageNo,pageSize);
        Page<GoodsEntity> goodsPage = goodsEntityService.getAllBySymptom(page,symptomId,(int)request.getSession().getAttribute("supplierId"));
        return goodsPage;
    }

    /**
     *查询某个店铺某个品牌下的所有商品并分页
     * @param pageNo
     * @param pageSize
     * @param brandId
     * @return
     */
    @RequestMapping("/getGoodsByBrand")
    @ResponseBody
    public Page<GoodsEntity> getGoodsByBrand(int pageNo,int pageSize,int brandId, HttpServletRequest request) {
        Page<GoodsEntity> page = new Page<>(pageNo,pageSize);
        Page<GoodsEntity> goodsPage = goodsEntityService.getAllByBrand(page,brandId,(int)request.getSession().getAttribute("supplierId"));
        return goodsPage;
    }

    /**
     * 得到所有症状并分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/getAllSymptom")
    @ResponseBody
    public Page<SymptomEntity> getAllSymptom(int pageNo, int pageSize) {
        Page<SymptomEntity> page = new Page<>(pageNo,pageSize);
        Page<SymptomEntity> goodsPage = symptomEntityService.getListOfSymptom(page);
        return goodsPage;
    }

    /**
     * 根据药品名模糊查询并分页
     * @param pageNo
     * @param pageSize
     * @param name
     * @param request
     * @return
     */
    @RequestMapping("/getGoodsByName")
    @ResponseBody
    public Page<GoodsEntity> getAllSymptom(int pageNo, int pageSize,String name, HttpServletRequest request) {
        Page<GoodsEntity> page = new Page<>(pageNo,pageSize);
        Page<GoodsEntity> goodsPage = goodsEntityService.getGoodsByName(page,name,(int)request.getSession().getAttribute("supplierId"));
        return goodsPage;
    }

    /**
     * 根据药品条形码查询药品（唯一）
     * @param barcode
     * @param request
     * @return
     */
    @RequestMapping("/getGoodsByBarCode")
    @ResponseBody
    public GoodsEntity getGoodsByBarCode(String barcode,HttpServletRequest request){

        GoodsEntity goodsEntity = goodsEntityService.getGoodsByBarCode(barcode,(int)request.getSession().getAttribute("supplierId"));
        return goodsEntity;
    }

    /**
     * 通过id获取该店铺某个商品
     * @param goodsId
     * @param request
     * @return
     */
    @RequestMapping("/getGoodsById")
    @ResponseBody
    public GoodsEntity getGoodsById(int goodsId,HttpServletRequest request){

        GoodsEntity goodsEntity = goodsEntityService.getGoodsById(goodsId,(int)request.getSession().getAttribute("supplierId"));
        return goodsEntity;
    }

}