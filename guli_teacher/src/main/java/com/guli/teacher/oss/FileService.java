package com.guli.teacher.oss;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: Administrator
 * @Date: 2020/1/17 11:01
 * @Description:
 */
public interface FileService {
    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}

