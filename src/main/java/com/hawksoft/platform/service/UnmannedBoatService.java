package com.hawksoft.platform.service;

import com.hawksoft.platform.entity.UnmannedBoat;

public interface UnmannedBoatService {

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
     */
    public void setUBtime();
}
