package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.VideoDao;
import com.hawksoft.platform.entity.Video;
import com.hawksoft.platform.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Override
    public List<Map<String,Object>> queryURL(Map<String ,Object> map) {
        return videoDao.queryURL(map);
    }
}
