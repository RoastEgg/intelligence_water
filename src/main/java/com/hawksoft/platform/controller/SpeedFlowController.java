package com.hawksoft.platform.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hawksoft.platform.VO.SpeedFlowVO;
import com.hawksoft.platform.entity.Flow;
import com.hawksoft.platform.entity.SpeedFlow;
import com.hawksoft.platform.service.FlowService;
import com.hawksoft.platform.service.SpeedFlowService;
import com.hawksoft.platform.service.WaterStationService;
import com.hawksoft.platform.socket.SocketUtils;
import com.hawksoft.platform.util.DataUtil;
import com.hawksoft.platform.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  流速流量模块
 *  处理流速流量模块的所有数据
 *  1、流速流量模块的入口都从这里进去
 *  2、流速流量模块的增删查改都从这里调用数据层接口处理
 *  @author linden (林全宝)
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/speedflow")
public class SpeedFlowController {

    @Autowired
    private SpeedFlowService speedFlowService;
    @Autowired
    private WaterStationService waterStationService;
    @Autowired
    private FlowService flowService;

    Logger logger= LoggerFactory.getLogger(SpeedFlowController.class);
    private Map<String, Object> params = new HashMap<>();


    /**
     * 通过站点ID查询实时流速流量数据（最新即最近一个时间的数据）
     * @return
     */
    @RequestMapping(value = "/queryLastSpeedFlowByStdId/{stnId}", method = RequestMethod.GET)
    @ResponseBody
    public SpeedFlowVO queryLastSpeedFlowByStdId(@PathVariable("stnId") int stnId)
    {
        SpeedFlowVO speedFlowVO = speedFlowService.queryLastSpeedFlowByStdId(stnId);
        //System.out.println("返回记录条数："+SpeedFlows.size());
        if (speedFlowVO!=null){
            System.out.println("获取到最新数据");
            System.out.println("p or v: "+speedFlowVO.getPicOrVideo());
            return speedFlowVO;
        }
        System.out.println("{\"msg\" : \"暂时无法获取流速流量数据\"}");
        return null;
    }

    /**
     * 通过站点ID和时间查询该站点这段时间内流速流量数据
     * @param (stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流速流量数据
     */

    @RequestMapping(value = "/querySpeedFlowByStdIdTime/{stnId}/{startTime}/{endTime}", method = RequestMethod.GET)
    @ResponseBody
    public String querySpeedFlowByStdIdTime(@PathVariable("stnId") int stnId,
                                            @PathVariable("startTime") String startTime,
                                            @PathVariable("endTime") String endTime ) throws Exception{

        //处理两种日期格式。 yyyy-MM-dd;MM-dd-yyyy
        int splitLen =startTime.indexOf("-");
        if (splitLen<3) {
            startTime = DateUtil.transData(startTime);
            endTime = DateUtil.transData(endTime);
        }
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("stnId",stnId);
        List<SpeedFlow>  SpeedFlows = speedFlowService.querySpeedFlowByStdIdTime(params);
        System.out.println("返回记录条数："+SpeedFlows.size());
        if (SpeedFlows.size()>0){
            return JSON.toJSON(SpeedFlows).toString();
        }
        return "{\"msg\" : \"暂时无法获取流速流量数据\"}";
    }

    /**
     * 取最新的DAYS条流速流量数据
     * @param (stnId 站点编号,days 记录条数)
     * @return
     */

    @RequestMapping(value = "/getRecentRecords/{stnId}/{days}", method = RequestMethod.GET)
    @ResponseBody
    public String getRecentRecords(@PathVariable("stnId") int stnId,
                                           @PathVariable("days") int days) throws Exception{

        params.put("days", days);
        params.put("stnId",stnId);
        List<SpeedFlow>  SpeedFlows = speedFlowService.getRecentRecords(params);
        System.out.println("返回记录条数："+SpeedFlows.size());
        if (SpeedFlows.size()>0){
            return JSON.toJSON(SpeedFlows).toString();
        }
        return "{\"msg\" : \"暂时无法获取流速流量数据\"}";
    }

