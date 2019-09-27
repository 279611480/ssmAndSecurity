package org.yun.ssmAndSecurity.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yun.ssmAndSecurity.util.MD5Util;

/**
 * @ClassName WebSecurityConfig
 * @Description (1)SpringSecurity配置文件继承WebSecurityConfigurerAdapter
 * @Autor 落笔丶
 * @Date 2019/9/16 22:13
 * @Tel 279611480@qq.com
 */
@Configuration//表示配置文件
@EnableWebSecurity//@EnableGlobalMethodSecurity(prePostEnabled=true)//是否启用注解进行接口权限拦截的（默认为false）
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    /*自定义生成两个带有权限的用户
     *  * 其中，配置了2个固定的用户，分别是 admin（密码是123），角色是ADMIN，第二个用户是user（密码是123），角色是USER。

//     * */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("SecurityConfig.configure > withUser");
//
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("ADMIN")
//                .and()
//                .withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER");
//    }
    //配置加密
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //指定密码加密所使用的加密器为new PasswordEncode(){}   里面有自己的MD5Util工具包加密解密
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new PasswordEncoder() {
            //加密
            @Override
            public String encode(CharSequence charSequence) {//charSequence是页面输入的密码
                return MD5Util.encode((String) charSequence);
            }
            //解密   前者是输入的密码    后者是，数据库查询的密码
            @Override
            public boolean matches(CharSequence charSequence, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String) charSequence));
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/img/**");
        //   web.ignoring().antMatchers("/lib/**");
        //解决服务注册url被拦截的问题  不加的话，会把自己的之前的url全部，替换成SpringSecurity自带的登录页
        web.ignoring().antMatchers("/user/**");
    }
    //配置拦截策略
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/security/login")//登录页面
            .defaultSuccessUrl("/securitycurity/index") //登录成功后默认跳转页面
            .failureUrl("/security/error")//登录失败跳转页面
            .permitAll() // 允许任何用户访问
            //其它页面必须验证后，才能访问
            .and()
            .csrf().disable();//关掉防跨站式攻击

             /*  //解决中文乱码问题
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            filter.setEncoding("UTF-8");
            filter.setForceEncoding(true);
            http.addFilterBefore(filter, CsrfFilter.class);*/
    }



}
