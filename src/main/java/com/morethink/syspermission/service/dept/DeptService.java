package com.morethink.syspermission.service.dept;

import com.morethink.syspermission.param.DeptParams;

/**
 * 部门操作服务类接口
 *
 * @author wangpf
 */
public interface DeptService {

    /**
     * 保存部门
     *
     * @param params 部门传递的參數
     */
    public void addDept(DeptParams params);

    /**
     * 更新部门
     *
     * @param params 需要更新的部门参数
     */
    void updateDept(DeptParams params);
}
