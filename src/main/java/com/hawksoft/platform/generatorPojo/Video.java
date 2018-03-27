package com.hawksoft.platform.generatorPojo;

public class Video {
    private Integer id;

    private Integer stnid;

    private String type;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStnid() {
        return stnid;
    }

    public void setStnid(Integer stnid) {
        this.stnid = stnid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}