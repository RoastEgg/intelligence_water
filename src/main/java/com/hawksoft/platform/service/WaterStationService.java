package com.hawksoft.platform.service;

import com.hawksoft.platform.entity.WaterStation;

import java.util.List;

public interface WaterStationService {
    /**
     * 通过站点ID查询断面图
     * @param stnId
     * @return 返回断面图路径
     */
    public List<WaterStation> querySectionMap(int stnId);
}
