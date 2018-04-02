package com.hawksoft.platform.controller;

import com.alibaba.fastjson.JSON;
import com.hawksoft.platform.service.WaterStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/waterStation")
public class WaterStationController {

    @Autowired
    private WaterStationService waterStationService;

    /**
     * 查询站点是否有水位、水质、流量、漂浮物、无人船信息
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
}
