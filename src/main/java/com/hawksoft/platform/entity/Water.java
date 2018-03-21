package com.hawksoft.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 水位
 */
public class Water implements Serializable {

    private int id;
    private int stnId;                      //站点id
    private BigDecimal waterLevel;          //水位值
    private String c_time;                  //创建时间
    private String picPath;                 //图片地址
    private WaterStation waterStation;      //站点对象

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStnId() {
        return stnId;
    }

    public void setStnId(int stnId) {
        this.stnId = stnId;
    }

    public BigDecimal getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(BigDecimal waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public WaterStation getWaterStation() {
        return waterStation;
    }

    public void setWaterStation(WaterStation waterStation) {
        this.waterStation = waterStation;
    }


    @Override
    public String toString() {
        return "Water{" +
                "id=" + id +
                ", stnId=" + stnId +
                ", waterLevel=" + waterLevel +
                ", c_time='" + c_time + '\'' +
                ", picPath='" + picPath + '\'' +
                ", waterStation=" + waterStation +
                '}';
    }
}
