package com.ycy.service.impl;

import com.ycy.dao.SupplierEntityDao;
import com.ycy.dao.impl.SupplierEntityDaoImpl;
import com.ycy.entity.SupplierEntity;
import com.ycy.service.ISupplierEntityService;

import java.util.List;

public class SupplierEntityServiceImpl implements ISupplierEntityService {

    private SupplierEntityDao supplierEntityDao = new SupplierEntityDaoImpl();
    @Override
    public void saveOrUpdate(List<SupplierEntity> list) {
        if (list.isEmpty()){
            return;
        }
        supplierEntityDao.saveOrUpdate(list);
    }
}
