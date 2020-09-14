package com.guli.teacher.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.common.R;
import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.service.EduCourseService;
import com.guli.teacher.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/frontteacher")
@CrossOrigin
public class FrontTeacherController {


    @Autowired
    private EduTeacherService eduTeacherService;


    @Autowired
    private EduCourseService eduCourseService;
    @GetMapping("findAllTeacherFront/{page}/{limit}")
    public R findAllTeacherFront(@PathVariable Long page, @PathVariable Long limit) {


        Page<EduTeacher> pageTeacher = new Page<>(page, limit);


        Map<String,Object> map= eduTeacherService.getTeacherAllFront(pageTeacher);
        return R.ok().data(map);

    }
    @GetMapping("getFrontTeacherInfo/{id}")
    public R getFrontTeacherInfo(@PathVariable Long id ){


//        Page<EduTeacher> pageTeacher = new Page<>(page, limit);


        EduTeacher teacherInfo= eduTeacherService.getById(id);

        String id2 = id.toString();
        List<EduCourse> courseList = eduCourseService.getCourseListByTeacherId(id2);

        return R.ok().data("teacherInfo",teacherInfo).data("courseList",courseList);

    }
}
