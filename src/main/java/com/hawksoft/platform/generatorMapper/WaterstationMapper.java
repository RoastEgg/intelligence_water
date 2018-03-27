package com.hawksoft.platform.generatorMapper;

import com.hawksoft.platform.generatorPojo.Waterstation;

public interface WaterstationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Waterstation record);

    int insertSelective(Waterstation record);

    Waterstation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Waterstation record);

    int updateByPrimaryKey(Waterstation record);
}