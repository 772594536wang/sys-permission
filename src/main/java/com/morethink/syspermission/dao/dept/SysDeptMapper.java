package com.morethink.syspermission.dao.dept;

import com.morethink.syspermission.entity.dept.SysDept;
import com.morethink.syspermission.entity.dept.SysDeptExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDeptMapper {
    long countByExample(SysDeptExample example);

    int deleteByExample(SysDeptExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysDept record);

    /**
     * 插入记录
     *
     * @param record 部门对象
     */
    void insertSelective(SysDept record);

    List<SysDept> selectByExample(SysDeptExample example);

    SysDept selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysDept record, @Param("example") SysDeptExample example);

    int updateByExample(@Param("record") SysDept record, @Param("example") SysDeptExample example);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    /**
     * 查询所有部门
     *
     * @return 部门list
     */
    List<SysDept> selectAllDept();

    /**
     * 获取当前部门的所有子部门
     *
     * @param level 当前部门的level
     * @return 子部门list
     */
    List<SysDept> selectChildDeptListByLevel(String level);

    /**
     * 批量更新level
     *
     * @param sysDeptList 被更新的部门列表
     */
    void batchUpdateLevel(List<SysDept> sysDeptList);

    /**
     * 通过名称或parentId获取部门的数量
     *
     * @param parentId 部Id门的parent
     * @param deptName 部门名称
     * @param deptId   部门id
     * @return 查询数量
     */
    int countByNameAndParentId(Integer parentId, String deptName, Integer deptId);
}