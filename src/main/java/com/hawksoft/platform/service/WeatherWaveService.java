package com.hawksoft.platform.service;


import com.hawksoft.platform.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 气象、波浪共一张数据库表，但因为气象、波浪在前端分为两个模块，所以在这里查询也分成两类查询，
 * 这样在查询和传递数据时，提高效率
 */

public interface WeatherWaveService {

    /**
     * 取得一段时间内某个站点具体指标的气象、波浪信息
     * @return 当前站点某个参数的气象、波浪信息
     */
    public List<WeatherWave> queryWeatherWaveByType(Map<String, Object> map);

    /**
     * 取最新的DAYS条实时气象、波浪数据
     * @return 当前站点某个参数的气象、波浪信息
     */
    public List<WeatherWave> lastWeatherWaveRecordsNum(Map<String, Object> map);

    /**
     * 获取历史气象、波浪数据
     * @param map 查询参数
     * @return 返回历史气象、波浪的数据
     */
    public List<WeatherWave> historyWeatherWave(Map<String, Object> map);

    /**
     *  通过ID获取该站点最新一条气象、波浪数据
     * @return 当前站点的所有气象信息
     */
    public List<WeatherWave> findLastWeatherWave(int stnId);

    /**
     * 查询实时气象数据
     * @return 当前站点的所有气象信息
     */
    public List<Weather> findLastWeather(int stnId);


    /**
     * 取最新的DAYS条实时气象
     * @return 当前站点某个参数的气象信息
     */
    public List<Weather> lastWeatherRecordsNum(Map<String, Object> map);


    /**
     * 获取历史气象
     * @param map 查询参数
     * @return 返回历史气象的数据
     */
    public List<Weather> historyWeather(Map<String, Object> map);

    /**
     * 查询实时波浪数据
     * @return 当前站点的所有波浪信息
     */
    public List<Wave> findLastWave(int stnId);


    /**
     * 取最新的DAYS条实时波浪
     * @return 当前站点某个参数的波浪信息
     */
    public List<Wave> lastWaveRecordsNum(Map<String, Object> map);


    /**
     * 获取历史波浪
     * @param map 查询参数
     * @return 返回历史波浪的数据
     */
    public List<Wave> historyWave(Map<String, Object> map);


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
     * 查询上周气象、波浪
     * @param map
     * @return
     */
    public List<WeatherWave>  queryLastWeekWeatherWave(Map<String, Object> map);

    /**
     * 查询上月气象、波浪
     * @param map
     * @return
     */
    public List<WeatherWave> lastMonthWeatherWave(Map<String, Object> map);

    /**
     * 查询上月气象、波浪
     * @param map
     * @return
     */
    public List<WeatherWave> lastYearWeatherWave(Map<String, Object> map);

    /**
     * 保存气象、波浪信息
     * @param weatherWave
     */
    public int saveWeatherWave(WeatherWave weatherWave);

    /**
     * 更新气象、波浪信息
     * @param weatherWave
     */
    public int updateWeatherWave(WeatherWave weatherWave);

    /**
     * 删除气象、波浪信息
     * @param weatherWave
     */
    public int deleteWeatherWave(WeatherWave weatherWave);

    /**
     * 更新预警值
     * @param waterEarlyWarn
     * @return
     */
    public int updateWeatherWaveWarn(WaterEarlyWarn waterEarlyWarn);

    /**
     * 获取预警值
     * @param stnId
     * @param type 1代表是默认的，2代表是自定义的
     * @return 气象、波浪预警信息
     */
    public WaterEarlyWarn getWeatherWaveWarn(@Param("stnId") int stnId, @Param("type") int type);


    /**
     * 为了给生成历史数据和schedule复用，将生成数据方法独立出来
     * @param stnId
     * @param date
     * @return
     */
    public int generateData(int stnId,Date date);
}
