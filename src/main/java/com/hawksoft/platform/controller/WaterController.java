package com.hawksoft.platform.controller;

import com.alibaba.fastjson.JSON;
import com.hawksoft.platform.VO.Water_queryLastAndHisVO;
import com.hawksoft.platform.entity.CameraArgs;
import com.hawksoft.platform.entity.Water;
import com.hawksoft.platform.entity.WaterEarlyWarn;
import com.hawksoft.platform.entity.WaterStation;
import com.hawksoft.platform.service.WaterService;
import com.hawksoft.platform.service.WaterStationService;
import com.hawksoft.platform.socket.SocketUtils;
import com.hawksoft.platform.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  水位模块
 *  处理水位模块的所有数据
 *  1、水位模块的入口都从这里进去
 *  2、水位模块的增删查改都从这里调用数据层接口处理
 *  @author 陈小吕 v1.0
 *  @author linden (林全宝) v2.0
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/water")
public class WaterController {
    Logger logger= LoggerFactory.getLogger(WaterController.class);
    @Autowired
    private WaterService waterService;
    @Autowired
    private WaterStationService waterStationService;
    private Map<String, Object> params = new HashMap<>();

    /**
     * 获取根据站点ｉｄ查询的所有水位信息
     * @param stnId　站点ｉｄ
     * @param startTime　开始时间
     * @param endTime　结束时间
     * @param model
     * @return
     */
    @RequestMapping("/getAll/{stnId}/{startTime}/{endTime}")
    public String getAll(@PathVariable("stnId") int stnId,
                         @PathVariable("startTime") String startTime,
                         @PathVariable("endTime") String endTime,
                         Model model){

        Map<String, Object> map = new HashMap<>();
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("stnId", stnId);

        List<Water> waters = waterService.hisWater(params);
        Water water = waterService.queryLastWaterByStnId(stnId);
        List<String> cities = waterService.queryCityData();
        List<WaterStation> waterStations = waterService.queryAllWaterStation(params);
        List<String> picPaths = waterService.queryPicPathByStnId(stnId);

        map.put("cities", JSON.toJSON(cities).toString());
        map.put("stations", JSON.toJSON(waterStations).toString());

        if (waters.size() != 0){
            map.put("waters", JSON.toJSON(waters).toString());
        }else {
            map.put("waters", null);
        }
        if (water != null){
            map.put("water", JSON.toJSON(water).toString());
        }else {
            map.put("water", null);
        }
        if (picPaths.size() != 0){
            map.put("picPaths", JSON.toJSON(picPaths).toString());
        }else {
            map.put("picPaths", null);
        }
        model.addAllAttributes(map);
        return "";
    }

    /**
     * 实时水位
     * @return
     */
    @RequestMapping(value = "/lastWater/{stnId}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWater(@PathVariable("stnId") int stnId){

        Water water = waterService.queryLastWaterByStnId(stnId);
        if (water != null){
            return JSON.toJSON(water).toString();
        }

        return "{\"msg\" : \"暂时无法获取水位数据\"}";
    }

    /**
     * 取最新的DAYS条实时水位
     * @return
     */
    @RequestMapping(value = "/lastWaterRecordsNum/{stnId}/{days}", method = RequestMethod.GET)
    @ResponseBody
    public String lastWaterRecordsNum(@PathVariable("stnId") int stnId,
                                      @PathVariable("days") int days){
        params.put("stnId",stnId);
        params.put("days",days);
        List<Water> waters = waterService.lastWaterRecordsNum(params);
        if (waters.size() == 0){
            return "{\"msg\": \"对不起,需要的数据不存在\"}";
        }
        return JSON.toJSON(waters).toString();
    }

    /**
     * 历史水位
     * @param startTime
     * @param endTime
     * @param stnId
     * @return
     */
    @RequestMapping("/hisWater/{stnId}/{startTime}/{endTime}")
    @ResponseBody
    public String hisWater(@PathVariable("stnId") int stnId,
                           @PathVariable("startTime") String startTime,
                           @PathVariable("endTime") String endTime) throws Exception
    {
        params.put("startTime", getCorrectTime(startTime));
        params.put("endTime", getCorrectTime(endTime));
        params.put("stnId", stnId);

        List<Water> waters = waterService.hisWater(params);
        if (waters.size() == 0){
            return "{\"msg\": \"对不起,需要的数据不存在\"}";
        }

        return JSON.toJSON(waters).toString();
    }

