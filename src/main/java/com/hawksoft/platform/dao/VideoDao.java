package com.hawksoft.platform.dao;

import com.hawksoft.platform.entity.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoDao {

    /**
     * 根据站点id和视频类型查询视频URL
     * @param stnId
     * @param type
     * @return 返回视频URL列表
     */
    public List<Video> queryURL(int stnId, String type);
}
