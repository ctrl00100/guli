package com.guli.teacher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: Administrator
 * @Date: 2020/1/13 16:50
 * @Description:
 */

@SpringBootApplication
//@MapperScan("com.guli.teacher.mapper")
@EnableEurekaClient
@EnableFeignClients
public class TeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeacherApplication.class, args);
    }

}
