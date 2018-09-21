package com.morethink.syspermission.controller;

import com.morethink.syspermission.common.JsonData;
import com.morethink.syspermission.param.TestVo;
import com.morethink.syspermission.util.BeanValidator;
import org.apache.commons.collections.MapUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/hello.json",method = RequestMethod.GET)
    public JsonData hello(){
        System.out.println(">>>>>>>>>>>>>>>>>>");
        throw new RuntimeException("test ex!!!");
        //return JsonData.success();
    }

    @RequestMapping(value = "/validate.json")
    public JsonData validate(TestVo vo){
        try {
            Map<String, String> map = BeanValidator.validateObject(vo);
            if(!MapUtils.isEmpty(map)){
                for(Map.Entry<String,String> entry : map.entrySet()){
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                }
            }
        }catch (Exception ex){

        }

        return JsonData.success("test validate");
    }

}
