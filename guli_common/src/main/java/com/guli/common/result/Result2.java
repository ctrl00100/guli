package com.guli.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2020/1/14 15:38
 * @Description:
 */


@Data
@ApiModel(value = "全局统一返回结果")
public class Result2 {

    @ApiModelProperty(value = "dateTime")
    private Long dateTime;

    @ApiModelProperty(value = "\"℃\",")
    private String unit;

    @ApiModelProperty(value = "返回消息")
    private String eleValue;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();
    private List<Object> list = new ArrayList<>();

    private Result2(){}

    public static Result2 ok(){
        Result2 r = new Result2();
        r.setDateTime(20181113010000l);
        r.setUnit("℃");
        r.setEleValue("m,h,w,j,s,l,t");
        return r;
    }


    public Result2 list(List<Object> list){
        this.list=list;
        return this;
    }


    public Result2 data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result2 data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}

