package com.hawksoft.platform.generatorPojo;

import java.util.Date;

public class Floatingmatter {
    private Integer id;

    private Integer stnid;

    private Date collectiontime;

    private Integer camindex;

    private String filepathorigin;

    private String filepathresult;

    private String picorvideo;

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

    public Date getCollectiontime() {
        return collectiontime;
    }

    public void setCollectiontime(Date collectiontime) {
        this.collectiontime = collectiontime;
    }

    public Integer getCamindex() {
        return camindex;
    }

    public void setCamindex(Integer camindex) {
        this.camindex = camindex;
    }

    public String getFilepathorigin() {
        return filepathorigin;
    }

    public void setFilepathorigin(String filepathorigin) {
        this.filepathorigin = filepathorigin == null ? null : filepathorigin.trim();
    }

    public String getFilepathresult() {
        return filepathresult;
    }

    public void setFilepathresult(String filepathresult) {
        this.filepathresult = filepathresult == null ? null : filepathresult.trim();
    }

    public String getPicorvideo() {
        return picorvideo;
    }

    public void setPicorvideo(String picorvideo) {
        this.picorvideo = picorvideo == null ? null : picorvideo.trim();
    }
}