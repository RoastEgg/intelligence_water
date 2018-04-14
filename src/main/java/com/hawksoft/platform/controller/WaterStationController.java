package com.hawksoft.platform.controller;

import com.alibaba.fastjson.JSON;
import com.hawksoft.platform.VO.HistoryRecordVO;
import com.hawksoft.platform.service.WaterStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/waterStation")
public class WaterStationController {

    @Autowired
    private WaterStationService waterStationService;

    /**
     * 查询站点是否有水位、水质、流量、漂浮物、无人船信息、历史记录信息
     * @param stnId
     * @return
     */
    @RequestMapping(value = "/stationInfo/{stnId}",method = RequestMethod.GET)
    @ResponseBody
    public String queryStationInfo(@PathVariable ("stnId") int stnId) {

        return JSON.toJSON(waterStationService.queryStationInfo(stnId)).toString();
    }

    /**
     * 查询所有站点信息,比上面的方法多出了站点Id、name、x、y
     * @return
     */
    @RequestMapping(value = "/allStationInfo",method = RequestMethod.GET)
    @ResponseBody
    public String queryAllStationInfo(){
        return JSON.toJSON(waterStationService.queryAllStationInfo()).toString();
    }


    @RequestMapping(value = "/historyInfo/{stnId}" ,method = RequestMethod.GET)
    @ResponseBody
    public HistoryRecordVO queryHistoryInfo(@PathVariable("stnId") int stnId){

        HistoryRecordVO historyRecordVO = waterStationService.queryHistoryInfo(stnId);
        if (historyRecordVO!=null){
            System.out.println("得到历史数据");
            return historyRecordVO;
        }
        System.out.println("未得到历史数据！");
        return null;
    }
}
