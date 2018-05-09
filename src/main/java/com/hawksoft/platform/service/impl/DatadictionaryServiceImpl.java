package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.DatadictionaryDao;
import com.hawksoft.platform.entity.Datadictionary;
import com.hawksoft.platform.service.DatadictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DatadictionaryServiceImpl implements DatadictionaryService {

    @Autowired
    private DatadictionaryDao datadictionaryDao;
    /**
     *查询数据字典信息
     * @param map
     * @return
     */
    public Datadictionary getDatadictionary(Map<String,Object> map){
        return datadictionaryDao.getDatadictionary(map);
    }

}
