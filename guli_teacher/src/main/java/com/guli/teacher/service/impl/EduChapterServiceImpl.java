package com.guli.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.common.exception.EduException;
import com.guli.teacher.entity.EduChapter;
import com.guli.teacher.entity.EduVideo;
import com.guli.teacher.entity.vo.ChapterDto;
import com.guli.teacher.entity.vo.OneChapter;
import com.guli.teacher.entity.vo.TwoVideo;
import com.guli.teacher.entity.vo.VideoDto;
import com.guli.teacher.mapper.EduChapterMapper;
import com.guli.teacher.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.teacher.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author guli
 * @since 2020-01-20
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;

    @Override
    public void removeByCourseId(String id) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<OneChapter> getChapterAndVideoById(String id) {

        List<OneChapter> list = new ArrayList<>();
        //判断ID
        //1、根据ID查询章节列表
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        wrapper.orderByAsc("sort");
        List<EduChapter> chapterList = baseMapper.selectList(wrapper);
        //判断集 合
        //2、遍历章节列表
        for(EduChapter chapter : chapterList){

            //3、把每一个章节对象复制到OneChapter
            OneChapter oneChapter = new OneChapter();
            BeanUtils.copyProperties(chapter, oneChapter);
            //4、根据每一个章节ID查询小节列表
            QueryWrapper<EduVideo> wr = new QueryWrapper<>();
            wr.eq("chapter_id",oneChapter.getId());
            wr.orderByAsc("sort");
            List<EduVideo> videoList = videoService.list(wr);
            //5、遍历每一个小节
            for(EduVideo video : videoList){
                //6、把每一个小节对象复制到TwoVideo
                TwoVideo twoVideo = new TwoVideo();
                BeanUtils.copyProperties(video, twoVideo);
                //7、把每一个TwoVideo加到章节children
                oneChapter.getChildren().add(twoVideo);
            }

            //8、把每一个章节加到总集合中
            list.add(oneChapter);
        }


        return list;
    }

    @Override
    public boolean saveChapter(EduChapter chapter) {
        if(chapter == null){
            return false;
        }
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("title", chapter.getTitle());
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0){
            return false;
        }
        int insert = baseMapper.insert(chapter);

        return insert == 1;
    }

    @Override
    public boolean updateChapterById(EduChapter chapter) {
        if(chapter == null){
            return false;
        }
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("title", chapter.getTitle());
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0){
            throw new EduException(20001,"章节名称已存在");
        }
        int i = baseMapper.updateById(chapter);

        return i == 1;
    }

    @Override
    public Boolean removeChapterById(String id) {
        //1、判断章节的ID下面是否存在小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", id);
        List<EduVideo> list = videoService.list(wrapper);
        if(list.size() != 0){
            throw new EduException(20001, "此章节下有小节，先删除小节");
        }
        //2、如果有不能删除直接false
        int i = baseMapper.deleteById(id);
        //3、删除章节
        return i == 1;
    }

    @Override
    public List<ChapterDto> getAllChapterVideo(String id) {
        return null;
    }

/*    //查询课程里面的所有章节和小节数据，使用dto进行封装
    @Override
    public List<ChapterDto> getAllChapterVideo(String courseId) {
        //1 查询课程里面所有章节
        //根据课程id查询章节数据
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapper);

        //2 查询课程里面所有的小节
        //根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);

        //定义集合，用于存储最终封装的数据
        List<ChapterDto> finalList = new ArrayList<>();

        //封装章节
        //把课程里面所有的章节集合遍历
        for (int i = 0; i < eduChapterList.size(); i++) {
            //得到每个章节
            EduChapter eduChapter = eduChapterList.get(i);
            //把eduChapter对象转换成功chapterDto对象
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(eduChapter,chapterDto);
            //chapterDto放到list集合中
            finalList.add(chapterDto);

            //创建集合，用户存储小节数据
            List<VideoDto> videoList = new ArrayList<>();

            //遍历课程里面所有的小节
            for (int m = 0; m < eduVideoList.size(); m++) {
                //得到每个小节
                EduVideo eduVideo = eduVideoList.get(m);
                //判断小节里面chapterid  和 章节里面id是否一样
                if(eduVideo.getChapterId().equals(eduChapter.getId())) {
                    //把eduVideo对象转换videoDto对象
                    VideoDto videoDto = new VideoDto();
                    BeanUtils.copyProperties(eduVideo,videoDto);
                    //放到list集合
                    videoList.add(videoDto);
                }
            }
            //把封装好的小节放到章节里面
            chapterDto.setChildren(videoList);
        }
        return finalList;
    }*/
}
