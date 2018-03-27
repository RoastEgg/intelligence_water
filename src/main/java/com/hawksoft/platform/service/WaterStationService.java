package com.hawksoft.platform.service;

import java.util.List;
import java.util.Map;

public interface WaterStationService {
    /**
     * 通过站点ID查询断面图
     * @param stnId
     * @return 返回stnId和断面图路径
     */

    public List<Map<String ,Object>> querySectionMap(int stnId);
}
