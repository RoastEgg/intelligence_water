package com.hawksoft.platform.VO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Water_queryLastAndHisVO {
    private String stnName;
    private double lastWaterLevel;
    private double maxLevel;
    private double minLevel;
    private List<Water_PictureVO> picList = new ArrayList<>();

    public Water_queryLastAndHisVO() {
    }

    public String getStnName() {
        return stnName;
    }

    public void setStnName(String stnName) {
        this.stnName = stnName;
    }

    public double getLastWaterLevel() {
        return lastWaterLevel;
    }

    public void setLastWaterLevel(double lastWaterLevel) {
        this.lastWaterLevel = lastWaterLevel;
    }

    public double getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(double maxLevel) {
        this.maxLevel = maxLevel;
    }

    public double getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(double minLevel) {
        this.minLevel = minLevel;
    }

    public List<Water_PictureVO> getPicList() {
        return picList;
    }

    public void setPicList(List<Water_PictureVO> picList) {
        this.picList = picList;
    }
}
