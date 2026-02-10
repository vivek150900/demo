package com.dynamicrbac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;

import com.dynamicrbac.security.CustomPermissionEvaluator;

@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig {

    private final CustomPermissionEvaluator permissionEvaluator;

    public MethodSecurityConfig(CustomPermissionEvaluator permissionEvaluator) {
        this.permissionEvaluator = permissionEvaluator;
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        DefaultMethodSecurityExpressionHandler handler =
                new DefaultMethodSecurityExpressionHandler();
        handler.setPermissionEvaluator(permissionEvaluator);
        return handler;
    }
}
