package com.guli.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.teacher.client.VodClient;
import com.guli.teacher.entity.EduVideo;
import com.guli.teacher.mapper.EduVideoMapper;
import com.guli.teacher.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author guli
 * @since 2020-01-20
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
//    @Override
//    public Boolean removeVideoById(String id) {
//
//        // TODO 删除阿里云上的视频
//
//        // 删除数据库中的Video
//        int delete = baseMapper.deleteById(id);
//
//        return delete == 1;
//    }

    @Autowired
    private VodClient vodClient;

    @Override
    public Boolean removeVideoById(String id) {

        //查询云端视频id
        EduVideo video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        //删除视频资源
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }

        // 删除数据库中的Video
        int delete = baseMapper.deleteById(id);

        return delete == 1;
    }

    @Override
    public Boolean removeVideoByCourseId(String courseId) {

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(wrapper);
        //定义一个集合存放视频ID
        List<String> videoIds = new ArrayList<>();
        //2、可以获取视频ID
        for(EduVideo video : videoList){
            if(!StringUtils.isEmpty(video.getVideoSourceId())){
                videoIds.add(video.getVideoSourceId());
            }
        }
        if(videoIds.size() > 0){
            vodClient.getRemoveList(videoIds);
        }
        System.out.println(videoIds);
        QueryWrapper<EduVideo> wr = new QueryWrapper<>();
        wr.eq("course_id",courseId);
        //3、删除
        int delete = baseMapper.delete(wr);

        return delete > 0;
    }
}
