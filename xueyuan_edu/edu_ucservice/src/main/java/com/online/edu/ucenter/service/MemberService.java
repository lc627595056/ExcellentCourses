package com.online.edu.ucenter.service;

import com.online.edu.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-05
 */
public interface MemberService extends IService<Member> {
    Integer countRegisterNum(String day);

    Member existWxUser(String openid);
}
