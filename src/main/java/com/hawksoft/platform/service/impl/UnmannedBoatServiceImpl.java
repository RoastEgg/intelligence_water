package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.UnmannedBoatDao;
import com.hawksoft.platform.entity.UnmannedBoat;
import com.hawksoft.platform.service.UnmannedBoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnmannedBoatServiceImpl implements UnmannedBoatService{

    @Autowired
    private UnmannedBoatDao unmannedBoatDao;

    @Override
    public int saveUnmannedBoat(UnmannedBoat unmannedBoat) {
        return unmannedBoatDao.saveUnmannedBoat(unmannedBoat);
    }

    @Override
    public int queryStationInfo(int stnId) {
        return unmannedBoatDao.queryStationInfo(stnId);
    }

}
