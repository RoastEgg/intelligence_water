package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.WeatherWaveDao;
import com.hawksoft.platform.entity.*;
import com.hawksoft.platform.service.WeatherWaveService;
import com.hawksoft.platform.util.DataUtil;
import com.hawksoft.platform.util.DateUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.*;

@Service
public class WeatherWaveServiceImpl implements WeatherWaveService {

    @Autowired
    private WeatherWaveDao weatherWaveDao;

    /**
     * 取得一段时间内某个站点具体指标的气象、波浪信息
     *
     * @return 当前站点某个参数的气象、波浪信息
     */
    @Override
    public List<WeatherWave> queryWeatherWaveByType(Map<String, Object> map) {
        return weatherWaveDao.queryWeatherWaveByType(map);
    }

    /**
     * 取最新的DAYS条实时气象、波浪数据
     *
     * @return 当前站点某个参数的气象、波浪信息
     */
    @Override
    public List<WeatherWave> lastWeatherWaveRecordsNum(Map<String, Object> map) {
        return weatherWaveDao.lastWeatherWaveRecordsNum(map);
    }

    /**
     * 获取历史气象、波浪数据
     *
     * @param map 查询参数
     * @return 返回历史气象、波浪的数据
     */
    @Override
    public List<WeatherWave> historyWeatherWave(Map<String, Object> map) {
        return weatherWaveDao.historyWeatherWave(map);
    }

    /**
     * 通过ID获取该站点最新一条气象、波浪数据
     *
     * @return 当前站点的所有气象信息
     */
    @Override
    public List<WeatherWave> findLastWeatherWave(int stnId) {
        return weatherWaveDao.findLastWeatherWave(stnId);
    }

    /**
     * 查询实时气象数据
     *
     * @return 当前站点的所有气象信息
     */
    @Override
    public List<Weather> findLastWeather(int stnId) {
        return weatherWaveDao.findLastWeather(stnId);
    }


    /**
     * 取最新的DAYS条实时气象
     *
     * @return 当前站点某个参数的气象信息
     */
    @Override
    public List<Weather> lastWeatherRecordsNum(Map<String, Object> map) {
        return weatherWaveDao.lastWeatherRecordsNum(map);
    }


    /**
     * 获取历史气象
     *
     * @param map 查询参数
     * @return 返回历史气象的数据
     */
    @Override
    public List<Weather> historyWeather(Map<String, Object> map) {
        return weatherWaveDao.historyWeather(map);
    }

    /**
     * 查询实时波浪数据
     *
     * @return 当前站点的所有波浪信息
     */
    @Override
    public List<Wave> findLastWave(int stnId) {
        return weatherWaveDao.findLastWave(stnId);
    }


    /**
     * 取最新的DAYS条实时波浪
     *
     * @return 当前站点某个参数的波浪信息
     */
    @Override
    public List<Wave> lastWaveRecordsNum(Map<String, Object> map) {
        return weatherWaveDao.lastWaveRecordsNum(map);
    }


    /**
     * 获取历史波浪
     *
     * @param map 查询参数
     * @return 返回历史波浪的数据
     */
    @Override
    public List<Wave> historyWave(Map<String, Object> map) {
        return weatherWaveDao.historyWave(map);
    }


    /**
     * 查询所有站点的信息
     *
     * @return 返回站点的信息
     */
    @Override
    public List<WaterStation> queryAllWaterStation() {
        return weatherWaveDao.queryAllWaterStation();
    }

    /**
     * 查询所有城市的数据，比如南京，北京
     *
     * @return
     */
    @Override
    public List<String> queryCityData() {
        return weatherWaveDao.queryCityData();
    }


    /**
     * 查询上周气象、波浪
     *
     * @param map
     * @return
     */
    @Override
    public List<WeatherWave> queryLastWeekWeatherWave(Map<String, Object> map) {
        return weatherWaveDao.queryLastWeekWeatherWave(map);
    }

    /**
     * 查询上月气象、波浪
     *
     * @param map
     * @return
     */
    @Override
    public List<WeatherWave> lastMonthWeatherWave(Map<String, Object> map) {
        return weatherWaveDao.lastMonthWeatherWave(map);
    }

    /**
     * 查询上年气象、波浪
     *
     * @param map
     * @return
     */
    @Override
    public List<WeatherWave> lastYearWeatherWave(Map<String, Object> map) {
        return weatherWaveDao.lastYearWeatherWave(map);
    }

    /**
     * 保存气象、波浪信息
     *
     * @param weatherWave
     */
    @Override
    public int saveWeatherWave(WeatherWave weatherWave) {
        return weatherWaveDao.saveWeatherWave(weatherWave);
    }

    /**
     * 更新气象、波浪信息
     *
     * @param weatherWave
     */
    @Override
    public int updateWeatherWave(WeatherWave weatherWave) {
        return weatherWaveDao.updateWeatherWave(weatherWave);
    }

    /**
     * 删除气象、波浪信息
     *
     * @param weatherWave
     */
    @Override
    public int deleteWeatherWave(WeatherWave weatherWave) {
        return weatherWaveDao.deleteWeatherWave(weatherWave);
    }

