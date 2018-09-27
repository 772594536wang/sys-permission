package com.morethink.syspermission.service.user;

import com.morethink.syspermission.param.UserParams;

/**
 * 用户相关接口类
 *
 * @author wangpf
 */
public interface SysUserService {

    /**
     * 增加用户
     *
     * @param params 用户的参数
     */
    public void addUser(UserParams params);

    /**
     * 更新用户
     *
     * @param params 需要更新的用户参数
     */
    void updateUser(UserParams params);

}
