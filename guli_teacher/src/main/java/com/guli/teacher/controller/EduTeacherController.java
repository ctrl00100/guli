package com.guli.teacher.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.exception.EduException;
import com.guli.common.result.Result;
import com.guli.common.result.ResultCode;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.entity.query.TeacherQuery;
import com.guli.teacher.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang3.StringUtils;
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
@CrossOrigin
//@RestController
//@RequestMapping("/teacher/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    public Result getTeacherList(){
//        int a = 1 / 0;
        try {
            List<EduTeacher> list = eduTeacherService.list(null);
//        return list;
            return Result.ok().data("items", list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }


    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public Result removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){


        try {
            eduTeacherService.removeById(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();

        }


    }
    @ApiOperation(value = "讲师分页列表")
    @GetMapping("/{page}/{limit}")
    public Result selectTeacherByPage(
            @ApiParam(name="page",value = "当前页", required = true)
            @PathVariable(value = "page") Integer page,
            @ApiParam(name="limit",value = "每页显示记录数", required = true)
            @PathVariable Integer limit){
        try {
            Page<EduTeacher> teacherPage = new Page<>(page, limit);
            eduTeacherService.page(teacherPage,null);
            return Result.ok().data("total",teacherPage.getTotal()).data("rows",teacherPage.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    //条件查询的分页58
    // 1、 有分页
    // 2、条件 ： 根据讲师名称、讲师等级、创建时间、修改时间
    @ApiOperation(value = "根据讲师条件分页查询")
    @PostMapping("/{page}/{limit}")
    public Result selectTeacherByPageAndWrapper(
            @ApiParam(name="page",value = "当前页", required = true)
            @PathVariable(value = "page") Integer page,
            @ApiParam(name="limit",value = "每页显示记录数", required = true)
            @PathVariable Integer limit,
            @RequestBody TeacherQuery query){
        try {
            Page<EduTeacher> teacherPage = new Page<>(page, limit);
            //Wrapper<TeacherQuery> queryWrapper = new QueryWrapper<TeacherQuery>();
            System.out.println(query);
            eduTeacherService.pageQuery(teacherPage, query);
            return Result.ok().data("total",teacherPage.getTotal()).data("rows",teacherPage.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @ApiOperation(value = "保存讲师对象")
    @PostMapping("save")
    public Result saveTeacher(@RequestBody EduTeacher teacher){
        try {
            eduTeacherService.save(teacher);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @ApiOperation(value = "根据ID查询")
    @GetMapping("{id}")
    public Result selectTeacherById(
            @ApiParam(name="id",value = "讲师ID", required = true)
            @PathVariable  String id){

//         当我们的业务被非法参数操作时、我们可以自定义异常（业务异常）
        EduTeacher teacher = eduTeacherService.getById(id);
        if(teacher == null){
            throw new EduException(ResultCode.EDU_ID_ERROR,"没有此讲师信息");
        }

        try {
//            EduTeacher teacher = eduTeacherService.getById(id);
//            if (teacher == null || teacher.equals("")) {
//                throw new EduException((ResultCode.EDU_ID_ERROR),"没有此");
//            }


            return Result.ok().data("teacher",teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @ApiOperation(value = "修改讲师信息")
    @PutMapping("update")
    public Result selectTeacherById(@RequestBody EduTeacher teacher){
        try {
            eduTeacherService.updateById(teacher);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}


