package com.guli.teacher.service;

import com.guli.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.teacher.entity.vo.CourseDesc;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author guli
 * @since 2020-01-20
 */
public interface EduCourseService extends IService<EduCourse> {
    /**
     * 保存课程基本信息
     * @param courseDesc
     * @return 课程的ID
     */
    String saveCourseDesc(CourseDesc courseDesc);

}
