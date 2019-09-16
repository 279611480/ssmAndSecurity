package org.yun.ssmAndSecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SecurityController
 * @Description
 * @Autor 落笔丶
 * @Date 2019/9/14 16:16
 * @Tel 279611480@qq.com
 */
@Controller
@RequestMapping("/security")
@Slf4j
public class SecurityController {

    @RequestMapping("/index")
    public String index(){
        log.debug("首页开始跳转");
        return "/security/index";//需要加上Security，是以为自己在资源文件夹下面的templates里面创建了security子文件夹，将security测试的HTML文件，放进去了，免得分不清
    }

    @RequestMapping("/login")
    public String login(){
        return "/security/login";
    }

    @RequestMapping("/error")
    public String error(){
        return "/security/error";
    }

}
