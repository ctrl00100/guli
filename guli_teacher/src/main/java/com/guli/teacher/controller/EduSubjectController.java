package com.guli.teacher.controller;


import com.guli.common.result.Result;
import com.guli.teacher.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author guli
 * @since 2020-01-17
 */
@Api(description="poi管理")
@RestController
@RequestMapping("/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("import")
    public Result importExl(MultipartFile file){
        List<String> errorMessage = subjectService.importExl(file);
        if(errorMessage.size() == 0){
            return Result.ok();
        } else{
            return Result.error().data("message",errorMessage);
        }
    }


}


