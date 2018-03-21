package com.hawksoft.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 预警值
 */
public class WaterEarlyWarn implements Serializable{

    private int id;
    private int stnId;                  //站点id
    private BigDecimal wl_yellow;       //黄色预警值
    private BigDecimal wl_orange;       //橙色预警值
    private BigDecimal wl_red;          //红色预警值
    private Integer warning_level;      //自定义预警级别
    private String warning_values;      //自定义预警值
    private String wl_status;            //相机状态

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

    public BigDecimal getWl_yellow() {
        return wl_yellow;
    }

    public void setWl_yellow(BigDecimal wl_yellow) {
        this.wl_yellow = wl_yellow;
    }

    public BigDecimal getWl_orange() {
        return wl_orange;
    }

    public void setWl_orange(BigDecimal wl_orange) {
        this.wl_orange = wl_orange;
    }

    public BigDecimal getWl_red() {
        return wl_red;
    }

    public void setWl_red(BigDecimal wl_red) {
        this.wl_red = wl_red;
    }

    public Integer getWarning_level() {
        return warning_level;
    }

    public void setWarning_level(Integer warning_level) {
        this.warning_level = warning_level;
    }

    public String getWarning_values() {
        return warning_values;
    }

    public void setWarning_values(String warning_values) {
        this.warning_values = warning_values;
    }

    public String getWl_status() {
        return wl_status;
    }

    public void setWl_status(String wl_status) {
        this.wl_status = wl_status;
    }

    @Override
    public String toString() {
        return "WaterEarlyWarn{" +
                "id=" + id +
                ", stnId=" + stnId +
                ", wl_yellow=" + wl_yellow +
                ", wl_orange=" + wl_orange +
                ", wl_red=" + wl_red +
                ", warning_level=" + warning_level +
                ", warning_values='" + warning_values + '\'' +
                ", wl_status='" + wl_status + '\'' +
                '}';
    }
}
