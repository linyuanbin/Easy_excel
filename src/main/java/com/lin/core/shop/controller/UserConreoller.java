package com.lin.core.shop.controller;


import com.lin.core.shop.bean.CityCodeMapping;
import com.lin.core.shop.bean.User;
import com.lin.core.shop.bean.request.FileRequest;
import com.lin.core.shop.service.CityService;
import com.lin.core.shop.service.UserService;
import com.lin.core.shop.util.excel.ExcelContext;
import com.lin.core.shop.util.excel.result.ExcelExportResult;
import com.lin.core.shop.util.excel.util.MyExcelTool;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author yuanbin.lin
 * @date 2018/11/13
 */
@RequestMapping("/user/")
@Controller
public class UserConreoller {

    @Resource
    private UserService userService;

    @Resource
    private CityService cityService;

    @Resource
    private MyExcelTool myExcelTool;

    @RequestMapping("getAllUser/v1")
    public String getAllUser(Model model) {

        List<User> allUser = userService.getAllUser();
        model.addAttribute("users", allUser);
        return "user";
    }

    @RequestMapping("export/v1")
    public void exportUsers(HttpServletResponse response) throws IOException {
        String fileName = String.format("Student%s", new Date());
        setExcelResponseHeader(response, fileName);
        OutputStream ops = response.getOutputStream();
        ExcelContext context = new ExcelContext("excel-config.xml");
        //List<User> allUser = userService.getAllUser();
        List<User> allUser = new ArrayList<>();
        User user=new User();
        user.setCode("颜色");
        user.setCode("编码");
        allUser.add(user);
        ExcelExportResult exportResult = null;
        try {
            exportResult = context.createExcelForPart("student", allUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //假设这是第二次从数据库或其他平台查询到到数据
       // List<CityCodeMapping> allCity = cityService.getAllCity();
        Workbook workbook = exportResult.build();
        workbook.write(ops);
        ops.close();
        workbook.close();
    }

    @RequestMapping("exportUserAndCity/v1")
    public void exportUsersAndCitys(HttpServletResponse response) throws IOException {
        String fileName = String.format("Student%s", new Date());
        setExcelResponseHeader(response, fileName);
        OutputStream ops = response.getOutputStream();
        ExcelContext context = new ExcelContext("excel-config.xml");
        List<User> allUser = userService.getAllUser();
        List<CityCodeMapping> allCity = cityService.getAllCity();
        ExcelExportResult exportResult = null;
        try {
            Map<String, List> map = new HashedMap();
            map.put("student", allUser);
            map.put("city", allCity);
            exportResult = context.createMultiSheetExcel(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Workbook workbook = exportResult.build();
        workbook.write(ops);
        ops.close();
        workbook.close();
    }

    @RequestMapping("cityExport/v1")
    public void exportCitys(HttpServletResponse response) throws IOException {
        String fileName = String.format("cityDetail%s", new Date());
        setExcelResponseHeader(response, fileName);
        OutputStream ops = response.getOutputStream();
        ExcelContext context = new ExcelContext("excel-config.xml");
        List<CityCodeMapping> allCity = cityService.getAllCity();
        ExcelExportResult exportResult = null;
        try {
            exportResult = context.createExcelForPart("city", allCity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Workbook workbook = exportResult.build();
        workbook.write(ops);
        ops.close();
        workbook.close();
    }

    @RequestMapping("import/v1")
    public String importUser(@RequestParam("file") MultipartFile multipartFile) {
        //第二个参数需要注意,它是指标题索引的位置,可能你的前几行并不是标题,而是其他信息,
        //比如数据批次号之类的,关于如何转换成javaBean,具体参考配置信息描述
        String id = "student";
        List<User> users = myExcelTool.getBeans(multipartFile, "excel-config.xml", id, 1);
        for (User stu : users) {
            System.out.println(stu.getUsername());
            userService.insert(stu);
        }
        return "index";
    }

    /**
     * 上传多个文件
     *
     * @param request
     * @return
     */
    @RequestMapping("upload/v1")
    public String upload(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            List<MultipartFile> files = request.getFiles(fileName);
            System.out.println(files.size());
        }
        return "index";
    }

    /**
     * 设置response响应头
     */
    private static void setExcelResponseHeader(HttpServletResponse response, String fileName)
            throws UnsupportedEncodingException {
        response.setCharacterEncoding("utf-8");
        fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1") + ".xlsx";
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setHeader("Pragma", "No-cache");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    }

    //加密
 /*   public String encodePassword(String password){
        //加盐 的思路 加上一定乱序字符串增加复杂度
        //password="sdsdsdnskksn" + password +"sdjsnsck";

        //1.一层 MD5 算法加密
        String algorithm="MD5";
        char[] chars=null;
        try {
            MessageDigest instance = MessageDigest.getInstance(algorithm); //使用jdk提供的MD5加密
            //加密结果
            byte[] digest = instance.digest(password.getBytes());

            //2.二层 十六进制
            chars = Hex.encodeHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new String(chars);
    }*/

}