    /**
     * 获取当前和历史的水位、图片信息
     * @param stnId
     * @param startTime
     * @param endTime
     * @return 站点名称、当前水位、历史水位最小值、历史水位最大值、当前图片，历史图片List
     * @throws Exception
     */
    @RequestMapping(value = "/historicalWaterLevel/{stnId}/{startTime}/{endTime}",method = RequestMethod.GET)
    @ResponseBody
    public Water_queryLastAndHisVO historicalWaterLevel(@PathVariable("stnId") int stnId,
                                       @PathVariable("startTime") String startTime,
                                       @PathVariable("endTime") String endTime) throws  Exception{
        params.put("startTime", getCorrectTime(startTime));
        params.put("endTime", getCorrectTime(endTime));
        params.put("stnId", stnId);

        Water_queryLastAndHisVO w_qVO = waterService.queryLastAndHis(params);
        //System.out.println("pic info list:"+JSON.toJSON(w_qVO.getPicList()).toString());
        return  w_qVO;
    }

    /**
     * 根据某一时刻，获取这个时刻的水位图片
     * @param currTime
     * @param stnId
     * @return
     */
    @RequestMapping("/hisWaterLevelPic/{stnId}/{curr_time}")
    @ResponseBody
    public String hisWaterLevelPic(@PathVariable("stnId") int stnId,
                           @PathVariable("curr_time") String currTime) throws Exception
    {
        params.put("currTime", getCorrectTime(currTime));
        params.put("stnId", stnId);
        Water water = waterService.hisWaterLevelPic(params);

        if (water == null){
            return "{\"msg\": \"对不起,需要的数据不存在\"}";
        }

        return JSON.toJSON(water).toString();
    }


    /**
     * 更新自定义水位预警值
     * @param waterEarlyWarn 水位预警值
     * @return 成功返回success，失败返回fail
     */
    @RequestMapping("/updateWaterWarn")
    @ResponseBody
    public String updateWaterWarn(WaterEarlyWarn waterEarlyWarn){
        return (waterService.updateWaterWarn(waterEarlyWarn) != 0) ? "success" : "fail";
    }

    /**
     * 获取水位预警值数据
     * @param stnId 站点id
     * @param type 请求类型 ，1代表默认的预警值，2代表自定义的预警值
     * @return 成功返回预警值的json，失败则返回错误信息
     */
    @RequestMapping("/getWaterWarn/{stnId}/{type}")
    @ResponseBody
    public String getWaterWarn(@PathVariable("stnId") int stnId,@PathVariable("type") int type){

        WaterEarlyWarn waterEarlyWarn = waterService.getWaterWarn(stnId,type);
        if (waterEarlyWarn != null){
            return JSON.toJSON(waterEarlyWarn).toString();
        }
        return "{\"msg\": \"对不起,暂时没有数据\"}";
    }

    //TODO:以下是对相机操作 与其他模块重复
    /**
     * 设置相机参数
     * @param args 相机参数
     * @return 返回json
     */
    @RequestMapping("/setCameraArgs")
    @ResponseBody
    public String setCameraArgs(CameraArgs args){

        CameraArgs cameraArgs = waterService.findCameraArgsNoDefaultById(args.getId());

        if (cameraArgs != null){
            waterService.deleteCameraArgsNoDefaultById(cameraArgs.getId());
        }

        args.setCam_def(2);
        return (waterService.saveCameraArgs(args) != 0 )? "success" : "fail";
    }

    /**
     * 根据站点id获取相机参数
     * @param stnId 站点id
     * @param response
     * @return 相机参数的json数据
     */
    @RequestMapping("/getCameraArgs/{stnId}")
    @ResponseBody
    public String getCameraArgs(@PathVariable("stnId") int stnId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<CameraArgs> cameraArgsList = waterService.findCameraArgsByStnId(stnId);
        if (cameraArgsList.size() == 1){
            return JSON.toJSON(cameraArgsList).toString();
        }else {
            List<CameraArgs> tempArgList = new ArrayList<>();
            for (CameraArgs cameraArgs : cameraArgsList){
                if (cameraArgs.getCam_def() == 2){
                    tempArgList.add(cameraArgs);
                }
            }
            return JSON.toJSON(tempArgList).toString();
        }
    }

