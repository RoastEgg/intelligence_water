package com.hawksoft.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.hawksoft.platform.dao.*;
import com.hawksoft.platform.service.WaterStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WaterStationServiceImpl implements WaterStationService {
    @Autowired
    private WaterStationDao waterStationDao;
    @Autowired
    private WaterDao waterDao;
    @Autowired
    private WaterQualityDao waterQualityDao;
    @Autowired
    private FlowDao flowDao;
    @Autowired
    private FloatingMatterDao floatingMatterDao;
    @Autowired
    private UnmannedBoatDao unmannedBoatDao;


    @Override
    public List<Map<String ,Object>> querySectionMap(int stnId) {
        return waterStationDao.querySectionMap(stnId);
    }

    @Override
    public Map<String, Boolean> queryStationInfo(int stnId) {
        Boolean hasWaterLevel = waterDao.queryStationInfo(stnId)>0?true:false;
        Boolean hasWaterQuality = waterQualityDao.queryStationInfo(stnId)>0?true:false;
        Boolean hasWaterFlow = flowDao.queryStationInfo(stnId)>0?true:false;
        Boolean hasFloatingMaterial = floatingMatterDao.queryStationInfo(stnId)>0?true:false;
        //Boolean hasUnmannedShip = unmannedBoatDao.queryStationInfo(stnId)>0?true:false;
        Boolean hasUnmannedShip = stnId==1?true:false;

        Map<String ,Boolean> map = new HashMap<>();
        map.put("hasWaterLevel",hasWaterLevel);
        map.put("hasWaterQuality",hasWaterQuality);
        map.put("hasWaterFlow",hasWaterFlow);
        map.put("hasFloatingMaterial",hasFloatingMaterial);
        map.put("hasUnmannedShip",hasUnmannedShip);
        return map;
    }
}
