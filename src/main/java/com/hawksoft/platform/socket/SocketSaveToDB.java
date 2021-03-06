package com.hawksoft.platform.socket;


import com.hawksoft.platform.constant.WaterConstant;
import com.hawksoft.platform.entity.*;
import com.hawksoft.platform.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class SocketSaveToDB {
    private static ApplicationContext applicationContext;

    private static WaterService waterService;
    private static WaterQualityService waterQualityService;
    private static UnmannedBoatService unmannedBoatService;
    private static SpeedFlowService speedFlowService;
    private static FloatingMatterService floatingMatterService;
    private static double RiverArea1=15.23;//摄像头1的断面面积
    private static double RiverArea2=14.31;//摄像头2的断面面积
    private static double RiverArea3=16.43;//摄像头3的断面面积
    private static double RiverArea4=14.01;//摄像头4的断面面积
    private static double RiverArea5=15.78;//摄像头5的断面面积

    static Logger logger= LoggerFactory.getLogger(SocketSaveToDB.class);

    public  static void initEnv() {
        if (applicationContext == null)
        {
            applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        }

        if (applicationContext != null) {
            if (waterService == null) {
                waterService = applicationContext.getBean(WaterService.class);
            }

            if (waterQualityService == null) {
                waterQualityService = applicationContext.getBean(WaterQualityService.class);
            }

            if (speedFlowService == null) {
                speedFlowService = applicationContext.getBean(SpeedFlowService.class);
            }
            if (unmannedBoatService == null) {
                unmannedBoatService =applicationContext.getBean(UnmannedBoatService.class);
            }
            if (floatingMatterService == null) {
                floatingMatterService = applicationContext.getBean(FloatingMatterService.class);
            }
        }
    }

    /**
     * 保存采集的水位数据
     * @param stndId
     * @param picPath
     * @param collectionValue
     * @param collectionTime
     */
    public static void doReceiveSW(int stndId,String picPath,int collectionValue,String collectionTime) {
        Water water = new Water();
        water.setStnId(stndId);
        water.setWaterLevel(new BigDecimal(collectionValue/1000.0));//江总传输时乘以1000，这里要除以1000；
        water.setC_time(collectionTime);
        water.setPicPath(picPath);

        logger.info("water---"+water);

        if (waterService == null) {
            initEnv();
        }

        if (waterService != null) {
            waterService.saveWater(water);
            logger.info("saveWater success");
        } else {
            System.out.println("Init Water Service Failed!");
        }
    }

    /**
     * 保存采集的流速流量数据
     * @param stndId
     * @param picPath
     * @param cameraValue
     * @param collectionValue
     * @param collectionTime
     */
    public static void doReceiveLL(int stndId, String picPath,int cameraValue,int collectionValue,String collectionTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("stnId",stndId);
        map.put("collectionTime",collectionTime);

        if (speedFlowService == null) {
            initEnv();
        }

        if (speedFlowService != null) {
            //通过站点ID和采集时间取得唯一一条获取站点的流速流量记录
            SpeedFlow speedFlow = speedFlowService.findSpeedFlowByStdIdTime(map);

            boolean update = false;
            //如果数据库中没有数据，新记录
            if(speedFlow == null)
            {
                speedFlow = new SpeedFlow();
                speedFlow.setStnId(stndId);//站点ID
                speedFlow.setCollectionTime(collectionTime);//采集时间
            } else {
                update = true;
            }

            switch (cameraValue)
            {
                case 1:
                    speedFlow.setFilePath1(picPath);//文件路径
                    speedFlow.setState1(1);//摄像机状态，1为正常
                    speedFlow.setWaterSpeed1( Double.parseDouble(""+(collectionValue/1000.0)));//江总传输时乘以1000，这里要除以1000；
                    speedFlow.setWaterFlow1(RiverArea1*(collectionValue/1000));//流量
                    break;
                case 2:
                    speedFlow.setFilePath2(picPath);//文件路径
                    speedFlow.setState2(1);//摄像机状态，1为正常
                    speedFlow.setWaterSpeed2( Double.parseDouble(""+(collectionValue/1000.0)));//江总传输时乘以1000，这里要除以1000；
                    speedFlow.setWaterFlow2(RiverArea2*(collectionValue/1000));//流量
                    break;
                case 3:
                    speedFlow.setFilePath3(picPath);//文件路径
                    speedFlow.setState3(1);//摄像机状态，1为正常
                    speedFlow.setWaterSpeed3( Double.parseDouble(""+(collectionValue/1000.0)));//江总传输时乘以1000，这里要除以1000；
                    speedFlow.setWaterFlow3(RiverArea3*(collectionValue/1000));//流量
                    break;
                case 4:
                    speedFlow.setFilePath4(picPath);//文件路径
                    speedFlow.setState4(1);//摄像机状态，1为正常
                    speedFlow.setWaterSpeed4( Double.parseDouble(""+(collectionValue/1000.0)));//江总传输时乘以1000，这里要除以1000；
                    speedFlow.setWaterFlow4(RiverArea4*(collectionValue/1000));//流量
                    break;
                case 5:
                    speedFlow.setFilePath5(picPath);//文件路径
                    speedFlow.setState5(1);//摄像机状态，1为正常
                    speedFlow.setWaterSpeed5( Double.parseDouble(""+(collectionValue/1000.0)));//江总传输时乘以1000，这里要除以1000；
                    speedFlow.setWaterFlow5(RiverArea5*(collectionValue/1000));//流量
                    break;
            }

            if (update) {
                logger.info("update speedFlow---"+speedFlow);
                speedFlowService.updateSpeedFlow(speedFlow);
                logger.info("update success");
            } else {
                logger.info("save speedFlow---"+speedFlow);
                speedFlowService.saveSpeedFlow(speedFlow);
                logger.info("save success");
            }
        } else {
            System.out.println("Init Speed Flow Service Failed!");
        }
    }

    /**
     *
     *保存采集的漂浮物数据
     * @param stnId
     * @param picPath
     * @param cameraValue
     * @param collectionTime
     */
    public static void doReceiveFOR(int stnId, String picPath,int cameraValue,String collectionTime){
        FloatingMatter floatingMatter = new FloatingMatter();
        floatingMatter.setStnId(stnId);
        floatingMatter.setCamIndex(cameraValue);
        floatingMatter.setFilePathResult(picPath);
        floatingMatter.setCollectionTime(collectionTime);
        if (floatingMatterService==null){
            initEnv();
        }
        if (floatingMatterService!=null){
            floatingMatterService.saveFloatingMatter(floatingMatter);
        } else {
            System.out.println("Init floatingMatter Service Failed!");
        }
    }


    //取得河流的断面面积
    public static double getRiverArea() {
        return RiverArea1 + RiverArea2 + RiverArea3 + RiverArea4 + RiverArea5;
    }

    //取得河流的平均流速
    public static double getRiverAvgSpeed(SpeedFlow speedFlow) {
        return (speedFlow.getWaterSpeed1() + speedFlow.getWaterSpeed2()  + speedFlow.getWaterSpeed3()  + speedFlow.getWaterSpeed4()  + speedFlow.getWaterSpeed5())/5 ;
    }

    //同一时间、站点ID的所有摄像头的流速数据是否已传输完成
    public static boolean isCompleteSpeed(SpeedFlow speedFlow) {
        return (speedFlow.getState1() ==1 && speedFlow.getState2() ==1 && speedFlow.getState3() ==1  && speedFlow.getState4() ==1 && speedFlow.getState5() ==1 ) ;
    }

    public static void saveWQ(RtuHeaderInfo headerInfo, String wqData) {
        WaterQuality quality = new WaterQuality();
        String[] wqList = wqData.split(":");
        for (String wq : wqList) {
            String[] temp = wq.split("=");
            switch (WaterConstant.WQ_PARAM.values()[Integer.parseInt(temp[0]) - 1]) {
                case PH:
                    quality.setPh(Double.parseDouble(temp[1]));
                    break;
                case TP:
                    quality.setTemperature(temp[1]);
                    break;
                case DO:
                    quality.setDissolvedOxygen(Double.parseDouble(temp[1]));
                    break;
                case CT:
                    quality.setConductivity(Double.parseDouble(temp[1]));
                    break;
                case TU:
                    quality.setTurbidity(Double.parseDouble(temp[1]));
                    break;
                case ORP:
                    quality.setRedox(Double.parseDouble(temp[1]));
                    break;
                case BOD:
                    break;
                case COD:
                    break;
                case OPA:
                    quality.setTransparency(Double.parseDouble(temp[1]));
                    break;
            }
        }

        quality.setStnId(headerInfo.getSiteNo());
        quality.setCollectionTime(headerInfo.getStime().replace("_", ":"));
        quality.setInstockTime(quality.getCollectionTime());
        quality.setState("1");

        if (waterQualityService == null) {
            initEnv();
        }

        if (waterQualityService != null) {
            System.out.println(">>>>>>>" + quality.toString());
            waterQualityService.saveWaterQuality(quality);
        } else {
            System.out.println("Init Water Quality Service Failed!");
        }
    }

    public static void saveUB(RtuHeaderInfo headerInfo, String ubData){
        UnmannedBoat unmannedBoat = new UnmannedBoat();
        String [] ubList  = ubData.split(",");//发送的报文用","隔开
        unmannedBoat.setCourse(Double.parseDouble(ubList[0]));
        unmannedBoat.setOriginalSpeed(Double.parseDouble(ubList[1]));
        unmannedBoat.setTrailAngle(Double.parseDouble(ubList[2]));
        unmannedBoat.setManualCourse(Double.parseDouble(ubList[3]));
        unmannedBoat.setAutoCourse(Double.parseDouble(ubList[4]));
        unmannedBoat.setSpacing(Double.parseDouble(ubList[5]));
        unmannedBoat.setYawDistance(Double.parseDouble(ubList[6]));
        unmannedBoat.setLeftOutput(Double.parseDouble(ubList[7]));
        unmannedBoat.setRightOutput(Double.parseDouble(ubList[8]));
        unmannedBoat.setAccelerator(Double.parseDouble(ubList[9]));
//        unmannedBoat.setCourseChangeRate(Double.parseDouble(ubList[12]));
//        unmannedBoat.setYawChangeRate(Double.parseDouble(ubList[13]));
        unmannedBoat.setSailingSpeed(Double.parseDouble(ubList[10]));
        unmannedBoat.setTargetSpeed(Double.parseDouble(ubList[11]));
        unmannedBoat.setLongitude(Double.parseDouble(ubList[12]));
        unmannedBoat.setLatitude(Double.parseDouble(ubList[13]));
        unmannedBoat.setObstacleDistance(Double.parseDouble(ubList[14]));
        unmannedBoat.setObstacleAngle(Double.parseDouble(ubList[15]));

        unmannedBoat.setNumber(headerInfo.getUbNo());
        unmannedBoat.setTime(headerInfo.getStime());

        if (unmannedBoatService == null){
            initEnv();
        }

        if (unmannedBoatService != null){
            System.out.println(">>>>>>>" + unmannedBoat.toString());
            unmannedBoatService.saveUnmannedBoat(unmannedBoat);
        }
        else {
            System.out.println("Init unmanned Boat Service Failed!");
        }
    }

    public static void saveWQFromUB(int ph,int temper){
        WaterQuality wq = new WaterQuality();
        wq.setPh(ph*1.0/1000);
        wq.setTemperature(temper*1.0/1000+"");
        if (waterQualityService == null) {
            initEnv();
        }

        if (waterQualityService != null) {
            System.out.println(">>>>>>>" + wq.toString());
            waterQualityService.saveWaterQuality(wq);
        } else {
            System.out.println("Init Water Quality Service Failed!");
        }
    }
    public static void save(RtuHeaderInfo headerInfo, String path, String colTime) {
        switch (headerInfo.getType()) {
            case 1: // Water Level
                doReceiveSW(headerInfo.getSiteNo(), path, headerInfo.getValue(), colTime);
                break;

            case 2: // Flux
                doReceiveLL(headerInfo.getSiteNo(), path, headerInfo.getCameraId(), headerInfo.getValue(), colTime);
                break;
            case 5://floating matter
                doReceiveFOR(headerInfo.getSiteNo(),path,headerInfo.getCameraId(),colTime);
        }
    }
}