package com.guli.teacher.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.teacher.entity.query.CourseQuery;
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



    /**
     * 根据课程ID查询课程基本信息和描述
     * @param id
     * @return
     */
    CourseDesc getCourseVoById(String id);


    Boolean updateVo(CourseDesc vo);

    void getPageList(Page<EduCourse> objectPage, CourseQuery courseQuery);

    Boolean deleteById(String id);
}
