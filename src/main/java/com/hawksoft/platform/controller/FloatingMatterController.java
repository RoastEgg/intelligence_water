package com.hawksoft.platform.controller;

import com.alibaba.fastjson.JSON;
import com.hawksoft.platform.entity.FloatingMatter;
import com.hawksoft.platform.service.FloatingMatterService;
import com.hawksoft.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  漂浮物模块
 *  处理漂浮物模块的所有数据
 *  1、漂浮物模块的入口都从这里进去
 *  2、漂浮物模块的增删查改都从这里调用数据层接口处理
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/floating")
public class FloatingMatterController {

    @Autowired
    private FloatingMatterService floatingMatterService;
    private Map<String, Object> params = new HashMap<>();

    /**
     * 通过站点ID和时间查询该站点这段时间内漂浮物数据
     * @param (stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内漂浮物详细数据
     */
    @RequestMapping(value = "/queryFMByStdIdTime/{stnId}/{startTime}/{endTime}",
                    method = RequestMethod.GET,
                    produces={"text/html;charset=UTF-8"})
    @ResponseBody
    public String queryFMByStdIdTime(@PathVariable("stnId") int stnId,
                                            @PathVariable("startTime") String startTime,
                                            @PathVariable("endTime") String endTime ) throws Exception{

        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("stnId",stnId);
        List<FloatingMatter> floatingMatters = floatingMatterService.findHistFloatingMatters(params);
        if (floatingMatters.size()>0){
            return JSON.toJSON(floatingMatters).toString();
        }
        return "{\"msg\" : \"暂时没有数据或无法获取漂浮物信息\"}";
    }

    /**
     * 返回最近days天每天的记录数
     * @param stnId
     * @param days
     * @return 每天的记录数
     */
    @RequestMapping(value = "/queryLastFMRecordsNum/{stnId}/{days}",
                    method = RequestMethod.GET,
                    produces={"text/html;charset=UTF-8"})
    @ResponseBody
    public String queryLastFMRecordsNum(@PathVariable("stnId") int stnId,
                                            @PathVariable("days") int days){
        params.put("stnId",stnId);
        params.put("days",days);
        List<Map<String,Object>> records = floatingMatterService.queryLastDaysRecordsNum(params);
        if (records.size()>0){
            return JSON.toJSON(records).toString();
        }
        return "{\"msg\" : \"暂时没有或无法获取漂浮物统计数据\"}";
    }

    /**
     * 根据日期查询漂浮物数据
     * @param *map 主要是日期时间
     * @return  当天的漂浮物的详细数据
     */
    @RequestMapping(value = "/queryFMRecordsByDate/{stnId}/{date}",
                    method = RequestMethod.GET,
                    produces={"text/html;charset=UTF-8"})
    @ResponseBody
    public String queryFMRecordsByDate(@PathVariable("stnId") int stnId,
                                     @PathVariable("date") String date ){

        params.put("date", date);
        params.put("stnId",stnId);
        List<FloatingMatter> fms = floatingMatterService.queryFMByDate(params);
        if (fms.size()>0){
            return JSON.toJSON(fms).toString();
        }
        return "{\"msg\" : \"暂时没有或无法获取漂浮物信息\"}";
    }

    /**
     * 查询这段时间内每天的漂浮物记录数
     * @param *map ，如果map有时间参数:startTime 开始时间， endTime 结束时间
     * @return 每天的记录数
     */
    @RequestMapping(value = "/queryRecordsNumByTime/{stnId}/{startTime}/{endTime}",
                    method = RequestMethod.GET,
                    produces={"text/html;charset=UTF-8"})
    @ResponseBody
    public String queryRecordsNumByTime(@PathVariable("stnId") int stnId,
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
        List<Map<String,Object>> records = floatingMatterService.queryRecordsNumByTime(params);
        if (records.size()>0){
            return JSON.toJSON(records).toString();
        }
        return "{\"msg\" : \"暂时没有或无法获取漂浮物统计数据\"}";
    }

    /**
     * 返回处理后图片路径
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value="/importImgDetail", method=RequestMethod.POST)
    @ResponseBody
    public String importImg(@RequestParam("file") MultipartFile file,HttpServletRequest request){

        String path=request.getServletContext().getRealPath("/images");
        File originFile = new File(path,file.getOriginalFilename());
        if(!originFile.getParentFile().exists()){    //如果所在目录不存在，那么创建
            originFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(new File(path+File.separator+file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "resources/common/images/11.jpg";
    }
}