    /**
     * 更新预警值
     *
     * @param waterEarlyWarn
     * @return
     */
    @Override
    public int updateWeatherWaveWarn(WaterEarlyWarn waterEarlyWarn) {
        return weatherWaveDao.updateWeatherWaveWarn(waterEarlyWarn);
    }

    /**
     * 获取预警值
     *
     * @param stnId
     * @param type  1代表是默认的，2代表是自定义的
     * @return 气象、波浪预警信息
     */
    @Override
    public WaterEarlyWarn getWeatherWaveWarn(@Param("stnId") int stnId, @Param("type") int type) {
        return weatherWaveDao.getWeatherWaveWarn(stnId, type);
    }


    @Override
    public int generateData(int stnId, Date date) {

        String temper = "";
        double minTemper = -0.002, maxTemper = 9.998;
        double baro, minBaro = 53.6420, maxBaro = 103.8570;
        double humidity, minHumidity = 25.0320, maxHumidity = 100;
        double rainfall, minRainfall = 0, maxRainfall = 18.34;
        double meanWindSpeed10, minMeanWindSpeed10 = 0, maxMeanWindSpeed10 = 11.008;
        double meanWindDirection10, minMeanWindDirection10 = 0, maxMeanWindDirection10 = 359.938;
        double windSpeedDeviation10, minWindSpeedDeviation10 = 0, maxWindSpeedDeviation10 = 2.822;
        double maxWindSpeed10, minMaxWindSpeed10 = 0, maxMaxWindSpeed10 = 12.785;
        String maxWindSpeedTime10;
        double maxWindSpeedDirection10, minMaxWindSpeedDirection10 = 0, maxMaxWindSpeedDirection10 = 359.97;
        double maxWindSpeedDay, minMaxWindSpeedDay = 0.983, maxMaxWindSpeedDay = 12.785;
        String maxWindSpeedTimeDay;
        double maxWindDirectionDay, minMaxWindDirectionDay = 0.104, maxMaxWindDirectionDay = 359.97;
        double waterPressure, minWaterPressure = 0, maxWaterPressure = 11.926;
        String waterTemperature;
        double minWaterTemperature = 3, maxWaterTemperature = 9.986;
        String heading;
        double minHeading = 0, maxHeading = 209.4;
        double flowSpeedX, minFlowSpeedX = -0.08, maxFlowSpeedX = 0.025;
        double flowSpeedY, minFlowSpeedY = -0.056, maxFlowSpeedY = 0.021;
        double longitude, minLongitude = 0.0000000, maxLongitude = 1212002.6250000;
        double latitude, minLatitude = 0.0000000, maxLatitude = 31278.0000000;
        String collectionTime;

        int id= 13625;
        List<WeatherWave> weatherWaveList = weatherWaveDao.queryForData(id);
        WeatherWave ww = new WeatherWave();
        if (weatherWaveList.size()>0){
            ww = weatherWaveList.get(0);
        }
        if (weatherWaveList!=null){

            temper = Double.toString(DataUtil.randomData5per(Double.parseDouble(ww.getTemperature())));
            baro = DataUtil.randomData5per(ww.getBaro());
            humidity = DataUtil.randomData5per(ww.getHumidity());
            rainfall = DataUtil.randomData5per(ww.getRainfall());
            meanWindSpeed10 = DataUtil.randomData5per(ww.getMeanWindSpeed10());
            meanWindDirection10 = DataUtil.randomData5per(ww.getMeanWindDirection10());
            windSpeedDeviation10 = DataUtil.randomData5per(ww.getWindSpeedDeviation10());
            maxWindSpeed10 = DataUtil.randomData5per(ww.getMaxWindSpeed10());
            maxWindSpeedTime10 = DateUtil.parseDate(date);
            maxWindSpeedDirection10 = DataUtil.randomData5per(ww.getMaxWindSpeedDirection10());
            maxWindSpeedDay = DataUtil.randomData5per(ww.getMaxWindSpeedDay());
            maxWindSpeedTimeDay  =DateUtil.parseDate(date);
            maxWindDirectionDay = DataUtil.randomData5per(ww.getMaxWindDirectionDay());
            waterPressure = DataUtil.randomData5per(ww.getWaterPressure());
            waterTemperature = Double.toString(DataUtil.randomData5per(Double.parseDouble(ww.getWaterTemperature())));
            heading = Double.toString(DataUtil.randomData5per(Double.parseDouble(ww.getHeading())));
            flowSpeedX = DataUtil.randomData5per(ww.getFlowSpeedX());
            flowSpeedY = DataUtil.randomData5per(ww.getFlowSpeedY());
            collectionTime = DateUtil.parseDate(date);
            ww = new WeatherWave(stnId, temper, baro,humidity,rainfall,meanWindSpeed10,
                    meanWindDirection10,windSpeedDeviation10,maxWindSpeed10,maxWindSpeedTime10,
                    maxWindSpeedDirection10,maxWindSpeedDay,maxWindSpeedTimeDay,maxWindDirectionDay,
                    waterPressure,waterTemperature,heading,flowSpeedX,flowSpeedY,null,null,
                    collectionTime,null, null,null, null, null, null,null, null);
            int ans = saveWeatherWave(ww);
            return ans;
        }

        return 0;
    }


}
