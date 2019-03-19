package com.lin.core.shop.util.excel;


import com.lin.core.shop.util.excel.config.ExcelDefinition;

import java.util.Map;

/**
 * Excel定义接口
 *
 * @author yuanbin.lin
 */
public interface ExcelDefinitionReader {
    /**
	 * 获取 ExcelDefinition注册信息
	 * @return
	 */
	Map<String, ExcelDefinition> getRegistry();
}