package com.hawksoft.platform.service;

import com.hawksoft.platform.entity.Datadictionary;

import java.util.Map;

public interface DatadictionaryService {

    /**
     *查询数据字典信息
     * @param map
     * @return
     */
    Datadictionary getDatadictionary(Map<String,Object> map);
}
