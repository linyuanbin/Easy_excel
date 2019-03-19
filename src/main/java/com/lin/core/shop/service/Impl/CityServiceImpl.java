package com.lin.core.shop.service.Impl;

import com.lin.core.shop.bean.CityCodeMapping;
import com.lin.core.shop.bean.CityCodeMappingExample;
import com.lin.core.shop.dao.CityCodeMappingMapper;
import com.lin.core.shop.service.CityService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuanbin.lin
 * @date 2018/11/14
 */
@Service
public class CityServiceImpl implements CityService {
    @Resource
    private CityCodeMappingMapper cityCodeMappingMapper;
    @Override
    public List<CityCodeMapping> getAllCity() {
        CityCodeMappingExample example=new CityCodeMappingExample();
        List<CityCodeMapping> cityCodeMappings = cityCodeMappingMapper.selectByExample(example);
        return CollectionUtils.isEmpty(cityCodeMappings) ? new ArrayList<CityCodeMapping>() : cityCodeMappings;
    }
}
