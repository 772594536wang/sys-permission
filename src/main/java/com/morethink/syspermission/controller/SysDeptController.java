package com.morethink.syspermission.controller;

import com.morethink.syspermission.common.JsonData;
import com.morethink.syspermission.dto.DeptLevelDTO;
import com.morethink.syspermission.param.DeptParams;
import com.morethink.syspermission.service.SysTreeService;
import com.morethink.syspermission.service.dept.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private SysTreeService sysTreeService;

    @RequestMapping(value = "/add.json")
    public JsonData addDept(DeptParams params) {
        deptService.addDept(params);
        return JsonData.success();
    }

    @RequestMapping(value = "tree.json")
    public JsonData tree() {
        List<DeptLevelDTO> deptTree = sysTreeService.getDeptTree();
        return JsonData.success(deptTree);
    }

    @RequestMapping(value = "/update.json")
    public JsonData updateDept(DeptParams params){
        deptService.updateDept(params);
        return JsonData.success();
    }

    @RequestMapping(value = "/page.json")
    public ModelAndView page(){
        return new ModelAndView("dept");
    }


}
