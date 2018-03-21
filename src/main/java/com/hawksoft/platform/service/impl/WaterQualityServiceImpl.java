package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.WaterQualityDao;
import com.hawksoft.platform.entity.WaterQuality;
import com.hawksoft.platform.entity.WaterEarlyWarn;
import com.hawksoft.platform.entity.WaterStation;
import com.hawksoft.platform.service.WaterQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WaterQualityServiceImpl implements WaterQualityService {

    @Autowired
    private WaterQualityDao WaterQualityDao;

    @Override
    public List<WaterQuality> findLastWaterQuality(int stnId) {
        return WaterQualityDao.findLastWaterQuality(stnId);
    }

    @Override
    public List<WaterQuality> hisWaterQuality(Map<String, Object> params) {
        return WaterQualityDao.hisWaterQuality(params);
    }

    @Override
    public List<WaterStation> queryAllWaterStation() {
        return WaterQualityDao.queryAllWaterStation();
    }

    @Override
    public List<String> queryCityData() {
        return WaterQualityDao.queryCityData();
    }

    @Override
    public WaterQuality queryLastWaterQualityByStnId(int stnId) {
        return WaterQualityDao.queryLastWaterQualityByStnId(stnId);
    }

    /**
     * 查询上周水质
     * @param map
     * @return
     */
    @Override
    public List<WaterQuality>  queryLastWeekWaterQuality(Map<String, Object> map){
        return WaterQualityDao.queryLastWeekWaterQuality(map);
    }

    /**
     * 查询当前站点某个参数的水质数据
     * @return 当前站点某个参数的水质信息
     */
    @Override
    public List<WaterQuality> queryLastWaterQualityByStnIdType(Map<String, Object> map){
        return WaterQualityDao.queryLastWaterQualityByStnIdType(map);
    }

    /**
     * 查询上月水质
     * @param map
     * @return
     * 因为查询语句都一样，所以直接用queryLastWeekWaterQuality(map)来进行查询
     */
    @Override
    public List<WaterQuality>  lastMonthWaterQuality(Map<String, Object> map){
        return WaterQualityDao.queryLastWeekWaterQuality(map);
    }

    /**
     * 查询上年水质
     * @param map
     * @return
     * 因为查询语句都一样，所以直接用queryLastWeekWaterQuality(map)来进行查询
     */
    @Override
    public List<WaterQuality>  lastYearWaterQuality(Map<String, Object> map){
        return WaterQualityDao.queryLastWeekWaterQuality(map);
    }


    /**
     * 保存水质信息
     * @param waterquality
     */
    @Override
    public int saveWaterQuality(WaterQuality waterquality) {
        return WaterQualityDao.saveWaterQuality(waterquality);
    }

    /**
     * 更新水质信息
     * @param waterquality
     */
    @Override
    public int updateWaterQuality(WaterQuality waterquality){
        return WaterQualityDao.updateWaterQuality(waterquality);
    }

    /**
     * 删除水质信息
     * @param waterquality
     */
    @Override
    public int deleteWaterQuality(WaterQuality waterquality){
        return WaterQualityDao.deleteWaterQuality(waterquality);
    }

    @Override
    public int updateWaterQualityWarn(WaterEarlyWarn waterEarlyWarn) {
        return WaterQualityDao.updateWaterQualityWarn(waterEarlyWarn);
    }

    @Override
    public WaterEarlyWarn getWaterQualityWarn(int stnId, int type) {
        return WaterQualityDao.getWaterQualityWarn(stnId, type);
    }

    /**
     * 取最新的DAYS条实时水质
     * @return 当前站点某个参数的水质信息
     */
    @Override
    public List<WaterQuality> lastWaterQualityRecordsNum(Map<String, Object> map)
    {
        return WaterQualityDao.lastWaterQualityRecordsNum(map);
    }

}
