package com.morethink.syspermission.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 部门传递参数
 *
 * @author wangpf
 */
@Setter
@Getter
@ToString
public class DeptParams {

    private Integer id;

    @NotEmpty(message = "部门名称不能为空")
    @Length(max = 15, min = 2, message = "部门名称需要在2-15个字之间")
    private String name;

    private Integer parentId;

    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    @Length(max = 150, message = "备注的长度不能超过150个字")
    private String remark;

}
