package com.hawksoft.platform.generatorMapper;

import com.hawksoft.platform.generatorPojo.Floatingmatter;

public interface FloatingmatterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Floatingmatter record);

    int insertSelective(Floatingmatter record);

    Floatingmatter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Floatingmatter record);

    int updateByPrimaryKey(Floatingmatter record);
}