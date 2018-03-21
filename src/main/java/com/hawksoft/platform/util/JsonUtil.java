package com.hawksoft.platform.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hawksoft.platform.constant.WaterConstant;
import com.hawksoft.platform.entity.*;

import java.io.IOException;
import java.math.BigDecimal;


public class JsonUtil {

    /**
     * 解析传输的json
     * @param json json数据
     * @param <T> 返回对象
     * @return
     */
    public static <T>Object parseJson(String json){

        System.out.println(json);
        JSONObject object = JSON.parseObject(json);
        String type = object.getString(WaterConstant.COMMON_JSON_TYPE);
        JSONArray data = object.getJSONArray(WaterConstant.COMMON_JSON_DATA);
        JSONObject oo =(JSONObject) data.get(0);

        if ("water".equals(type)) {
            BigDecimal waterLevel = oo.getBigDecimal("waterLevel");
            int stnId = oo.getInteger("stnId");
            String c_time = oo.getString("c_time");

            String picPath = ImgHandler.imgFilePathAndName("water",stnId+"" );
            Water water = new Water();
            water.setStnId(stnId);
            water.setWaterLevel(waterLevel);
            water.setC_time(c_time);
            water.setPicPath(picPath);

            return water;
        }else if ("speedflow".equals(type)){
            SpeedFlow speedFlow = new SpeedFlow();

            int stnId = oo.getInteger("stnId");
            speedFlow.setStnId(stnId);
            Double waterSpeed1 = oo.getDouble("waterSpeed1");
            speedFlow.setWaterSpeed1(waterSpeed1 != null ? waterSpeed1 : 0);
            Double waterSpeed2 = oo.getDouble("waterSpeed2");
            speedFlow.setWaterSpeed2(waterSpeed2 != null ? waterSpeed2 : 0);
            Double waterSpeed3 = oo.getDouble("waterSpeed3");
            speedFlow.setWaterSpeed3(waterSpeed3 != null ? waterSpeed3 : 0);
            Double waterSpeed4 = oo.getDouble("waterSpeed4");
            speedFlow.setWaterSpeed4(waterSpeed4 != null ? waterSpeed4 : 0);
            Double waterSpeed5 = oo.getDouble("waterSpeed5");
            speedFlow.setWaterSpeed5(waterSpeed5 != null ? waterSpeed5 : 0);
            Double waterFlow1 = oo.getDouble("waterFlow1");
            speedFlow.setWaterFlow1(waterFlow1 != null ? waterFlow1 : 0);
            Double waterFlow2 = oo.getDouble("waterFlow2");
            speedFlow.setWaterFlow2(waterFlow2 != null ? waterFlow2 : 0);
            Double waterFlow3 = oo.getDouble("waterFlow3");
            speedFlow.setWaterFlow3(waterFlow3 != null ? waterFlow3 : 0);
            Double waterFlow4 = oo.getDouble("waterFlow4");
            speedFlow.setWaterFlow4(waterFlow4 != null ? waterFlow4 : 0);
            Double waterFlow5 = oo.getDouble("waterFlow5");
            speedFlow.setWaterFlow5(waterFlow5 != null ? waterFlow5 : 0);
            Integer state1 = oo.getInteger("state1");
            speedFlow.setState1(state1 != null ? state1 : 0);
            Integer state2 = oo.getInteger("state2");
            speedFlow.setState2(state2 != null ? state2 : 0);
            Integer state3 = oo.getInteger("state3");
            speedFlow.setState3(state3 != null ? state3 : 0);
            Integer state4 = oo.getInteger("state4");
            speedFlow.setState4(state4 != null ? state4 : 0);
            Integer state5 = oo.getInteger("state5");
            speedFlow.setState5(state5 != null ? state5 : 0);
            String filePath1 = oo.getString("filePath1");
            speedFlow.setFilePath1(filePath1);
            String filePath2 = oo.getString("filePath2");
            speedFlow.setFilePath2(filePath2);
            String filePath3 = oo.getString("filePath3");
            speedFlow.setFilePath3(filePath3);
            String filePath4 = oo.getString("filePath4");
            speedFlow.setFilePath4(filePath4);
            String filePath5 = oo.getString("filePath5");
            speedFlow.setFilePath5(filePath5);
            String picOrVideo = oo.getString("picOrVideo");
            speedFlow.setPicOrVideo(picOrVideo);
            String collectionTime = oo.getString("collectionTime");


            //TODO:路径的获取
            String picFilePath = ImgHandler.imgFilePathAndName("speedflow",stnId+"");
            String videoFilePath = ImgHandler.imgFilePathAndName("speedflow",stnId + "");


            speedFlow.setCollectionTime(collectionTime);

            return speedFlow;
        }else if("waterQuality".equals(type)){
            //String json = "json{\"type\": \"waterQuality\",
            //                    \"data\": [{\"stnId\": 1, \"temperature\": 1.3,\"ph\": 1.1,
            //                    \"DO\": 1.2,\"conductivity\": 1.3,\"turbidity\": 1.4,\"NH3\": 1.5,
            //                    \"state\": \"1\",\"collectionTime\": \"2017-1-1 19:21:1\"，
            //					\"redox\":1.5,\"dissolvedOxygen\":1.6,\"transparency\":1.7}]}";
            int stnId = oo.getInteger("stnId");
            String temperature = oo.getDouble("temperature") + "℃";
            Double Ph = oo.getDouble("Ph");
            Double dissolvedOxygen = oo.getDouble("dissolvedOxygen");
            Double conductivity = oo.getDouble("conductivity");
            Double turbidity = oo.getDouble("turbidity");
            Double NH3 = oo.getDouble("NH3");
            Double redox = oo.getDouble("redox");
            Double transparency = oo.getDouble("transparency");
            String collectionTime = oo.getString("collectionTime");

            //TODO:路径的获取
            String picFilePath = ImgHandler.imgFilePathAndName("waterquality",stnId + "");
            //String  videoFilePath =

            WaterQuality waterQuality = new WaterQuality();
            waterQuality.setStnId(stnId);
            waterQuality.setDissolvedOxygen(dissolvedOxygen);
            waterQuality.setCollectionTime(collectionTime);
            waterQuality.setConductivity(conductivity);
            waterQuality.setNH3(NH3);
            waterQuality.setTurbidity(turbidity);
            waterQuality.setPh(Ph);
            waterQuality.setTemperature(temperature);
            waterQuality.setRedox(redox);
            waterQuality.setTransparency(transparency);
            return waterQuality;

        }else if("floating".equals(type)){
            int stnId = oo.getInteger("stnId");
            byte[] picOrigin = oo.getBytes("picOrigin");
            byte[] picResult = oo.getBytes("picResult");
            String collectionTime = oo.getString("collectionTime");
            int camIndex = oo.getInteger("camIndex");

            FloatingMatter floatingMatter = new FloatingMatter();
            floatingMatter.setStnId(stnId);
            floatingMatter.setCamIndex(camIndex);
            floatingMatter.setCollectionTime(collectionTime);
            //floatingMatter.setPicOrigin(picOrigin);
            //floatingMatter.setPicResult(picResult);
            return floatingMatter;

        }


        return null;
    }

}
