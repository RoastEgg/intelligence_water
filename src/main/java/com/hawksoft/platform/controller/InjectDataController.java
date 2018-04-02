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

    private int stnId = 1;
    private double minSpeed = 0.11,maxSpeed = 0.87;
    private double minFlow = 100,maxFlow =722;
    private double avgSpeed [] = new double[7];
    private double avgFlow [] = new double [7];//flow表需要的数据

    private double camSpeed [][] = new double[7][5];//speedflow表需要的数据，5个相机测出来的流速

    //waterQuality表需要的数据：
    private double temper,minTemper = 2.401,maxTemper = 11.675;        //温度值
    private double ph,minPh = 3.879,maxPh = 8.551;                 //PH值
    private double dissolvedOxygen,minDissolvedOxygen = 4.105,maxDissolvedOxygen = 15.489;    //溶解氧值

    // 和DB对比之后，觉得此处需确认
    private double redox,minRedox = 103.923,maxRedox = 412.66;               //氧化还原值  ***

    private double conductivity,minConductivity = 0.381,maxConductivity = 0.9;
    private double turbidity,minTurbidity = 4.28,maxTurbidity = 243.035;
    private double salinity,minSalinity = 0.184,maxSalinity = 0.43;
    private double tds,minTds = 0.255,maxTds = 0.603;
    private double density,minDensity = 500.046,maxDensity = 1000.319;
    private double doSaturation,minDoSaturation = 35.129,maxDoSaturation = 138.515;
    private double tss,minTss = 0.024,maxTss = 0.972;
    private double chlorophylA,minChlorophylA = -0.744,maxChlorophylA = 124.168;
    private double phycocyanobilin,minPhycocyanobilin = 9.295,maxPhycocyanobilin = 113.817;
    private double voltage,minVoltage = 11.85,maxVoltage = 15.892;


    @RequestMapping(value = "/flowAndSpeedFlow",method = RequestMethod.GET)
    @ResponseBody
    public boolean InjectFlowAndSpeedFlow(){

        int time [] = {0,3,5,7,9,13,17};

        for (int month=1;month<=3;month++){  //月
            for (int i=1;i<=31;i++) {        //天
                if ((month == 2 && i > 28)){
                    continue;
                }
                for (int j=0;j<7;j++) {
                    avgSpeed[j] = DataUtil.getRandom(maxSpeed, minSpeed);
                    avgFlow[j] = DataUtil.getRandom(maxFlow, minFlow);
                }
                Arrays.sort(avgSpeed);//升序排序(内置)
                Arrays.sort(avgFlow);
                for (int j=0;j<7;j++){//每天7个时间段

                    Flow flow = new Flow();
                    flow.setStnId(stnId);
                    flow.setAvgSpeed(avgSpeed[j]);
                    flow.setAvgFlow(avgFlow[j]);
                    String cTime = "2018-0"+month+"-"+i+" "+time[j]+":00:00";//如："2018-02-5 3:00:00"
                    cTime = DateUtil.parseDate(DateUtil.parseString(cTime));//如："2018-02-05 03:00:00"
                    flow.setCollectionTime(cTime);
                    flowService.saveFlow(flow);
                }

                //注入speedflow数据：
                for (int j=0;j<7;j++){//每天7个时间段
                    for (int cam=0;cam<5;cam++) {//每个时间段5个相机的数据
                        camSpeed[j][cam] = DataUtil.getRandom(maxSpeed, minSpeed);
                    }
                    SpeedFlow speedFlow = new SpeedFlow();
                    speedFlow.setStnId(stnId);
                    speedFlow.setWaterSpeed1(camSpeed[j][0]);
                    speedFlow.setWaterSpeed2(camSpeed[j][1]);
                    speedFlow.setWaterSpeed3(camSpeed[j][2]);
                    speedFlow.setWaterSpeed4(camSpeed[j][3]);
                    speedFlow.setWaterSpeed5(camSpeed[j][4]);
                    speedFlow.setFilePath1("");
                    speedFlow.setFilePath2("");
                    speedFlow.setFilePath3("");
                    speedFlow.setFilePath4("");
                    speedFlow.setFilePath5("");

                    String sTime = "2018-0"+month+"-"+i+" "+time[j]+":00:00";//如："2018-02-5 3:00:00"
                    sTime = DateUtil.parseDate(DateUtil.parseString(sTime));//如："2018-02-05 03:00:00"
                    speedFlow.setCollectionTime(sTime);
                    speedFlow.setInstockTime(sTime);
                    speedFlowService.saveSpeedFlow(speedFlow);

                }


            }
        }
        return true;

    }

    @RequestMapping(value = "/waterQuality",method = RequestMethod.GET)
    @ResponseBody
    public boolean InjectWaterQuality(){

        for (int id=0;id<2;id++){//循环2次，分别生成id=1和id=7的数据

            for (int month=1;month<=3;month++) {  //月
                for (int i = 1; i <= 31; i++) {        //天
                    if ((month == 2 && i > 28)) {
                        continue;
                    }
                    for (int j=0;j<24;j++){
                        temper = DataUtil.getRandom(maxTemper, minTemper);
                        ph = DataUtil.getRandom(maxPh, minPh);
                        dissolvedOxygen = DataUtil.getRandom(maxDissolvedOxygen, minDissolvedOxygen);
                        redox = DataUtil.getRandom(maxRedox, minRedox);
                        conductivity = DataUtil.getRandom(maxConductivity,minConductivity);
                        turbidity = DataUtil.getRandom(maxTurbidity,minTurbidity);
                        salinity = DataUtil.getRandom(maxSalinity,minSalinity);
                        tds = DataUtil.getRandom(maxTds,minTds);
                        density =DataUtil.getRandom(maxDensity,minDensity);
                        doSaturation = DataUtil.getRandom(maxDoSaturation,minDoSaturation);
                        tss = DataUtil.getRandom(maxTss,minTss);
                        chlorophylA = DataUtil.getRandom(maxChlorophylA,minChlorophylA);
                        phycocyanobilin = DataUtil.getRandom(maxPhycocyanobilin,minPhycocyanobilin);
                        voltage = DataUtil.getRandom(maxVoltage,minVoltage);

                        String wqTime = getWqTime(month,i,j);
                        if (id == 0){
                            WaterQuality waterQuality =
                                    new WaterQuality(1,temper+"℃",ph,dissolvedOxygen,redox,0.0,conductivity,
                                            turbidity,0.0,wqTime,wqTime,"1","v","",salinity,tds,
                                            density,doSaturation,tss,chlorophylA,phycocyanobilin,0.0,0.0,voltage);
                            waterQualityService.saveWaterQuality(waterQuality);

                        }
                        else {
                            WaterQuality waterQuality =
                                    new WaterQuality(7,temper+"℃",ph,dissolvedOxygen,redox,0.0,conductivity,
                                            turbidity,0.0,wqTime,wqTime,"1","v","",salinity,tds,
                                            density,doSaturation,tss,chlorophylA,phycocyanobilin,0.0,0.0,voltage);
                            waterQualityService.saveWaterQuality(waterQuality);

                        }

                    }
                }
            }
        }

        return true;
    }

    public String getWqTime(int month,int day,int hour){
        String time = "2018-0"+month+"-"+day+" "+hour+":00:00";//如："2018-02-5 3:00:00"
        time = DateUtil.parseDate(DateUtil.parseString(time));//如："2018-02-05 00:00:00"
        return time;
    }
}
