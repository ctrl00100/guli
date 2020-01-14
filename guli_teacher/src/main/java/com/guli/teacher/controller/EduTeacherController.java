package com.guli.teacher.controller;


import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author guli
 * @since 2020-01-14
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/teacher")
//
//@RestController
//@RequestMapping("/teacher/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    private List<EduTeacher> getTeacherList(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public boolean removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        return eduTeacherService.removeById(id);
    }
}


