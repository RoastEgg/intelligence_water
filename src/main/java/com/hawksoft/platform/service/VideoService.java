package com.hawksoft.platform.service;

import com.hawksoft.platform.entity.Video;

import java.util.List;

public interface VideoService {

    /**
     * 根据站点id和视频类型查询视频URL
     * @param stnId
     * @param type
     * @return 返回视频URL列表
     */
    public List<Video> queryURL(int stnId, String type);
}
