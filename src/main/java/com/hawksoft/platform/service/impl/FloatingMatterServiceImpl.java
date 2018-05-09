package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.FloatingMatterDao;
import com.hawksoft.platform.entity.FloatingMatter;
import com.hawksoft.platform.service.FloatingMatterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FloatingMatterServiceImpl implements FloatingMatterService {

    @Autowired
    private FloatingMatterDao floatingMatterDao;


    @Override
    public List<FloatingMatter> findHistFloatingMatters(Map<String, Object> map) {
        return floatingMatterDao.findFMByTime(map);
    }

    @Override
    public List<FloatingMatter> queryFMByDate(Map<String, Object> map) {
        return floatingMatterDao.queryFMByDate(map);
    }

    @Override
    public List<Map<String,Object>> queryRecordsNumByTime(Map<String, Object> map) {
        return floatingMatterDao.queryRecordsNumByDayTime(map);
    }

    @Override
    public List<Map<String, Object>> queryLastDaysRecordsNum(Map<String, Object> map) {
        return floatingMatterDao.queryLastDaysRecordsNum(map);
    }

    @Override
    public int saveFloatingMatter(FloatingMatter floatingMatter) {
        return floatingMatterDao.saveFloatingMatter(floatingMatter);
    }

    @Override
    public List<FloatingMatter> queryLastRecord(int stnId) {
        return floatingMatterDao.queryLastRecord(stnId);
    }

    @Override
    public int queryStationInfo(int stnId) {
        return floatingMatterDao.queryStationInfo(stnId);
    }

    /**
     * 根据月份查询漂浮物天数
     * @param map
     * @return
     */
    @Override
    public int findDaysByMonth(Map<String, Object> map) {
        return floatingMatterDao.findDaysByMonth(map);
    }

    /**
     * 查询漂浮物的数量与总的漂浮物面积
     * @param param
     * @return
     */
    @Override
    public  Map<String,Object> queryFloatCountAndSumArea(Map<String,Object> param){
        return floatingMatterDao.queryFloatCountAndSumArea(param);
    }
}
