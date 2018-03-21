package com.hawksoft.platform.dao;

import com.hawksoft.platform.entity.FloatingMatter;
import com.hawksoft.platform.entity.FloatingMatter;
import com.hawksoft.platform.entity.Flow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FloatingMatterDao {

    /**
     * 查询历史漂浮物纪录总数
     * @return
     */
    public List<Map<String,Object>> queryRecordsNumByDayTime(Map<String,Object> map);

    /**
     * 根据天数查询最近有记录的n天的纪录数
     * @param map
     * @return
     */
    public List<Map<String,Object>> queryLastDaysRecordsNum(Map<String,Object> map);

    /**
     * 根据日期查询漂浮物数据
     * @param map
     * @return
     */
    public List<FloatingMatter> queryFMByDate(Map<String,Object> map);

    /**
     * 根据条件查询详细的漂浮物数据
     * @param map
     * @return
     */
    public List<FloatingMatter> findFMByTime(Map<String,Object> map);

    /**
     * 添加数据
     * @param floatingMatter
     * @return
     */
    public int saveFloatingMatter(FloatingMatter floatingMatter);

    /**
     * 更新流量汇总信息
     * @param floatingMatter
     */
    public int updateFloatingMatter(FloatingMatter floatingMatter);

}