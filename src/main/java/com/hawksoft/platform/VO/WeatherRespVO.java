package com.hawksoft.platform.VO;

import java.util.ArrayList;
import java.util.List;

public class WeatherRespVO {
    private int success;
    private List<WeatherVO> result = new ArrayList();

    public WeatherRespVO() {
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<WeatherVO> getResult() {
        return result;
    }

    public void setResult(List<WeatherVO> result) {
        this.result = result;
    }
}