    /**
     * 通过站点ID和时间查询该站点这段时间内流速流量数据
     * @param *map(stnId 站点编号,startTime 开始时间,endTime 结束时间,cameraNo 摄像机序号为1至5号)
     * @return 返回该站点这段时间内流速流量数据
     */
    @RequestMapping(value = "/querySpeedFlowByStdIdTimeCamera/{stnId}/{startTime}/{endTime}/{cameraNo}", method = RequestMethod.GET)
    @ResponseBody
    public String querySpeedFlowByStdIdTimeCamera(@PathVariable("stnId") int stnId,
                                                  @PathVariable("startTime") String startTime,
                                                  @PathVariable("endTime") String endTime,
                                                  @PathVariable("cameraNo") String cameraNo
                                                  ) throws Exception{

        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("stnId",stnId);
        params.put("cameraNo",cameraNo);
        List<SpeedFlow>  SpeedFlows = speedFlowService.querySpeedFlowByStdIdTimeCamera(params);
        System.out.println("返回记录条数："+SpeedFlows.size());
        if (SpeedFlows.size()>0){
            return JSON.toJSON(SpeedFlows).toString();
        }
        return "{\"msg\" : \"暂时无法获取流速流量数据\"}";
    }

    /**
     * 查询这段时间内流速流量数据
     * @param *如果有时间参数:startTime 开始时间， endTime 结束时间
     * @return
     */
    @RequestMapping(value = "/queryAllSpeedFlowByTime/{startTime}/{endTime}", method = RequestMethod.GET)
    @ResponseBody
    public String queryAllSpeedFlowByTime(@PathVariable("startTime") String startTime,
                                          @PathVariable("endTime") String endTime) throws Exception{

        params.put("startTime", startTime);
        params.put("endTime", endTime);

        List<SpeedFlow>  SpeedFlows = speedFlowService.queryAllSpeedFlowByTime(params);
        System.out.println("返回记录条数："+SpeedFlows.size());
        if (SpeedFlows.size()>0){
            return JSON.toJSON(SpeedFlows).toString();
        }
        return "{\"msg\" : \"暂时无法获取流速流量数据\"}";
    }


    /**
     * 保存录入流速流量信息
     * @param speedFlow 流速流量信息
     * @return 成功返回success，失败返回fail
     */
    @RequestMapping("/saveSpeedFlow")
    @ResponseBody
    public String saveSpeedFlow(SpeedFlow speedFlow){

        //TODO:根据各个相机的流速和流量计算统计值 并存入流量统计表
        //按目前的表结构 每次更新数据时需要更新其他相关记录 例如年流量 就得更新这一年的每一条记录里的年流量
        /*Flow flow = new Flow();
        flow.setStnId(speedFlow.getStnId());
        double avgSpeed = (speedFlow.getWaterSpeed1() + speedFlow.getWaterSpeed2()
                            + speedFlow.getWaterSpeed3() + speedFlow.getWaterSpeed3()
                            + speedFlow.getWaterFlow5()) / 5;
        flow.setAvgSpeed(avgSpeed);
        double avgFlow = (speedFlow.getWaterFlow1() + speedFlow.getWaterFlow2()
                            + speedFlow.getWaterFlow3() + speedFlow.getWaterFlow4()
                            + speedFlow.getWaterFlow5()) / 5;
        flow.setAvgFlow(avgFlow);
        flow.setCollectionTime(speedFlow.getCollectionTime());*/

        return (speedFlowService.saveSpeedFlow(speedFlow) != 0) ? "success" : "fail";

    }

    /**
     * 更新录入流速流量信息
     * @param speedFlow 流速流量信息
     * @return 成功返回success，失败返回fail
     */
    @RequestMapping("/updateSpeedFlow")
    @ResponseBody
    public String updateSpeedFlow(SpeedFlow speedFlow){
        return (speedFlowService.updateSpeedFlow(speedFlow) != 0) ? "success" : "fail";
    }

    /**
     * 删除已录入流速流量信息
     * @param speedFlow 流速流量信息
     * @return 成功返回success，失败返回fail
     */
    @RequestMapping("/deleteSpeedFlow")
    @ResponseBody
    public String deleteSpeedFlow(SpeedFlow speedFlow){
        return (speedFlowService.deleteSpeedFlow(speedFlow) != 0) ? "success" : "fail";
    }

    /**
     * 采集数据
     * @return
     */
    @RequestMapping(value = "/collectData/{stnId}",method = RequestMethod.GET)
    @ResponseBody
    public int collectData(@PathVariable("stnId") int stnId){
        int res =  speedFlowService.generateData(stnId,new Date());
        return res;
    }

