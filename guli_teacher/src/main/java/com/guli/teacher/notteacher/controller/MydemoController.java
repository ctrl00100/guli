package com.guli.teacher.notteacher.controller;


import com.guli.common.result.Result;
import com.guli.common.result.Result2;
import com.guli.teacher.notteacher.entity.Mydemo;
import com.guli.teacher.notteacher.service.MydemoService;
import com.guli.teacher.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * mydemo 前端控制器
 * </p>
 *
 * @author guli
 * @since 2020-02-03
 */
@RestController
@CrossOrigin
@RequestMapping("/mydemo")
public class MydemoController {


    @Autowired
    private MydemoService mydemoService;

    @PostMapping("add")
    @Transactional
    public Result saveCourse(@RequestBody Mydemo courseDesc){ //接收课程和描述对象

        try {
            //接收课程和描述对象
            courseDesc.setGmtCreate(new Date());
            courseDesc.setGmtModified(new Date());
              mydemoService.save(courseDesc);
            return Result.ok();

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }


    @GetMapping("show")
    @Transactional
    public Map<String, Object> saveCourse2(){ //接收课程和描述对象

        try {
            //接收课程和描述对象
            List<Mydemo> list = mydemoService.list(null);

             Map<String, Object> data = new HashMap<String, Object>();
            data.put("unit","℃");
            data.put("eleValue", "m,h,w,j,s,l,t");
            data.put("dateTime", "20181113010000");
            data.put("list", list);



            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @ApiOperation(value = "导入excel")
    @PostMapping("import")
    public Result importExl(MultipartFile file){
        System.out.println("erer");
        String TYPESTR[] = {".xls",".xlsx"};
        boolean flag = false;
        //判断文件格式
        for(String type : TYPESTR){
            if(StringUtils.endsWithIgnoreCase(file.getOriginalFilename(),type)){
                flag = true;
                break;
            }
        }
        if(!flag){
            return Result.error().data("message","非xls,xlsx文件");
        }
        List<String> errorMessage = mydemoService.importExl(file);
        if(errorMessage.size() == 0){
            return Result.ok();
        } else{
            return Result.error().data("message",errorMessage);
        }
    }

//    @Api(description="导入excel管理")
//    @RestController
//    @RequestMapping("/excel")
//    @CrossOrigin
//    public class EduSubjectController {
//        @Autowired
//        private EduSubjectService subjectService;
//
//


}

