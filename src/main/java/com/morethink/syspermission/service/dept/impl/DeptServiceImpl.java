package com.morethink.syspermission.service.dept.impl;

import com.google.common.base.Preconditions;
import com.morethink.syspermission.dao.dept.SysDeptMapper;
import com.morethink.syspermission.entity.dept.SysDept;
import com.morethink.syspermission.exception.ParamException;
import com.morethink.syspermission.param.DeptParams;
import com.morethink.syspermission.service.dept.DeptService;
import com.morethink.syspermission.util.BeanValidator;
import com.morethink.syspermission.util.LevelUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDept(DeptParams params) {
        BeanValidator.check(params);
        if (checkExistDept(params.getParentId(), params.getName(), params.getId())) {
            throw new ParamException("同一层级下的存在相同名称的部门！ ");
        }
        SysDept beforeDept = sysDeptMapper.selectByPrimaryKey(params.getId());
        Preconditions.checkNotNull(beforeDept, "待更新的部门不存在");

        SysDept afterDept = SysDept.builder().id(params.getId()).name(params.getName()).parentId(params.getParentId())
                .seq(params.getSeq()).remark(params.getRemark()).build();

        afterDept.setLevel(LevelUtil.calculateLevel(getLevel(params.getParentId()), params.getParentId()));
        afterDept.setOperator("system");
        afterDept.setOperateIp("127.0.0.1");
        afterDept.setOperateTime(new Date());


        // 判断子部门是否需要更新
        String newLevelPrefix = afterDept.getLevel();
        String oldLevelPrefix = beforeDept.getLevel();
        if (!newLevelPrefix.equals(oldLevelPrefix)) {
            List<SysDept> deptList = sysDeptMapper.selectChildDeptListByLevel(beforeDept.getLevel());
            if (!CollectionUtils.isEmpty(deptList)) {
                for (SysDept dept : deptList) {
                    String level = dept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }

        sysDeptMapper.updateByPrimaryKeySelective(afterDept);
    }

    /**
     * 判断部门是否存在
     *
     * @param parentId 部门的parent
     * @param deptName 部门名称
     * @param deptId   部门id
     * @return 是否存在
     */
    private boolean checkExistDept(Integer parentId, String deptName, Integer deptId) {
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
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
