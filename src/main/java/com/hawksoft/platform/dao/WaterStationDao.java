package com.hawksoft.platform.dao;

import com.hawksoft.platform.entity.WaterStation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WaterStationDao {

    /**
     * 通过站点ID查询断面图
     * @param stnId
     * @return 返回stnId和断面图路径
     */
    public List<Map<String ,Object>> querySectionMap(int stnId);

    /**
     * 获取所有站点基本信息
     * @return
     */
    public List<WaterStation> queryStationBaseInfo();

    /**
     * 部分站点没有水位水质等信息，只有历史信息，包括PDF信息和picture路径
     */
    public String queryHistoryInfo(int stnId);

    /**
     * 通过站点名称查询站点ID
     * @param name
     * @return
     */
    int queryIdByName(String name);

    /**
     * 通过站点ID查询站点名称
     * @param stnId
     * @return
     */
    String queryCodeById(int stnId);
}
