package com.morethink.syspermission.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TestVo {

    @NotEmpty
    private String msg;

    @NotNull(message = "id不可以为空")
    @Max(value = 10, message = "id 不能大于10")
    @Min(value = 0, message = "id 至少大于等于0")
    private Integer id;

    private List<String> str;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getStr() {
        return str;
    }

    public void setStr(List<String> str) {
        this.str = str;
    }
}
