package com.guli.teacher.mapper;

import com.guli.teacher.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.teacher.entity.vo.CoursePublishVo;
import com.guli.teacher.entity.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author guli
 * @since 2020-01-20
 */
//public interface EduCourseMapper extends BaseMapper<EduCourse> {
//
//}
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getCoursePublishVoById(String id);

    CourseWebVo getFrontCourseInfoId(String id);
}
