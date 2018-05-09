package com.hawksoft.platform.dao;

import com.hawksoft.platform.entity.Datadictionary;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DatadictionaryDao {
    /**
     *查询数据字典信息
     * @param map
     * @return
     */
    Datadictionary getDatadictionary(Map<String,Object> map);
}
