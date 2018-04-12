package com.hawksoft.platform.entity;

import java.io.Serializable;

/**
 * 气象、波浪
 */
public class WeatherWave implements Serializable {

    private int id;
    private Integer stnId;              //站点编号
    private String temperature;        //温度值
    private Double baro;                  //气压
    private Double humidity;    //湿度
    private Double rainfall; //雨量
    private Double meanWindSpeed10; //10分钟平均风速
    private Double meanWindDirection10; //10分钟平均风向
    private Double windSpeedDeviation10; //10分钟风速标准差
    private Double maxWindSpeed10; //10分钟极大风速
    private String maxWindSpeedTime10; //10分钟极大风速时间
    private Double maxWindSpeedDirection10; //10分钟极大风速风向
    private Double maxWindSpeedDay; //日极大风速
    private String maxWindSpeedTimeDay; //日极大风速时间
    private Double maxWindDirectionDay; //日极大风向
    private Double waterPressure; //水压
    private String waterTemperature; //水温
    private String heading; //艏向
    private Double flowSpeedX; //X流速
    private Double flowSpeedY; //Y流速
    private Double longitude; //经度
    private Double latitude; //纬度
    private String collectionTime; //采集时间
    private String Hs;//有效波高
    private String Ts;//有效波周期
    private String Hmax;//最大波高
    private String Tp;//谱峰周期
    private String T1;//平均波周期
    private String Tz;//平均跨零周期
    private String Dmean;//平均波向
    private String Dpeak;//谱峰波向

    public WeatherWave() {
    }

    public WeatherWave( Integer stnId, String temperature, Double baro, Double humidity, Double rainfall, Double meanWindSpeed10, Double meanWindDirection10, Double windSpeedDeviation10, Double maxWindSpeed10, String maxWindSpeedTime10, Double maxWindSpeedDirection10, Double maxWindSpeedDay, String maxWindSpeedTimeDay, Double maxWindDirectionDay, Double waterPressure, String waterTemperature, String heading, Double flowSpeedX, Double flowSpeedY, Double longitude, Double latitude, String collectionTime, String hs, String ts, String hmax, String tp, String t1, String tz, String dmean, String dpeak) {

        this.stnId = stnId;
        this.temperature = temperature;
        this.baro = baro;
        this.humidity = humidity;
        this.rainfall = rainfall;
        this.meanWindSpeed10 = meanWindSpeed10;
        this.meanWindDirection10 = meanWindDirection10;
        this.windSpeedDeviation10 = windSpeedDeviation10;
        this.maxWindSpeed10 = maxWindSpeed10;
        this.maxWindSpeedTime10 = maxWindSpeedTime10;
        this.maxWindSpeedDirection10 = maxWindSpeedDirection10;
        this.maxWindSpeedDay = maxWindSpeedDay;
        this.maxWindSpeedTimeDay = maxWindSpeedTimeDay;
        this.maxWindDirectionDay = maxWindDirectionDay;
        this.waterPressure = waterPressure;
        this.waterTemperature = waterTemperature;
        this.heading = heading;
        this.flowSpeedX = flowSpeedX;
        this.flowSpeedY = flowSpeedY;
        this.longitude = longitude;
        this.latitude = latitude;
        this.collectionTime = collectionTime;
        Hs = hs;
        Ts = ts;
        Hmax = hmax;
        Tp = tp;
        T1 = t1;
        Tz = tz;
        Dmean = dmean;
        Dpeak = dpeak;
    }

    private String returnDateValue;//注意，本参数是专门给前端返回固定字段名称使用，方便前端，数据库中不存在此字段
    public String getReturnDateValue() {
        return returnDateValue;
    }

