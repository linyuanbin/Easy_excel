package com.lin.core.shop.util.excel.util;

import com.lin.core.shop.util.excel.ExcelContext;
import com.lin.core.shop.util.excel.parsing.ExcelError;
import com.lin.core.shop.util.excel.result.ExcelExportResult;
import com.lin.core.shop.util.excel.result.ExcelImportResult;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author yuanbin.lin
 * @date 2018/11/15
 */
@Service
public class MyExcelTool {

    /**
     * 导入校验
     *
     * @param multipartFile
     * @param modelId
     * @param titleIndex
     * @param <T>
     * @return
     */
    public <T> List<T> getBeans(MultipartFile multipartFile, String xmlFile, String modelId, int titleIndex) {
        if (multipartFile == null) {
            throw new IllegalArgumentException(String.valueOf("文件为空"));
        }
        ExcelContext context = new ExcelContext(xmlFile);
        ExcelImportResult result = null;
        try {
            result = context.readExcel(modelId, titleIndex, multipartFile.getInputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过导入结果集的hasErrors方法判断
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ExcelError err : result.getErrors()) {
                sb.append(err.getErrorMsg());
            }
            throw new IllegalArgumentException(String.valueOf("导入包含错误信息" + sb.toString()));
        }
        return result.getListBean();
    }

    /**
     * 导出
     */
    public boolean export(String fileName, HttpServletResponse response, String xmlFile, List<T> beans, String modelId) throws IOException {
        setExcelResponseHeader(response, fileName);
        OutputStream ops = response.getOutputStream();
        //"excel-config.xml"
        ExcelContext context = new ExcelContext(xmlFile);
        ExcelExportResult exportResult = null;
        try {
            exportResult = context.createExcelForPart(modelId, beans);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Workbook workbook = exportResult.build();
        workbook.write(ops);
        ops.close();
        workbook.close();
        return true;
    }

    /**
     * 设置response响应头
     */
    private static void setExcelResponseHeader(HttpServletResponse response, String fileName)
            throws UnsupportedEncodingException {
        fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1") + ".xlsx";
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setHeader("Pragma", "No-cache");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    }
}
