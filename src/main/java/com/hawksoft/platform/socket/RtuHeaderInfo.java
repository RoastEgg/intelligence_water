package com.hawksoft.platform.socket;

public class RtuHeaderInfo {
    private int type;
    private int siteNo;
    private int cameraId;
    private int value;
    private String filename;
    private String stime;
    private int size; // file size or data size
    private SocketUtils.IWRtuMsgType msgType;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(int siteNo) {
        this.siteNo = siteNo;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public void setMsgType(SocketUtils.IWRtuMsgType msgType) {
        this.msgType = msgType;
    }

    public SocketUtils.IWRtuMsgType getMsgType() {
        return msgType;
    }
}
