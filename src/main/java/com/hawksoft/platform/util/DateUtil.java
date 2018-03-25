package com.hawksoft.platform.util;

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
    public static void main(String[] arg) throws Exception
    {

        DateUtil.getBefore30Dates();
        DateUtil.getLastMonday(null);
        DateUtil.getLastWeek(null);
        DateUtil.getLastWeek("2017-12-21");
        DateUtil.getLastMonth(null);
        DateUtil.getLastMonth("2017-12-08");
        DateUtil.getLastYear(null);
        DateUtil.getLastYear("2016-12-08");
        DateUtil.transData("01-12-2017");
    }
}
