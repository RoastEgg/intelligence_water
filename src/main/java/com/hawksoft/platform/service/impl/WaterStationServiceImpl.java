package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.WaterStationDao;
import com.hawksoft.platform.entity.WaterStation;
import com.hawksoft.platform.service.WaterStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterStationServiceImpl implements WaterStationService {
    @Autowired
    private WaterStationDao waterStationDao;

    @Override
    public List<WaterStation> querySectionMap(int stnId) {
        return waterStationDao.querySectionMap(stnId);
    }
}
