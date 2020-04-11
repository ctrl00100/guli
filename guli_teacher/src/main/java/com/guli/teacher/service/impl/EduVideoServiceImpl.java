package com.guli.teacher.service.impl;

import com.guli.teacher.entity.EduVideo;
import com.guli.teacher.mapper.EduVideoMapper;
import com.guli.teacher.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    @Override
    public Boolean removeVideoById(String id) {

        // TODO 删除阿里云上的视频

        // 删除数据库中的Video
        int delete = baseMapper.deleteById(id);

        return delete == 1;
    }
}
