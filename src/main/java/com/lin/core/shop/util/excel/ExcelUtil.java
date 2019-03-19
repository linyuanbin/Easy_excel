package com.lin.core.shop.util.excel;

import com.lin.core.shop.util.excel.parsing.ExcelHeader;
import com.lin.core.shop.util.excel.result.ExcelImportResult;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * Excel 导出方法调用(主)
 *
 * @author yuanbin.lin
 */
public class ExcelUtil<T> {

//创建excel上下文实例,配置文件路径
private ExcelContext context;
//Excel配置文件中配置的id
private String excelId;

public ExcelUtil(ExcelContext context, String excelId) {
    this.context = context;
    this.excelId = excelId;
}

/**
 * @param startRow 头部从第几行开始导入
 * @param fis      文件流
 * @return
 * @throws Exception
 */
public List<T> importExcel(int startRow, InputStream fis) throws Exception {
    //第二个参数需要注意,它是指标题索引的位置,可能你的前几行并不是标题,而是其他信息,
    //比如数据批次号之类的,关于如何转换成javaBean,具体参考配置信息描述
    ExcelImportResult result = context.readExcel(excelId, startRow, fis);
//        System.out.println(result.getHeader());
    List<T> stus = result.getListBean();
    return stus;
    //这种方式和上面的没有任何区别,底层方法默认标题索引为0
    //context.readExcel(excelId, fis);
}

    /**
     * 导出Excel并下载
     * @param response
     * @param list     结果集,null默认导出模板
     * @param header   自定义表头,null默认无
     * @param specifyFields 导出字段,null导出所有字段
     * @param fileName  下载的文件名
     */
    public void exportExcel(HttpServletResponse response, List<T> list, ExcelHeader header, List<String> specifyFields, String fileName) {
        File file = null;
        OutputStream ops = null;
        OutputStream out = null;
        Workbook workbook = null;
        try {
            /**
             * Step1:创建临时xlsx文件
             */
            file = File.createTempFile("tmp", ".xlsx");
            /**
             * Step2:导出数据写入临时xlsx文件
             */
            String path = file.getAbsolutePath();
            ops = new FileOutputStream(path);
            //获取POI创建结果
            if (list != null && !list.isEmpty()) {
                workbook = context.createExcel(excelId, list, header, specifyFields);
//                workbook = context.createExcel(excelId, list);
            } else {//查询结果为空,导出模板
                workbook = context.createExcelTemplate(excelId, header, specifyFields);
            }
            workbook.write(ops);
            /**
             * Step3:下载临时xlsx文件
             */
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            fileName = URLEncoder.encode(fileName + ".xlsx", "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file.deleteOnExit();//临时文件若存在,则删除
            try {
                if (ops != null) {
                    ops.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

 }