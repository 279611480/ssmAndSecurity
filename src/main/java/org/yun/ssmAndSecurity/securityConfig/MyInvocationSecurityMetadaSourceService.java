package org.yun.ssmAndSecurity.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.yun.ssmAndSecurity.entity.SecurityPermission;
import org.yun.ssmAndSecurity.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @ClassName MyInvocationSecurityMetadaSourceService
 * @Description （3）自定义权限验证   实现FilterInvocationSecurityMetadataSource接口。
 * 该类用于加载权限表中的url信息，并和request的url进行对比，有匹配则将该URL所需要的权限返回给decide()方法，不存在则返回空
 * @Autor 落笔丶
 * @Date 2019/9/25 20:47
 * @Tel 279611480@qq.com
 */
@Service
public class MyInvocationSecurityMetadaSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    SecurityService securityService;

    //ConfigAttribute此处放的是权限名    第一个参数，准备放的是权限URL   第二个参数，就是参数名了
    private HashMap<String , Collection<ConfigAttribute>> map = new HashMap<>();

    //在demo启动第一个用户登录后，加载所有的权限进map
    public void loadResourceDefine(){
        Collection<ConfigAttribute> collections = new ArrayList<>();//此处相当于权限名的集合
        ConfigAttribute configAttribute;//权限名
        //查询所有的权限信息
        List<SecurityPermission> list = securityService.findAllPermission();
        //遍历循环
        for (SecurityPermission securityPermission : list) {
            //此处，只添加了用户的名字，其实还可以添加更多的权限的信息，
            // 例如，请求方法，到ConfigAttribute的集合中去，此处，添加的信息将会作为MyAccessDecisionManager类的第三个参数
            configAttribute = new SecurityConfig(securityPermission.getName());//获取权限名
            collections.add(configAttribute);
            //用权限的getUrl()作为Map的Key，用ConfigAttribute的集合作为Value
            map.put(securityPermission.getUrl(),collections);
        }
    }


    // 根据URL，找到相关的权限配置。
    // object 是一个URL，被用户请求的url。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (map == null){
            loadResourceDefine();
        }
        //o 是参数       // object 是一个URL，被用户请求的url。
        HttpServletRequest request = ((FilterInvocation)o).getHttpRequest();
        AntPathRequestMatcher matcher;  //https://www.jianshu.com/p/4f9ee6007213   Url的匹配比较
        //遍历权限中的url
        //Map.keyset()，表示将map对象的所有key值已set集合的形式返回，因为map也是无序的，且key值也是不可重复的
        for (String url : map.keySet()){
            matcher = new AntPathRequestMatcher(url);
            //与 request 被用户请求的URL（页面的URL） 作比较 , 符合的话，则说明权限表中有该权限的URL
            if (matcher.equals(request)){
                return map.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
