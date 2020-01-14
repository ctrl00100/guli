package com.guli.teacher.exception;

import com.baomidou.mybatisplus.extension.api.R;
import com.guli.common.exception.EduException;
import com.guli.common.result.ExceptionUtil;
import com.guli.common.result.Result;
import com.guli.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: Administrator
 * @Date: 2020/1/14 18:25
 * @Description:
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(BadSqlGrammarException.class)
//    @ResponseBody
//    public R error(BadSqlGrammarException e){
//        e.printStackTrace();
//        return R.error().code(ResultCode.SQL_ERROR).message("SQL语法错误");
//    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error().message("出错了");

    }

    @ExceptionHandler(EduException.class)
    @ResponseBody
    public Result error(EduException e){
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));

        return Result.error().code(e.getCode()).message(e.getMsg());
    }

}


