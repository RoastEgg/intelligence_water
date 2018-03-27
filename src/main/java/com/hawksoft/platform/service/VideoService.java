package com.hawksoft.platform.service;

import com.hawksoft.platform.entity.Video;

import java.util.List;
import java.util.Map;

public interface VideoService {


    /**
     * 根据站点id和视频类型查询视频URL
     * @param map
     * @return返回视频URL列表
     */
    public List<Map<String,Object>> queryURL(Map<String ,Object> map);
}
