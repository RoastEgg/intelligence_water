package com.hawksoft.platform.controller;

import com.hawksoft.platform.entity.Flow;
import com.hawksoft.platform.entity.SpeedFlow;
import com.hawksoft.platform.entity.WaterQuality;
import com.hawksoft.platform.service.FlowService;
import com.hawksoft.platform.service.SpeedFlowService;
import com.hawksoft.platform.service.WaterQualityService;
import com.hawksoft.platform.util.DataUtil;
import com.hawksoft.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/flowAndSpeedFlow", method = RequestMethod.GET)
    @ResponseBody
    public boolean InjectFlowAndSpeedFlow() {

        int time[] = {0, 3, 5, 7, 9, 13, 17};

        for (int month = 1; month <= 3; month++) {  //月
            for (int i = 1; i <= 31; i++) {        //天
                if ((month == 2 && i > 28)) {
                    continue;
                }
                for (int j = 0; j < 7; j++) {//每天7个时间段

                    String cTime = "2018-0" + month + "-" + i + " " + time[j] + ":00:00";//如："2018-02-5 3:00:00"
                    Date cDate  = DateUtil.parseString(cTime);

                    Flow flow = flowService.generateFlow(cDate);

                    cTime = DateUtil.parseDate(cDate);//如："2018-02-05 03:00:00"
                    flow.setCollectionTime(cTime);
                    flowService.saveFlow(flow);
                }

                //注入speedflow数据：
                for (int j = 0; j < 7; j++) {//每天7个时间段

                    String sTime = "2018-0" + month + "-" + i + " " + time[j] + ":00:00";//如："2018-02-5 3:00:00"
                    Date sDate = DateUtil.parseString(sTime);
                    SpeedFlow speedFlow = speedFlowService.generateSpeedFlow(sDate);

                    sTime = DateUtil.parseDate(sDate);//如："2018-02-05 03:00:00"
                    speedFlow.setCollectionTime(sTime);
                    speedFlow.setInstockTime(sTime);
                    speedFlowService.saveSpeedFlow(speedFlow);

                }

            }
        }
        return true;

    }

    @RequestMapping(value = "/waterQuality", method = RequestMethod.GET)
    @ResponseBody
    public boolean InjectWaterQuality() {
        int stnId = 1;

        for (int id = 1; id <= 2; id++) {//循环2次，分别生成id=1和id=7的数据

            if (id == 1) {
                stnId = 1;
            } else {
                stnId = 7;
            }

            for (int month = 1; month <= 3; month++) {  //月
                for (int i = 1; i <= 31; i++) {        //天
                    if ((month == 2 && i > 28)) {
                        continue;
                    }
                    for (int j = 0; j < 24; j++) {//小时

                        for (int minute = 0; minute < 12; minute++) {//5分钟一次，每小时12次

                            String wqTime = getWqTime(month, i, j, minute);
                            WaterQuality waterQuality = waterQualityService.generateWQ(stnId);
                            waterQuality.setCollectionTime(wqTime);
                            waterQuality.setInstockTime(wqTime);
                            waterQualityService.saveWaterQuality(waterQuality);
                        }

                    }
                }
            }
        }

        return true;
    }

    public String getWqTime(int month, int day, int hour, int rate) {
        int minute = rate * 5;
        String time = "2018-" + month + "-" + day + " " + hour + ":" + minute + ":00";//如："2018-02-5 3:00:00"
        time = DateUtil.parseDate(DateUtil.parseString(time));//如："2018-02-05 00:00:00"
        return time;
    }
}
