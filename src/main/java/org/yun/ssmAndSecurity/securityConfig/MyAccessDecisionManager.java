package org.yun.ssmAndSecurity.securityConfig;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @ClassName MyAccessDecisionManager
 * @Description （4）实现，AccessDecisionManager接口，表示为决策类。决策该用户的request是否，有权限访问
 * @Autor 落笔丶
 * @Date 2019/9/25 21:37
 * @Tel 279611480@qq.com
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
    *
     * @Author 落笔丶
     * @Description 决策方法  decide
     * @Date  2019/9/25 21:39
     * @Param authentication   UserService中循环添加到GrantedAuthority中的权限信息集合  可见-->MyUserDetailsService
     * @param  o              包含客户端发起的request信息，可转换为HttpRequest
     * @Param collection      Url所需的权限集合-->可见MyInvocationSecurityMetadaSourceService
     * //configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
     * @return
     **/
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //判断Url所需的权限集合是否为空，为空就放行
        if(collection == null || collection.size() <=0){
            return;
        }
        String needPermission;//自定义 所需要的权限  字段

        for (ConfigAttribute configAttribute : collection) {
            //获取所需权限
            needPermission = configAttribute.getAttribute();
            //遍历，用户所拥有的权限与URL所需的权限进行判断
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()){
                // trim()的作用是去掉字符串两端的多余的空格,注意,是两端的空格,且无论两端的空格有多少个都会去掉,当然 中间的那些空格不会被去掉
                if (needPermission.trim().equals(grantedAuthority.getAuthority())){
                    return;
                }
            }
        }
        throw new IllegalStateException("no permission 没有该权限--->MyAccessDecisionManager");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
