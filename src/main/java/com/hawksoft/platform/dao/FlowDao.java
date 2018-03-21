package com.hawksoft.platform.dao;


import com.hawksoft.platform.entity.Flow;
import com.hawksoft.platform.entity.WaterQuality;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FlowDao {

    /**
     * 通过站点ID查询实时流量汇总数据（最新即最近一个时间的数据）
     * @return
     */
    public List<Flow> queryLastFlowByStdId(int stnId);

    /**
     * 通过站点ID和时间查询该站点这段时间内流量汇总数据
     * @param map(stnId 站点ID， days 取最新的多少条记录)
     */
    public List<Flow> queryFlowByStdIdTime(Map<String, Object> map);

    /**
     * 取最新的DAYS条实时水位
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流量汇总数据
     */
    public List<Flow> lastFlowRecordsNum(Map<String, Object> map);

    /**
     * 根据日期查询当日流量值
     * @param map
     * @return
     */
    public Flow queryFlowByDate(Map<String,Object> map);

    /**
     * 根据日期查询本周流量值
     * @param map
     * @return
     */
    public Flow queryFlowByWeek(Map<String,Object> map);

    /**
     * 根据日期查询本月流量值
     * @param map
     * @return
     */
    public Flow queryFlowByMonth(Map<String,Object> map);

    /**
     * 根据日期查询本年流量值
     * @param map
     * @return
     */
    public Flow queryAllFlow(Map<String,Object> map);

    /**
     * 根据日期查询本年流量值
     * @param map
     * @return
     */
    public Flow queryFlowByYear(Map<String,Object> map);

    /**
     * 查询这段时间内流量汇总数据
     * @param map ，如果map有时间参数:startTime 开始时间， endTime 结束时间
     * @return
     */
    public List<Flow> queryAllFlowByTime(Map<String, Object> map);

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

}
