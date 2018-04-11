package com.hawksoft.platform.generatorPojo;

public class Waterstation {
    private Integer id;

    private String stncode;

    private String stnname;

    private String stnaddr;

    private String picurl;

    private String province;

    private String city;

    private String x;

    private String y;

    private String institute;

    private String state;

    private String stntype;

    private String stnnote;

    private String sectionmappath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStncode() {
        return stncode;
    }

    public void setStncode(String stncode) {
        this.stncode = stncode == null ? null : stncode.trim();
    }

    public String getStnname() {
        return stnname;
    }

    public void setStnname(String stnname) {
        this.stnname = stnname == null ? null : stnname.trim();
    }

    public String getStnaddr() {
        return stnaddr;
    }

    public void setStnaddr(String stnaddr) {
        this.stnaddr = stnaddr == null ? null : stnaddr.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x == null ? null : x.trim();
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y == null ? null : y.trim();
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute == null ? null : institute.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getStntype() {
        return stntype;
    }

    public void setStntype(String stntype) {
        this.stntype = stntype == null ? null : stntype.trim();
    }

    public String getStnnote() {
        return stnnote;
    }

    public void setStnnote(String stnnote) {
        this.stnnote = stnnote == null ? null : stnnote.trim();
    }

    public String getSectionmappath() {
        return sectionmappath;
    }

    public void setSectionmappath(String sectionmappath) {
        this.sectionmappath = sectionmappath == null ? null : sectionmappath.trim();
    }
}