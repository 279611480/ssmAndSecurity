package org.yun.ssmAndSecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.yun.ssmAndSecurity.service.UserService;
import org.yun.ssmAndSecurity.vo.UserVO;

import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Autor 拾笔丶
 * @Date 2019/9/7 22:34
 */
//4、@restcontroller注解的功能等同于@controller和@responsebody
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController<test> {

    @Autowired(required=true)
    private UserService userService;

    //跳转首页（登录页）  http://localhost:8080/user/index   user是@RequestMapping设置的（24行类上面的）  index是32行方法上面的
    @RequestMapping("/index")
    public String index(){
        return "user/index";//因为resources/templates创建了user文件夹  把内容放进去了
    }

    @RequestMapping("/login")
    public String login(WebRequest request){//request也可以这么拿  SpringMvc中
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)   ){
            System.out.println("登录失败");
            return "user/fail";//因为resources/templates创建了user文件夹  把内容放进去了
        }
        log.debug("查询开始");
        List<UserVO> all = userService.findAll();
        log.debug("查询结束");
        for (UserVO userVO : all) {
            if (userVO.getName().equals(name) && userVO.getPassword().equals(password)) {
                //登录成功
                return "user/index";//因为resources/templates创建了user文件夹  把内容放进去了
            }
        }
        //登录失败
        return "用户名  密码未注册";
    }


    @Test
    public void test() {
        List<UserVO> all = userService.findAll();
        for (UserVO userVO : all) {
            System.out.println(userVO.getName());
        }
    }

    /*  @RequestMapping("/listPage")
    public ResultVO<?> listPage(@RequestBody FactoryDTO dto){
        try {
            PageInfo pageInfo =factoryService.listPage(dto);
            dto.setTotal(pageInfo.getTotal());
            dto.setPages(pageInfo.getPages());
            return ResultSupport.successfy(pageInfo.getList(),dto);
        }catch (Exception e){
            log.error("查询失败");
            return ResultSupport.fail("查询失败");
        }
    }*/
}
