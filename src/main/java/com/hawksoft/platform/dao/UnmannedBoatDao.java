package com.hawksoft.platform.dao;

import com.hawksoft.platform.entity.UnmannedBoat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnmannedBoatDao {
    /**
     * 保存无人船信息
     * @param unmannedBoat
     */
    public int saveUnmannedBoat(UnmannedBoat unmannedBoat);

    /**
     * 查询某站点是否有无人船信息
     * @param stnId
     * @return 记录条数
     */
    public int queryStationInfo(int stnId);


}
