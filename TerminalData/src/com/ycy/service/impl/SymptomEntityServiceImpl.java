package com.ycy.service.impl;

import com.ycy.dao.SymptomEntityDao;
import com.ycy.dao.impl.SymptomEntityDaoImpl;
import com.ycy.entity.SymptomEntity;
import com.ycy.service.ISymptomEntityService;

import java.util.List;

public class SymptomEntityServiceImpl  implements ISymptomEntityService {
    private SymptomEntityDao symptomEntityDao = new SymptomEntityDaoImpl();
    @Override
    public void saveOrUpdate(List<SymptomEntity> list) {
        symptomEntityDao.saveOrUpdate(list);
    }

    @Override
    public List<SymptomEntity> getSymptomByYS(int num) {
        return symptomEntityDao.getSymptomByYS(num);
    }
}
