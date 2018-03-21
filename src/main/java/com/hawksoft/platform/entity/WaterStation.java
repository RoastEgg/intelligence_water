package com.hawksoft.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 站点
 */
public class WaterStation implements Serializable{

    private int id;//id
    private String stnCode;         //站点编号
    private String stnName;         //站点名称
    private String stnAddr;         //站点地址
    private String picUrl;         //站点图片URL或路径
    private String province;        //所属省份
    private String city;            //所属城市
    private String x;           //位置x轴
    private String y;           //位置y轴
    private String institute;       //所属水文局
    private String stnType;       //站点属性(值是0，代表全部站点，1代表水动力，2代表水质，3代表视频)
    private String stnNote;       //站点说明

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStnCode() {
        return stnCode;
    }

    public void setStnCode(String stnCode) {
        this.stnCode = stnCode;
    }

    public String getStnName() {
        return stnName;
    }

    public void setStnName(String stnName) {
        this.stnName = stnName;
    }

    public String getStnAddr() {
        return stnAddr;
    }

    public void setStnAddr(String stnAddr) {
        this.stnAddr = stnAddr;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getStnType() {
        return stnType;
    }

    public void setStnType(String stnType) {
        this.stnType = stnType;
    }

    public String getStnNote() {
        return stnNote;
    }

    public void setStnNote(String stnNote) {
        this.stnNote = stnNote;
    }

    @Override
    public String toString() {
        return "WaterStation{" +
                "id=" + id +
                ", stnCode='" + stnCode + '\'' +
                ", stnName='" + stnName + '\'' +
                ", stnAddr='" + stnAddr + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", institute='" + institute + '\'' +
                ", stnType='" + stnType + '\'' +
                ", stnNote='" + stnNote + '\'' +
                '}';
    }
}
