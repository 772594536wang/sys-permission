package com.morethink.syspermission.service.user.impl;

import com.morethink.syspermission.dao.user.SysUserMapper;
import com.morethink.syspermission.param.UserParams;
import com.morethink.syspermission.service.user.SysUserService;
import com.morethink.syspermission.util.BeanValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户相关接口实现类
 *
 * @author wangpf
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper userMapper;
    @Override
    public void addUser(UserParams params) {
        BeanValidator.check(params);

    }
}
