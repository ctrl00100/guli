package com.guli.teacher.service;

import com.guli.teacher.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author guli
 * @since 2020-01-17
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 导入Excl表格
     * @param file
     * @return
     */
    List<String> importExl(MultipartFile file);

}
