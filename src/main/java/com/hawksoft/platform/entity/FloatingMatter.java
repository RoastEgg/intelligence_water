package com.hawksoft.platform.entity;

import java.io.Serializable;
import java.util.Arrays;

public class FloatingMatter implements Serializable{

    private int id;
    private Integer stnId;              //站点编号
    private String collectionTime;     //采集时间
    private Integer camIndex;           //相机编号
    private String filePathOrigin;//处理前图片路径
    private String filePathResult;//处理后图片路径
    private String picOrVideo;           //文件是图片还是视频（p为图片；v为视频)

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

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public Integer getCamIndex() {
        return camIndex;
    }

    public void setCamIndex(Integer camIndex) {
        this.camIndex = camIndex;
    }

    public String getFilePathOrigin() {
        return filePathOrigin;
    }

    public void setFilePathOrigin(String filePathOrigin) {
        this.filePathOrigin = filePathOrigin;
    }

    public String getFilePathResult() {
        return filePathResult;
    }

    public void setFilePathResult(String filePathResult) {
        this.filePathResult = filePathResult;
    }

    public String getPicOrVideo() {
        return picOrVideo;
    }

    public void setPicOrVideo(String picOrVideo) {
        this.picOrVideo = picOrVideo;
    }

    @Override
    public String toString() {
        return "FloatingMatter{" +
                "id=" + id +
                ", stnId=" + stnId +
                ", collectionTime='" + collectionTime + '\'' +
                ", camIndex=" + camIndex +
                ", filePathOrigin='" + filePathOrigin + '\'' +
                ", filePathResult='" + filePathResult + '\'' +
                ", picOrVideo='" + picOrVideo + '\'' +
                '}';
    }
}
