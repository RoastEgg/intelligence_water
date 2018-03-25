package com.hawksoft.platform.dao;

import com.hawksoft.platform.entity.WaterStation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterStationDao {

    /**
     * 通过站点ID查询断面图
     * @param stnId
     * @return 返回断面图路径
     */
    public List<WaterStation> querySectionMap(int stnId);

}
