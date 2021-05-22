package com.online.edu.ucenter.mapper;

import com.online.edu.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-05
 */
public interface MemberMapper extends BaseMapper<Member> {
    Integer countRegisterNum(String day);
}
