package org.yun.ssmAndSecurity.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;


/**
 * @ClassName MyFilterSecurityInterceptor
 * @Description (5) 继承AbstractSecurityInterceptor 以及实现Filter
 *      访问鉴权过滤器
 *      该过滤器的作用是：用户请求时，提供权限资源管理器和权限判断器工作的场所，实现鉴权操作
 * @Autor 落笔丶
 * @Date 2019/9/26 15:02
 * @Tel 279611480@qq.com
 */
@Service
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    //设置决策器
    @Autowired
    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager){
        super.setAccessDecisionManager(myAccessDecisionManager);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(filterInvocation);
    }

    public void invoke(FilterInvocation filterInvocation) throws IOException, ServletException{
        //filterInvocation里面有一个被拦截的URL
        //里面调用MyInvocationSecurityMetadaSource的getAttributea(Object object)这个方法获取filterInvocation对应的所有权限
        //再调用，MyAccessDecisionManager的decide方法来检验用户的权限是否足够
        InterceptorStatusToken interceptorStatusToken = super.beforeInvocation(filterInvocation);
        try {
            //执行下一个拦截器
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(),filterInvocation.getResponse());
        }finally {
            super.afterInvocation(interceptorStatusToken,null);
        }
    }



    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    //添加判断URL所需的权限类
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.filterInvocationSecurityMetadataSource;
    }
}
