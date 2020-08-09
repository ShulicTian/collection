package com.data.collection.common.config;

import com.data.collection.common.entity.FilterChainDefinition;
import com.data.collection.common.entity.ShiroProperties;
import com.data.collection.common.filters.AuthFilter;
import com.data.collection.common.security.SystemAuthorizingRealm;
import com.data.collection.common.security.session.CacheSessionDAO;
import com.data.collection.common.security.session.SessionManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro配置类
 *
 * @author ShulicTian
 * @date 2020/01/09
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private ShiroProperties shiroProperties;

    /**
     * 将自己的认证方式加入容器
     *
     * @return
     */
    @Bean
    public SystemAuthorizingRealm sysShiroRealm() {
        SystemAuthorizingRealm sysRealm = new SystemAuthorizingRealm();
        return sysRealm;
    }

    /**
     * 配置Shiro缓存
     *
     * @return
     */
    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:cache/ehcache-local.xml");
        return em;
    }

    @Bean
    public SessionManager sessionManager() {
        //将我们继承后重写的shiro session 注册
        SessionManager sessionManager = new SessionManager();
        //如果后续考虑多tomcat部署应用，可以使用shiro-redis开源插件来做session 的控制，或者nginx 的负载均衡

        sessionManager.setSessionDAO(new CacheSessionDAO());
        return sessionManager;
    }

    /**
     * 配置权限管理器
     *
     * @param ehCacheManager
     * @return
     */
    @Bean
    public SecurityManager securityManager(EhCacheManager ehCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(sysShiroRealm());
        securityManager.setCacheManager(ehCacheManager);
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterMap = new LinkedHashMap<>();
        List<FilterChainDefinition> list = shiroProperties.getFilterChainDefinitionList();
        //读取配置文件中的过滤配置信息
        Collections.reverse(list);
        list.forEach(obj -> filterMap.put(obj.getKey(), obj.getValue()));
        //登录
        shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
        //首页
        shiroFilterFactoryBean.setSuccessUrl(shiroProperties.getSuccessUrl());
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());
        //将过滤配置注入
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        filtersMap.put("authFilter", new AuthFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * 开启aop注解支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}