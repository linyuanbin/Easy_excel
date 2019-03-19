import com.lin.core.shop.bean.User;
import com.lin.core.shop.service.UserService;
import com.lin.core.shop.util.excel.ExcelContext;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 * Created by yuanbin.lin on 2018/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class TestMain {

    //测试时文件磁盘路径
    private static String path = "src/test/resources/test-export-excel.xlsx";
    //配置文件路径
    private static ExcelContext context = new ExcelContext("excel-config.xml");
    //Excel配置文件中配置的id
    private static String excelId = "student";

    /**
     * 导出空数据模板示例1
     * @throws Exception
     */

    @Test
    public void testCreateExcelTemplate() throws Exception{
        OutputStream ops = new FileOutputStream(path);
        Workbook workbook = context.createExcelTemplate(excelId,null,null);
        workbook.write(ops);
        ops.close();
        workbook.close();
    }

    @Resource
    private UserService userService;
    @Test
    public void testUser(){
        User user=new User();
        user.setCode("ssss");
        user.setUsername("rer");
        System.out.println(userService.insert(user)+"----");;

    }



}
