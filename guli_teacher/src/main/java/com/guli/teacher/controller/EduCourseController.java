package com.guli.teacher.controller;


import com.guli.common.result.Result;
import com.guli.teacher.entity.vo.CourseDesc;
import com.guli.teacher.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EduCourseService eduCourseService;

    @PostMapping("save")
    public Result saveCourse(@RequestBody CourseDesc courseDesc){ //接收课程和描述对象

        try {
            //接收课程和描述对象
            String courseId = eduCourseService.saveCourseDesc(courseDesc);
            return Result.ok().data("courseId",courseId);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

}

