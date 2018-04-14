package com.hawksoft.platform.dao;


import com.hawksoft.platform.entity.WaterQuality;
import com.hawksoft.platform.entity.WaterEarlyWarn;
import com.hawksoft.platform.entity.WaterStation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WaterQualityDao {

    /**
     * 查询实时水质数据
     * @return 当前站点的所有水质信息
     */
    public List<WaterQuality> findLastWaterQuality(int stnId);

    /**
     * 查询当前站点某个参数的水质数据
     * @return 当前站点某个参数的水质信息
     */
    public List<WaterQuality> queryLastWaterQualityByStnIdType(Map<String, Object> map);

    /**
     * 取最新的DAYS条实时水质
     * @return 当前站点某个参数的水质信息
     */
    public List<WaterQuality> lastWaterQualityRecordsNum(Map<String, Object> map);


    /**
     * 获取历史水质
     * @param map 查询参数
     * @return 返回历史水质的数据
     */
    public List<WaterQuality> hisWaterQuality(Map<String, Object> map);

    /**
     * 查询所有站点的信息
     * @return 返回站点的信息
     */
    public List<WaterStation> queryAllWaterStation();

    /**
     * 查询所有城市的数据，比如南京，北京
     * @return
     */
    public List<String> queryCityData();

    /**
     * 根据站点id查询实时水质
     * @param stnId
     * @return
     */
    public WaterQuality queryLastWaterQualityByStnId(int stnId);

    /**
     * 查询上周水质
     * @param map
     * @return
     */
    public List<WaterQuality>  queryLastWeekWaterQuality(Map<String, Object> map);

    /**
     * 查询上月水质
     * @param map
     * @return
     */
    public List<WaterQuality> lastMonthWaterQuality(Map<String, Object> map);

    /**
     * 查询上月水质
     * @param map
     * @return
     */
    public List<WaterQuality> lastYearWaterQuality(Map<String, Object> map);

    /**
     * 保存水质信息
     * @param waterquality
     */
    public int saveWaterQuality(WaterQuality waterquality);

    /**
     * 更新水质信息
     * @param waterquality
     */
    public int updateWaterQuality(WaterQuality waterquality);

    /**
     * 删除水质信息
     * @param waterquality
     */
    public int deleteWaterQuality(WaterQuality waterquality);

    /**
     * 更新预警值
     * @param waterEarlyWarn
     * @return
     */
    public int updateWaterQualityWarn(WaterEarlyWarn waterEarlyWarn);

    /**
     * 获取预警值
     * @param stnId
     * @param type 1代表是默认的，2代表是自定义的
     * @return 水质预警信息
     */
    public WaterEarlyWarn getWaterQualityWarn(@Param("stnId") int stnId, @Param("type") int type);

    /**
     * 从无人船中获取水质信息
     * @param map
     * @return 站点Id，GPS经纬度，水质参数（温度、PH、溶氧、氧化还原）
     */
    public Map<String ,Object> queryWaterQualityFromUB(Map<String,Object> map);

    /**
     * 从无人船中获取水质历史信息
     * @param map
     * @return 站点Id，GPS经纬度，水质参数（温度、PH）
     */
    public List<Map<String,Object>> queryUBHistory(Map<String,Object> map);
    /**
     * 查询某站点是否有水质信息
     * @param stnId
     * @return 记录条数
     */
    public int queryStationInfo(int stnId);
}
