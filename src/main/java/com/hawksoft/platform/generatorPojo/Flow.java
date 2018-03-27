package com.hawksoft.platform.generatorPojo;

import java.math.BigDecimal;
import java.util.Date;

public class Flow {
    private Long flowid;

    private Integer stnid;

    private BigDecimal avgspeed;

    private BigDecimal riverarea;

    private BigDecimal avgflow;

    private Date collectiontime;

    private String state;

    private BigDecimal dayflow;

    private BigDecimal weekflow;

    private BigDecimal monthflow;

    private BigDecimal yearflow;

    private BigDecimal totalflow;

    private String remark;

    public Long getFlowid() {
        return flowid;
    }

    public void setFlowid(Long flowid) {
        this.flowid = flowid;
    }

    public Integer getStnid() {
        return stnid;
    }

    public void setStnid(Integer stnid) {
        this.stnid = stnid;
    }

    public BigDecimal getAvgspeed() {
        return avgspeed;
    }

    public void setAvgspeed(BigDecimal avgspeed) {
        this.avgspeed = avgspeed;
    }

    public BigDecimal getRiverarea() {
        return riverarea;
    }

    public void setRiverarea(BigDecimal riverarea) {
        this.riverarea = riverarea;
    }

    public BigDecimal getAvgflow() {
        return avgflow;
    }

    public void setAvgflow(BigDecimal avgflow) {
        this.avgflow = avgflow;
    }

    public Date getCollectiontime() {
        return collectiontime;
    }

    public void setCollectiontime(Date collectiontime) {
        this.collectiontime = collectiontime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public BigDecimal getDayflow() {
        return dayflow;
    }

    public void setDayflow(BigDecimal dayflow) {
        this.dayflow = dayflow;
    }

    public BigDecimal getWeekflow() {
        return weekflow;
    }

    public void setWeekflow(BigDecimal weekflow) {
        this.weekflow = weekflow;
    }

    public BigDecimal getMonthflow() {
        return monthflow;
    }

    public void setMonthflow(BigDecimal monthflow) {
        this.monthflow = monthflow;
    }

    public BigDecimal getYearflow() {
        return yearflow;
    }

    public void setYearflow(BigDecimal yearflow) {
        this.yearflow = yearflow;
    }

    public BigDecimal getTotalflow() {
        return totalflow;
    }

    public void setTotalflow(BigDecimal totalflow) {
        this.totalflow = totalflow;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}