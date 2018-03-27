package com.hawksoft.platform.generatorPojo;

import java.math.BigDecimal;
import java.util.Date;

public class Waterquality {
    private Long id;

    private Integer stnid;

    private String temperature;

    private BigDecimal conductivity;

    private BigDecimal salinity;

    private BigDecimal tds;

    private BigDecimal density;

    private BigDecimal ph;

    private BigDecimal dissolvedoxygen;

    private BigDecimal dosaturation;

    private BigDecimal redox;

    private BigDecimal turbidity;

    private BigDecimal tss;

    private BigDecimal chlorophyla;

    private BigDecimal phycocyanobilin;

    private BigDecimal hatchopen;

    private BigDecimal waterpenetration;

    private BigDecimal voltage;

    private BigDecimal transparency;

    private BigDecimal nh3;

    private Date collectiontime;

    private Date instocktime;

    private Integer state;

    private String picorvideo;

    private String filepath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStnid() {
        return stnid;
    }

    public void setStnid(Integer stnid) {
        this.stnid = stnid;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public BigDecimal getConductivity() {
        return conductivity;
    }

    public void setConductivity(BigDecimal conductivity) {
        this.conductivity = conductivity;
    }

    public BigDecimal getSalinity() {
        return salinity;
    }

    public void setSalinity(BigDecimal salinity) {
        this.salinity = salinity;
    }

    public BigDecimal getTds() {
        return tds;
    }

    public void setTds(BigDecimal tds) {
        this.tds = tds;
    }

    public BigDecimal getDensity() {
        return density;
    }

    public void setDensity(BigDecimal density) {
        this.density = density;
    }

    public BigDecimal getPh() {
        return ph;
    }

    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }

    public BigDecimal getDissolvedoxygen() {
        return dissolvedoxygen;
    }

    public void setDissolvedoxygen(BigDecimal dissolvedoxygen) {
        this.dissolvedoxygen = dissolvedoxygen;
    }

    public BigDecimal getDosaturation() {
        return dosaturation;
    }

    public void setDosaturation(BigDecimal dosaturation) {
        this.dosaturation = dosaturation;
    }

    public BigDecimal getRedox() {
        return redox;
    }

    public void setRedox(BigDecimal redox) {
        this.redox = redox;
    }

    public BigDecimal getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(BigDecimal turbidity) {
        this.turbidity = turbidity;
    }

    public BigDecimal getTss() {
        return tss;
    }

    public void setTss(BigDecimal tss) {
        this.tss = tss;
    }

    public BigDecimal getChlorophyla() {
        return chlorophyla;
    }

    public void setChlorophyla(BigDecimal chlorophyla) {
        this.chlorophyla = chlorophyla;
    }

    public BigDecimal getPhycocyanobilin() {
        return phycocyanobilin;
    }

    public void setPhycocyanobilin(BigDecimal phycocyanobilin) {
        this.phycocyanobilin = phycocyanobilin;
    }

    public BigDecimal getHatchopen() {
        return hatchopen;
    }

    public void setHatchopen(BigDecimal hatchopen) {
        this.hatchopen = hatchopen;
    }

    public BigDecimal getWaterpenetration() {
        return waterpenetration;
    }

    public void setWaterpenetration(BigDecimal waterpenetration) {
        this.waterpenetration = waterpenetration;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }

    public BigDecimal getTransparency() {
        return transparency;
    }

    public void setTransparency(BigDecimal transparency) {
        this.transparency = transparency;
    }

    public BigDecimal getNh3() {
        return nh3;
    }

    public void setNh3(BigDecimal nh3) {
        this.nh3 = nh3;
    }

    public Date getCollectiontime() {
        return collectiontime;
    }

    public void setCollectiontime(Date collectiontime) {
        this.collectiontime = collectiontime;
    }

    public Date getInstocktime() {
        return instocktime;
    }

    public void setInstocktime(Date instocktime) {
        this.instocktime = instocktime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPicorvideo() {
        return picorvideo;
    }

    public void setPicorvideo(String picorvideo) {
        this.picorvideo = picorvideo == null ? null : picorvideo.trim();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }
}