//package com.hawksoft.platform.task;
//
//import com.hawksoft.platform.service.*;
//import com.hawksoft.platform.util.DateUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import us.codecraft.webmagic.Spider;
//
//import java.util.*;
//
//@Component
//public class GenerateDataSchedule {
//
//    @Autowired
//    public FlowService flowService;
//    @Autowired
//    public SpeedFlowService speedFlowService;
//    @Autowired
//    public WaterQualityService waterQualityService;
//    @Autowired
//    public WeatherWaveService weatherWaveService;
//
//    @Autowired
//    private ChangJiangRiverRobotProcessor processor;
//
//    Logger logger= LoggerFactory.getLogger(GenerateDataSchedule.class);
//
//    @Scheduled(cron = "0 0 14 * * ?")//每天的14点生成流量数据
//    void generateFlowAndSpeedFlow(){
//        if (DateUtil.parseDate(new Date()).compareTo("2018-04-13 00:00:00") > 0){
//            flowService.generateData(1,new Date());
//            //speedFlowService.generateData(1,new Date());//这张表的实时数据由采集程序发送来
//        }
//    }
//
//    @Scheduled(fixedRate = 300000)//每隔5分钟生成水质数据
//    void generateWaterQuality() throws Exception {
//        if (DateUtil.parseDate(new Date()).compareTo("2018-04-13 00:00:00") > 0) {
//            waterQualityService.generateData(1,new Date());
//            waterQualityService.generateData(6,new Date());
//            waterQualityService.generateData(7,new Date());
//        }
//    }
//
//    @Scheduled(fixedRate = 600000)//每隔10分钟生生成一次气象波浪数据
//    void generateWeatherWave(){
//        weatherWaveService.generateData(7,new Date());
//    }
//
//
//    //每隔1小时抓取长江水文数据
//    @Scheduled(cron = "0 0 */1 * * ?")
//    public void captureData(){
//        String url="http://www.cjh.com.cn/sq/data/sc.action?scid=cjh.waterdata";
//        List<String> urls = new ArrayList<String>();
//        urls.add(url);
//        Spider.create(processor)
//                .startUrls(urls)
//                .thread(6)
//                .runAsync();
//    }
//
//}
