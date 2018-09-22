package com.morethink.syspermission.service.dept.impl;

import com.morethink.syspermission.dao.dept.SysDeptMapper;
import com.morethink.syspermission.entity.dept.SysDept;
import com.morethink.syspermission.exception.ParamException;
import com.morethink.syspermission.param.DeptParams;
import com.morethink.syspermission.service.dept.DeptService;
import com.morethink.syspermission.util.BeanValidator;
import com.morethink.syspermission.util.LevelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public void addDept(DeptParams params) {
        BeanValidator.check(params);
        if (checkExistDept(params.getParentId(), params.getName(), params.getId())) {
            throw new ParamException("同一层级下的存在相同名称的部门！ ");
        }
        SysDept dept = SysDept.builder().name(params.getName()).parentId(params.getParentId())
                .seq(params.getSeq()).remark(params.getRemark()).build();
        dept.setLevel(LevelUtil.calculateLevel(getLevel(params.getParentId()), params.getParentId()));
        dept.setOperator("system");
        dept.setOperateIp("127.0.0.1");
        dept.setOperateTime(new Date());
        sysDeptMapper.insertSelective(dept);
    }

    private boolean checkExistDept(Integer parentId, String deptName, Integer deptId) {
        // TODO 判断当前目录下部门是否存在
        return true;
    }

    /**
     * 根据部门id部门所处的层级level
     *
     * @param deptId 部门id
     * @return 部门层级
     */
    private String getLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }

        return dept.getLevel();
    }
}
