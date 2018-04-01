package com.hawksoft.platform.service;

import com.hawksoft.platform.entity.FloatingMatter;

import java.util.List;
import java.util.Map;

public interface FloatingMatterService {

    /**
     * 通过站点ID查询历史漂浮物数据
     * @return
     */
    public List<FloatingMatter> findHistFloatingMatters(Map<String,Object> map);

    /**
     * 通过日期查询漂浮物数据
     * @param map
     * @return
     */
    public List<FloatingMatter> queryFMByDate(Map<String,Object> map);

    /**
     * 通过站点ID和时间查询该站点这段时间内的漂浮物记录数
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流量汇总数据
     */
    public List<Map<String,Object>> queryRecordsNumByTime(Map<String, Object> map);

    /**
     * 通过站点ID和时间查询该站点这段时间内的漂浮物记录数
     * @param map(stnId 站点编号,startTime 开始时间,endTime 结束时间)
     * @return 返回该站点这段时间内流量汇总数据
     */
    public List<Map<String,Object>> queryLastDaysRecordsNum(Map<String, Object> map);

    /**
     * 保存漂浮物数据信息
     * @param floatingMatter
     */
    public int saveFloatingMatter(FloatingMatter floatingMatter);

    /**
     * 根据站点Id查询最新的漂浮物图片
     * @param stnId
     * @return
     */
    public List<FloatingMatter> queryLastRecord(int stnId);

    /**
     * 查询某站点是否有漂浮物信息
     * @param stnId
     * @return 记录条数
     */
    public int queryStationInfo(int stnId);
}
