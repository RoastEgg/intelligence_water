package com.hawksoft.platform.dao;

import com.hawksoft.platform.entity.UnmannedBoat;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 设置一下数据库里已经的历史数据的时间这个字段
     * @param map
     */
    public void setUBtime(Map<String,Object> map);

}
