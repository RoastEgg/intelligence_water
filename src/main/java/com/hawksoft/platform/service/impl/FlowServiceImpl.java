package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.FlowDao;
import com.hawksoft.platform.entity.*;
import com.hawksoft.platform.service.FlowService;
import com.hawksoft.platform.util.DataUtil;
import com.hawksoft.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlowServiceImpl implements FlowService {

    @Autowired
    private FlowDao flowDao;

    /**
     * 通过站点ID查询实时流量汇总数据（最新即最近一个时间的数据）
     * @return
     */
    @Override
    public List<Flow> queryLastFlowByStdId(int stnId){
        return flowDao.queryLastFlowByStdId(stnId);
    }

    /**
     * 通过站点ID和时间查询该站点这段时间内流量汇总数据
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流量汇总数据
     */
    @Override
    public List<Flow> queryFlowByStdIdTime(Map<String, Object> map)
    {
        return flowDao.queryFlowByStdIdTime(map);
    }

    /**
     * 查询这段时间内流量汇总数据
     * @param map ，如果map有时间参数:startTime 开始时间， endTime 结束时间
     * @return
     */
    @Override
    public List<Flow> queryAllFlowByTime(Map<String, Object> map){
        return flowDao.queryAllFlowByTime(map);
    }

    @Override
    public Flow queryFlowByDate(Map<String, Object> map) {
        Flow thisDay = flowDao.queryFlowByDate(map);
        return thisDay != null ? thisDay : new Flow("initial");
    }

    @Override
    public Flow queryFlowByWeek(Map<String, Object> map) {
        Flow thisWeek = flowDao.queryFlowByWeek(map);
        return thisWeek != null ? thisWeek : new Flow("initial");
    }

    @Override
    public Flow queryFlowByMonth(Map<String, Object> map) {
        Flow thisMonth = flowDao.queryFlowByMonth(map);
        return thisMonth != null ? thisMonth : new Flow("initial");
    }

    @Override
    public Flow queryFlowByYear(Map<String, Object> map) {
        Flow thisYear = flowDao.queryFlowByYear(map);
        return thisYear != null ? thisYear : new Flow("initial");
    }

    public Flow queryAllFlow(Map<String,Object>map){
        Flow all = flowDao.queryAllFlow(map);
        return all != null ? all : new Flow("initial");
    }


    /**
     * 保存流量汇总信息
     * @param flow
     */
    @Override
    public int saveFlow(Flow flow){
        return flowDao.saveFlow(flow);
    }
    /**
     * 更新流量汇总信息
     * @param flow
     */
    @Override
    public int updateFlow(Flow flow){
        return flowDao.updateFlow(flow);

    }
    /**
     * 删除流量汇总信息
     * @param flow
     */
    @Override
    public int deleteFlow(Flow flow){
        return flowDao.deleteFlow(flow);
    }

    /**
     * 取最新的DAYS条实时水位
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流量汇总数据
     */
    @Override
    public List<Flow> lastFlowRecordsNum(Map<String, Object> map)
    {
        return flowDao.lastFlowRecordsNum(map);
    }

    /**
     * 获取最近的流量和水位信息（水位、流量、测试时间）
     * @param stnId
     * @return
     */
    @Override
    public List<Map<String , Object>> queryLastFlowAndWater(int stnId) {
        return flowDao.queryLastFlowAndWater(stnId);
    }

    /**
     * 获取这个时间点的流量，水位和5个相机的5个视频url
     * @param map
     * @return
     */
    @Override
    public List<Map<String , Object>> queryFlowBystnIdAndTime(Map<String ,Object> map) {
        return flowDao.queryFlowBystnIdAndTime(map);
    }

    @Override
    public int queryStationInfo(int stnId) {
        return flowDao.queryStationInfo(stnId);
    }

    @Override
    public boolean generateData() {
        int stnId = 1;
        double minSpeed = 0.11,maxSpeed = 0.87;
        double minFlow = 100,maxFlow =722;
        double rangeSpeed = 0.1,rangeFlow = 100;
        double avgSpeed ,avgFlow;//flow表需要的数据
        Flow flow = new Flow();
        Map<String,Object> map = new HashMap<>();
        map.put("date",DateUtil.parseDate(new Date()));
        Flow todayFlow = queryFlowByDate(map);//去数据库里查询今天的最新一条的流量数据
        avgSpeed = todayFlow.getAvgSpeed();
        avgFlow = todayFlow.getAvgFlow();
        if (avgSpeed>0 || avgFlow>0){//若查到数据，则在这条记录中的字段基础上做扩大型随机
            avgSpeed = DataUtil.expendData(avgSpeed,maxSpeed,minSpeed,rangeSpeed);
            avgFlow = DataUtil.expendData(avgFlow,maxFlow,minFlow,rangeFlow);
        }
        else {
            avgSpeed = DataUtil.getRandom(maxSpeed,minSpeed);//若没查到数据，则重新做随机
            avgFlow = DataUtil.getRandom(maxFlow,minFlow);
        }

        flow.setStnId(stnId);
        flow.setAvgSpeed(avgSpeed);
        flow.setAvgFlow(avgFlow);
        String cTime = DateUtil.parseDate(new Date());//当前时间
        flow.setCollectionTime(cTime);
        saveFlow(flow);

        return true;
    }


}
