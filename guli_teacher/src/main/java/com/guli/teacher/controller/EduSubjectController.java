package com.guli.teacher.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.common.result.Result;
import com.guli.teacher.entity.EduSubject;
import com.guli.teacher.entity.vo.OneSubject;
import com.guli.teacher.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author guli
 * @since 2020-01-17
 */
@Api(description="课程目录poi管理")
@RestController
@RequestMapping("/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation(value = "导入课程目录")
    @PostMapping("import")
    public Result importExl(MultipartFile file){
          String TYPESTR[] = {".xls",".xlsx"};
        boolean flag = false;
        //判断文件格式
        for(String type : TYPESTR){
            if(StringUtils.endsWithIgnoreCase(file.getOriginalFilename(),type)){
                flag = true;
                break;
            }
        }
        if(!flag){
            return Result.error().data("message","非xls,xlsx文件");
        }
        List<String> errorMessage = subjectService.importExl(file);
        if(errorMessage.size() == 0){
            return Result.ok();
        } else{
            return Result.error().data("message",errorMessage);
        }
    }

    @ApiOperation(value = "get课程目录")
    @GetMapping("tree")
    public Result getSubjectList(){
        List<OneSubject> list = subjectService.getTree();
        return Result.ok().data("subjectList",list);
    }

    @ApiOperation(value = "Delete课程")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable  String id){
        boolean a=subjectService.selectTwo(id);
        System.out.println(a);
        if(a){
            return Result.error().data("message","请先删除子分类");
        }


        boolean b = subjectService.removeById(id);
        if(b){
            return Result.ok();
        } else {
            return Result.error();
        }


//        // 根据ID查询数据库中是否存在此ID为ParentId（二级分类）
//        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
//        wrapper.eq("parent_id",id);
//        List<EduSubject> subjectList = baseMapper.selectList(wrapper);
//        if(subjectList.size() != 0){
//            //如果有，返回false
//            return false;
//        }
//        int i = baseMapper.deleteById(id);
//        // 如果没有直接删除并返回他true
//        return i == 1;
    }
    @ApiOperation(value = "新增课程一级分类")
    @PostMapping("saveLevelOne")
    public Result saveLevelOne(
            @ApiParam(name = "subject", value = "课程分类对象", required = true)
            @RequestBody EduSubject subject){
        boolean result = subjectService.saveLevelOne(subject);
        if(result){
            return Result.ok().message("新增课程一级分类ok" );
        }else{
            return Result.error().message("新增课程一级分类失败");
        }
    }



    @ApiOperation(value = "新增二级分类")
    @PostMapping("saveLevelTwo")
    public Result saveLevelTwo(
            @ApiParam(name = "subject", value = "课程分类对象", required = true)
            @RequestBody EduSubject subject){

        boolean result = subjectService.saveLevelTwo(subject);
        if(result){
            return Result.ok();
        }else{
            return Result.error().message("保存失败");
        }
    }





}


