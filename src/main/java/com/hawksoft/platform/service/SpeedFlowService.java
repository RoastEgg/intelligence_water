package com.hawksoft.platform.service;

import com.hawksoft.platform.VO.SpeedFlowVO;
import com.hawksoft.platform.entity.SpeedFlow;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SpeedFlowService {

    /**
     * 通过站点ID查询实时流速流量数据（最新即最近一个时间的数据）
     * @return
     */
    public SpeedFlowVO queryLastSpeedFlowByStdId(int stnId);

    /**
     * 通过站点ID和时间查询该站点这段时间内流速流量数据
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流速流量数据
     */

    public List<SpeedFlow> querySpeedFlowByStdIdTime(Map<String, Object> map);

    /**
     * 通过站点ID和时间查询该站点这段时间内流速流量数据
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间,cameraNo 摄像机序号为1至5号)
     * @return 返回该站点这段时间内流速流量数据
     */
    public List<SpeedFlow> querySpeedFlowByStdIdTimeCamera(Map<String, Object> map);

    /**
     * 查询这段时间内流速流量数据
     * @param map ，如果map有时间参数:startTime 开始时间， endTime 结束时间
     * @return
     */
    public List<SpeedFlow> queryAllSpeedFlowByTime(Map<String, Object> map);



    /**
     * 保存流速流量信息
     * @param speedFlow
     */
    public int saveSpeedFlowAndFlow(SpeedFlow speedFlow);

    /**
     * 保存流速信息
     * @param speedFlow
     * @return
     */
    public int saveSpeedFlow(SpeedFlow speedFlow);

    /**
     * 更新流速流量信息
     * @param speedFlow
     */
    public int updateSpeedFlow(SpeedFlow speedFlow);

    /**
     * 删除流速流量信息
     * @param speedFlow
     */
    public int deleteSpeedFlow(SpeedFlow speedFlow);

    /**
     * 通过站点ID和采集时间取得唯一一条获取站点的流速流量记录
     * @param map
     */
    public SpeedFlow findSpeedFlowByStdIdTime(Map<String, Object> map);

    /**
     * 取最新的DAYS条流速流量数据
     * @param map
     */
    public List<SpeedFlow> getRecentRecords(Map<String, Object> map);


    /**
     * 为了给生成历史数据和schedule复用，将生成数据方法独立出来
     * @param stnId
     * @param date
     * @return
     */
    public int generateData(int stnId,Date date);

    /**
     * 通过站点ID查询流量图
     * @param stnId
     * @return
     */
    public String queryFlowMap(int stnId);

}
