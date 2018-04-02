package com.hawksoft.platform.service;

import com.hawksoft.platform.entity.WaterStation;

import java.util.List;
import java.util.Map;

public interface WaterStationService {
    /**
     * 通过站点ID查询断面图
     * @param stnId
     * @return 返回stnId和断面图路径
     */

    public List<Map<String ,Object>> querySectionMap(int stnId);

    /**
     * 查询站点是否有水位、水质、流量、漂浮物、无人船信息
     * @param stnId
     * @return
     */
    public Map<String,Object> queryStationInfo(int stnId);

    /**
     * 查询所有站点信息
     * @return
     */
    public List<Map<String,Object>> queryAllStationInfo();
}
