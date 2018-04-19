package com.hawksoft.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.hawksoft.platform.VO.HistoryRecordVO;
import com.hawksoft.platform.VO.HistoryStationInfoVO;
import com.hawksoft.platform.VO.PdfVO;
import com.hawksoft.platform.dao.*;
import com.hawksoft.platform.entity.WaterStation;
import com.hawksoft.platform.service.WaterStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private WeatherWaveDao weatherWaveDao;


    @Override
    public List<Map<String ,Object>> querySectionMap(int stnId) {
        return waterStationDao.querySectionMap(stnId);
    }

    @Override
    public Map<String, Object> queryStationInfo(int stnId) {
        Boolean hasWaterLevel = waterDao.queryStationInfo(stnId)>0?true:false;
        Boolean hasWaterQuality = waterQualityDao.queryStationInfo(stnId)>0?true:false;
        Boolean hasWaterFlow = flowDao.queryStationInfo(stnId)>0?true:false;
        Boolean hasFloatingMaterial = floatingMatterDao.queryStationInfo(stnId)>0?true:false;
        Boolean hasUnmannedShip = unmannedBoatDao.queryStationInfo(stnId)>0?true:false;
        Boolean hasHistoryRecord = true;
        HistoryRecordVO historyRecordVO = queryHistoryInfo(stnId);
        if (historyRecordVO.getPdfVOList()==null&&historyRecordVO.getPicList()==null){
            hasHistoryRecord = false;
        }
        Boolean hasWeather = weatherWaveDao.findLastWeather(stnId).size()>0?true:false;
        Boolean hasWave = weatherWaveDao.findLastWave(stnId).size()>0?true:false;

        //Boolean hasUnmannedShip = stnId==1?true:false;

        Map<String ,Object> map = new HashMap<>();
        map.put("hasWaterLevel",hasWaterLevel);
        map.put("hasWaterQuality",hasWaterQuality);
        map.put("hasWaterFlow",hasWaterFlow);
        map.put("hasFloatingMaterial",hasFloatingMaterial);
        map.put("hasUnmannedShip",hasUnmannedShip);
        map.put("hasHistoryRecord",hasHistoryRecord);
        map.put("hasWeather",hasWeather);
        map.put("hasWave",hasWave);
        return map;
    }

    @Override
    public List<Map<String,Object>> queryAllStationInfo() {
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<WaterStation> waterStationList= waterStationDao.queryStationBaseInfo();
        for (WaterStation ws:waterStationList){
            Map<String,Object> map = queryStationInfo(ws.getId());
            map.put("id",ws.getId());
            map.put("name",ws.getStnName());
            map.put("x",ws.getX());
            map.put("y",ws.getY());
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public HistoryRecordVO queryHistoryInfo(int stnId) {
        HistoryRecordVO historyRecordVO = new HistoryRecordVO();

        List<PdfVO> pdfList = HistoryStationInfoVO.pdfMap.get(stnId);
        List<String> picList = HistoryStationInfoVO.picMap.get(stnId);
        historyRecordVO.setPdfVOList(pdfList);
        historyRecordVO.setPicList(picList);
        return historyRecordVO;
    }
}
