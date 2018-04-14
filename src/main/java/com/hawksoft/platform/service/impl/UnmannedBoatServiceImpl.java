package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.UnmannedBoatDao;
import com.hawksoft.platform.entity.UnmannedBoat;
import com.hawksoft.platform.service.UnmannedBoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public void setUBtime() {
        Calendar cal = Calendar.getInstance();
        cal.set(2018,3,13,0,0,0);
        cal.set(Calendar.MILLISECOND,0);
        for (int i=1;i<=6555;i++){
            Map<String,Object> map = new HashMap<>();
            cal.add(Calendar.MILLISECOND,100);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String time = formatter.format(cal.getTime());
            map.put("id",i);
            map.put("time",time);
            unmannedBoatDao.setUBtime(map);
        }
    }

}
