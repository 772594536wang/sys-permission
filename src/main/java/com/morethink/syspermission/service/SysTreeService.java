package com.morethink.syspermission.service;

import com.morethink.syspermission.dto.DeptLevelDTO;

import java.util.List;

/**
 * 系统中tree对象的接口类
 *
 * @author wangpf
 */
public interface SysTreeService {

    /**
     * 获取所有部门树
     *
     * @return 部门DTO列表
     */
    public List<DeptLevelDTO> getDeptTree();

}
