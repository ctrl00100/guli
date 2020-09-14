package com.guli.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.entity.query.TeacherQuery;
import com.guli.teacher.mapper.EduTeacherMapper;
import com.guli.teacher.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author guli
 * @since 2020-01-14
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> teacherPage, TeacherQuery query) {

        if(query == null){
            baseMapper.selectPage(teacherPage,null);
        }

        //获取对象属性
        String name = query.getName();
        Integer level = query.getLevel();
        Date gmtCreate = query.getGmtCreate();
        Date gmtModified = query.getGmtModified();

        //创建一个Wrapper
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //判断对象属性是否存在
        if(!StringUtils.isEmpty(name)){
            //如果存在再加入条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        //判断创建时间是否存在，如果存在，查询的是大于等于此时间的
        if (!StringUtils.isEmpty(gmtCreate)) {
            wrapper.ge("gmt_create", gmtCreate);
        }

        if (!StringUtils.isEmpty(gmtModified)) {
            wrapper.le("gmt_create", gmtModified);
        }
        baseMapper.selectPage(teacherPage,wrapper);
    }


    //根据多个ID删分类

    /*Tree
     A
            B  刪除
                    b1
                    b2
                            bb1
                            bb2
            C*/
    /*public void deletes(String parent_id){
        List<String> ids = new ArrayList<>();
        ids.add(parent_id);
        //递归获取ID
        getIds(ids,parent_id);
        baseMapper.deleteBatchIds(ids);
    }*/

    //方法递归方法
    /*public void getIds(List<String> ids, String parent_id){
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parent_id);
        List<EduTeacher> teacherList = baseMapper.selectList(wrapper);
        for(EduTeacher teacher : teacherList){
            ids.add(teacher.getId());
            getIds(ids,teacher.getId());
        }
    }*/
//前台系统讲师分页列表的方法
    @Override
    public Map<String, Object> getTeacherAllFront(Page<EduTeacher> pageTeacher) {
        //调用方法分页查询，把分页数据封装到pageTeacher对象里面
        baseMapper.selectPage(pageTeacher, null);

        System.out.println(pageTeacher);
        //把pageTeacher对象里面分页数据获取出来，封装到map集合中
        long current = pageTeacher.getCurrent(); //当前页
        long pages = pageTeacher.getPages(); //总页数
        long size = pageTeacher.getSize(); //每页显示记录数
        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //每页数据list集合
        boolean hasPrevious = pageTeacher.hasPrevious(); //上一页
        boolean hasNext = pageTeacher.hasNext(); //下一页

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
}
