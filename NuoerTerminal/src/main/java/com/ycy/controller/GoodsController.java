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

    @RequestMapping("/getAllSymptom")
    @ResponseBody
    public Page<SymptomEntity> getAllSymptom(int pageNo, int pageSize) {
        Page<SymptomEntity> page = new Page<>(pageNo,pageSize);
        Page<SymptomEntity> goodsPage = symptomEntityService.getListOfSymptom(page);
        return goodsPage;
    }

}