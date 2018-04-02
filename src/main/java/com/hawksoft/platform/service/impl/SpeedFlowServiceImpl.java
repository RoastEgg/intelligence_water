package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.SpeedFlowDao;
import com.hawksoft.platform.entity.Flow;
import com.hawksoft.platform.entity.SpeedFlow;
import com.hawksoft.platform.service.FlowService;
import com.hawksoft.platform.service.SpeedFlowService;
import com.hawksoft.platform.util.DataUtil;
import com.hawksoft.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpeedFlowServiceImpl implements SpeedFlowService {

    @Autowired
    private SpeedFlowDao speedFlowDao;
    @Autowired
    private FlowService flowService;

    /**
     * 通过站点ID查询实时流速流量数据（最新即最近一个时间的数据）
     * @return
     */
    @Override
    public List<SpeedFlow> queryLastSpeedFlowByStdId(int stnId)
    {
        return speedFlowDao.queryLastSpeedFlowByStdId(stnId);
    }

    /**
     * 通过站点ID和时间查询该站点这段时间内流速流量数据
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流速流量数据
     */
    @Override
    public List<SpeedFlow> querySpeedFlowByStdIdTime(Map<String, Object> map)
    {
        return speedFlowDao.querySpeedFlowByStdIdTime(map);
    }

    /**
     * 通过站点ID和时间查询该站点这段时间内流速流量数据
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间,cameraNo 摄像机序号为1至5号)
     * @return 返回该站点这段时间内流速流量数据
     */
    @Override
    public List<SpeedFlow> querySpeedFlowByStdIdTimeCamera(Map<String, Object> map)
    {
        return speedFlowDao.querySpeedFlowByStdIdTimeCamera(map);
    }

    /**
     * 查询这段时间内流速流量数据
     * @param map ，如果map有时间参数:startTime 开始时间， endTime 结束时间
     * @return
     */
    @Override
    public List<SpeedFlow> queryAllSpeedFlowByTime(Map<String, Object> map){
        return speedFlowDao.queryAllSpeedFlowByTime(map);
    }

    /**
     * 保存流速流量信息
     * @param speedFlow
     */
    @Override
    public int saveSpeedFlowAndFlow(SpeedFlow speedFlow)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date instockTime = new Date();
        speedFlow.setInstockTime(sdf.format(instockTime));

        Flow flow = new Flow();
        flow.setStnId(speedFlow.getStnId());
        flow.setCollectionTime(speedFlow.getInstockTime());
        Double avgSpeed = (speedFlow.getWaterSpeed1() + speedFlow.getWaterSpeed2() + speedFlow.getWaterSpeed3()
                + speedFlow.getWaterSpeed4() + speedFlow.getWaterSpeed5()) / 5;
        flow.setAvgSpeed(avgSpeed);
        Double timeFlow = speedFlow.getWaterFlow1() + speedFlow.getWaterFlow2() + speedFlow.getWaterFlow3()
                + speedFlow.getWaterFlow4() + speedFlow.getWaterFlow5();
        Double avgFlow = timeFlow / 5;
        flow.setAvgFlow(avgFlow);
        Map<String,Object> map = new HashMap<>();
        map.put("date",speedFlow.getInstockTime());
        Flow thisDay = flowService.queryFlowByDate(map);
        Double dayFlow = thisDay.getDayFlow();
        Flow thisWeek = flowService.queryFlowByWeek(map);
        Double weekFlow = thisWeek.getWeekFlow();
        Flow thisMonth = flowService.queryFlowByMonth(map);
        Double monthFlow = thisMonth.getMonthFlow();
        Flow thisYear = flowService.queryFlowByYear(map);
        Double yearFlow = thisYear.getYearFlow();
        Flow all = flowService.queryAllFlow(map);
        Double totalFlow = all.getTotalFlow();
        flow.setDayFlow(dayFlow + timeFlow);
        flow.setWeekFlow(weekFlow + timeFlow);
        flow.setMonthFlow(monthFlow + timeFlow);
        flow.setYearFlow(yearFlow + timeFlow);
        flow.setTotalFlow(totalFlow + timeFlow);
        Integer state = (new Integer(speedFlow.getState1()) + new Integer(speedFlow.getState2()) + new Integer(speedFlow.getState3())
                + new Integer(speedFlow.getState4()) +  new Integer(speedFlow.getState5()) );
        flow.setState(state == 5 ? "1" : "0");
        flowService.saveFlow(flow);
        return speedFlowDao.saveSpeedFlow(speedFlow);
    }

    /**
     * 保存流速信息
     * @param speedFlow
     */
    @Override
    public int saveSpeedFlow(SpeedFlow speedFlow){
        return speedFlowDao.saveSpeedFlow(speedFlow);
    }

    /**
     * 更新流速流量信息
     * @param speedFlow
     */
    @Override
    public int updateSpeedFlow(SpeedFlow speedFlow)
    {
        return speedFlowDao.updateSpeedFlow(speedFlow);
    }

    /**
     * 删除流速流量信息
     * @param speedFlow
     */
    @Override
    public int deleteSpeedFlow(SpeedFlow speedFlow)
    {
        return speedFlowDao.deleteSpeedFlow(speedFlow);
    }


    /**
     * 通过站点ID和采集时间取得唯一一条获取站点的流速流量记录
     * @param map
     */
    @Override
    public SpeedFlow findSpeedFlowByStdIdTime(Map<String, Object> map)
    {
        return speedFlowDao.findSpeedFlowByStdIdTime(map);
    }

    /**
     * 取最新的DAYS条流速流量数据
     * @param map
     */
    @Override
    public List<SpeedFlow> getRecentRecords(Map<String, Object> map)
    {
        return speedFlowDao.getRecentRecords(map);
    }

    @Override
    public boolean generateData() {
        int stnId = 1;
        double rangeSpeed = 0.1;
        double minSpeed = 0.11,maxSpeed = 0.87;//speedflow表需要的数据
        double waterSpeed1,waterSpeed2,waterSpeed3,waterSpeed4,waterSpeed5;
        SpeedFlow todaySpeedFlow = new SpeedFlow();

        Map<String,Object> map = new HashMap<>();
        map.put("days", stnId);
        map.put("stnId",stnId);
        List<SpeedFlow> speedFlowList = getRecentRecords(map);//利用已有的实现，取最新一条记录
        if (speedFlowList.size()>0){
            todaySpeedFlow = speedFlowList.get(0);
        }
        waterSpeed1 = todaySpeedFlow.getWaterSpeed1();
        waterSpeed2 = todaySpeedFlow.getWaterSpeed2();
        waterSpeed3 = todaySpeedFlow.getWaterSpeed3();
        waterSpeed4 = todaySpeedFlow.getWaterSpeed4();
        waterSpeed5 = todaySpeedFlow.getWaterSpeed5();
        //如果已有当天的数据，那么就做扩大型随机，否则就重新随机
        if (DateUtil.judgeDate(DateUtil.parseString(todaySpeedFlow.getCollectionTime()))){
            waterSpeed1 = DataUtil.expendData(waterSpeed1,maxSpeed,minSpeed,rangeSpeed);
            waterSpeed2 = DataUtil.expendData(waterSpeed2,maxSpeed,minSpeed,rangeSpeed);
            waterSpeed3 = DataUtil.expendData(waterSpeed3,maxSpeed,minSpeed,rangeSpeed);
            waterSpeed4 = DataUtil.expendData(waterSpeed4,maxSpeed,minSpeed,rangeSpeed);
            waterSpeed5 = DataUtil.expendData(waterSpeed5,maxSpeed,minSpeed,rangeSpeed);
        }
        else {
            waterSpeed1 = DataUtil.getRandom(maxSpeed,minSpeed);
            waterSpeed2 = DataUtil.getRandom(maxSpeed,minSpeed);
            waterSpeed3 = DataUtil.getRandom(maxSpeed,minSpeed);
            waterSpeed4 = DataUtil.getRandom(maxSpeed,minSpeed);
            waterSpeed5 = DataUtil.getRandom(maxSpeed,minSpeed);
        }

        SpeedFlow speedFlow = new SpeedFlow();
        speedFlow.setStnId(stnId);
        speedFlow.setWaterSpeed1(waterSpeed1);
        speedFlow.setWaterSpeed2(waterSpeed2);
        speedFlow.setWaterSpeed3(waterSpeed3);
        speedFlow.setWaterSpeed4(waterSpeed4);
        speedFlow.setWaterSpeed5(waterSpeed5);
        speedFlow.setFilePath1("");
        speedFlow.setFilePath2("");
        speedFlow.setFilePath3("");
        speedFlow.setFilePath4("");
        speedFlow.setFilePath5("");

        String sTime = DateUtil.parseDate(new Date());//获取当前时间
        speedFlow.setCollectionTime(sTime);
        speedFlow.setInstockTime(sTime);
        return true;
    }


}
