package com.guli.teacher.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.exception.EduException;
import com.guli.common.result.Result;
import com.guli.common.result.ResultCode;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.entity.query.TeacherQuery;
import com.guli.teacher.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author guli
 * @since 2020-01-14
 */
@CrossOrigin
@Api(description = "User登陆")
@RestController
@RequestMapping("user")

public class UserController {


    @ApiOperation(value = "User登陆")
    @PostMapping("login")
    @ResponseBody
    public Result login() {
        System.out.println(159);
        return Result.ok().data("token", "admin");

    }


    //    public Result getTeacherList(){
////        int a = 1 / 0;
//        try {
//            List<EduTeacher> list = eduTeacherService.list(null);
////        return list;
//            return Result.ok().data("items", list);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.error();
//        }
//    }
    @GetMapping("info")
    @ResponseBody
    public Result info() {
        //"data":{"roles":["admin"],"name":"admin",
        // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
        return Result.ok().data("roles", "[\"admin\"]")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}