    public void setReturnDateValue(String returnDateValue) {
        this.returnDateValue = returnDateValue;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getStnId() {
        return stnId;
    }

    public void setStnId(Integer stnId) {
        this.stnId = stnId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Double getBaro() {
        return baro;
    }

    public void setBaro(Double baro) {
        this.baro = baro;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getRainfall() {
        return rainfall;
    }

    public void setRainfall(Double rainfall) {
        this.rainfall = rainfall;
    }

    public Double getMeanWindSpeed10() {
        return meanWindSpeed10;
    }

    public void setMeanWindSpeed10(Double meanWindSpeed10) {
        this.meanWindSpeed10 = meanWindSpeed10;
    }

    public Double getMeanWindDirection10() {
        return meanWindDirection10;
    }

    public void setMeanWindDirection10(Double meanWindDirection10) {
        this.meanWindDirection10 = meanWindDirection10;
    }

    public Double getWindSpeedDeviation10() {
        return windSpeedDeviation10;
    }

    public void setWindSpeedDeviation10(Double windSpeedDeviation10) {
        this.windSpeedDeviation10 = windSpeedDeviation10;
    }

    public Double getMaxWindSpeed10() {
        return maxWindSpeed10;
    }

    public void setMaxWindSpeed10(Double maxWindSpeed10) {
        this.maxWindSpeed10 = maxWindSpeed10;
    }

    public String getMaxWindSpeedTime10() {
        return maxWindSpeedTime10;
    }

    public void setMaxWindSpeedTime10(String maxWindSpeedTime10) {
        this.maxWindSpeedTime10 = maxWindSpeedTime10;
    }

    public Double getMaxWindSpeedDirection10() {
        return maxWindSpeedDirection10;
    }

    public void setMaxWindSpeedDirection10(Double maxWindSpeedDirection10) {
        this.maxWindSpeedDirection10 = maxWindSpeedDirection10;
    }

    public Double getMaxWindSpeedDay() {
        return maxWindSpeedDay;
    }

    public void setMaxWindSpeedDay(Double maxWindSpeedDay) {
        this.maxWindSpeedDay = maxWindSpeedDay;
    }

    public String getMaxWindSpeedTimeDay() {
        return maxWindSpeedTimeDay;
    }

    public void setMaxWindSpeedTimeDay(String maxWindSpeedTimeDay) {
        this.maxWindSpeedTimeDay = maxWindSpeedTimeDay;
    }

    public Double getMaxWindDirectionDay() {
        return maxWindDirectionDay;
    }

    public void setMaxWindDirectionDay(Double maxWindDirectionDay) {
        this.maxWindDirectionDay = maxWindDirectionDay;
    }

    public Double getWaterPressure() {
        return waterPressure;
    }

    public void setWaterPressure(Double waterPressure) {
        this.waterPressure = waterPressure;
    }

    public String getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(String waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public Double getFlowSpeedX() {
        return flowSpeedX;
    }

    public void setFlowSpeedX(Double flowSpeedX) {
        this.flowSpeedX = flowSpeedX;
    }

    public Double getFlowSpeedY() {
        return flowSpeedY;
    }

    public void setFlowSpeedY(Double flowSpeedY) {
        this.flowSpeedY = flowSpeedY;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getHs() {
        return Hs;
    }

    public void setHs(String hs) {
        Hs = hs;
    }

    public String getTs() {
        return Ts;
    }

    public void setTs(String ts) {
        Ts = ts;
    }

    public String getHmax() {
        return Hmax;
    }

    public void setHmax(String hmax) {
        Hmax = hmax;
    }

    public String getTp() {
        return Tp;
    }

    public void setTp(String tp) {
        Tp = tp;
    }

    public String getT1() {
        return T1;
    }

    public void setT1(String t1) {
        T1 = t1;
    }

    public String getTz() {
        return Tz;
    }

    public void setTz(String tz) {
        Tz = tz;
    }

    public String getDmean() {
        return Dmean;
    }

    public void setDmean(String dmean) {
        Dmean = dmean;
    }

    public String getDpeak() {
        return Dpeak;
    }

    public void setDpeak(String dpeak) {
        Dpeak = dpeak;
    }


}
