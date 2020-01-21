package com.guli.teacher.mapper;

import com.guli.teacher.entity.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author guli
 * @since 2020-01-17
 */
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

//
//    @Select("SELECT * FROM UUSER WHERE username = #{userCode}")
//    Uuser selectByUsernamezj(@Param("userCode")String userCode);


    @Select("SELECT title FROM edu_subject WHERE parent_id = #{id}")
    List<EduSubject> findTwoByid(@Param("id")String id);



}
