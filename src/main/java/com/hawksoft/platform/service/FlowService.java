package com.hawksoft.platform.service;

import com.hawksoft.platform.entity.Flow;
import java.util.List;
import java.util.Map;

public interface FlowService {

    /**
     * 通过站点ID查询实时流量汇总数据（最新即最近一个时间的数据）
     * @return
     */
    public List<Flow> queryLastFlowByStdId(int stnId);

    /**
     * 通过站点ID和时间查询该站点这段时间内流量汇总数据
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流量汇总数据
     */
    public List<Flow> queryFlowByStdIdTime(Map<String, Object> map);

    /**
     * 取最新的DAYS条实时水位
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流量汇总数据
     */
    public List<Flow> lastFlowRecordsNum(Map<String, Object> map);


    /**
     * 查询这段时间内流量汇总数据
     * @param map ，如果map有时间参数:startTime 开始时间， endTime 结束时间
     * @return
     */
    public List<Flow> queryAllFlowByTime(Map<String, Object> map);

    /**
     * 根据日期查询当日流量信息
     * @param map
     * @return
     */
    public Flow queryFlowByDate(Map<String,Object> map);

    /**
     * 根据日期查询本周流量信息
     * @param map
     * @return
     */
    public Flow queryFlowByWeek(Map<String,Object> map);

    /**
     * 根据日期查询当月流量信息
     * @param map
     * @return
     */
    public Flow queryFlowByMonth(Map<String,Object> map);

    /**
     * 根据日期查询年流量信息
     * @param map
     * @return
     */
    public Flow queryFlowByYear(Map<String,Object> map);

    /**
     * 根据日期查询年流量信息
     * @param map
     * @return
     */
    public Flow queryAllFlow(Map<String,Object> map);

    /**
     * 保存流量汇总信息
     * @param flow
     */
    public int saveFlow(Flow flow);
    /**
     * 更新流量汇总信息
     * @param flow
     */
    public int updateFlow(Flow flow);
    /**
     * 删除流量汇总信息
     * @param flow
     */
    public int deleteFlow(Flow flow);
    /**
     * 获取最近的流量和水位信息（水位、流量、测试时间）
     * @param stnId
     * @return
     */
    public List<Map<String , Object>> queryLastFlowAndWater(int stnId);
    /**
     * 获取这个时间点的流量，水位和5个相机的5个视频url
     * @param map
     * @return
     */
    public List<Map<String , Object>> queryFlowBystnIdAndTime(Map<String ,Object> map);

    /**
     * 查询某站点是否有流量信息
     * @param stnId
     * @return 记录条数
     */
    public int queryStationInfo(int stnId);

}
