package com.xu.springbootdemo.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {

    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //调用getUserRealm获得UserRealm
        securityManager.setRealm(getUserRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
        factory.setSecurityManager(getSecurityManager());

        /**
         * 拦截请求
         * anon:无需认证
         * authc:需要认证
         * user:需要使用rember后才能访问
         */
        Map<String,String> chain = new LinkedHashMap<>();
        chain.put("/","anon");
        factory.setFilterChainDefinitionMap(chain);
        //未登录时跳转到的页面
        factory.setLoginUrl("/toLogin");
        factory.setSuccessUrl("/toAdmin");
        return factory;
    }

    /**
     * 配合前端页面使用标签
     * <html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 开启aop注解支持
     * 即在controller中使用 @RequiresPermissions("user/userList")
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        //设置安全管理器
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * 开启aop注解支持
     * over
     */
    /**
     * 处理未授权的异常，返回自定义的错误页面（403）
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        /*未授权处理页*/
        properties.setProperty("UnauthorizedException", "login.html");
        resolver.setExceptionMappings(properties);
        return resolver;
    }


}
