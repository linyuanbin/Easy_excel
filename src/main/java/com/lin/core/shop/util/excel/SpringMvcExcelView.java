package com.lin.core.shop.util.excel;



import com.lin.core.shop.util.excel.util.ExcelDownLoadUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yuanbin.lin
 */
public class SpringMvcExcelView extends AbstractView {

	/** Excel 名称 */
    public static final String EXCEL_NAME = "Excel.excelName";

	/** POI Workbook */
	public static final String EXCEL_WORKBOOK = "Excel.workbook";
	
	/** 当没有数据时提示的消息 */
	public static final String EXCEL_EMPTY_MESSAGE = "Excel.emptyMessage";
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
										   HttpServletResponse response) throws Exception {
		Workbook workbook = (Workbook) model.get(EXCEL_WORKBOOK);
		String excelName = MapUtils.getString(model, EXCEL_NAME);
		String emptyMessage = MapUtils.getString(model, EXCEL_EMPTY_MESSAGE);
		if(StringUtils.isBlank(emptyMessage)){
			emptyMessage="没有可以导出的数据";
		}
		ExcelDownLoadUtil.downLoadExcel(workbook, excelName,emptyMessage, request, response);
	}

}