package com.hawksoft.platform.service;

import com.hawksoft.platform.entity.CameraArgs;
import com.hawksoft.platform.entity.Water;
import com.hawksoft.platform.entity.WaterEarlyWarn;
import com.hawksoft.platform.entity.WaterStation;

import java.util.List;
import java.util.Map;

public interface WaterService {

    public List<Water> findLastWater();
    public List<Water> hisWater(Map<String, Object> map);
    public Water hisWaterLevelPic(Map<String, Object> map);
    public List<WaterStation> queryAllWaterStation(Map<String, Object> params);
    public List<String> queryCityData();
    public Water queryLastWaterByStnId(int stnId);
    public void saveWater(Water water);
    public List<String> queryPicPathByStnId(int stnId);
    public int saveCameraArgs(CameraArgs args);
    public CameraArgs findCameraArgsById(int id);
    public List<CameraArgs> findCameraArgsByStnId(int stnId);
    public CameraArgs findCameraArgsNoDefaultById(int id);
    public CameraArgs findCameraArgsNoDefault(int stnId);
    public int deleteCameraArgsNoDefaultById(int id);
    public int updateWaterWarn(WaterEarlyWarn waterEarlyWarn);
    public WaterEarlyWarn getWaterWarn(int stnId, int type);
    /**
     * 取最新的DAYS条实时水位
     * @param map 查询参数
     * @return 返回历史水位的数据
     */
    public List<Water> lastWaterRecordsNum(Map<String, Object> map);
    /**
     * 查询某站点是否有水位信息
     * @param stnId
     * @return 记录条数
     */
    public int queryStationInfo(int stnId);

    /**
     * 查询当前和历史的水位相关信息
     * @param map
     * @return
     */
    public Map<String ,Object> queryLastAndHis(Map<String ,Object> map);
}
