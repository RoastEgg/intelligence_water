package com.hawksoft.platform.VO;

public class PdfVO {
    private String name;
    private String url;
    private String time;
    private String note;

    public PdfVO() {
    }

    public PdfVO(String name, String url, String time) {
        this.name = name;
        this.url = url;
        this.time = time;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
