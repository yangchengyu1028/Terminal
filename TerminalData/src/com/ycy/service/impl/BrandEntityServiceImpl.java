package com.ycy.service.impl;

import com.ycy.dao.BrandEntityDao;
import com.ycy.dao.impl.BrandEntityDaoImpl;
import com.ycy.entity.BrandEntity;
import com.ycy.service.IBrandEntityService;

import java.util.List;

public class BrandEntityServiceImpl implements IBrandEntityService {
    private BrandEntityDao brandEntityDao = new BrandEntityDaoImpl();
    @Override
    public void saveOrUpdate(List<BrandEntity> list) {
        if (list.isEmpty()){
            return;
        }
        brandEntityDao.saveOrUpdate(list);
    }

    @Override
    public List<BrandEntity> getBrandByYS() {
        return brandEntityDao.getBrandByYS();
    }
}
