package com.guli.teacher.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.common.R;
import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.entity.vo.ChapterDto;
import com.guli.teacher.entity.vo.CourseWebVo;
import com.guli.teacher.entity.vo.OneChapter;
import com.guli.teacher.service.EduChapterService;
import com.guli.teacher.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/eduservice/frontcourse")
@CrossOrigin
public class FrontCourseController {


    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    //    //根据课程id查询课程详情信息
    @GetMapping("getFrontCourseInfo/{id}")
    public R getFrontCourseInfo(@PathVariable String id) {
        //1 根据id查询课程基本信息
        CourseWebVo courseFrontInfo = courseService.getCourseInfoFrontId(id);
        System.out.println("333"+courseFrontInfo);
        //2 根据课程id查询课程大纲（章节和小节）
        List<OneChapter> allChapterVideo = chapterService.getChapterAndVideoById(id);
        System.out.println("111"+allChapterVideo);
        return R.ok().data("courseFrontInfo",courseFrontInfo).data("chapterVideoList",allChapterVideo);
    }
    //课程分页列表
    @GetMapping("/getCourseFrontList/{page}/{limit}")
    public R getCourseFrontList(@PathVariable Long page,
                                @PathVariable Long limit) {
        Page<EduCourse> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getFrontCourseList(pageCourse);
        return R.ok().data(map);
    }

}
