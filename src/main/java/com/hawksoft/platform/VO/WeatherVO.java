package com.hawksoft.platform.VO;

public class WeatherVO {
    private int weaid;// "1"
    private String week;// "星期一"
    private String cityno;// "beijing"
    private String citynm;// "北京"
    private String cityid;//: "101010100"   /*气象编号*/
    private String uptime;//: "2015-07-20 00:50:00"  /*更新时间*/
    private String temperature;//: "22℃"  /*该时段温度*/
    private String humidity;//: "97%",  /*该时段湿度*/
    private String aqi;  /*PM2.5 AQI*/
    private String weather;//": "晴"   /*该时段天气*/
    private String weather_icon;// "http://api.k780.com/upload/weather/d/0.gif", /*时段气象图标 全部气象图标下载*/
    private String wind;//: "东北风"  /*该时段风向*/
    private String winp;//: "1级";   /*该时段风力*/
    private double temp;//: "22";   /*温度*/
    private String weatid;//: "1";  /*天气编号*/
    private String windid;//: "13";   /*风向编号*/
    private String winpid;//: "201"   /*风力编号*/
    private String weather_iconid;//: "1"  /*气象图标编号,对应weather_icon 0.gif*/

    public WeatherVO() {
    }

    public int getWeaid() {
        return weaid;
    }

    public void setWeaid(int weaid) {
        this.weaid = weaid;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCityno() {
        return cityno;
    }

    public void setCityno(String cityno) {
        this.cityno = cityno;
    }

    public String getCitynm() {
        return citynm;
    }

    public void setCitynm(String citynm) {
        this.citynm = citynm;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeather_icon() {
        return weather_icon;
    }

    public void setWeather_icon(String weather_icon) {
        this.weather_icon = weather_icon;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWinp() {
        return winp;
    }

    public void setWinp(String winp) {
        this.winp = winp;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getWeatid() {
        return weatid;
    }

    public void setWeatid(String weatid) {
        this.weatid = weatid;
    }

    public String getWindid() {
        return windid;
    }

    public void setWindid(String windid) {
        this.windid = windid;
    }

    public String getWinpid() {
        return winpid;
    }

    public void setWinpid(String winpid) {
        this.winpid = winpid;
    }

    public String getWeather_iconid() {
        return weather_iconid;
    }

    public void setWeather_iconid(String weather_iconid) {
        this.weather_iconid = weather_iconid;
    }
}
