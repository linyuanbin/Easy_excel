package com.lin.core.shop.dao;

import com.lin.core.shop.bean.CityCodeMapping;
import com.lin.core.shop.bean.CityCodeMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CityCodeMappingMapper {
    long countByExample(CityCodeMappingExample example);

    int deleteByExample(CityCodeMappingExample example);

    int deleteByPrimaryKey(Integer areaid);

    int insert(CityCodeMapping record);

    int insertSelective(CityCodeMapping record);

    List<CityCodeMapping> selectByExample(CityCodeMappingExample example);

    CityCodeMapping selectByPrimaryKey(Integer areaid);

    int updateByExampleSelective(@Param("record") CityCodeMapping record, @Param("example") CityCodeMappingExample example);

    int updateByExample(@Param("record") CityCodeMapping record, @Param("example") CityCodeMappingExample example);

    int updateByPrimaryKeySelective(CityCodeMapping record);

    int updateByPrimaryKey(CityCodeMapping record);
}