package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.WaterDao;
import com.hawksoft.platform.dao.WaterStationDao;
import com.hawksoft.platform.entity.CameraArgs;
import com.hawksoft.platform.entity.Water;
import com.hawksoft.platform.entity.WaterEarlyWarn;
import com.hawksoft.platform.entity.WaterStation;
import com.hawksoft.platform.service.WaterService;
import com.hawksoft.platform.service.WaterStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WaterServiceImpl implements WaterService {

    @Autowired
    private WaterDao waterDao;
    @Autowired
    private WaterStationDao waterStationDao;

    @Override
    public List<Water> findLastWater() {
        return waterDao.findLastWater();
    }

    @Override
    public List<Water> hisWater(Map<String, Object> params) {
        return waterDao.hisWater(params);
    }

    @Override
    public Water hisWaterLevelPic(Map<String, Object> params) {
        return waterDao.hisWaterLevelPic(params);
    }

    @Override
    public List<WaterStation> queryAllWaterStation(Map<String, Object> params) {
        return waterDao.queryAllWaterStation(params);
    }

    @Override
    public List<String> queryCityData() {
        return waterDao.queryCityData();
    }

    @Override
    public Water queryLastWaterByStnId(int stnId) {
        return waterDao.queryLastWaterByStnId(stnId);
    }

    @Override
    public void saveWater(Water water) {
        waterDao.saveWater(water);
    }

    @Override
    public List<String> queryPicPathByStnId(int stnId) {
        return waterDao.queryPicPathByStnId(stnId);
    }

    @Override
    public int saveCameraArgs(CameraArgs args) {
        return waterDao.saveCameraArgs(args);
    }

    @Override
    public CameraArgs findCameraArgsById(int id) {
        return waterDao.findCameraArgsById(id);
    }

    @Override
    public List<CameraArgs> findCameraArgsByStnId(int stnId) {
        return waterDao.findCameraArgsByStnId(stnId);
    }

    @Override
    public CameraArgs findCameraArgsNoDefaultById(int id) {
        return waterDao.findCameraArgsNoDefaultById(id);
    }

    @Override
    public CameraArgs findCameraArgsNoDefault(int stnId) {
        return waterDao.findCameraArgsNoDefault(stnId);
    }

    @Override
    public int deleteCameraArgsNoDefaultById(int id) {
        return waterDao.deleteCameraArgsNoDefaultById(id);
    }

    @Override
    public int updateWaterWarn(WaterEarlyWarn waterEarlyWarn) {
        return waterDao.updateWaterWarn(waterEarlyWarn);
    }

    @Override
    public WaterEarlyWarn getWaterWarn(int stnId, int type) {
        return waterDao.getWaterWarn(stnId, type);
    }

    /**
     * 取最新的DAYS条实时水位
     * @param map 查询参数
     * @return 返回历史水位的数据
     */
    @Override
    public List<Water> lastWaterRecordsNum(Map<String, Object> map)

    {
        return waterDao.lastWaterRecordsNum(map);
    }

    @Override
    public int queryStationInfo(int stnId) {
        return waterDao.queryStationInfo(stnId);
    }

    @Override
    public Map<String, Object> queryLastAndHis(Map<String, Object> map) {
        Map<String,Object> answerMap = new HashMap<>();
        Map<String,Object> picMap = new HashMap<>();

        int stnId = (Integer) map.get("stnId");
//        String startTime = (String) map.get("startTime");
//        String endTime = (String) map.get("endTime");
        BigDecimal maxLevel ,minLevel;

        Water water = queryLastWaterByStnId(stnId);//当前水位信息
        List<Water> waterList = hisWater(map);//历史水位信息
        maxLevel = waterList.get(0).getWaterLevel();
        minLevel = waterList.get(0).getWaterLevel();
        for (Water w:waterList){
            if (w.getWaterLevel().compareTo(maxLevel) == 1) {maxLevel = w.getWaterLevel();}
            if (w.getWaterLevel().compareTo(minLevel) == -1){minLevel = w.getWaterLevel();}

            Map<String,Object> hisPicMap = new HashMap<>();
            hisPicMap.put("hisUrl",w.getPicPath());
            hisPicMap.put("hisDate",w.getC_time());
            answerMap.put("hisPic",hisPicMap);//历史图片，包括url和date
        }

        List<WaterStation> waterStationList = waterStationDao.queryStationBaseInfo();
        for (WaterStation ws:waterStationList){
            if (ws.getId() == stnId){
                answerMap.put("name",ws.getStnName());//站点名称
            }
        }
        answerMap.put("lastWaterLevel",water.getWaterLevel());//当前水位
        picMap.put("lastUrl",water.getPicPath());
        picMap.put("lastDate",water.getC_time());
        answerMap.put("lastPic",picMap);//当前图片，包括URL和date
        answerMap.put("maxLevel",maxLevel);//历史水位最大值
        answerMap.put("minLevel",minLevel);//历史水位最小值

        return answerMap;
    }
}
