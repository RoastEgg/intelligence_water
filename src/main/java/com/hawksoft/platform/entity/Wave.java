package com.hawksoft.platform.entity;

import java.io.Serializable;

/**
 * 波浪实体类
 * 气象、波浪共一张数据库表，但因为气象、波浪在前端分为两个模块，所以在这里查询也分成两类查询，
 * 这样在查询和传递数据时，提高效率
 */
public class Wave implements Serializable {

    private int id;
    private Integer stnId;              //站点编号

    private Double waterPressure; //水压
    private String waterTemperature; //水温
    private String heading; //艏向
    private Double flowSpeedX; //X流速
    private Double flowSpeedY; //Y流速
    private Double longitude; //经度
    private Double latitude; //纬度
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

    @Override
    public String toString() {
        return "Wave{" +
                "id=" + id +
                ", stnId=" + stnId +
                ", waterPressure=" + waterPressure +
                ", waterTemperature='" + waterTemperature + '\'' +
                ", heading='" + heading + '\'' +
                ", flowSpeedX=" + flowSpeedX +
                ", flowSpeedY=" + flowSpeedY +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", collectionTime='" + collectionTime + '\'' +
                '}';
    }
}
