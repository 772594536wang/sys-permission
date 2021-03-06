package com.morethink.syspermission.service.user.impl;

import com.google.common.base.Preconditions;
import com.morethink.syspermission.dao.user.SysUserMapper;
import com.morethink.syspermission.entity.user.SysUser;
import com.morethink.syspermission.entity.user.SysUserExample;
import com.morethink.syspermission.exception.ParamException;
import com.morethink.syspermission.param.UserParams;
import com.morethink.syspermission.service.user.SysUserService;
import com.morethink.syspermission.util.BeanValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
        if (checkTelephoneExist(params.getTelephone(), params.getId())) {
            throw new ParamException("电话已经被占用");
        }
        if (checkEmailExist(params.getMail(), params.getId())) {
            throw new ParamException("邮箱已经被占用");
        }

        //String password = PasswordUtil.randomPassword();
        String password = "123456";
        SysUser sysUser = SysUser.builder().username(params.getUsername()).telephone(params.getTelephone())
                .mail(params.getMail()).password(password).deptId(params.getDeptId()).status(params.getStatus())
                .remark(params.getRemark()).build();

        sysUser.setOperator("system");
        sysUser.setOperateIp("127.0.0.1");
        sysUser.setOperateTime(new Date());

        userMapper.insertSelective(sysUser);
    }

    @Override
    public void updateUser(UserParams params) {
        BeanValidator.check(params);
        if (checkTelephoneExist(params.getTelephone(), params.getId())) {
            throw new ParamException("电话已经被占用");
        }
        if (checkEmailExist(params.getMail(), params.getId())) {
            throw new ParamException("邮箱已经被占用");
        }

        SysUser beforeUser = userMapper.selectByPrimaryKey(params.getId());
        Preconditions.checkNotNull(beforeUser, "待更新的用户不存在");

        SysUser afterUser = SysUser.builder().id(params.getId()).username(params.getUsername()).telephone(params.getTelephone())
                .mail(params.getMail()).password(beforeUser.getPassword()).deptId(params.getDeptId()).status(params.getStatus())
                .remark(params.getRemark()).build();

        userMapper.updateByPrimaryKeySelective(afterUser);
    }

    @Override
    public SysUser findUserByUsername(String username) {
        return null;
    }


    private boolean checkEmailExist(String email, Integer userId) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andMailEqualTo(email).andIdEqualTo(userId);
        return userMapper.countByExample(example) > 0;
    }

    private boolean checkTelephoneExist(String telephone, Integer userId) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andTelephoneEqualTo(telephone).andIdEqualTo(userId);
        return userMapper.countByExample(example) > 0;
    }
}
