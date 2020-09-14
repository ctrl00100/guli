package com.guli.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.exception.EduException;
import com.guli.teacher.entity.EduCourse;
import com.guli.teacher.entity.EduCourseDescription;
import com.guli.teacher.entity.query.CourseQuery;
import com.guli.teacher.entity.vo.CourseDesc;
import com.guli.teacher.entity.vo.CoursePublishVo;
import com.guli.teacher.entity.vo.CourseWebVo;
import com.guli.teacher.mapper.EduCourseMapper;
import com.guli.teacher.service.EduChapterService;
import com.guli.teacher.service.EduCourseDescriptionService;
import com.guli.teacher.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.teacher.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author guli
 * @since 2020-01-20
 */
@Service
@Transactional
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;


//    @Autowired
//    private EduCourseDescriptionService eduCourseDescriptionService;


    @Override
    public String saveCourseDesc(CourseDesc courseDesc) {

        //1、添加课程基本信息表
        int insert = baseMapper.insert(courseDesc.getEduCourse());
        if(insert <= 0){
            throw new EduException(20001,"添加课程失败");
        }
        //2、获取课程表的Id
        String courseId = courseDesc.getEduCourse().getId();
        //3、添加课程描述表
        courseDesc.getCourseDescription().setId(courseId);
        boolean save = eduCourseDescriptionService.save(courseDesc.getCourseDescription());
        if(!save){
            throw new EduException(20001,"添加描述失败");
        }
        return courseId;

    }


    @Override
    public CourseDesc getCourseVoById(String id) {

        //创建一个Vo对象
        CourseDesc vo = new CourseDesc();
        //根据课程ID获取课程对象 EduCourse
        EduCourse eduCourse = baseMapper.selectById(id);
        if(eduCourse == null ){
            return vo;
        }
        // 把课程加到vo对象中
        vo.setEduCourse(eduCourse);
        //根据课程ID获取课程描述
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        //把描述加在vo对象中
        if(eduCourseDescription == null ){
            return vo;
        }
        vo.setCourseDescription(eduCourseDescription);

        return vo;
    }

    @Override
    public Boolean updateVo(CourseDesc vo) {

        //1、 先判断ID是否存在、如果不存在直接放回false
        if(StringUtils.isEmpty(vo.getEduCourse().getId())){
            return false;
        }
        //2、修改course
        int i = baseMapper.updateById(vo.getEduCourse());
        if(i <= 0){
            return false;
        }
        //3、修改courseDesc
        vo.getCourseDescription().setId(vo.getEduCourse().getId());
        boolean b = eduCourseDescriptionService.updateById(vo.getCourseDescription());
        return b;
    }

    @Override
    public void getPageList(Page<EduCourse> objectPage, CourseQuery courseQuery) {

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        if(courseQuery == null){
            baseMapper.selectPage(objectPage,wrapper);
        }
        String subjectId = courseQuery.getSubjectId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String teacherId = courseQuery.getTeacherId();
        String title = courseQuery.getTitle();

        if(!StringUtils.isEmpty(subjectId)){
            wrapper.eq("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(subjectParentId)){
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(teacherId)){
            wrapper.eq("teacher_id",teacherId);
        }
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }

        baseMapper.selectPage(objectPage,wrapper);
    }

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;

    @Override
    public Boolean deleteById(String id) {

        // TODO 删除课程相关的小节
        //根据课程ID删除小节
        videoService.removeVideoByCourseId(id);
        // TODO 删除课程相关的章节
        chapterService.removeByCourseId(id);
        // 删除描述
        boolean b = eduCourseDescriptionService.removeById(id);
        if(!b){
            return false;
        }
        // 删除基本信息
        int i = baseMapper.deleteById(id);
        return i == 1;
    }


    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        CoursePublishVo vo = baseMapper.getCoursePublishVoById(id);
        return vo;
    }

    @Override
    public Boolean updateStatusById(String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus("Normal");
        int update = baseMapper.updateById(course);
        return update > 0;
    }
    //2 根据讲师id查询讲师所讲的课程
    @Override
    public List<EduCourse> getCourseListByTeacherId(String id) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> eduCourses = baseMapper.selectList(wrapper);
        return eduCourses;
    }

    ////课程分页列表
    @Override
    public Map<String, Object> getFrontCourseList(Page<EduCourse> pageCourse) {
        baseMapper.selectPage(pageCourse,null);

        long current = pageCourse.getCurrent(); //当前页
        long pages = pageCourse.getPages(); //总页数
        long size = pageCourse.getSize(); //每页显示记录数
        long total = pageCourse.getTotal(); //总记录数
        List<EduCourse> records = pageCourse.getRecords(); //每页数据list集合
        boolean hasPrevious = pageCourse.hasPrevious(); //上一页
        boolean hasNext = pageCourse.hasNext(); //下一页

        //封装map
        Map<String,Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    //根据id查询课程基本信息
    @Override
    public CourseWebVo getCourseInfoFrontId(String id) {
        CourseWebVo courseWebVo = baseMapper.getFrontCourseInfoId(id);
        return courseWebVo;
    }
}
