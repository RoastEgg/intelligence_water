package com.hawksoft.platform.generatorMapper;

import com.hawksoft.platform.generatorPojo.Flow;

public interface FlowMapper {
    int insert(Flow record);

    int insertSelective(Flow record);
}