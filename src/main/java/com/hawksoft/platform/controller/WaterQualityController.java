package com.hawksoft.platform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hawksoft.platform.entity.WaterQuality;
import com.hawksoft.platform.service.WaterQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.OpenJpaDialect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.hawksoft.platform.util.DateUtil;

/**
 *  水质模块
 *  处理水质模块的所有数据
 *  1、水质模块的入口都从这里进去
 *  2、水质模块的增删查改都从这里调用数据层接口处理
 *  @author linden (林全宝)
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/waterquality")
public class WaterQualityController {

    @Autowired
    private WaterQualityService waterQualityService;
    private Map<String, Object> params = new HashMap<>();

    /**
     * 取得某个指标的实时水质
     * 目前这个方法还有问题,返回数据时,DOUBLE字段的值是空的话,会出现JSON.toJSON解释出错,需要完善一个JSON的解释
     * @return
     */
    @RequestMapping(value = "/lastWaterQualityByType/{stnId}/{type}/{startTime}/{endTime}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWaterQualityByType(@PathVariable("stnId") int stnId,
                                         @PathVariable("type") String type,
                                         @PathVariable("startTime") String startTime,
                                         @PathVariable("endTime") String endTime) throws Exception{

        //处理两种日期格式。 yyyy-MM-dd;MM-dd-yyyy
        int splitLen =startTime.indexOf("-");
        if (splitLen<3) {
            startTime = DateUtil.transData(startTime);
            endTime = DateUtil.transData(endTime);
        }
        params.put("startTime", startTime);
        params.put("endTime",endTime);
        params.put("stnId",stnId);
        params.put("type",type);

        List<WaterQuality>  waterqualitys = waterQualityService.queryLastWaterQualityByStnIdType(params);
        System.out.println("返回记录条数："+waterqualitys.size());
        if (waterqualitys.size()>0){
           return JSON.toJSON(waterqualitys).toString();
          }
        return "{\"msg\" : \"暂时无法获取该参数的水质数据\"}";
    }

    /**
     * 取得某个站点某段时间内的实时水质
     * @return
     */
    @RequestMapping(value = "/lastWaterQualityByTime/{stnId}/{startTime}/{endTime}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWaterQualityByTime(@PathVariable("stnId") int stnId,
                                         @PathVariable("startTime") String startTime,
                                         @PathVariable("endTime") String endTime) throws Exception{

        //处理两种日期格式。 yyyy-MM-dd;MM-dd-yyyy
        int splitLen =startTime.indexOf("-");
        if (splitLen<3) {
            startTime = DateUtil.transData(startTime);
            endTime = DateUtil.transData(endTime);
        }
        params.put("startTime", startTime);
        params.put("endTime",endTime);
        params.put("stnId",stnId);
        //因为查询语句都一样，所以直接用queryLastWeekWaterQuality(map)来进行查询
        List<WaterQuality>  waterqualitys = waterQualityService.queryLastWeekWaterQuality(params);
        System.out.println("返回记录条数："+waterqualitys.size());
        if (waterqualitys.size()>0){
            return JSON.toJSONString(waterqualitys,SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取该参数的水质数据\"}";
    }

    /**
     * 取最新的DAYS条实时水质
     * @return
     */
    @RequestMapping(value = "/lastWaterQualityRecordsNum/{stnId}/{type}/{days}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWaterQualityRecordsNum(@PathVariable("stnId") int stnId,
                                             @PathVariable("type") String type,
                                             @PathVariable("days") int days)
    {

        params.put("days",days);
        params.put("stnId",stnId);
        params.put("type",type);
        List<WaterQuality>  waterqualitys = waterQualityService.lastWaterQualityRecordsNum(params);
        System.out.println("返回记录条数："+waterqualitys.size());
        if (waterqualitys.size()>0){
            return JSON.toJSON(waterqualitys).toString();
           // return JSON.toJSONString(waterqualitys, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取该参数的水质数据\"}";
    }


    /**
     * 实时水质
     * @return
     */
    @RequestMapping(value = "/lastWaterQuality/{stnId}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWaterQuality(@PathVariable("stnId") int stnId){

        WaterQuality waterquality = waterQualityService.queryLastWaterQualityByStnId(stnId);
        if (waterquality != null){
            //return JSON.toJSON(waterquality).toString();
            return JSON.toJSONString(waterquality, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取水质数据\"}";
    }

    /**
     * 上一周水质
     * @param startTime 当前时间
     * @param stnId 站点编号
     * @return
     */
    @RequestMapping(value = "/lastWeekWaterQuality/{stnId}/{startTime}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWeekWaterQuality(@PathVariable("stnId") int stnId,
                                       @PathVariable("startTime") String startTime) throws Exception{

        String[] time = DateUtil.getLastWeek(startTime);
        params.put("startTime", time[0]);
        params.put("endTime", time[1]);
        params.put("stnId",stnId);
        List<WaterQuality>  waterqualitys = waterQualityService.queryLastWeekWaterQuality(params);
        System.out.println("返回记录条数："+waterqualitys.size());
        if (waterqualitys.size()>0){
           // return JSON.toJSON(waterqualitys).toString();
            return JSON.toJSONString(waterqualitys, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取上一周水质数据\"}";
    }

    /**
     * 上一月水质
     * @param startTime 当前时间
     * @param stnId 站点编号
     * @return
     */
    @RequestMapping(value = "/lastMonthWaterQuality/{stnId}/{startTime}", method = RequestMethod.GET)
    @ResponseBody
    public String lastMonthWaterQuality(@PathVariable("stnId") int stnId,
                                       @PathVariable("startTime") String startTime) throws Exception{

        String[] time = DateUtil.getLastMonth(startTime);
        params.put("startTime", time[0]);
        params.put("endTime", time[1]);
        params.put("stnId",stnId);
        List<WaterQuality>  waterqualitys = waterQualityService.lastMonthWaterQuality(params);
        System.out.println("返回记录条数："+waterqualitys.size());
        if (waterqualitys.size()>0){
            //return JSON.toJSON(waterqualitys).toString();
            return JSON.toJSONString(waterqualitys, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取上一月水质数据\"}";
    }

    /**
     * 上一年水质
     * @param startTime 当前时间
     * @param stnId 站点编号
     * @return
     */
    @RequestMapping(value = "/lastYearWaterQuality/{stnId}/{startTime}", method = RequestMethod.GET)
    @ResponseBody
    public String lastYearWaterQuality(@PathVariable("stnId") int stnId,
                                        @PathVariable("startTime") String startTime) throws Exception{

        String[] time = DateUtil.getLastYear(startTime);
        params.put("startTime", time[0]);
        params.put("endTime", time[1]);
        params.put("stnId",stnId);
        List<WaterQuality>  waterqualitys = waterQualityService.lastYearWaterQuality(params);
        System.out.println("返回记录条数："+waterqualitys.size());
        if (waterqualitys.size()>0){
           // return JSON.toJSON(waterqualitys).toString();
            return JSON.toJSONString(waterqualitys, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取上一年水质数据\"}";
    }

    /**
     * 实时水质
     * @return
     */
    @RequestMapping(value = "/lastWaterQualityToView/{stnId}", method = RequestMethod.GET)
     public String lastWaterQualityToView(@PathVariable("stnId" ) int stnId,ModelMap mm){

        WaterQuality waterquality = waterQualityService.queryLastWaterQualityByStnId(stnId);
        if (waterquality != null){

           mm.put("id",waterquality.getId());
           mm.put("picPath",waterquality.getFilePath());

           mm.addAttribute("aa","ccc");
            mm.addAttribute("bb","dddd");
            mm.put("lin","linquanbao");

            return "realFlow";
        }
        return "{\"msg\" : \"暂时无法获取水质数据\"}";
    }

    /**
     * 保存录入水质信息
     * @param waterquality 水质信息
     * @return 成功返回success，失败返回fail
     */
    @RequestMapping("/saveWaterQuality")
    @ResponseBody
    public String saveWaterQuality(WaterQuality waterquality){
        return (waterQualityService.saveWaterQuality(waterquality) != 0) ? "success" : "fail";
    }

    /**
     * 更新录入水质信息
     * @param waterquality 水质信息
     * @return 成功返回success，失败返回fail
     */
    @RequestMapping("/updateWaterQuality")
    @ResponseBody
    public String updateWaterQuality(WaterQuality waterquality){
        return (waterQualityService.updateWaterQuality(waterquality) != 0) ? "success" : "fail";
    }

    /**
     * 删除已录入水质信息
     * @param waterquality 水质信息
     * @return 成功返回success，失败返回fail
     */
    @RequestMapping("/deleteWaterQuality")
    @ResponseBody
    public String deleteWaterQuality(WaterQuality waterquality){
        return (waterQualityService.deleteWaterQuality(waterquality) != 0) ? "success" : "fail";

    }

    /**
     * 从无人船中获取水质信息
     * @param stnId
     * @return 站点Id，GPS经纬度，水质参数（温度、PH）
     */
    @RequestMapping(value = "/UnmannedBoat/{stnId}",method = RequestMethod.GET)
    @ResponseBody()
    public String getWaterQualityFromUB(@PathVariable("stnId") int stnId){
        params.put("stnId",stnId);
        Map<String ,Object> info = waterQualityService.queryWaterQualityFromUB(params);
         if (info!=null){
             System.out.println("返回记录条数："+info.size());
             return JSON.toJSON(info).toString();
        }
        return "{\"msg\" : \"暂时无法获取指定时间最新的流量和水位数据\"}";
    }

    /**
     * 从无人船中获取水质历史信息
     * @param stnId
     * @return 站点Id，GPS经纬度，水质参数（温度、PH）
     */
    @RequestMapping(value = "/UnmannedBoatHisInfo/{stnId}",method = RequestMethod.GET)
    @ResponseBody()
    public String getWaterQualityFromUBHis(@PathVariable("stnId") int stnId){
        params.put("stnId",stnId);
        params.put("startTime","2018-04-13 10:00:00");
        params.put("endTime","2018-04-13 10:05:00");
        List<Map<String,Object>> infoList = waterQualityService.queryUBHistory(params);
        if (infoList.size()>0){
            System.out.println("获取到了无人船的历史水质数据");
            return JSON.toJSON(infoList).toString();
        }
        return "{\"msg\" : \"暂时无法获取指定时间最新的流量和水位数据\"}";
    }


        /**
         * 采集数据
         * @return
         */
    @RequestMapping(value = "/collectData/{stnId}",method = RequestMethod.GET)
    @ResponseBody
    public int collectData(@PathVariable ("stnId") int stnId) throws Exception {
        int res = waterQualityService.generateData(stnId,new Date());
        return res;
    }
}
