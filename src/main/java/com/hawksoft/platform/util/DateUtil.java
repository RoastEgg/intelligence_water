package com.hawksoft.platform.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/18.
 */
public class DateUtil {
    static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 将string转化为date，格式按照定义（注意可能string不能转化，产生异常）
     * @param s
     */
    public static Date parseString(String s){
        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            System.out.println("wrong format string!");
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断date1是否和date2是同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean judgeDate(Date date1,Date date2){
        SimpleDateFormat sdfD=new SimpleDateFormat("yyyy-MM-dd");
        if (sdfD.format(date1).equals(sdfD.format(date2))){
            return true;
        }
        return false;
    }

    public static String parseDate(Date date){
        String date1 = sdf.format(date);
        return date1;
    }

    /**
     * 取得当前日期
     * @return
     */
    public static String getNowDate(){
        Date date = new Date();
        return parseDate(date);
    }

    /**
     * 取得当前月份
     */
    public static int getNowMonth(){
        Calendar calendar=Calendar.getInstance();
        return calendar.get(Calendar.MONTH)+1;
    }
    /**
     * 取得当前年份
     */
    public static int getNowYear(){
        Calendar calendar=Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
    /**
     * 取得当前日期之前7天的时间
     * @return 取得当前日期，七天前的日期
     */
    public static String[] getBefore7Dates(){
        String[] timeArray= new String[2];
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow = Calendar.getInstance();
        Date dt = rightNow.getTime();
        timeArray[0]=sdf2.format(dt)+" 00:00:00";//从当天的00:00:00时算起;
        rightNow.add(Calendar.DAY_OF_YEAR,-7);//前当时间的前一周时间


         dt = rightNow.getTime();
        timeArray[1]= sdf2.format( dt )+" 00:00:00";//从一周前的0时算起
        System.out.println("一周时间段："+timeArray[0]+","+timeArray[1]);
        return timeArray;
    }

    /**
     * 取得当前日期的上一周时间
     * @param time yyyy-MM-dd
     * @return  上周一至上周日的时间数组
     */
    public static String[] getLastWeek(String time) throws Exception{
        String[] timeArray= new String[2];
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow = Calendar.getInstance();
        if(time!=null)
        {
            rightNow.setTime(sdf2.parse(time));
        }

        String monday = getLastMonday( sdf2.format(rightNow.getTime()));//取得上周一时间
        System.out.println("monday:"+monday);
        Date dt = sdf2.parse(monday);
        monday = sdf2.format(dt)+" 00:00:00";//从上周一时间的00:00:00时算起;
        timeArray[0]= monday;
        rightNow.setTime(dt);//设置上周一时间为当前时间

        rightNow.add(Calendar.DAY_OF_YEAR,6);//设置上周日的时间


        dt = rightNow.getTime();
        timeArray[1]= sdf2.format( dt )+" 23:59:59";//结束时间从上周日的23:59:59时算起
        System.out.println("上一周时间段：（上周一至上周日）"+timeArray[0]+","+timeArray[1]);
        return timeArray;
    }

    /**
     *取得上周一的时间
     *  @param time yyyy-MM-dd
     * @return
     * @throws Exception
     */

    public static String getLastMonday(String time) throws Exception{
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow = Calendar.getInstance();

        if(time!=null)
        {
            rightNow.setTime(sdf2.parse(time));
        }
        while(rightNow.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY )
        {
            rightNow.add(Calendar.DAY_OF_WEEK, -1 );
        }
        int dayOfWeek = rightNow.get(Calendar.DAY_OF_WEEK)-1;
        int offset = 1-dayOfWeek;
        rightNow.add(Calendar.DATE,offset-7);
        String monday = sdf.format(rightNow.getTime());
        System.out.println(monday);
        return monday;
    }

    /**
     * 取得当前日期,以及一月的时间
     * @return
     */
    public static String[] getBefore30Dates(){
        String[] timeArray= new String[2];
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow = Calendar.getInstance();
        Date dt = rightNow.getTime();
        timeArray[0]=sdf2.format(dt)+" 00:00:00";//从当天的00:00:00时算起;
        rightNow.add(Calendar.MONTH,-1);//前当时间的前一月时间


        dt = rightNow.getTime();
        timeArray[1]= sdf2.format( dt )+" 00:00:00";//从一月前的0时算起
        System.out.println("一月时间段："+timeArray[0]+","+timeArray[1]);
        return timeArray;
    }

    /**
     * 取得上一月的月初时间和月底时间
     *  @param time yyyy-MM-dd
     * @return 上一月的月初时间，上一月的月底时间 数组
     */

    public static String[] getLastMonth(String time) throws Exception{
        String[] timeArray= new String[2];
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow =  Calendar.getInstance();
        if(time!=null)
        {
            rightNow.setTime(sdf2.parse(time));
        }

        rightNow.set(Calendar.DAY_OF_MONTH,1);//这个月的第一天
        rightNow.add(Calendar.DATE,-1);//这个月减一天，即是上个月最后一天。

        Date dt = rightNow.getTime();
        timeArray[1]=sdf2.format(dt)+" 23:59:59";//从当天的23:59:59时算起;上个月最后一天


        rightNow.set(Calendar.DAY_OF_MONTH,1);//现在当前时间是上个月最后天天，设置成上个月的第一天
         dt = rightNow.getTime();
        timeArray[0]=sdf2.format(dt)+" 00:00:00";//从当天的00:00:00时算起;

        System.out.println("**上个月第一天和最后一天："+timeArray[0]+","+timeArray[1]);
        return timeArray;
    }

    /**
     * 取得上一年的年初时间和年底时间
     *  @param time yyyy-MM-dd
     * @return 上一年的年初时间，上一年的年底时间 数组
     */

    public static String[] getLastYear(String time) throws Exception{
        String[] timeArray= new String[2];
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow =  Calendar.getInstance();
        if(time!=null)
        {
            rightNow.setTime(sdf2.parse(time));
        }

        rightNow.set(Calendar.DAY_OF_YEAR,1);//当前这年的第一天
        rightNow.add(Calendar.DATE,-1);//当前这年减一天，即是上一年最后一天。

        Date dt = rightNow.getTime();
        timeArray[1]=sdf2.format(dt)+" 23:59:59";//从当天的23:59:59时算起;上一年最后一天


        rightNow.set(Calendar.DAY_OF_YEAR,1);//现在当前时间是上一年最后一天，设置成上一年的第一天
        dt = rightNow.getTime();
        timeArray[0]=sdf2.format(dt)+" 00:00:00";//从当天的00:00:00时算起;

        System.out.println("**上一年第一天和最后一天："+timeArray[0]+","+timeArray[1]);
        return timeArray;
    }

    public static String transData(String date) throws Exception
    {
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
        Date newdate = sdf2.parse(date);
        sdf2 = new SimpleDateFormat("yyyy-MM-dd");
       // System.out.println("**sdf2.format(newdate):"+sdf2.format(newdate));

        return sdf2.format(newdate);
    }

    /**
     * 获取当前时间之前或之后的几小时时间
     * @param hour
     * @return
     */
    public static String getTimeByHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    /**
     * 获取某个特定时间之前或之后的几个小时时间
     * @param time
     * @param hour
     * @return
     */
    public static String getTimeByHour(String time,int hour){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format="";
        try {
            calendar.setTime(dateFormat.parse(time));
//            calendar.add(Calendar.HOUR_OF_DAY,hour);
            calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)+hour);
            format=dateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            logger.info("DateUtil.getTimeByHour:",e.getMessage());
            e.printStackTrace();
        }
        return format;
    }

    /**
     * 获取当前时间之前或之后的几分钟时间
     * @param minute
     * @return
     */
    public static String getTimeByMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    /**
     * 获取某个特定时间之前或之后的几分钟时间
     * @param time
     * @param minute
     * @return
     */
    public static String getTimeByMinute(String time,int minute){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = "";
        try {
            calendar.setTime(dateFormat.parse(time));
            calendar.add(Calendar.MINUTE,minute);
            format = dateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            logger.info("DateUtil.getTimeByMinute:",e.getMessage());
            e.printStackTrace();
        }
        return format;
    }
//    public static String[] getN
    public static void main(String[] arg) throws Exception
    {

//        DateUtil.getBefore30Dates();
//        DateUtil.getLastMonday(null);
//        DateUtil.getLastWeek(null);
//        DateUtil.getLastWeek("2017-12-21");
//        DateUtil.getLastMonth(null);
//        DateUtil.getLastMonth("2017-12-08");
//        DateUtil.getLastYear(null);
//        DateUtil.getLastYear("2016-12-08");
//        DateUtil.transData("01-12-2017");
        System.out.println(getTimeByHour("2017-12-08 00:00:00",-1));
//        System.out.println(DateUtil.getNowYear());
    }
}
