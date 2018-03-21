package com.hawksoft.platform.entity;

import java.math.BigDecimal;

/**
 * 水位预警
 */
public class WaterWarm {

     private int id;
     //水位站点id
     private int stnId;
     private BigDecimal wl_yellow;
     private BigDecimal wl_orange;
     private BigDecimal wl_red;
     private String waring_value;
     private String wl_status;

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

    public String getWaring_value() {
        return waring_value;
    }

    public void setWaring_value(String waring_value) {
        this.waring_value = waring_value;
    }

    public String getWl_status() {
        return wl_status;
    }

    public void setWl_status(String wl_status) {
        this.wl_status = wl_status;
    }

    @Override
    public String toString() {
        return "WaterWarm{" +
                "id=" + id +
                ", stnId=" + stnId +
                ", wl_yellow=" + wl_yellow +
                ", wl_orange=" + wl_orange +
                ", wl_red=" + wl_red +
                ", waring_value='" + waring_value + '\'' +
                ", wl_status='" + wl_status + '\'' +
                '}';
    }
}
