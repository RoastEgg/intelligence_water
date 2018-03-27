package com.hawksoft.platform.controller;

import com.alibaba.fastjson.JSON;

import com.hawksoft.platform.entity.Flow;

import com.hawksoft.platform.service.FlowService;

import com.hawksoft.platform.service.VideoService;
import com.hawksoft.platform.service.WaterStationService;
import com.hawksoft.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**流量汇总数据
 *  流量汇总数据模块
 *  处理流量汇总数据的所有数据
 *  1、流量汇总数据模块的入查改都从这里调用数据层接口处理
 *  2、流量汇总数据模块的增删口都从这里进去
 * @author linden (林全宝)
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/flow")
public class FlowController {

    @Autowired
    private FlowService flowService;

    @Autowired
    private VideoService videoService;
    @Autowired
    private WaterStationService waterStationService;

    private Map<String, Object> params = new HashMap<>();

    /**
     * 通过站点取得实时流量汇总数据（最新时间的流量汇总数据）
     * @return
     */
    @RequestMapping(value = "/queryLastFlowByStdId/{stnId}", method = RequestMethod.GET)
    @ResponseBody
    public String queryLastFlowByStdId(@PathVariable("stnId") int stnId){

        List<Flow> flows = flowService.queryLastFlowByStdId(stnId);
        System.out.println("返回记录条数："+flows.size());
        if (flows.size()>0){
            return JSON.toJSON(flows).toString();
        }
        return "{\"msg\" : \"暂时无法获取流量汇总数据\"}";
    }

    /**
     * 通过站点ID和时间查询该站点这段时间内流量汇总数据
     * @param (stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流量汇总数据
     */

    @RequestMapping(value = "/queryFlowByStdIdTime/{stnId}/{startTime}/{endTime}", method = RequestMethod.GET)
    @ResponseBody
    public String queryFlowByStdIdTime(@PathVariable("stnId") int stnId,
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
        List<Flow> flows = flowService.queryFlowByStdIdTime(params);
        System.out.println("返回记录条数："+flows.size());
        if (flows.size()>0){
            return JSON.toJSON(flows).toString();
        }
        return "{\"msg\" : \"暂时无法获取流量汇总数据\"}";
    }

    /**
     * 查询这段时间内最新的days条流量汇总数据
     * @param stnId 站点ID， days 取最新的多少条记录
     * @return
     */
    @RequestMapping(value = "/lastFlowRecordsNum/{stnId}/{days}", method = RequestMethod.GET)
    @ResponseBody
    public String lastFlowRecordsNum(@PathVariable("stnId") int stnId,
                                      @PathVariable("days") int days) {


        params.put("stnId", stnId);
        params.put("days", days);
        // params.put("stnId",stnId);
        List<Flow> flows = flowService.lastFlowRecordsNum(params);
        System.out.println("返回记录条数："+flows.size());
        if (flows.size()>0){
            return JSON.toJSON(flows).toString();
        }
        return "{\"msg\" : \"暂时无法获取流量汇总数据\"}";
    }


    /**
     * 查询这段时间内流量汇总数据
     * @param *map ，如果map有时间参数:startTime 开始时间， endTime 结束时间
     * @return
     */
    @RequestMapping(value = "/queryAllFlowByTime/{startTime}/{endTime}", method = RequestMethod.GET)
    @ResponseBody
    public String queryAllFlowByTime(@PathVariable("startTime") String startTime,
                                     @PathVariable("endTime") String endTime ) throws Exception{


        params.put("startTime", startTime);
        params.put("endTime", endTime);
       // params.put("stnId",stnId);
        List<Flow> flows = flowService.queryAllFlowByTime(params);
        System.out.println("返回记录条数："+flows.size());
        if (flows.size()>0){
            return JSON.toJSON(flows).toString();
        }
        return "{\"msg\" : \"暂时无法获取流量汇总数据\"}";
    }


    /**
     * 保存流量汇总信息
     * @param flow
     */
    @RequestMapping("/saveFlow")
    @ResponseBody
    public String saveFlow(Flow flow){
        return (flowService.saveFlow(flow) != 0) ? "success" : "fail";
    }

    /**
     * 更新流量汇总信息
     * @param flow
     */
    @ResponseBody
    public String updateFlow(Flow flow){
        return (flowService.updateFlow(flow) != 0) ? "success" : "fail";
    }

    /**
     * 删除流量汇总信息
     * @param flow
     */
    @RequestMapping("/deleteFlow")
    @ResponseBody
    public String deleteFlow(Flow flow){
        return (flowService.deleteFlow(flow)  != 0) ? "success" : "fail";
    }

    /**
     * 获取最新的流量信息，包括水位、流量和测试时间
     * @param stnId
     */
    @RequestMapping(value = "/lastFlow/{stnId}",method = RequestMethod.GET)
    @ResponseBody
    public String queryLastFlowAndWater(@PathVariable("stnId") int stnId){
        List<Map<String ,Object>> infos = flowService.queryLastFlowAndWater(stnId);
        System.out.println("返回记录条数："+infos.size());
        if (infos.size()>0){
            return JSON.toJSON(infos).toString();
        }
        return "{\"msg\" : \"暂时无法获取最新的流量和水位数据\"}";
    }

    @RequestMapping(value = "/historyFlowVideo/{stnId}/{time}",method = RequestMethod.GET)
    @ResponseBody
    public String queryFlowBystnIdAndTime(@PathVariable("stnId") int stnId,
                                          @PathVariable("time") String time){
        params.put("stnId",stnId);
        params.put("time",time);
        List<Map<String ,Object>> infos = flowService.queryFlowBystnIdAndTime(params);
        System.out.println("返回记录条数："+infos.size());
        if (infos.size()>0){
            return JSON.toJSON(infos).toString();
        }
        return "{\"msg\" : \"暂时无法获取最新的流量和水位数据\"}";

    }

    @RequestMapping(value = "/video/{stnId}/{type}",method = RequestMethod.GET)
    @ResponseBody
    public String getVideo(@PathVariable("stnId") int stnId,
                           @PathVariable("type") String type){

        params.put("stnId",stnId);
        params.put("type",type);
        List<Map<String , Object>> videoList = videoService.queryURL(params);
        System.out.println("视频记录条数："+videoList.size());
        if (videoList.size()>0){
            return JSON.toJSON(videoList).toString();
        }
        return "{\"msg\" : \"暂时无法获取视频数据\"}";
    }


    @RequestMapping(value = "/sectionMap/{stnId}",method = RequestMethod.GET)
    @ResponseBody
    public String getSectionMap(@PathVariable("stnId") int stnId){
        List<Map<String ,Object>> waterStationList = waterStationService.querySectionMap(stnId);
        System.out.println("断面图数："+waterStationList.size());
        if (waterStationList.size()>0){
            return JSON.toJSON(waterStationList).toString();
        }
        return "{\"msg\" : \"暂时无法获取断面图数据\"}";
    }

}
