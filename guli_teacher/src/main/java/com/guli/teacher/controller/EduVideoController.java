package com.guli.teacher.controller;


import com.guli.common.result.Result;
import com.guli.teacher.entity.EduVideo;
import com.guli.teacher.service.EduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author guli
 * @since 2020-01-20
 */
@Api(description="/teacher/edu-video")
@RestController
@CrossOrigin
@RequestMapping("/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    /**
     * 1、保存
     */
    @PostMapping("save")
    public Result save(@RequestBody EduVideo video){
        boolean save = videoService.save(video);
        if(save){
            return Result.ok();
        } else{
            return Result.error();
        }
    }

    /**
     * 2、根据ID查询Video对象的 回显
     */
    @GetMapping("{id}")
    public Result getVideoById(@PathVariable String id){
        EduVideo video = videoService.getById(id);
        return Result.ok().data("video", video);
    }

    /**
     * 3、修改
     */
    @PutMapping("update")
    public Result update(@RequestBody EduVideo video){
        boolean update = videoService.updateById(video);
        if(update){
            return Result.ok();
        } else{
            return Result.error();
        }
    }
    /**
     * 4、删除
     */
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable String id){
        Boolean flag = videoService.removeVideoById(id);
        if(flag){
            return Result.ok();
        } else{
            return Result.error();
        }
    }
}

