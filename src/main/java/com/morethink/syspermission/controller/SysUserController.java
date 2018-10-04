package com.morethink.syspermission.controller;

import com.morethink.syspermission.common.JsonData;
import com.morethink.syspermission.param.UserParams;
import com.morethink.syspermission.service.user.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 操作用户相关controller
 *
 * @author wangpf
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "/add.json", method = RequestMethod.PUT)
    public JsonData addUser(UserParams params) {
        sysUserService.addUser(params);
        return JsonData.success();
    }

    @RequestMapping(value = "/update.json", method = RequestMethod.POST)
    public JsonData updateDept(UserParams params){
        sysUserService.updateUser(params);
        return JsonData.success();
    }

    @RequestMapping(value = "/user.page", method = RequestMethod.GET)
    public ModelAndView page(){
        return new ModelAndView("user");
    }

}
