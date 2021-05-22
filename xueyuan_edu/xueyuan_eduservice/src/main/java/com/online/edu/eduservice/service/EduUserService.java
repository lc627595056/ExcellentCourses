package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-10
 */
public interface EduUserService extends IService<EduUser> {

    EduUser getByUser(String username, String password);
}
