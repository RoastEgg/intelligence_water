package com.hawksoft.platform.entity;

public class Video {
    private int id;//主键，自增
    private int sthId;//站点ID
    private String type;//视频类型
    private String url;//视频URL

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSthId() {
        return sthId;
    }

    public void setSthId(int sthId) {
        this.sthId = sthId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
