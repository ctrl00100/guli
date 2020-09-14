package cn.eduxueyuan.educenter.mapper;

import cn.eduxueyuan.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-04-13
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {


    //统计某一天注册人数
    public Integer countRegisterNum(String day);
//    public Integer countRegisterNum(String day   ,String a);/*
//    public Integer countRegisterNum(@Param("aa") String day   ,String a);
//
//     WHERE DATE(uc.gmt_create)=#{0,1}
//    */




}
