package com.guli.teacher.notteacher.service;

import com.guli.teacher.notteacher.entity.Mydemo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * mydemo 服务类
 * </p>
 *
 * @author guli
 * @since 2020-02-03
 */
public interface MydemoService extends IService<Mydemo> {

    List<String> importExl(MultipartFile file);
}
