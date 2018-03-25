package com.hawksoft.platform.controller;

import com.alibaba.fastjson.JSON;
import com.hawksoft.platform.entity.Video;
import com.hawksoft.platform.entity.WaterStation;
import com.hawksoft.platform.service.VideoService;
import com.hawksoft.platform.service.WaterStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private WaterStationService waterStationService;

    @RequestMapping(value = "/video/{stnId}/{type}",method = RequestMethod.GET)
    @ResponseBody
    public String getVideo(@PathVariable("stnId") int stnId,
                           @PathVariable("type") String type){
        List<Video> videoList = videoService.queryURL(stnId,type);
        System.out.println("视频记录条数："+videoList.size());
        if (videoList.size()>0){
            return JSON.toJSON(videoList).toString();
        }
        return "{\"msg\" : \"暂时无法获取视频数据\"}";
    }


    @RequestMapping(value = "/sectionMap/{stnId}",method = RequestMethod.GET)
    @ResponseBody
    public String getSectionMap(@PathVariable("stnId") int stnId){
        List<WaterStation> waterStationList = waterStationService.querySectionMap(stnId);
        System.out.println("断面图数："+waterStationList.size());
        if (waterStationList.size()>0){
            return JSON.toJSON(waterStationList).toString();
        }
        return "{\"msg\" : \"暂时无法获取断面图数据\"}";
    }

}
