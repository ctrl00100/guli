import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

public class WriteTest {

    /**
     * poi写入Excl表格 03版本的
     */
    @Test
    public void Writer03() throws Exception{

        //1、创建工作表workbook
        Workbook workbook = new HSSFWorkbook();
        //2、在工作表中创建Sheet
        Sheet sheet = workbook.createSheet("会员管理");
        //3、在这个Sheet中在创建行数
        //创建行和列：他们的索引是从0开始的
        Row row = sheet.createRow(0);
        //4、在行数中创建列
        Cell cell1 = row.createCell(0);
        //5、在响应的列对应的单元格中设置数据
        cell1.setCellValue("人数");

        Cell cell12 = row.createCell(1);
        cell12.setCellValue(12345);
        //6、写入磁盘
        FileOutputStream outputStream = new FileOutputStream("D:/0401.xls");
        workbook.write(outputStream);
        //7、关闭流
        outputStream.close();

    }

    /**
     * poi写入Excl表格 03版本的
     */
    @Test
    public void Writer07() throws Exception{

        //1、创建工作表workbook
        Workbook workbook = new XSSFWorkbook();
        //2、在工作表中创建Sheet
        Sheet sheet = workbook.createSheet("会员管理");
        //3、在这个Sheet中在创建行数
        //创建行和列：他们的索引是从0开始的
        Row row = sheet.createRow(0);
        //4、在行数中创建列
        Cell cell1 = row.createCell(0);
        //5、在响应的列对应的单元格中设置数据
        cell1.setCellValue("人数");

        Cell cell12 = row.createCell(1);
        cell12.setCellValue(12345);
        //6、写入磁盘
        FileOutputStream outputStream = new FileOutputStream("D:/0401.xlsx");
        workbook.write(outputStream);
        //7、关闭流
        outputStream.close();

    }

    /**
     * 写入的时候、写的是大数据
     * 比如： 几万条数据
     * 时间考虑性能
     *
     * 03版本：
     */
    @Test
    public void WriterData03() throws  Exception{

        Long begin = System.currentTimeMillis();

        //1、创建工作表workbook
        Workbook workbook = new HSSFWorkbook();
        //2、在工作表中创建Sheet
        Sheet sheet = workbook.createSheet("会员管理");
        //3、在这个Sheet中在创建行数
        //创建行和列：他们的索引是从0开始的
        for(int i = 0; i < 65535; i++){
            Row row = sheet.createRow(i);
            //4、在行数中创建列
            Cell cell1 = row.createCell(0);
            //5、在响应的列对应的单元格中设置数据
            cell1.setCellValue("人数"+i);
        }

        //6、写入磁盘
        FileOutputStream outputStream = new FileOutputStream("D:/0401-03.xls");
        workbook.write(outputStream);

        Long end = System.currentTimeMillis();

        System.err.println((double) (end-begin)/1000);

        //7、关闭流
        outputStream.close();

    }


    @Test
    public void WriterData07() throws  Exception{

        Long begin = System.currentTimeMillis();

        //1、创建工作表workbook
        Workbook workbook = new XSSFWorkbook();
        //2、在工作表中创建Sheet
        Sheet sheet = workbook.createSheet("会员管理");
        //3、在这个Sheet中在创建行数
        //创建行和列：他们的索引是从0开始的
        //如果是07 版本的话、能写大于65535数据
        for(int i = 0; i < 65537; i++){
            Row row = sheet.createRow(i);
            //4、在行数中创建列
            Cell cell1 = row.createCell(0);
            //5、在响应的列对应的单元格中设置数据
            cell1.setCellValue("人数"+i);
        }

        //6、写入磁盘
        FileOutputStream outputStream = new FileOutputStream("D:/0401-07.xlsx");
        workbook.write(outputStream);

        Long end = System.currentTimeMillis();

        System.err.println((double) (end-begin)/1000);

        //7、关闭流
        outputStream.close();

    }


    @Test
    public void SXWriterData07() throws  Exception{

        Long begin = System.currentTimeMillis();

        //1、创建工作表workbook
        Workbook workbook = new SXSSFWorkbook();
        //2、在工作表中创建Sheet
        Sheet sheet = workbook.createSheet("会员管理");
        //3、在这个Sheet中在创建行数
        //创建行和列：他们的索引是从0开始的
        //如果是07 版本的话、能写大于65535数据
        for(int i = 0; i < 100000; i++){
            Row row = sheet.createRow(i);
            //4、在行数中创建列
            Cell cell1 = row.createCell(0);
            //5、在响应的列对应的单元格中设置数据
            cell1.setCellValue("人数"+i);
        }

        //6、写入磁盘
        FileOutputStream outputStream = new FileOutputStream("D:/0401SXSSF-07.xlsx");
        workbook.write(outputStream);


        Long end = System.currentTimeMillis();

        System.err.println((double) (end-begin)/1000);

        //7、关闭流
        outputStream.close();

        ((SXSSFWorkbook)workbook).dispose();
    }
}
