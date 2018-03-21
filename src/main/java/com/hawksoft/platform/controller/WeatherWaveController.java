package com.hawksoft.platform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hawksoft.platform.entity.WeatherWave;
import com.hawksoft.platform.entity.Weather;
import com.hawksoft.platform.entity.Wave;
import com.hawksoft.platform.service.WeatherWaveService;
import com.hawksoft.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 气象、波浪共一张数据库表，但因为气象、波浪在前端分为两个模块，所以在这里查询也分成两类查询，
 * 这样在查询和传递数据时，提高效率
 *当然，在一些特殊情况下，客户要同时看气象、波浪数据，这样的数据才有实际意义，所以同时查询气象、波浪数据的方法也得提供
 *  气象、波浪模块
 *  处理气象、波浪模块的所有数据
 *  1、气象、波浪模块的入口都从这里进去
 *  2、气象、波浪模块的增删查改都从这里调用数据层接口处理
 *  @author linden (林全宝)
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/WeatherWave")
public class WeatherWaveController {

    @Autowired
    private WeatherWaveService weatherWaveService;
    private Map<String, Object> params = new HashMap<>();


    /**
     * 取最新的DAYS条实时气象、波浪
     * @return
     */
    @RequestMapping(value = "/lastWeatherWaveRecordsNum/{stnId}/{type}/{days}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWeatherWaveRecordsNum(@PathVariable("stnId") int stnId,
                                            @PathVariable("type") String type,
                                            @PathVariable("days") int days)
    {

        params.put("days",days);
        params.put("stnId",stnId);
        params.put("type",type);
        List<WeatherWave>  WeatherWaves = weatherWaveService.lastWeatherWaveRecordsNum(params);
        System.out.println("返回记录条数："+WeatherWaves.size());
        if (WeatherWaves.size()>0){
           // return JSON.toJSON(WeatherWaves).toString();
            return JSON.toJSONString(WeatherWaves, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取该参数的气象、波浪数据\"}";
    }

    /**
     * 取最新的DAYS条实时气象数据
     * @return
     */
    @RequestMapping(value = "/lastWeatherRecordsNum/{stnId}/{type}/{days}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWeatherRecordsNum(@PathVariable("stnId") int stnId,
                                        @PathVariable("type") String type,
                                        @PathVariable("days") int days)
    {

        params.put("days",days);
        params.put("stnId",stnId);
        params.put("type",type);
        List<Weather>  weathers = weatherWaveService.lastWeatherRecordsNum(params);
        System.out.println("返回记录条数："+weathers.size());
        if (weathers.size()>0){
            // return JSON.toJSON(WeatherWaves).toString();
            return JSON.toJSONString(weathers, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取该参数的气象数据\"}";
    }
    /**
     * 取最新的DAYS条实时波浪数据
     * @return
     */
    @RequestMapping(value = "/lastWaveRecordsNum/{stnId}/{type}/{days}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWaveRecordsNum(@PathVariable("stnId") int stnId,
                                     @PathVariable("type") String type,
                                     @PathVariable("days") int days)
    {

        params.put("days",days);
        params.put("stnId",stnId);
        params.put("type",type);
        List<Wave>  waves = weatherWaveService.lastWaveRecordsNum(params);
        System.out.println("返回记录条数："+waves.size());
        if (waves.size()>0){
            // return JSON.toJSON(waves).toString();
            return JSON.toJSONString(waves, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取该参数的波浪数据\"}";
    }

    /**
     * 通过ID获取该站点最新一条气象、波浪数据
     * @return
     */
    @RequestMapping(value = "/lastWeatherWave/{stnId}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWeatherWave(@PathVariable("stnId") int stnId){

        List<WeatherWave> weatherWaves= weatherWaveService.findLastWeatherWave(stnId);
        if (weatherWaves != null){
            //return JSONweatherWaves.toJSON(weatherWaves).toString();
            return JSON.toJSONString(weatherWaves, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取气象、波浪数据\"}";
    }

    /**
     * 通过ID获取该站点最新一条气象数据
     * @return
     */
    @RequestMapping(value = "/lastWeather/{stnId}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWeather(@PathVariable("stnId") int stnId){

        List<Weather> weathers= weatherWaveService.findLastWeather(stnId);
        if (weathers != null){
            //return JSON.toJSON(weathers).toString();
            return JSON.toJSONString(weathers, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取气象、波浪数据\"}";
    }

    /**
     * 通过ID获取该站点最新一条波浪数据
     * @return
     */
    @RequestMapping(value = "/lastWave/{stnId}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWave(@PathVariable("stnId") int stnId){

        List<Wave> waves = weatherWaveService.findLastWave(stnId);
        if (waves != null){
            //return JSON.toJSON(waves).toString();
            return JSON.toJSONString(waves, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取气象、波浪数据\"}";
    }

    /**
     * 取得该站点一段时间内的气象、波浪数据
     * @param startTime 当前时间
     * @param stnId 站点编号
     * @return
     */
    @RequestMapping(value = "/historyWeatherWave/{stnId}/{type}/{startTime}/{endTime}", method = RequestMethod.GET)
    @ResponseBody
    public String historyWeatherWave(@PathVariable("stnId") int stnId,
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
        List<WeatherWave>  weatherWaves = weatherWaveService.historyWeatherWave(params);
        System.out.println("返回记录条数："+weatherWaves.size());
         if (weatherWaves.size()>0){
            return JSON.toJSON(weatherWaves).toString();
            //return JSON.toJSONString(weatherWaves, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取该站点一段时间内的气象、波浪数据\"}";
    }

    /**
     * 取得该站点一段时间内的气象数据
     * @param startTime 当前时间
     * @param stnId 站点编号
     * @return
     */
    @RequestMapping(value = "/historyWeather/{stnId}/{type}/{startTime}/{endTime}", method = RequestMethod.GET)
    @ResponseBody
    public String historyWeather(@PathVariable("stnId") int stnId,
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
        List<Weather>  weathers = weatherWaveService.historyWeather(params);
        System.out.println("返回记录条数："+weathers.size());
        if (weathers.size()>0){
            return JSON.toJSON(weathers).toString();
           // return JSON.toJSONString(weathers, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取该站点一段时间内的气象、波浪数据\"}";
    }


    /**
     * 取得该站点一段时间内的波浪数据
     * @param startTime 当前时间
     * @param stnId 站点编号
     * @return
     */
    @RequestMapping(value = "/historyWave/{stnId}/{type}/{startTime}/{endTime}", method = RequestMethod.GET)
    @ResponseBody
    public String historyWave(@PathVariable("stnId") int stnId,
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
        List<Wave>  waves = weatherWaveService.historyWave(params);
        System.out.println("返回记录条数："+waves.size());
        if (waves.size()>0){
             return JSON.toJSON(waves).toString();
           // return JSON.toJSONString(waves, SerializerFeature.WriteMapNullValue);
        }
        return "{\"msg\" : \"暂时无法获取该站点一段时waves间内的波浪数据\"}";
    }


    /**
     * 取得一段时间内某个站点具体指标的气象、波浪信息
     * 目前这个方法还有问题,返回数据时,DOUBLE字段的值是空的话,会出现JSON.toJSON解释出错,需要完善一个JSON的解释
     * @return
     */
    @RequestMapping(value = "/queryWeatherWaveByType/{stnId}/{type}/{startTime}/{endTime}", method = RequestMethod.GET)
    @ResponseBody
    public String queryWeatherWaveByType(@PathVariable("stnId") int stnId,
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

        List<WeatherWave>  weatherWaves = weatherWaveService.queryWeatherWaveByType(params);
       // System.out.println("返回记录条数："+weatherWaves.size());
        if (weatherWaves.size()>0){
            return JSON.toJSON(weatherWaves).toString();
        }
        return "{\"msg\" : \"暂时无法获取该参数的气象、波浪信息数据\"}";
    }

    /**
     * 保存录入气象、波浪信息
     * @param WeatherWave 气象、波浪信息
     * @return 成功返回success，失败返回fail
     */
    @RequestMapping("/saveWeatherWave")
    @ResponseBody
    public String saveWeatherWave(WeatherWave WeatherWave){
        return (weatherWaveService.saveWeatherWave(WeatherWave) != 0) ? "success" : "fail";
    }


    /**
     * 更新录入气象、波浪信息
     * @param WeatherWave 气象、波浪信息
     * @return 成功返回success，失败返回fail
     */
    @RequestMapping("/updateWeatherWave")
    @ResponseBody
    public String updateWeatherWave(WeatherWave WeatherWave){
        return (weatherWaveService.updateWeatherWave(WeatherWave) != 0) ? "success" : "fail";
    }

    /**
     * 删除已录入气象、波浪信息
     * @param WeatherWave 气象、波浪信息
     * @return 成功返回success，失败返回fail
     */
    @RequestMapping("/deleteWeatherWave")
    @ResponseBody
    public String deleteWeatherWave(WeatherWave WeatherWave){
        return (weatherWaveService.deleteWeatherWave(WeatherWave) != 0) ? "success" : "fail";

    }
}
