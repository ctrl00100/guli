package com.guli.teacher.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.result.Result;
import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.query.CourseQuery;
import com.guli.teacher.entity.vo.CourseDesc;
import com.guli.teacher.entity.vo.CoursePublishVo;
import com.guli.teacher.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author guli
 * @since 2020-01-20
 */
@Api(description="teacher/edu-course")
@RestController
@CrossOrigin
@RequestMapping("/course")
public class EduCourseController {


    @Autowired
    private EduCourseService courseService;

    @PostMapping("saveVo")
    @Transactional
    public Result saveCourse(@RequestBody CourseDesc courseDesc){ //接收课程和描述对象

        try {
            //接收课程和描述对象
            String courseId = courseService.saveCourseDesc(courseDesc);
            return Result.ok().data("courseId",courseId);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 根据课程ID获取课程基本信息和描述
     */
    @GetMapping("{id}")
    public Result getCourseVoById(@PathVariable String id){
        CourseDesc vo = courseService.getCourseVoById(id);
        return Result.ok().data("courseInfo",vo);
    }



    /**
     * 修改课程基本信息
     */
    @PutMapping("updateVo")
    public Result updateVo(@RequestBody CourseDesc vo){
        Boolean flag = courseService.updateVo(vo);
        if(flag){
            return Result.ok();
        } else{
            return Result.error();
        }
    }


    /**
     * 根据搜索条件分页查询
     *
     */
    @PostMapping("{page}/{limit}")
    public Result getPageList(@PathVariable Integer page,
                              @PathVariable Integer limit,
                              @RequestBody CourseQuery courseQuery){

        Page<EduCourse> objectPage = new Page<>(page, limit);
        courseService.getPageList(objectPage, courseQuery);
        return  Result.ok()
                .data("rows",objectPage.getRecords())
                .data("total",objectPage.getTotal());
    }

    /**
     * 根据课程ID删除课程信息
     */
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable String id){
        Boolean flag = courseService.deleteById(id);
        if(flag){
            return Result.ok();
        } else{
            return Result.error();
        }
    }


    /**
     * 根据课程Id查询课程Vo对象
     * @param id
     * @return
     */
    @GetMapping("vo/{id}")
    public Result getCoursePublishVoById(@PathVariable String id){
        CoursePublishVo voursePublishVo = courseService.getCoursePublishVoById(id);
        if(voursePublishVo != null){
            return Result.ok().data("item",voursePublishVo);
        } else {
            return Result.error();
        }
    }

//    @PutMapping("/status/{id}")
    @GetMapping("/updateStatusById/{id}")
    public Result updateByStatusById(@PathVariable String id){
        Boolean flag = courseService.updateStatusById(id);
        if(flag){
            return Result.ok();
        } else{
            return Result.error();
        }
    }


}

