package com.hawksoft.platform.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hawksoft.platform.entity.Camera;

import java.util.ArrayList;
import java.util.List;

//@JsonInclude(JsonInclude.Include.ALWAYS)
public class SpeedFlowVO {
    private int stnId;
    private String collectionTime;
    private String instockTime;
    private String picOrVideo;
    private List<Camera> list = new ArrayList<>();

    public SpeedFlowVO() {
    }

    public int getStnId() {
        return stnId;
    }

    public void setStnId(int stnId) {
        this.stnId = stnId;
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

    public List<Camera> getList() {
        return list;
    }

    public void setList(List<Camera> list) {
        this.list = list;
    }

    public String getPicOrVideo() {
        return picOrVideo;
    }

    public void setPicOrVideo(String picOrVideo) {
        this.picOrVideo = picOrVideo;
    }
}
