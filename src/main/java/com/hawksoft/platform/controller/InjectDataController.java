package com.hawksoft.platform.controller;

import com.hawksoft.platform.entity.Flow;
import com.hawksoft.platform.entity.SpeedFlow;
import com.hawksoft.platform.entity.WaterQuality;
import com.hawksoft.platform.entity.WeatherWave;
import com.hawksoft.platform.service.*;
import com.hawksoft.platform.util.DataUtil;
import com.hawksoft.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.text.resources.cldr.eu.FormatData_eu;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 数据注入模块，注入flow、speedflow和waterquality3这张表中，
 * 日期范围是2018-01-01 到 2018-03-31
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/injectData")
public class InjectDataController {
    @Autowired
    private FlowService flowService;
    @Autowired
    private SpeedFlowService speedFlowService;
    @Autowired
    private WaterQualityService waterQualityService;
    @Autowired
    private WeatherWaveService weatherWaveService;
    @Autowired
    private UnmannedBoatService unmannedBoatService;

    @RequestMapping(value = "/flowAndSpeedFlow/{stnId}", method = RequestMethod.GET)
    @ResponseBody
    public boolean InjectFlowAndSpeedFlow(@PathVariable("stnId") int stnId) {

        int time[] = {0, 3, 5, 7, 9, 13, 17};

        for (int month = 1; month <= 3; month++) {  //月
            for (int i = 1; i <= 31; i++) {        //天
                if ((month == 2 && i > 28)) {
                    continue;
                }
                for (int j = 0; j < 7; j++) {//每天7个时间段

                    String cTime = "2018-0" + month + "-" + i + " " + time[j] + ":00:00";//如："2018-02-5 3:00:00"
                    Date cDate = DateUtil.parseString(cTime);
                    flowService.generateData(stnId,cDate);

                }

                //注入speedflow数据：
                for (int j = 0; j < 7; j++) {//每天7个时间段

                    String sTime = "2018-0" + month + "-" + i + " " + time[j] + ":00:00";//如："2018-02-5 3:00:00"
                    Date sDate = DateUtil.parseString(sTime);
                    speedFlowService.generateData(stnId,sDate);

                }

            }
        }
        return true;

    }

    @RequestMapping(value = "/waterQuality/{stnId}", method = RequestMethod.GET)
    @ResponseBody
    public boolean InjectWaterQuality(@PathVariable("stnId") int stnId) throws Exception {

        for (int month = 1; month <= 3; month++) {  //月
            for (int i = 1; i <= 31; i++) {        //天
                if ((month == 2 && i > 28)) {
                    continue;
                }
                for (int j = 0; j < 24; j++) {//小时

                    for (int minute = 0; minute < 12; minute++) {//5分钟一次，每小时12次

                        Date wqDate = transWqTime(month, i, j, minute);
                        waterQualityService.generateData(stnId,wqDate);
                    }

                }
            }
        }
        return true;
//        for (int month = 4; month <= 4; month++) {  //月
//            for (int i = 1; i <= 12; i++) {        //天
//
//                for (int j = 0; j < 24; j++) {//小时
//
//                    for (int minute = 0; minute < 12; minute++) {//5分钟一次，每小时12次
//
//                        Date wqDate = transWqTime(month, i, j, minute);
//                        waterQualityService.generateData(stnId,wqDate);
//                    }
//
//                }
//            }
//        }
//        return true;
    }

    @RequestMapping(value = "/setUBtime",method = RequestMethod.GET)
    @ResponseBody
    public boolean setUBtime(){
        unmannedBoatService.setUBtime();
        return true;
    }

    @RequestMapping(value = "/updateWQTime",method = RequestMethod.GET)
    @ResponseBody
    public boolean updateWQTime() throws Exception {
        for (int i=208827;i<=214001;i++){
            if (waterQualityService.queryById(i)!=null){
                waterQualityService.updateTemper(i);
            }
        }
//        if (waterQualityService.queryById(193453) == null){
//            return false;
//        }
        return true;
    }

//    @RequestMapping(value = "/weatherWave/{stnId}", method = RequestMethod.GET)
//    @ResponseBody
//    public boolean InjectWW(@PathVariable("stnId") int stnId) {
//
//        for (int month = 1; month <= 4; month++) {  //月
//            for (int i = 1; i <= 31; i++) {        //天
//                if ((month == 2 && i > 28)||(month ==2&&i>12)) {
//                    continue;
//                }
//                for (int j = 0; j < 24; j++) {//小时
//
//                    for (int minute = 0; minute < 12; minute++) {//5分钟一次，每小时12次
//
//                        Date wqDate = transWqTime(month, i, j, minute);
//                        weatherWaveService.generateData(stnId,wqDate);
//                    }
//
//                }
//            }
//        }
//        return true;
//    }

    public Date transWqTime(int month, int day, int hour, int rate) {
        int minute = rate * 5;
        String time = "2018-" + month + "-" + day + " " + hour + ":" + minute + ":00";//如："2018-02-5 3:00:00"
        Date date = DateUtil.parseString(time);//如："2018-02-05 00:00:00"转化为date
        return date;
    }
}
