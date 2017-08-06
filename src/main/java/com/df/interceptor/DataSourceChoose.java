package com.df.interceptor;

import com.df.common.JdbcContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;


public class DataSourceChoose {

	//方法执行前
	public void before(JoinPoint point){
		Object target = point.getTarget();  
        String method = point.getSignature().getName();  
        Class<?>[] classz = target.getClass().getInterfaces();  
        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);  
            if (m!=null && m.isAnnotationPresent(DataSource.class)) {  
                DataSource data = m.getAnnotation(DataSource.class);  
                JdbcContextHolder.clearJdbcType();
                JdbcContextHolder.setJdbcType(data.value().getName());
            }  
        } catch (Exception e) {  
            // TODO: 异常处理
            System.out.println("数据源选择出现异常:"+e.getMessage());
        } 
	}
}
