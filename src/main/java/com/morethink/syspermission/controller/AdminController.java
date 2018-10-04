package com.morethink.syspermission.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(value = "/index.page", method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("admin");
    }
}
