package com.morethink.syspermission.dto;

import com.google.common.collect.Lists;
import com.morethink.syspermission.entity.dept.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门层级DTO
 *
 * @author wangpf
 */
@Getter
@Setter
@ToString
public class DeptLevelDTO extends SysDept {

    private List<DeptLevelDTO> deptList = new ArrayList<>();

    public static DeptLevelDTO adapt(SysDept dept) {
        DeptLevelDTO dto = new DeptLevelDTO();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }
}
