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
}