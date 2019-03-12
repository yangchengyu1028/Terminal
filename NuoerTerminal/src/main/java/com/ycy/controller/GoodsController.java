package com.ycy.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ycy.entity.GoodsEntity;
import com.ycy.service.IGoodsEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GoodsController {
    @Autowired
    private IGoodsEntityService goodsEntityService;

    @RequestMapping("/getGoodsBySymptom")
    @ResponseBody
    public String index(int pageNo,int pageSize,int symptomId ) {
        Page<GoodsEntity> page = new Page<>(pageNo,pageSize);
        long beginTime = System.currentTimeMillis();
        Page<GoodsEntity> goodsPage = goodsEntityService.getAllBySymptom(page,symptomId);
        long time = System.currentTimeMillis() - beginTime;
        return "Hello SpringBoot" + goodsPage.getRecords().size() + ",消耗查询时间：" + time;

    }
    @RequestMapping("/hello")
    public String index1() {

        return "Hello SpringBoot" ;

    }
}