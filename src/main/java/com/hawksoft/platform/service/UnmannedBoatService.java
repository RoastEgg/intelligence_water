package com.hawksoft.platform.service;

import com.hawksoft.platform.entity.UnmannedBoat;

public interface UnmannedBoatService {

    /**
     * 保存无人船信息
     * @param unmannedBoat
     */
    public int saveUnmannedBoat(UnmannedBoat unmannedBoat);
}
