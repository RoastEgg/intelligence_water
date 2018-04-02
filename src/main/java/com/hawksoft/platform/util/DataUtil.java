package com.hawksoft.platform.util;

import java.text.DecimalFormat;

public class DataUtil {

    /**
     * 获取随机数，在min到max范围内
     * @param max
     * @param min
     * @return
     */
    public static double getRandom(double max, double min){
        double answer = Math.random()*(max - min) + min;
        DecimalFormat df = new DecimalFormat("0.000");
        answer = Double.parseDouble(df.format(answer));
        return answer;
    }

    /**
     * 调整数据，在range范围内
     * @param base
     * @param max
     * @param min
     * @param range
     * @return
     */
    public static double adjustData(double base,double max,double min,double range){
        double answer = 0.0;
        answer  = getRandom(Math.min(base + range,max),
                Math.max(base - range,min));
        return answer;
    }

    /**
     * 调整数据，流量的规律应该是一天中逐渐变大
     * @param base
     * @param max
     * @param min
     * @param range
     * @return
     */
    public static double expendData(double base,double max,double min,double range){
        double answer = 0.0;
        answer  = getRandom(Math.min(base + range,max), base);
        return answer;
    }
}
