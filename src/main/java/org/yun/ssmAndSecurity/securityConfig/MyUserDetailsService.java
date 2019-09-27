package org.yun.ssmAndSecurity.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yun.ssmAndSecurity.entity.SecurityPermission;
import org.yun.ssmAndSecurity.entity.SecurityRole;
import org.yun.ssmAndSecurity.service.SecurityService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyUserDetialsService
 * @Description (2)自定义登陆账号验证   实际开发中我们需要在数据库中存储用户的账号密码信息，所以我们需要自定义验证方式。
 * @Autor 落笔丶
 * @Date 2019/9/18 14:30
 * @Tel 279611480@qq.com
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    SecurityService securityService;
    /**
    *
     * @Author 落笔丶
     * @Description 重写自己验证  网页上面传输的账号信息  是否与自己的数据库一直
     * @Date  2019/9/18 14:32
     * @Param  * @param null
     * @return
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //此处的User是自己entity自定义的user类
        org.yun.ssmAndSecurity.entity.SecurityUser user = securityService.findByUsername(username);

        //完成，将每个用户对应的角色 以及  权限，放入
        if (user != null){
            //GrantedAuthority对象代表赋予给当前用户的一种权限。   相当于某一用户的权限集合
            ArrayList<GrantedAuthority> grantedAuthorities  = new ArrayList<>();
            //获取用户的角色集合
//            List<SecurityRole> securityRoles = user.getRoles();//先看看是否有值  没的话，放掉下面的注释
            List<SecurityRole> securityRoles = securityService.findByUserId(user.getId());
            //遍历角色集合，并且获取每个角色所拥有的权限
            for (SecurityRole role : securityRoles){
                //获取每个角色，对应的所有权限,倘若没值，释放下面的注释掉的方法
//                List<SecurityPermission> permissions = role.getPermissions();
               List<SecurityPermission> permissions = securityService.findByRoleId(role.getId());
                 //遍历权限集合，将其放入GrantedAuthority的简单实现类中SimpleGrantedAuthority
                for (SecurityPermission permission : permissions) {
                    //为每个授权中心对象写入权限名
                    grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
                }
            }

            //实现了UserDetails接口的User类，
            //简单来讲，就是，对比，看网页中传输的用户信息（账号密码等）是否与数据库一致，是的话，那么就，封装存储起来。
            //然后，就来到了这个实现了UserDetailServuce的类中，验证   成功，就返回一个封装类，将信息存储起来

            /* 此处的user是springSecurity中一个实现了UserDetails接口的User类，
            因为我们并没有在Entity中的SecurityUser里面实现UserDetails接口，所以只能在此处调用实现好了的构造方法

            就把用户信息的封装（包含着身份信息、细节信息等）存到SecurityContextHolder中（类似Session），使用的时候还要取出来。

            而这个过程中，从数据库中取出的用户信息的封装不是简单的User实例，
            而是一个实现了UserDetails这个接口的类的对象，这个对象里面不仅有用户的账号密码信息，
            还有一些判断账号是否可用、判断账号是否过期、判断账号是否被锁定的函数。

            在验证过程中，负责根据用户输入的用户名返回数据库中用户信息的封装这个功能的就是Service，它实现了UserDetailsService，
            重写了它的loadUserByUsername(String s)方法，这个方法就是根据用户名返回了UserDetails的一个具体实现。
            */
            return new User(user.getUsername(),user.getPassword(),grantedAuthorities);
        }
        return null;
    }
}
