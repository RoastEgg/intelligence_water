package com.hawksoft.platform.task;

import com.alibaba.fastjson.JSON;
import com.hawksoft.platform.controller.WaterController;
import com.hawksoft.platform.entity.Water;
import com.hawksoft.platform.service.*;
import com.hawksoft.platform.socket.SocketUtils;
import com.hawksoft.platform.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class GenerateDataSchedule {

    @Autowired
    public FlowService flowService;
    @Autowired
    public SpeedFlowService speedFlowService;
    @Autowired
    public WaterQualityService waterQualityService;
    @Autowired
    public WeatherWaveService weatherWaveService;
    @Autowired
    private WaterRobotProcessor processor;

    @Autowired
    private WaterStationService waterStationService;
    @Autowired
    private WaterService waterService;

    Logger logger= LoggerFactory.getLogger(GenerateDataSchedule.class);

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
//    //每隔1小时抓取水文数据
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



//    /**
//     * 根据站点ID获取相机拍摄水位信息
//     */
//    @Scheduled(fixedRate = 5000)
//    public String RealTimeAcquisitionData() {
//        int stnId=1;
//        String realTimeAcquisitionData="";
//        //通过站点ID获取站点名称
//        String stnCode = waterStationService.queryCodeById(stnId);
//        if (stnCode.isEmpty()) {
//            logger.error("该站点不支持实时采集");
//            return "该站点不支持实时采集";
//        }
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //获取发送请求时间
//        Date sendDate=new Date();
//        String sendTime=simpleDateFormat.format(sendDate);
//        logger.info("startTime:"+sendTime);
//
//        Socket so = SocketUtils.findSocket(stnCode);
//        logger.info("so-------"+so);
//        if (so != null && so.isConnected()) {
//            // build message and send to RTU
//            byte[] message = new byte[3];
//            //实时采集指令
//            message[0] = (byte)0x0100;
//            //站点ID
//            message[1] = (byte)stnId;
//            //采集水位
//            message[2] = (byte)0x0002;
//
//            try {
//                SocketUtils.sendMessage(so, message);
//                int i=0;
//                do {
//                    i++;
//                    //获取发送完时间
//                    Date receiveDate=new Date();
//                    String receiveTime=simpleDateFormat.format(receiveDate);
//                    logger.info("endTime:"+receiveTime);
//
//                    Map<String,Object> timeMap=new HashMap<>();
//                    timeMap.put("startTime",sendTime);
//                    timeMap.put("endTime",receiveTime);
//                    timeMap.put("stnId",stnId);
//                    List<Water> waterList=waterService.hisWater(timeMap);
//                    if (waterList.size()>0) {
//                        realTimeAcquisitionData= JSON.toJSON(waterList.get(0)).toString();
//                        break;
//                    }
//                    Thread.sleep(100);
//                } while (i<=600);
//            } catch (IOException e) {
//                logger.error("Send scoket message error");
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                logger.error("Thread running error");
//                e.printStackTrace();
//            }
//        } else {
//            logger.error("The connection with RTU was lost!");
//            return "The connection with RTU was lost!";
//        }
//
//        if(realTimeAcquisitionData.equals("")){
//            logger.error("RealTimeAcquisitionData is failing");
//            return "RealTimeAcquisitionData is failing";
//        }
//        return realTimeAcquisitionData;
//    }
}
