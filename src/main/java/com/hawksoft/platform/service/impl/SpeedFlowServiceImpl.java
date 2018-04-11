package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.VO.SpeedFlowVO;
import com.hawksoft.platform.dao.SpeedFlowDao;
import com.hawksoft.platform.entity.Camera;
import com.hawksoft.platform.entity.Flow;
import com.hawksoft.platform.entity.SpeedFlow;
import com.hawksoft.platform.service.FlowService;
import com.hawksoft.platform.service.SpeedFlowService;
import com.hawksoft.platform.util.DataUtil;
import com.hawksoft.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SpeedFlowServiceImpl implements SpeedFlowService {

    @Autowired
    private SpeedFlowDao speedFlowDao;
    @Autowired
    private FlowService flowService;

    /**
     * 通过站点ID查询实时流速流量数据（最新即最近一个时间的数据）
     *
     * @return
     */
    @Override
    public SpeedFlowVO queryLastSpeedFlowByStdId(int stnId) {

        List<SpeedFlow> speedFlowList = speedFlowDao.queryLastSpeedFlowByStdId(stnId);
        List<Camera> allCameraList = new ArrayList<>();
        List<Camera> additionalCameraList = speedFlowDao.queryLastSpeedFlowByStdIdAdd(stnId);
        SpeedFlowVO speedFlowVO = new SpeedFlowVO();

        if (speedFlowList!=null){

            for (SpeedFlow sf: speedFlowList){
                speedFlowVO.setStnId(stnId);
                speedFlowVO.setCollectionTime(sf.getCollectionTime());
                speedFlowVO.setInstockTime(sf.getInstockTime());
                speedFlowVO.setPicOrVideo(sf.getPicOrVideo());

                Camera camera = new Camera();
                camera.setStnId(stnId);
                camera.setCameraNo(1);
                camera.setFlow(sf.getWaterFlow1());
                camera.setSpeed(sf.getWaterSpeed1());
                camera.setState(sf.getState1());
                camera.setFilePath(sf.getFilePath1());
                camera.setCollectionTime(sf.getCollectionTime());
                camera.setInstockTime(sf.getInstockTime());
                allCameraList.add(camera);

                camera.setStnId(stnId);
                camera.setCameraNo(2);
                camera.setFlow(sf.getWaterFlow2());
                camera.setSpeed(sf.getWaterSpeed2());
                camera.setState(sf.getState2());
                camera.setFilePath(sf.getFilePath2());
                camera.setCollectionTime(sf.getCollectionTime());
                camera.setInstockTime(sf.getInstockTime());
                allCameraList.add(camera);

                camera.setStnId(stnId);
                camera.setCameraNo(3);
                camera.setFlow(sf.getWaterFlow3());
                camera.setSpeed(sf.getWaterSpeed3());
                camera.setState(sf.getState3());
                camera.setFilePath(sf.getFilePath3());
                camera.setCollectionTime(sf.getCollectionTime());
                camera.setInstockTime(sf.getInstockTime());
                allCameraList.add(camera);

                camera.setStnId(stnId);
                camera.setCameraNo(4);
                camera.setFlow(sf.getWaterFlow4());
                camera.setSpeed(sf.getWaterSpeed4());
                camera.setState(sf.getState4());
                camera.setFilePath(sf.getFilePath4());
                camera.setCollectionTime(sf.getCollectionTime());
                camera.setInstockTime(sf.getInstockTime());
                allCameraList.add(camera);

                camera.setStnId(stnId);
                camera.setCameraNo(5);
                camera.setFlow(sf.getWaterFlow5());
                camera.setSpeed(sf.getWaterSpeed5());
                camera.setState(sf.getState5());
                camera.setFilePath(sf.getFilePath5());
                camera.setCollectionTime(sf.getCollectionTime());
                camera.setInstockTime(sf.getInstockTime());
                allCameraList.add(camera);
            }
            allCameraList.addAll(additionalCameraList);
        }
        speedFlowVO.setList(allCameraList);
        return speedFlowVO;
    }

    /**
     * 通过站点ID和时间查询该站点这段时间内流速流量数据
     *
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流速流量数据
     */
    @Override
    public List<SpeedFlow> querySpeedFlowByStdIdTime(Map<String, Object> map) {
        return speedFlowDao.querySpeedFlowByStdIdTime(map);
    }

    /**
     * 通过站点ID和时间查询该站点这段时间内流速流量数据
     *
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间,cameraNo 摄像机序号为1至5号)
     * @return 返回该站点这段时间内流速流量数据
     */
    @Override
    public List<SpeedFlow> querySpeedFlowByStdIdTimeCamera(Map<String, Object> map) {
        return speedFlowDao.querySpeedFlowByStdIdTimeCamera(map);
    }

    /**
     * 查询这段时间内流速流量数据
     *
     * @param map ，如果map有时间参数:startTime 开始时间， endTime 结束时间
     * @return
     */
    @Override
    public List<SpeedFlow> queryAllSpeedFlowByTime(Map<String, Object> map) {
        return speedFlowDao.queryAllSpeedFlowByTime(map);
    }

    /**
     * 保存流速流量信息
     *
     * @param speedFlow
     */
    @Override
    public int saveSpeedFlowAndFlow(SpeedFlow speedFlow) {
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
        Map<String, Object> map = new HashMap<>();
        map.put("date", speedFlow.getInstockTime());
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
                + new Integer(speedFlow.getState4()) + new Integer(speedFlow.getState5()));
        flow.setState(state == 5 ? "1" : "0");
        flowService.saveFlow(flow);
        return speedFlowDao.saveSpeedFlow(speedFlow);
    }

    /**
     * 保存流速信息
     *
     * @param speedFlow
     */
    @Override
    public int saveSpeedFlow(SpeedFlow speedFlow) {
        return speedFlowDao.saveSpeedFlow(speedFlow);
    }

    /**
     * 更新流速流量信息
     *
     * @param speedFlow
     */
    @Override
    public int updateSpeedFlow(SpeedFlow speedFlow) {
        return speedFlowDao.updateSpeedFlow(speedFlow);
    }

    /**
     * 删除流速流量信息
     *
     * @param speedFlow
     */
    @Override
    public int deleteSpeedFlow(SpeedFlow speedFlow) {
        return speedFlowDao.deleteSpeedFlow(speedFlow);
    }


    /**
     * 通过站点ID和采集时间取得唯一一条获取站点的流速流量记录
     *
     * @param map
     */
    @Override
    public SpeedFlow findSpeedFlowByStdIdTime(Map<String, Object> map) {
        return speedFlowDao.findSpeedFlowByStdIdTime(map);
    }

    /**
     * 取最新的DAYS条流速流量数据
     *
     * @param map
     */
    @Override
    public List<SpeedFlow> getRecentRecords(Map<String, Object> map) {
        return speedFlowDao.getRecentRecords(map);
    }

    @Override
    public int generateData() {
        SpeedFlow speedFlow = generateSpeedFlow(new Date());
        int res = saveSpeedFlow(speedFlow);
        return res;
    }

    @Override
    public SpeedFlow generateSpeedFlow(Date date) {
        int stnId = 1;
        double minSpeed = 0.11, maxSpeed = 0.87;//speedflow表需要的数据
        double waterSpeed1, waterSpeed2, waterSpeed3, waterSpeed4, waterSpeed5;
        SpeedFlow lastSpeedFlow = new SpeedFlow();

        Map<String, Object> map = new HashMap<>();
        map.put("days", stnId);
        map.put("stnId", stnId);
        List<SpeedFlow> speedFlowList = getRecentRecords(map);//利用已有的实现，取最新一条记录
        if (speedFlowList.size() > 0) {//有数据的情况下，利用已有数据调整,否则就直接做随机
            lastSpeedFlow = speedFlowList.get(0);
            waterSpeed1 = lastSpeedFlow.getWaterSpeed1();
            waterSpeed2 = lastSpeedFlow.getWaterSpeed2();
            waterSpeed3 = lastSpeedFlow.getWaterSpeed3();
            waterSpeed4 = lastSpeedFlow.getWaterSpeed4();
            waterSpeed5 = lastSpeedFlow.getWaterSpeed5();
            //如果最新记录是当天的，那么就做扩大型随机，否则就做调整型随机
            if (DateUtil.judgeDate(DateUtil.parseString(lastSpeedFlow.getCollectionTime()),date)) {
                waterSpeed1 = DataUtil.expendData(waterSpeed1, maxSpeed, minSpeed);
                waterSpeed2 = DataUtil.expendData(waterSpeed2, maxSpeed, minSpeed);
                waterSpeed3 = DataUtil.expendData(waterSpeed3, maxSpeed, minSpeed);
                waterSpeed4 = DataUtil.expendData(waterSpeed4, maxSpeed, minSpeed);
                waterSpeed5 = DataUtil.expendData(waterSpeed5, maxSpeed, minSpeed);
            } else {
                waterSpeed1 = DataUtil.adjustDataForFlow(waterSpeed1, maxSpeed, minSpeed);
                waterSpeed2 = DataUtil.adjustDataForFlow(waterSpeed2, maxSpeed, minSpeed);
                waterSpeed3 = DataUtil.adjustDataForFlow(waterSpeed3, maxSpeed, minSpeed);
                waterSpeed4 = DataUtil.adjustDataForFlow(waterSpeed4, maxSpeed, minSpeed);
                waterSpeed5 = DataUtil.adjustDataForFlow(waterSpeed5, maxSpeed, minSpeed);
            }
        } else {
            waterSpeed1 = DataUtil.getRandom(maxSpeed, minSpeed);
            waterSpeed2 = DataUtil.getRandom(maxSpeed, minSpeed);
            waterSpeed3 = DataUtil.getRandom(maxSpeed, minSpeed);
            waterSpeed4 = DataUtil.getRandom(maxSpeed, minSpeed);
            waterSpeed5 = DataUtil.getRandom(maxSpeed, minSpeed);
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
        return speedFlow;
    }

}
