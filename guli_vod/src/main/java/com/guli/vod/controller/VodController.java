package com.guli.vod.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.guli.common.common.R;
import com.guli.vod.service.VodService;
import com.guli.vod.util.AliyunVodSDKUtil;
import com.guli.vod.util.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/vid")
@CrossOrigin
@Api(description = "阿里云视频操作接口")
public class VodController {

    @Autowired
    private VodService vodService;

    //根据视频id获取视频播放凭证
    @GetMapping("getPlayAuthFront/{vid}")
    public R getPlayAuthFront(@PathVariable String vid) {
        try {
            //1 初始化对象
            DefaultAcsClient client =
                    AliyunVodSDKUtil.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            //2 创建获取播放凭证的request对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(vid);

            //3 调用方法返回response
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        }catch(Exception e) {
            return null;
        }
    }

    //删除多个阿里云视频的方法
    //传递多个视频id，使用list集合
    @DeleteMapping("removeMoreVideo")
    public R removeMoreVideo(@RequestParam("videoIdList") List videoIdList) {
        vodService.getRemoveListByIds(videoIdList);
        return R.ok();
    }

    //上传视频到阿里云操作
    @PostMapping("uploadVideoAliyun")
    public R uploadVideoAliyun(MultipartFile file) {
        String videoId = vodService.upload(file);
        return R.ok().data("videoId",videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("deleteVideoAli/{videoId}")
    public R deleteVideoAli(@PathVariable String videoId) {
        vodService.deleteVodById(videoId);
        return R.ok();
    }
}
