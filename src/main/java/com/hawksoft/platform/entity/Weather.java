package com.hawksoft.platform.entity;

import java.io.Serializable;

/**
 * 气象实体类
 * 气象、波浪共一张数据库表，但因为气象、波浪在前端分为两个模块，所以在这里查询也分成两类查询，
 * 这样在查询和传递数据时，提高效率
 */
public class Weather implements Serializable {

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

    private String collectionTime; //采集时间

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

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", stnId=" + stnId +
                ", temperature='" + temperature + '\'' +
                ", baro=" + baro +
                ", humidity=" + humidity +
                ", rainfall=" + rainfall +
                ", meanWindSpeed10=" + meanWindSpeed10 +
                ", meanWindDirection10=" + meanWindDirection10 +
                ", windSpeedDeviation10=" + windSpeedDeviation10 +
                ", maxWindSpeed10=" + maxWindSpeed10 +
                ", maxWindSpeedTime10='" + maxWindSpeedTime10 + '\'' +
                ", maxWindSpeedDirection10=" + maxWindSpeedDirection10 +
                ", maxWindSpeedDay=" + maxWindSpeedDay +
                ", maxWindSpeedTimeDay='" + maxWindSpeedTimeDay + '\'' +
                ", maxWindDirectionDay=" + maxWindDirectionDay +
                ", collectionTime='" + collectionTime + '\'' +
                '}';
    }
}
