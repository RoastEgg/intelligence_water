package com.hawksoft.platform.entity;

/**
 * 相机参数类
 */
public class CameraArgs {

    private int id;
    private int RR;             //分辨率
    private int exposure;       //曝光
    private int intensity;      //明暗度
    private int tone_up;        //增益
    private int cam_def;         //是否默认，1是，2不是
    private int stnId;           //站点id
    private int cam_type;         //采集类型  1水位，2流量

    public int getCam_type() {
        return cam_type;
    }

    public void setCam_type(int cam_type) {
        this.cam_type = cam_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRR() {
        return RR;
    }

    public void setRR(int RR) {
        this.RR = RR;
    }

    public int getExposure() {
        return exposure;
    }

    public void setExposure(int exposure) {
        this.exposure = exposure;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getTone_up() {
        return tone_up;
    }

    public void setTone_up(int tone_up) {
        this.tone_up = tone_up;
    }

    public int getCam_def() {
        return cam_def;
    }

    public void setCam_def(int cam_def) {
        this.cam_def = cam_def;
    }

    public int getStnId() {
        return stnId;
    }

    public void setStnId(int stnId) {
        this.stnId = stnId;
    }

    @Override
    public String toString() {
        return "CameraArgs{" +
                "id=" + id +
                ", RR=" + RR +
                ", exposure=" + exposure +
                ", intensity=" + intensity +
                ", tone_up=" + tone_up +
                ", cam_def=" + cam_def +
                ", stnId=" + stnId +
                ", cam_type=" + cam_type +
                '}';
    }
}
