package com.ycy.service.impl;

import com.ycy.dao.GoodsEntityDao;
import com.ycy.dao.impl.GoodsEntityDaoImpl;
import com.ycy.entity.GoodsEntity;
import com.ycy.service.IGoodsEntityService;

import java.util.List;

public class GoodsEntityServiceImpl implements IGoodsEntityService {

    private GoodsEntityDao goodsEntityDao = new GoodsEntityDaoImpl();
    @Override
    public void saveOrUpdate(List<GoodsEntity> list) {
        if(list.isEmpty()){
            return;
        }
        goodsEntityDao.saveOrUpdate(list);
    }
}
