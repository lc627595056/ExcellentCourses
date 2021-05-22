package com.online.edu.eduservice.mapper;

import com.online.edu.eduservice.entity.EduUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-10
 */
public interface EduUserMapper extends BaseMapper<EduUser> {

    EduUser selectUser(String username, String password);
}