    /**
     * 根据站点ID获取相机拍摄采集流量信息
     */
    @RequestMapping(value = "/RealTimeAcquisitionData/{stnId}", method = RequestMethod.GET)
    @ResponseBody
    public String RealTimeAcquisitionData(@PathVariable("stnId") int stnId) {
        String realTimeAcquisitionData="";
        //通过站点ID获取站点名称
        String stnCode = waterStationService.queryCodeById(stnId);
        if (stnCode.isEmpty()) {
            logger.error("该站点不支持实时采集");
            return "fail";
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取发送请求时间
        String sendTime="2018-05-01 00:00:00";
//        String sendTime=DateUtil.getTimeByMinute(-1);
        logger.debug("startTime:"+sendTime);

//        Socket so = SocketUtils.findSocket(stnCode);
//        if (so != null && so.isConnected()) {
            // build message and send to RTU
            StringBuilder message=new StringBuilder("CMD:");
            message.append(stnId);
            message.append(":FLUX");
            try {
//                SocketUtils.sendMessage(so, message.toString());
                int i=0;
                do {
                    i++;
                    //获取发送完时间
                    String receiveTime="2018-05-10 00:00:00";
//                    String receiveTime=DateUtil.getTimeByMinute(sendTime,2);
                    logger.debug("endTime:"+receiveTime);

                    Map<String,Object> timeMap=new HashMap<>();
                    timeMap.put("startTime",sendTime);
                    timeMap.put("endTime",receiveTime);
                    timeMap.put("stnId",stnId);
                    List<SpeedFlow> speedFlowList=speedFlowService.querySpeedFlowByStdIdTime(timeMap);
                    if (speedFlowList.size()>0) {
                        //获取流速数据
                        SpeedFlow speedFlow=speedFlowList.get(speedFlowList.size()-1);
                        //计算平均流速
                        Double[] speedFlows = {speedFlow.getWaterSpeed1(),speedFlow.getWaterSpeed2(),
                                speedFlow.getWaterSpeed3(),speedFlow.getWaterSpeed4(),speedFlow.getWaterSpeed5()};
                        Double avgSpeed=DataUtil.getAvgCount(speedFlows);
                        //计算平均流量
                        Double[] waterFlows={speedFlow.getWaterFlow1(),speedFlow.getWaterFlow2(),
                                speedFlow.getWaterFlow3(),speedFlow.getWaterFlow4(),speedFlow.getWaterFlow5()};
                        Double avgFlow=DataUtil.getAvgCount(waterFlows);
                        Flow flow=new Flow();
                        flow.setStnId(stnId);
                        flow.setAvgSpeed(avgSpeed);
                        flow.setAvgFlow(avgFlow);
                        flow.setCollectionTime(speedFlow.getCollectionTime());
                        //将流量数据插入流量表
                        int saveFlow=flowService.saveFlow(flow);
                        if (saveFlow==0) {
                            logger.error("flowService.saveFlow ERROR");
                            return "fail";
                        }
                        Map<String,Object> resultMap=new HashMap<>();
                        resultMap.put("flowId",flow.getFlowId());
                        resultMap.put("avgFlow",avgFlow);
                        resultMap.put("speedflowid",speedFlow.getSpeedflowid());
                        resultMap.put("waterSpeed1",speedFlow.getWaterSpeed1());
                        resultMap.put("waterSpeed2",speedFlow.getWaterSpeed2());
                        resultMap.put("waterSpeed3",speedFlow.getWaterSpeed3());
                        resultMap.put("waterSpeed4",speedFlow.getWaterSpeed4());
                        resultMap.put("waterSpeed5",speedFlow.getWaterSpeed5());
                        realTimeAcquisitionData= JSON.toJSONString(resultMap);
                        break;
                    }
                    Thread.sleep(100);
                } while (i<=1200);
            }
//            catch (IOException e) {
//                logger.error("Send scoket message error");
//                e.printStackTrace();
//            }
            catch (InterruptedException e) {
                logger.error("Thread running error");
                e.printStackTrace();
            }
//        }
//        else {
//            logger.error("The connection with RTU was lost!");
//            return "fail";
//        }

        if(realTimeAcquisitionData.equals("")){
            logger.error("RealTimeAcquisitionData is failing");
            return "fail";
        }
        return realTimeAcquisitionData;
    }
}
