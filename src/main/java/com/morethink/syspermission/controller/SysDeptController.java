package com.morethink.syspermission.controller;

import com.morethink.syspermission.common.JsonData;
import com.morethink.syspermission.param.DeptParams;
import com.morethink.syspermission.service.dept.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 部门相关controller
 *
 * @author wangpf
 */
@RestController
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Resource
    private DeptService deptService;

    @RequestMapping(value = "/add.json")
    public JsonData addDept(DeptParams params){
        deptService.addDept(params);
        return JsonData.success();
    }

}
