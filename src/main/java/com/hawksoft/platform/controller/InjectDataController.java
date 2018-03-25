package com.hawksoft.platform.controller;

import com.hawksoft.platform.entity.Flow;
import com.hawksoft.platform.entity.SpeedFlow;
import com.hawksoft.platform.entity.WaterQuality;
import com.hawksoft.platform.service.FlowService;
import com.hawksoft.platform.service.SpeedFlowService;
import com.hawksoft.platform.service.WaterQualityService;
import com.hawksoft.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public boolean Inject(){
        int stnId = 1;
        double minSpeed = 0.0,maxSpeed = 0.87;
        double minFlow = 100,maxFlow =722;//此处真是数据的可信度存疑，有大量的100以下数据
        double avgSpeed [] = new double[7];
        double avgFlow [] = new double [7];//flow表需要的数据

        double camSpeed;//speedflow表需要的数据，相机测出来的流速

        //waterQuality表需要的数据：
        double temper,minTemper = 2.401,maxTemper = 11.675;        //温度值
        double ph,minPh = 3.879,maxPh = 8.551;                 //PH值
        double dissolvedOxygen,minDissolvedOxygen = 4.105,maxDissolvedOxygen = 15.489;    //溶解氧值,
        // 和DB对比之后，觉得此处需确认
        double redox,minRedox = 103.923,maxRedox = 412.66;               //氧化还原值  ***


        int time [] = {0,3,5,7,9,13,17};

        for (int month=1;month<=3;month++){  //月
            for (int i=1;i<=31;i++) {        //天
                if ((month == 2 && i > 28)){
                    continue;
                }
                for (int j=0;j<7;j++){//每天7个时间段
                    //流速这里有一个0的问题，是不该加进来还是像真实数据那样加进来，此处暂时采用后者
                    avgSpeed[j] = Math.random()*maxSpeed + minSpeed;
                    avgFlow[j] = Math.random()*maxFlow + minFlow;
                    Arrays.sort(avgSpeed);//升序排序(内置)
                    Arrays.sort(avgFlow);

                    Flow flow = new Flow();
                    flow.setStnId(stnId);
                    flow.setAvgSpeed(avgSpeed[j]);
                    String cTime = "2018-0"+month+"-"+i+" "+time[j]+":00:00";//如："2018-02-5 3:00:00"
                    cTime = DateUtil.parseDate(DateUtil.parseString(cTime));//如："2018-02-05 03:00:00"
                    flow.setCollectionTime(cTime);
                    flowService.saveFlow(flow);
                }

                //注入speedflow数据：
                for (int j=0;j<5;j++) {
                    camSpeed = Math.random()*maxSpeed + minSpeed;

                    SpeedFlow speedFlow = new SpeedFlow();
                    speedFlow.setStnId(stnId);
                    speedFlow.setWaterSpeed1(camSpeed);
                    String cTime = "2018-0"+month+"-"+i+" "+"00:00:00";//取当日0点，如："2018-02-5 00:00:00"
                    cTime = DateUtil.parseDate(DateUtil.parseString(cTime));//如："2018-02-05 00:00:00"
                    speedFlow.setCollectionTime(cTime);
                    speedFlow.setInstockTime(cTime);
                    speedFlowService.saveSpeedFlow(speedFlow);
                }

                for (int j=0;j<24;j++){
                    temper = Math.random()*maxTemper + minTemper;
                    ph = Math.random()*maxPh + minPh;
                    dissolvedOxygen = Math.random()*maxDissolvedOxygen + minDissolvedOxygen;
                    redox = Math.random()*maxRedox + minRedox;

                    WaterQuality waterQuality = new WaterQuality();
                    waterQuality.setTemperature(temper+"℃");  //此处存疑，是否加℃符号
                    waterQuality.setPh(ph);
                    waterQuality.setDissolvedOxygen(dissolvedOxygen);
                    waterQuality.setRedox(redox);
                    String cTime = "2018-0"+month+"-"+i+" "+j+":00:00";//如："2018-02-5 3:00:00"
                    cTime = DateUtil.parseDate(DateUtil.parseString(cTime));//如："2018-02-05 00:00:00"
                    waterQuality.setCollectionTime(cTime);
                    waterQuality.setInstockTime(cTime);
                    waterQualityService.saveWaterQuality(waterQuality);
                }
            }
        }
        return true;

    }
}
