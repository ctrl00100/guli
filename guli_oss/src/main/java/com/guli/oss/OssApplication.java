package com.guli.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: Administrator
 * @Date: 2020/1/16 22:55
 * @Description:
 */



@SpringBootApplication
//@MapperScan("com.guli.teacher.mapper")
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }

}
