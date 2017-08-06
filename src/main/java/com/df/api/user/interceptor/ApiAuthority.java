package com.df.api.user.interceptor;

import java.lang.annotation.*;

/**
 * 自动注解类
 * Created by Administrator on 2017-08-05.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Documented
public @interface ApiAuthority {
    ApiAuthorityType value() default ApiAuthorityType.Authority_1;
}
