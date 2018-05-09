import com.hawksoft.platform.entity.*;
import com.hawksoft.platform.service.WaterQualityService;
import com.hawksoft.platform.service.WaterService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;


public class MethodTest {
    private WaterQualityService waterQualityService;
    private WaterService waterService;


    private ApplicationContext applicationContext;

    @Before
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        waterService = applicationContext.getBean(WaterService.class);
        waterQualityService = applicationContext.getBean(WaterQualityService.class);
    }

    /**
     * 获取历史水位
     */
    @Test
    public void hisWater(){
        Map<String, Object> map = new HashMap<>();
        map.put("stnId", 1);
        map.put("startTime", "2015-10-11");
        map.put("endTime", "2015-12-11");
        List<Water> waters = waterService.hisWater(map);
        for (Water water : waters){
            System.out.println(water);
        }
    }


    /**
     * 获取城市数据
     */
    @Test
    public void cityTest(){

        List<String> cities = waterService.queryCityData();
        for (String city : cities){
            System.err.println(city);
        }
    }

    /**
     * 获取最近水位
     */
    @Test
    public void lastWater(){
        Water water = waterService.queryLastWaterByStnId(1);
        System.err.println(water);
    }

    /**
     * 获取图片默认为最新的10张图
     */
    @Test
    public void findPicPath(){
        List<String> ss = waterService.queryPicPathByStnId(1);
        for (String s : ss){
            System.out.println(s);
        }
    }

    /**
     * 获取所有站点信息
     */
    @Test
    public void queryAllWaterStation(){
        Map<String, Object> map = new HashMap<>();
        map.put("stnType", 1);
        List<WaterStation> waterStations = waterService.queryAllWaterStation(map);
        for (WaterStation waterStation : waterStations){
            System.out.println(waterStation);
        }
    }

    @Test
    public void saveWater(){
        Water water = new Water();
        water.setStnId(2);
        water.setWaterLevel(new BigDecimal(1.2));
        water.setC_time("2017-11-12 15:52:42");
        water.setPicPath("/a/b/c");
        waterService.saveWater(water);
    }

    @Test
    public void saveCameraArgs(){
        CameraArgs args = new CameraArgs();
        args.setStnId(2);
        args.setCam_def(1);
        args.setExposure(500);
        args.setIntensity(200);
        args.setRR(1080);
        args.setTone_up(1);
        int i = waterService.saveCameraArgs(args);
        System.out.println(i);
    }

    @Test
    public void findCameraArgsByStnId(){
        List<CameraArgs>  cameraArgsList = waterService.findCameraArgsByStnId(1);
        for (CameraArgs cameraArgs : cameraArgsList){
            System.out.println(cameraArgs);
        }
    }

    @Test
    public void findCameraArgsNoDefault(){
        CameraArgs cameraArgs = waterService.findCameraArgsNoDefault(2);
        System.out.println(cameraArgs);
    }

    @Test
    public void updateWaterWarn(){
        WaterEarlyWarn waterEarlyWarn = new WaterEarlyWarn();
        waterEarlyWarn.setStnId(1);
        waterEarlyWarn.setWarning_level(4);
        waterEarlyWarn.setWarning_values("1;2;3;4");
        int i = waterService.updateWaterWarn(waterEarlyWarn);
        System.out.println(i);
    }

    @Test
    public void getWaterWarn(){
        WaterEarlyWarn waterEarlyWarn = waterService.getWaterWarn(2,2);
        System.out.println(waterEarlyWarn);
    }

    @Test
    public void transferDate(){
        String filename="2018_01_31_15_21_48_180515.jpeg";
        String collectionTime=filename.substring(0,10).replace("_","-")+" "+filename.substring(11,19).replace("_",":");
        System.out.println(collectionTime);
    }


    @Test
    public void testJSON(){
        Map<String, Object> params = new HashMap<>();
        params.put("startTime", "2017-01-01");
        params.put("endTime","2018-12-20");
        params.put("stnId","1");
        params.put("type","ph");

        List<WaterQuality>  waterqualitys = waterQualityService.queryLastWaterQualityByStnIdType(params);
        System.out.println("返回记录条数："+waterqualitys.size());
        WaterQuality wq = (WaterQuality)waterqualitys.get(0);
        System.out.println("返回数据为：\r\n"+wq.toString());
      //  WaterQuality wq2 = new WaterQuality();
       // wq2.setPh(7);
       // wq2.setId(1);
      //  wq2.setCollectionTime("2017-12-21 08:23:41.0");
       // System.out.println("wq2 返回数据为：\r\n"+wq2.toString());
       // String str =JSON.toJSON(wq2).toString();

       // System.out.println("***:JSON DATA:\r\n" + str);

        if (waterqualitys.size()>0) {
           String str  =JSON.toJSON(waterqualitys).toString();

            System.out.println("JSON DATA:\r\n" + str);
        }
    }

    @Test
    public void testStringToBinary(){
        StringToBinary sb = new StringToBinary();
        String str=sb.toBinary();
        sb.BinstrToStr(str);
    }
}
