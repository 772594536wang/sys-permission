package com.morethink.syspermission.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用户相关传递参数
 *
 * @author wangpf
 */
@Getter
@Setter
public class UserParams {

    private Integer id;

    @NotEmpty(message = "用户名不可以空")
    @Length(min = 0, max = 20, message = "用户名长度需要在20个字以内")
    private String username;

    @NotEmpty(message = "电话号码不可以为空")
    @Length(min = 1, max = 13, message = "电话长度需要在13个字以内")
    private String telephone;

    @NotEmpty(message = "邮箱不能为空")
    @Length(min = 5, max = 50, message = "邮箱长度需要在20个字以内")
    private String mail;

    @NotNull(message = "必须提供用户所在部门")
    private Integer deptId;

    @NotNull(message = "必须指定用户的状态")
    @Min(value = 0, message = "用户状态不合法")
    @Max(value = 2, message = "用户状态不合法")
    private Integer status;

    @Length(min = 0, max = 200, message = "备注长度需要在200字以内")
    private String remark;
}
