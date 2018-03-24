package com.hawksoft.platform.entity;

import java.io.Serializable;

/**
 * 流速流量，记录5个摄像机采集的数据
 */
public class SpeedFlow implements Serializable {

    private int speedflowid;       //流速流量id
    private int stnId;              //站点id       ***
    private Double waterSpeed1;     //camera1流速  ***
    private Double waterFlow1;      //camera1流量
    private Double waterSpeed2;     //camera2流速  ***
    private Double waterFlow2;      //camera2流量
    private Double waterSpeed3;     //camera3流速  ***
    private Double waterFlow3;      //camera3流量
    private Double waterSpeed4;     //camera4流速  ***
    private Double waterFlow4;      //camera4流量
    private Double waterSpeed5;     //camera5流速  ***
    private Double waterFlow5;      //camera5流量
    private Integer state1;           //camera1设备状态
    private Integer state2;           //camera2设备状态
    private Integer state3;           //camera3设备状态
    private Integer state4;           //camera4设备状态
    private Integer state5;           //camera5设备状态
    private String FilePath1;        //camera1文件地址
    private String FilePath2;        //camera2文件地址
    private String FilePath3;        //camera3文件地址
    private String FilePath4;        //camera4文件地址
    private String FilePath5;        //camera5文件地址
    private String picOrVideo;       //文件是图片还是视频（p为图片；v为视频)
    private String collectionTime;   //采集时间
    private String instockTime;      //保存进数据库时间
    private WaterStation waterStation; //站点对象

    public int getSpeedflowid() {
        return speedflowid;
    }

    public void setSpeedflowid(int speedflowid) {
        this.speedflowid = speedflowid;
    }

    public int getStnId() {
        return stnId;
    }

    public void setStnId(int stnId) {
        this.stnId = stnId;
    }

    public Double getWaterSpeed1() {
        return waterSpeed1 != null ? waterSpeed1 : 0;
    }

    public void setWaterSpeed1(Double waterSpeed1) {
        this.waterSpeed1 = waterSpeed1;
    }

    public Double getWaterFlow1() {
        return waterFlow1 != null ? waterFlow1 : 0;
    }

    public void setWaterFlow1(Double waterFlow1) {
        this.waterFlow1 = waterFlow1;
    }

    public Double getWaterSpeed2() {
        return waterSpeed2 != null ? waterSpeed2 : 0 ;
    }

    public void setWaterSpeed2(Double waterSpeed2) {
        this.waterSpeed2 = waterSpeed2;
    }

    public Double getWaterFlow2() {
        return waterFlow2 != null ? waterFlow2 : 0;
    }

    public void setWaterFlow2(Double waterFlow2) {
        this.waterFlow2 = waterFlow2;
    }

    public Double getWaterSpeed3() {
        return waterSpeed3 != null ? waterSpeed3 : 0;
    }

    public void setWaterSpeed3(Double waterSpeed3) {
        this.waterSpeed3 = waterSpeed3;
    }

    public Double getWaterFlow3() {
        return waterFlow3 != null ? waterFlow3 : 0;
    }

    public void setWaterFlow3(Double waterFlow3) {
        this.waterFlow3 = waterFlow3;
    }

    public Double getWaterSpeed4() {
        return waterSpeed4 != null ? waterSpeed4 : 0;
    }

    public void setWaterSpeed4(Double waterSpeed4) {
        this.waterSpeed4 = waterSpeed4;
    }

    public Double getWaterFlow4() {
        return waterFlow4 != null ? waterFlow4 : 0;
    }

    public void setWaterFlow4(Double waterFlow4) {
        this.waterFlow4 = waterFlow4;
    }

    public Double getWaterSpeed5() {
        return waterSpeed5 != null ? waterSpeed5 : 0;
    }

    public void setWaterSpeed5(Double waterSpeed5) {
        this.waterSpeed5 = waterSpeed5;
    }

    public Double getWaterFlow5() {
        return waterFlow5 != null ? waterFlow5 : 0;
    }

    public void setWaterFlow5(Double waterFlow5) {
        this.waterFlow5 = waterFlow5;
    }

    public Integer getState1() {
        return state1 != null ? state1 : 0;
    }

    public void setState1(Integer state1) {
        this.state1 = state1;
    }

    public Integer getState2() {
        return state2 != null ? state2 : 0;
    }

    public void setState2(Integer state2) {
        this.state2 = state2;
    }

    public Integer getState3() {
        return state3 != null ? state3 : 0;
    }

    public void setState3(Integer state3) {
        this.state3 = state3;
    }

    public Integer getState4() {
        return state4 != null ? state4 : 0;
    }

    public void setState4(Integer state4) {
        this.state4 = state4;
    }

    public Integer getState5() {
        return state5 != null ? state5 : 0;
    }

    public void setState5(Integer state5) {
        this.state5 = state5;
    }

    public String getFilePath1() {
        return FilePath1;
    }

    public void setFilePath1(String filePath1) {
        FilePath1 = filePath1;
    }

    public String getFilePath2() {
        return FilePath2;
    }

    public void setFilePath2(String filePath2) {
        FilePath2 = filePath2;
    }

    public String getFilePath3() {
        return FilePath3;
    }

    public void setFilePath3(String filePath3) {
        FilePath3 = filePath3;
    }

    public String getFilePath4() {
        return FilePath4;
    }

    public void setFilePath4(String filePath4) {
        FilePath4 = filePath4;
    }

    public String getFilePath5() {
        return FilePath5;
    }

    public void setFilePath5(String filePath5) {
        FilePath5 = filePath5;
    }

    public String getPicOrVideo() {
        return picOrVideo;
    }

    public void setPicOrVideo(String picOrVideo) {
        this.picOrVideo = picOrVideo;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getInstockTime() {
        return instockTime;
    }

    public void setInstockTime(String instockTime) {
        this.instockTime = instockTime;
    }

    public WaterStation getWaterStation() {
        return waterStation;
    }

    public void setWaterStation(WaterStation waterStation) {
        this.waterStation = waterStation;
    }

    @Override
    public String toString() {
        return "SpeedFlow{" +
                "speedflowid=" + speedflowid +
                ", stnId=" + stnId +
                ", waterSpeed1=" + waterSpeed1 +
                ", waterFlow1=" + waterFlow1 +
                ", waterSpeed2=" + waterSpeed2 +
                ", waterFlow2=" + waterFlow2 +
                ", waterSpeed3=" + waterSpeed3 +
                ", waterFlow3=" + waterFlow3 +
                ", waterSpeed4=" + waterSpeed4 +
                ", waterFlow4=" + waterFlow4 +
                ", waterSpeed5=" + waterSpeed5 +
                ", waterFlow5=" + waterFlow5 +
                ", state1='" + state1 + '\'' +
                ", state2='" + state2 + '\'' +
                ", state3='" + state3 + '\'' +
                ", state4='" + state4 + '\'' +
                ", state5='" + state5 + '\'' +
                ", FilePath1='" + FilePath1 + '\'' +
                ", FilePath2='" + FilePath2 + '\'' +
                ", FilePath3='" + FilePath3 + '\'' +
                ", FilePath4='" + FilePath4 + '\'' +
                ", FilePath5='" + FilePath5 + '\'' +
                ", picOrVideo='" + picOrVideo + '\'' +
                ", collectionTime='" + collectionTime + '\'' +
                ", instockTime='" + instockTime + '\'' +
                ", waterStation=" + waterStation +
                '}';
    }
}
