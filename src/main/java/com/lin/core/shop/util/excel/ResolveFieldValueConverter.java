package com.lin.core.shop.util.excel;

import com.lin.core.shop.util.excel.config.FieldValue;

/**
 * @author yuanbin.lin
 */
public interface ResolveFieldValueConverter {

/**
 * 操作类型，导入或导出
 */
enum Type {
    EXPORT, IMPORT
}

/**
 * 解析配置中Field元素 处理后的值
 * @param bean Excel配置的JavaBean对象
 * @param value Excel原值
 * @param fieldValue FieldValue信息
 * @param type 导入或导出
 * @param rowNum 行号
 * @return 解析结果对应的value
 * @throws Exception
 */
public Object resolveFieldValue(Object bean, Object value, FieldValue fieldValue, Type type, int rowNum) throws Exception;
}