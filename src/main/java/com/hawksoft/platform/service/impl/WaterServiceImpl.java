package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.WaterDao;
import com.hawksoft.platform.entity.CameraArgs;
import com.hawksoft.platform.entity.Water;
import com.hawksoft.platform.entity.WaterEarlyWarn;
import com.hawksoft.platform.entity.WaterStation;
import com.hawksoft.platform.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WaterServiceImpl implements WaterService {

    @Autowired
    private WaterDao waterDao;

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
}
