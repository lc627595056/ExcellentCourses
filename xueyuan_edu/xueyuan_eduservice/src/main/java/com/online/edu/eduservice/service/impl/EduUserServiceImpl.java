package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.entity.EduUser;
import com.online.edu.eduservice.mapper.EduUserMapper;
import com.online.edu.eduservice.service.EduUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-10
 */
@Service
public class EduUserServiceImpl extends ServiceImpl<EduUserMapper, EduUser> implements EduUserService {

    @Autowired
    private EduUserMapper eduUserMapper;

    @Override
    public EduUser getByUser(String username, String password) {
        return eduUserMapper.selectUser(username,password);
    }
}
