package com.hawksoft.platform.util;

import java.text.DecimalFormat;

public class DataUtil {

    private static DecimalFormat df = new DecimalFormat("0.0000");
    /**
     * 获取随机数，在min到max范围内
     * @param max
     * @param min
     * @return
     */
    public static double getRandom(double max, double min){
        double answer = Math.random()*(max - min) + min;
        answer = Double.parseDouble(df.format(answer));
        return answer;
    }

    /**
     * 调整型随机，调整范围是5%
     * @param base
     * @param max
     * @param min
     * @return
     */
    public static double adjustData(double base,double max,double min){
        double range = 0.05*base,answer = 0.0;
        answer  = getRandom(Math.min(base + range,max),
                Math.max(base - range,min));
        answer = Double.parseDouble(df.format(answer));
        return answer;
    }

    /**
     * 扩大型随机，扩大范围是0.7%(这样才能保证前后两天的数据浮动范围是5%)
     * @param base
     * @param max
     * @param min
     * @return
     */
    public static double expendData(double base,double max,double min){
        double range = 0.007*base, answer = 0.0;
        answer  = getRandom(Math.min(base+ range ,max), base);
        answer = Double.parseDouble(df.format(answer));
        return answer;
    }
}