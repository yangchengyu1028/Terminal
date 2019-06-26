package com.ycy.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ycy.entity.BrandEntity;
import com.ycy.service.IBrandEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private IBrandEntityService brandEntityService;

    /**
     * 模糊查询品牌类型并分页(也可查询所有)
     * @param pageNo
     * @param pageSize
     * @param name 模糊品牌名
     * @return
     */
    @RequestMapping("/getBrandByLike")
    public Page<BrandEntity> getBrandByLike(int pageNo, int pageSize, String name){
        Page<BrandEntity> page = new Page<>(pageNo,pageSize);

        return brandEntityService.getBrandByLike(page,name);
    }


}
