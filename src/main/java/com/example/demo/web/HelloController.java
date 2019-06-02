package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RestController
public class HelloController {
    @ResponseBody
    @RequestMapping("/holle")
    public String hello(){
        return "hello world";
    }
    @RequestMapping(value = "/hi")
    public ModelAndView getHello(){

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("nihao","helle");
        modelAndView.setViewName("hello");
        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111");
        return modelAndView;
    }
    @RequestMapping(value = "/h")
    public String hello1() throws Exception {
        throw new Exception("发生错误");
    }
    @RequestMapping(value = "/")
    public String index(ModelMap map){
        //加入一个属性，用于在模板中读取
        map.addAttribute("host","http://baidu.com");
        //return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }
}
