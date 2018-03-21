package com.hawksoft.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 流量统计
 */
public class Flow implements Serializable {


    private int flowId;                 //flowId
    private int stnId;                  //站点id
    private Double avgSpeed;            //平均流速
    private Double riverArea;           //河道断面面积
    private Double avgFlow;             //平均流量
    private String collectionTime;     //采集时间
    private String state;               //状态编号（1为绿色，代表正常；2为灰色，代表机器问题或设备维护中没数据；3为红色，代表设备损坏）
    private Double dayFlow;             //日平均流量
    private Double weekFlow;            //周平均流量
    private Double monthFlow;           //月平均流量
    private Double yearFlow;            //年平均流量
    private Double totalFlow;           //累计平均流量
    private String remark;              //省份
    private WaterStation waterStation; //站点对象

    public Flow(){

    }

    public Flow(String type) {
            this.avgSpeed = 0.0;
            this.riverArea = 0.0;
            this.avgFlow = 0.0;
            this.state = "0";
            this.dayFlow = 0.0;
            this.weekFlow = 0.0;
            this.monthFlow = 0.0;
            this.yearFlow = 0.0;
            this.totalFlow = 0.0;

    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public int getStnId() {
        return stnId;
    }

    public void setStnId(int stnId) {
        this.stnId = stnId;
    }

    public Double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Double getRiverArea() {
        return riverArea;
    }

    public void setRiverArea(Double riverArea) {
        this.riverArea = riverArea;
    }

    public Double getAvgFlow() {
        return avgFlow;
    }

    public void setAvgFlow(Double avgFlow) {
        this.avgFlow = avgFlow;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getState() {
        return state != null ? state : "";
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getDayFlow() {
        return dayFlow != null ? dayFlow : 0;
    }

    public void setDayFlow(Double dayFlow) {
        this.dayFlow = dayFlow;
    }

    public Double getWeekFlow() {
        return weekFlow != null ? weekFlow : 0;
    }

    public void setWeekFlow(Double weekFlow) {
        this.weekFlow = weekFlow;
    }

    public Double getMonthFlow() {
        return monthFlow != null ? monthFlow : 0;
    }

    public void setMonthFlow(Double monthFlow) {
        this.monthFlow = monthFlow;
    }

    public Double getYearFlow() {
        return yearFlow != null ? yearFlow : 0;
    }

    public void setYearFlow(Double yearFlow) {
        this.yearFlow = yearFlow;
    }

    public Double getTotalFlow() {
        return totalFlow != null ? totalFlow : 0;
    }

    public void setTotalFlow(Double totalFlow) {
        this.totalFlow = totalFlow;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public WaterStation getWaterStation() {
        return waterStation;
    }

    public void setWaterStation(WaterStation waterStation) {
        this.waterStation = waterStation;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "flowId=" + flowId +
                ", stnId=" + stnId +
                ", avgSpeed=" + avgSpeed +
                ", riverArea=" + riverArea +
                ", avgFlow=" + avgFlow +
                ", collectionTime='" + collectionTime + '\'' +
                ", state='" + state + '\'' +
                ", dayFlow=" + dayFlow +
                ", weekFlow=" + weekFlow +
                ", monthFlow=" + monthFlow +
                ", yearFlow=" + yearFlow +
                ", totalFlow=" + totalFlow +
                ", remark='" + remark + '\'' +
                ", waterStation=" + waterStation +
                '}';
    }
}
