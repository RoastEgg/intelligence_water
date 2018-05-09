package com.hawksoft.platform.util;

import com.hawksoft.platform.entity.Datadictionary;
import com.hawksoft.platform.service.DatadictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FloatGradeUtil {
    @Autowired
    private DatadictionaryService datadictionaryService;

    /**
     * 漂浮物评级
     * @param avgArea
     * @return
     */
    public int getFloatGrade(Double avgArea){
        Map<String,Object> condition=new HashMap<>();
        condition.put("pid",1);
        int i;
        for (i=1;i<=5;i++) {
            condition.put("dicname",i);
            Datadictionary data=datadictionaryService.getDatadictionary(condition);
            Double value=Double.parseDouble(data.getValue());
            if(avgArea<=value){
                return i;
            }
        }
        return i;
    }

}
