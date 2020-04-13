package com.guli.vod.controller;

import com.guli.common.result.Result;
import com.guli.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("vod")
@CrossOrigin
public class UploadVodController {

    @Autowired
    private VodService vodService;

    @PostMapping("upload")
    public Result upload(MultipartFile file){
        String videoSourceId = vodService.upload(file);
        return Result.ok().data("videoSourceId", videoSourceId);
    }

    /**
     * 根据视频ID获取播放凭证
     * @return
     */
    @DeleteMapping("{videoSourceId}")
    public Result getVideoPlayAuth(@PathVariable String videoSourceId){
        Boolean flag = vodService.deleteVodById(videoSourceId);
        if(flag){
            return Result.ok();
        }
        return Result.error();
    }
    /**
     * 根据多个视频ID删除视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("removeList")
    public Result getRemoveList(@RequestParam("videoIdList") List videoIdList){

        Boolean flag = vodService.getRemoveListByIds(videoIdList);
        if(flag){
            return Result.ok();
        }
        return Result.error();
    }

}
