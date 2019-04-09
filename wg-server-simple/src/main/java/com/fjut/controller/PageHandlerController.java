package com.fjut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制
 * @author LGX
 *
 */
@Controller
public class PageHandlerController {

    /**
     * 跳转到主页面
     */
    @RequestMapping("/index")
    public String hello(){
        return "/index";
    }
}
