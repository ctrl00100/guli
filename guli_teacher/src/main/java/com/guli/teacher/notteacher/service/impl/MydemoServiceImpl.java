package com.guli.teacher.notteacher.service.impl;

import com.guli.teacher.entity.EduSubject;
import com.guli.teacher.notteacher.entity.Mydemo;
import com.guli.teacher.notteacher.mapper.MydemoMapper;
import com.guli.teacher.notteacher.service.MydemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.teacher.service.EduSubjectService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * mydemo 服务实现类
 * </p>
 *
 * @author guli
 * @since 2020-02-03
 */
@Service
public class MydemoServiceImpl extends ServiceImpl<MydemoMapper, Mydemo> implements MydemoService {


    @Autowired
    private MydemoService subjectService;


    @Override
    public List<String> importExl(MultipartFile file) {
        List<String> msg = new ArrayList<>();
        try {
            //1、获取文件流
            InputStream inputStream = file.getInputStream();
            //2、创建workbook
            Workbook workbook = null;
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), ".xls")) {
                workbook = new HSSFWorkbook(inputStream);

            } else {
                workbook = new XSSFWorkbook(inputStream);

            }

            //3 根据workbook获取sheet
            Sheet sheet = workbook.getSheetAt(0);
//            获取表格最后一个索引位置也就是行数
            int lastRowNum = sheet.getLastRowNum();
            System.err.println("获取行数：" + lastRowNum);
            if (lastRowNum <= 1) {
                msg.add("请填写数据");
                return msg;
            }
            //遍历行数
            for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
                Row rowData = sheet.getRow(rowNum);


                //根据每一行获取列表数据
                //获取第一列
                Cell cellOne = rowData.getCell(0);
                cellOne.setCellType(Cell.CELL_TYPE_STRING);

                //根据cell获取第一列里面内容
                if (cellOne == null) {
                    msg.add("第" + rowNum + "行第一列数据为空");
                    //跳到下一行进行操作
                    continue;
                }
                String cellOneValue = cellOne.getStringCellValue();
                if (StringUtils.isEmpty(cellOneValue)) {
                    msg.add("第" + rowNum + "行第一列数据为空");
                    //跳到下一行进行操作
                    continue;
                }

                //cellOneValue 是一级分类名称
                //判断添加的一级分类名称在数据库表里面是否有相同的，如果没有添加
//                EduSubject eduSubject = this.existOneSubject(cellOneValue);
                //为了后面添加二级分类，一级分类id
//                String pid = null;
//                if(eduSubject == null) { //表没有相同的一级分类
                //添加到数据库里面
                Mydemo subjectOne = new Mydemo();
                subjectOne.setJ(Float.parseFloat(cellOneValue));

//                    baseMapper.insert(subjectOne);
                //添加一级分类之后，获取添加之后id值
//                    pid = subjectOne.getId();
//                } else {
//                    //有相同的一级分类
//                    pid = eduSubject.getId();
//                }

                //获取第二列
                Cell cellTwo = rowData.getCell(1);
                cellTwo.setCellType(Cell.CELL_TYPE_STRING);

                if (cellTwo == null) {
                    msg.add("第" + rowNum + "行第二列数据为空");
                    continue;
                }
                // 根据cell获取第二列里面内容
                String cellTwoValue = cellTwo.getStringCellValue();

//                double cellTwoValue = cellTwo.getNumericCellValue();
                if (StringUtils.isEmpty(cellTwoValue)) {
                    msg.add("第" + rowNum + "行第二列数据为空");
                    continue;
                }
                //cellTwoValue 是二级分类名称
//                EduSubject eduSubjectTwo = this.existTwoSubject(cellTwoValue, pid);
//                if(eduSubjectTwo == null) {
                //没有相同的二级分类，实现二级分类添加
//                    EduSubject subjectTwo = new EduSubject();
                subjectOne.setW(Float.parseFloat(cellTwoValue));
//                    subjectTwo.setParentId(pid);
//                    baseMapper.insert(subjectTwo);
//                }

                //获取第3列

                Cell cell3 = rowData.getCell(2);
                cell3.setCellType(Cell.CELL_TYPE_STRING);
                System.out.println(cell3);
                // 根据cell获取第二列里面内容
                String cellValue3 = cell3.getStringCellValue();

                if (StringUtils.isEmpty(cellValue3)) {
                    msg.add("第" + rowNum + "行第二列数据为空");
                    continue;
                }
                subjectOne.setT(Float.parseFloat(cellValue3));
//获取第3列

                Cell cell4 = rowData.getCell(3);
                cell4.setCellType(Cell.CELL_TYPE_STRING);

                // 根据cell获取第二列里面内容
                String cellValue4 = cell4.getStringCellValue();
                if (StringUtils.isEmpty(cellValue4)) {
                    msg.add("第" + rowNum + "行第二列数据为空");
                    continue;
                }
                subjectOne.setS( cellValue4);

                subjectService.save(subjectOne);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

}

