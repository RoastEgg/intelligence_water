package com.hawksoft.platform.util;

import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.List;

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
     * 调整型随机，调整范围是5%，但是先把base降低5%，
     * 因为流量有早小晚大的规律，降低5%是为了每一天的第一条记录的值贴近前一天的第一条记录的值
     * （专为生成流量信息写的方法）
     * @param base
     * @param max
     * @param min
     * @return
     */
    public static double adjustDataForFlow(double base,double max,double min){
        double range = 0.05*base,answer = 0.0;
        answer  = getRandom(Math.min(base + range,max),
                Math.max(base - 2*range,min));
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

    public static double randomData5per(double base){
        double range = 0.05*base,answer = 0.0;
        answer  = getRandom(base + range,
                base - range);
//        answer = Double.parseDouble(df.format(answer));
        return answer;
    }


    /**
     * 判断对象数组中任意元素不为空
     * @param objects
     * @return
     */
    public static boolean anyNotEmpty(Object[] objects){
        for (int i = 0;i<objects.length;i++) {
            Object o = objects[i];
            boolean b = StringUtils.isEmpty(o);
            if (b == true) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Object[] objects={1,null,new Object()};
        System.out.println(DataUtil.anyNotEmpty(objects));
    }
}
