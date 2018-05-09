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

    /**
     * 计算数组的平均数
     * @param integers
     * @return
     */
    public static Integer getAvgCount(Integer[] integers){
        Integer sum=0;
        for (int i=0;i<integers.length;i++) {
            sum+=integers[i];
        }
        return sum/integers.length;
    }
    /**
     * 计算数组的平均数
     * @param doubles
     * @return
     */
    public static Double getAvgCount(Double[] doubles){
        Double sum=0.0;
        for (int i=0;i<doubles.length;i++) {
            sum+=doubles[i];
        }
        return sum/doubles.length;
    }

    /**
     * 把罗马数字转换为阿拉伯数字
     *
     * @param m
     * @return
     */
    public static int r2a(String m) {
        int graph[] = new int[400];
        graph['I'] = 1;
        graph['V'] = 5;
        graph['X'] = 10;
        graph['L'] = 50;
        graph['C'] = 100;
        graph['D'] = 500;
        graph['M'] = 1000;
        char[] num = m.toCharArray();
        int sum = graph[num[0]];
        for (int i = 0; i < num.length - 1; i++) {
            if (graph[num[i]] >= graph[num[i + 1]]) {
                sum += graph[num[i + 1]];
            } else {
                sum = sum + graph[num[i + 1]] - 2 * graph[num[i]];
            }
        }
        return sum;
    }
    /**
     * 把阿拉伯数字转换为罗马数字
     *
     * @param number
     * @return
     */
    public static String a2r(int number) {
        String rNumber = "";
        int[] aArray = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] rArray = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
                "IX", "V", "IV", "I" };
        if (number < 1 || number > 3999) {
            rNumber = "-1";
        } else {
            for (int i = 0; i < aArray.length; i++) {
                while (number >= aArray[i]) {
                    rNumber += rArray[i];
                    number -= aArray[i];
                }
            }
        }
        return rNumber;
    }

    public static void main(String[] args){
        System.out.println(a2r(1));
        System.out.println(r2a("II"));
    }
}
