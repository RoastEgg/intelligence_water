package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.WeatherWaveDao;
import com.hawksoft.platform.entity.*;
import com.hawksoft.platform.service.WeatherWaveService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WeatherWaveServiceImpl implements WeatherWaveService {

    @Autowired
    private WeatherWaveDao weatherWaveDao;

    /**
     * 取得一段时间内某个站点具体指标的气象、波浪信息
     * @return 当前站点某个参数的气象、波浪信息
     */
    @Override
    public List<WeatherWave> queryWeatherWaveByType(Map<String, Object> map)
    {
        return weatherWaveDao.queryWeatherWaveByType(map);
    }

    /**
     * 取最新的DAYS条实时气象、波浪数据
     * @return 当前站点某个参数的气象、波浪信息
     */
    @Override
    public List<WeatherWave> lastWeatherWaveRecordsNum(Map<String, Object> map)
    {
        return weatherWaveDao.lastWeatherWaveRecordsNum(map);
    }

    /**
     * 获取历史气象、波浪数据
     * @param map 查询参数
     * @return 返回历史气象、波浪的数据
     */
    @Override
    public List<WeatherWave> historyWeatherWave(Map<String, Object> map)
    {
        return weatherWaveDao.historyWeatherWave(map);
    }

    /**
     *  通过ID获取该站点最新一条气象、波浪数据
     * @return 当前站点的所有气象信息
     */
    @Override
    public List<WeatherWave> findLastWeatherWave(int stnId)
    {
        return weatherWaveDao.findLastWeatherWave(stnId);
    }

    /**
     * 查询实时气象数据
     * @return 当前站点的所有气象信息
     */
    @Override
    public List<Weather> findLastWeather(int stnId)
    {
        return weatherWaveDao.findLastWeather(stnId);
    }


    /**
     * 取最新的DAYS条实时气象
     * @return 当前站点某个参数的气象信息
     */
    @Override
    public List<Weather> lastWeatherRecordsNum(Map<String, Object> map)
    {
        return weatherWaveDao.lastWeatherRecordsNum(map);
    }


    /**
     * 获取历史气象
     * @param map 查询参数
     * @return 返回历史气象的数据
     */
    @Override
    public List<Weather> historyWeather(Map<String, Object> map)
    {
        return weatherWaveDao.historyWeather(map);
    }

    /**
     * 查询实时波浪数据
     * @return 当前站点的所有波浪信息
     */
    @Override
    public List<Wave> findLastWave(int stnId)
    {
        return weatherWaveDao.findLastWave(stnId);
    }


    /**
     * 取最新的DAYS条实时波浪
     * @return 当前站点某个参数的波浪信息
     */
    @Override
    public List<Wave> lastWaveRecordsNum(Map<String, Object> map)
    {
        return weatherWaveDao.lastWaveRecordsNum(map);
    }


    /**
     * 获取历史波浪
     * @param map 查询参数
     * @return 返回历史波浪的数据
     */
    @Override
    public List<Wave> historyWave(Map<String, Object> map)
    {
        return weatherWaveDao.historyWave(map);
    }


    /**
     * 查询所有站点的信息
     * @return 返回站点的信息
     */
    @Override
    public List<WaterStation> queryAllWaterStation()
    {
        return weatherWaveDao.queryAllWaterStation();
    }

    /**
     * 查询所有城市的数据，比如南京，北京
     * @return
     */
    @Override
    public List<String> queryCityData()
    {
        return weatherWaveDao.queryCityData();
    }



    /**
     * 查询上周气象、波浪
     * @param map
     * @return
     */
    @Override
    public List<WeatherWave>  queryLastWeekWeatherWave(Map<String, Object> map)
    {
        return weatherWaveDao.queryLastWeekWeatherWave(map);
    }

    /**
     * 查询上月气象、波浪
     * @param map
     * @return
     */
    @Override
    public List<WeatherWave> lastMonthWeatherWave(Map<String, Object> map)
    {
        return weatherWaveDao.lastMonthWeatherWave(map);
    }

    /**
     * 查询上年气象、波浪
     * @param map
     * @return
     */
    @Override
    public List<WeatherWave> lastYearWeatherWave(Map<String, Object> map)
    {
        return weatherWaveDao.lastYearWeatherWave(map);
    }

    /**
     * 保存气象、波浪信息
     * @param weatherWave
     */
    @Override
    public int saveWeatherWave(WeatherWave weatherWave)
    {
        return weatherWaveDao.saveWeatherWave(weatherWave);
    }

    /**
     * 更新气象、波浪信息
     * @param weatherWave
     */
    @Override
    public int updateWeatherWave(WeatherWave weatherWave)
    {
        return weatherWaveDao.updateWeatherWave(weatherWave);
    }

    /**
     * 删除气象、波浪信息
     * @param weatherWave
     */
    @Override
    public int deleteWeatherWave(WeatherWave weatherWave)
    {
        return weatherWaveDao.deleteWeatherWave(weatherWave);
    }

    /**
     * 更新预警值
     * @param waterEarlyWarn
     * @return
     */
    @Override
    public int updateWeatherWaveWarn(WaterEarlyWarn waterEarlyWarn)
    {
        return weatherWaveDao.updateWeatherWaveWarn(waterEarlyWarn);
    }

    /**
     * 获取预警值
     * @param stnId
     * @param type 1代表是默认的，2代表是自定义的
     * @return 气象、波浪预警信息
     */
    @Override
    public WaterEarlyWarn getWeatherWaveWarn(@Param("stnId") int stnId, @Param("type") int type)
    {
        return weatherWaveDao.getWeatherWaveWarn(stnId,type);
    }

}
