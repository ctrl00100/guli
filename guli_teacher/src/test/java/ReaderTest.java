import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;

public class ReaderTest {

    /**
     * 读取EXCL表格内容
     * @throws Exception
     */
    @Test
    public void reader03() throws Exception{

        //1、获取一个文件（EXCL）路径，获取流
        FileInputStream inputStream = new FileInputStream("D:/0401.xls");

        //2、根据这个流创建一个WorkBook
        Workbook workbook = new HSSFWorkbook(inputStream);
        //3、获取次工作表中的Sheet
        Sheet sheet = workbook.getSheetAt(0);
        //4、根据这个Sheet获取一行
        Row row = sheet.getRow(0);
        //5、获取列
        Cell cell1 = row.getCell(0);
        Cell cell2 = row.getCell(1);
        //6、根据列获取列中的数据
        String value = cell1.getStringCellValue();
        double numericCellValue = cell2.getNumericCellValue();
        System.err.println(value + "----" + numericCellValue);
        //7、关闭流
        inputStream.close();
    }


    @Test
    public void reader07() throws Exception{

        //1、获取一个文件（EXCL）路径，获取流
        FileInputStream inputStream = new FileInputStream("D:/0401.xlsx");

        //2、根据这个流创建一个WorkBook
        Workbook workbook = new XSSFWorkbook(inputStream);
        //3、获取次工作表中的Sheet
        Sheet sheet = workbook.getSheetAt(0);
        //4、根据这个Sheet获取一行
        Row row = sheet.getRow(0);
        //5、获取列
        Cell cell1 = row.getCell(0);
        Cell cell2 = row.getCell(1);
        //6、根据列获取列中的数据
        String value = cell1.getStringCellValue();
        double numericCellValue = cell2.getNumericCellValue();
        System.err.println(value + "----" + numericCellValue);
        //7、关闭流
        inputStream.close();
    }
}
