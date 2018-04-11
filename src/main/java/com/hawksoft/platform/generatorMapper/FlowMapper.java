package com.hawksoft.platform.generatorMapper;

import com.hawksoft.platform.generatorPojo.Flow;

public interface FlowMapper {
    int deleteByPrimaryKey(Long flowid);

    int insert(Flow record);

    int insertSelective(Flow record);

    Flow selectByPrimaryKey(Long flowid);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);
}