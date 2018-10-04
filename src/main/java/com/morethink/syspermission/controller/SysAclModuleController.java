package com.morethink.syspermission.controller;

import com.morethink.syspermission.common.JsonData;
import com.morethink.syspermission.param.AclModuleParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 权限模块的controller
 *
 * @author wangpf
 */
@RestController
@RequestMapping("/sys/aclModule")
@Slf4j
public class SysAclModuleController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView page(){
        return null;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public JsonData addAclModule(AclModuleParams params){
        return null;
    }

}