    /**
     * 开启、关闭相机，切换相机模式为视频或拍照
     * @param stnId     站点id
     * @param status    相机目标状态  开启 关闭 拍照 视频
     * @return
     */
    @RequestMapping("/switchMode/{stnId}/{status}")
    @ResponseBody
    public String switchMode(@PathVariable("stnId") int stnId,@PathVariable("status") int status){
        boolean flag = false;
        //TODO:给硬件发送指令 根据返回信息修改flag的值

        return flag ? "success" : "fail";
    }

    /**
     * 查询所有站点的信息
     * @return 返回站点的信息
     */
    @RequestMapping(value = "/queryAllWaterStation/{stnType}", method = RequestMethod.GET)
    @ResponseBody
    public String queryAllWaterStation(@PathVariable("stnType") int stnType) {
        //如果是0，说明是全部,就不用stnType这个参数限制了。
        if (stnType==0) {
            params.put("stnType", null);
        }
        else
        {
            if (stnType==3) {
                params.put("stnType", " where stnType like '%3%' or stnType like '%4%'");
            }
            else
            {
                params.put("stnType", " where stnType like '%"+stnType+"%'");
            }
        }
        List<WaterStation> waterStations = waterService.queryAllWaterStation(params);
        if (waterStations.size() == 0){
            return "{\"msg\": \"对不起,您想要查询的数据不存在\"}";
        }

        return JSON.toJSON(waterStations).toString();
    }

    private String getCorrectTime(String stime) throws Exception {
        //处理两种日期格式。 yyyy-MM-dd;MM-dd-yyyy
        return (stime.indexOf("-") < 3) ? DateUtil.transData(stime) : stime;
    }

    /**
     * 根据站点ID获取相机拍摄水位信息
     */
    @RequestMapping(value = "/RealTimeAcquisitionData/{stnId}", method = RequestMethod.GET)
    @ResponseBody
    public String RealTimeAcquisitionData(@PathVariable("stnId") int stnId) {
        String realTimeAcquisitionData="";
        //通过站点ID获取站点名称
        String stnCode = waterStationService.queryCodeById(stnId);
        if (stnCode.isEmpty()) {
            return "该站点不支持实时采集";
        }
        //获取发送请求时间
        String sendTime=DateUtil.getNowDate();
        logger.debug("startTime:"+sendTime);

        Socket so = SocketUtils.findSocket(stnCode);
        if (so != null && so.isConnected()) {
            // build message and send to RTU
             StringBuilder message=new StringBuilder("CMD:1:WL");
            try {
                SocketUtils.sendMessage(so, message.toString());
                int i=0;
                do {
                    i++;
                    //获取发送完时间
                    String receiveTime=DateUtil.getNowDate();
                    logger.debug("endTime:"+receiveTime);

                    Map<String,Object> timeMap=new HashMap<>();
                    timeMap.put("startTime",sendTime);
                    timeMap.put("endTime",receiveTime);
                    timeMap.put("stnId",stnId);
                    List<Water> waterList=waterService.hisWater(timeMap);
                    if (waterList.size()>0) {
                        realTimeAcquisitionData= JSON.toJSON(waterList.get(0)).toString();
                        break;
                    }
                    Thread.sleep(100);
                } while (i<=600);

            } catch (IOException e) {
                logger.error("Send scoket message error");
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                logger.error("Thread running error");
                e.printStackTrace();
            }
        } else {
            return "The connection with RTU was lost!";
        }

        if(realTimeAcquisitionData.equals("")){
            return "RealTimeAcquisitionData is failing";
        }
        return realTimeAcquisitionData;
    }

    /**
     * 确认 修改水位信息
     */
    @RequestMapping(value = "/updateWater", method = RequestMethod.POST)
    @ResponseBody
    public String updateWater(Water water){
        int result=waterService.updateWater(water);
        if (result>0) {
            return "success";
        }else{
            return "fail";
        }
    }

    /**
     * 取消 删除水位信息
     */
    @RequestMapping(value = "/deleteWater")
    @ResponseBody
    public String deleteWater(Water water){
        int result=waterService.deleteWater(water);
        if (result>0) {
            return "success";
        }else{
            return "fail";
        }
    }

}
