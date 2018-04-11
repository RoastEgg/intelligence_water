package com.hawksoft.platform.task;

import com.hawksoft.platform.service.FlowService;
import com.hawksoft.platform.service.SpeedFlowService;
import com.hawksoft.platform.service.WaterQualityService;
import com.hawksoft.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GenerateDataSchedule {

    @Autowired
    public FlowService flowService;
    @Autowired
    public SpeedFlowService speedFlowService;
    @Autowired
    public WaterQualityService waterQualityService;

    @Scheduled(cron = "0 0 0,3,5,7,9,13,17 * * ?")//每天的0点、3点、5点、7点、9点、13点、17点生成流量数据
    void generateFlowAndSpeedFlow(){
        if (DateUtil.parseDate(new Date()).compareTo("2018-04-05 00:00:00") > 0){
            flowService.generateData();
            speedFlowService.generateData();
        }
    }

    @Scheduled(fixedRate = 300000)//每隔5分钟生成水质数据
    void generateWaterQuality(){
        if (DateUtil.parseDate(new Date()).compareTo("2018-04-05 00:00:00") > 0) {
            waterQualityService.generateData();
        }
    }

//    @Scheduled(fixedRate = 5000)//每隔5秒钟测一次
//    void test(){
//
//            System.out.println("output data");
//    }
}
