package com.df.api.user.interceptor;

import com.df.common.CommonString;
import com.df.common.RCodeUtil;
import com.df.common.RequestUtil;
import com.df.common.StrUtil;
import com.google.gson.Gson;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-05.
 */
public class ApiAuthorityAnnotationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;

            Class<?> clazz = hm.getBeanType();
            Method m = hm.getMethod();
            try {
                if (clazz != null && m != null) {
                    boolean isClzAnnotation = clazz.isAnnotationPresent(ApiAuthority.class);
                    boolean isMethondAnnotation = m.isAnnotationPresent(ApiAuthority.class);
                    ApiAuthority apiauthority = null;
                    // 如果方法和类声明中同时存在这个注解，那么方法中的会覆盖类中的设定。
                    if (isMethondAnnotation) {
                        apiauthority = m.getAnnotation(ApiAuthority.class);
                    } else if (isClzAnnotation) {
                        apiauthority = clazz.getAnnotation(ApiAuthority.class);
                    }
                    int code = -1;
                    String msg = "错误！";
                    if (apiauthority != null) {
                        //System.out.println("对"+m.getName()+"进行权限认证");
                        if (ApiAuthorityType.NoValidate == apiauthority.value()) {
                            // 标记为不验证,放行
                            return true;
                        } else if (ApiAuthorityType.Authority_1 == apiauthority.value()) {
                            // 进行等级1的验证
                            String apiToken = RequestUtil.getValueByReques(request, CommonString.UserTokenKey );
                            if( !StrUtil.isNullOrEmpty(apiToken) ) {
                                // todo 验证获得的token是否有权限
                                boolean islogin = false;
                                if(islogin){
                                    return true;
                                }
                            }
                            code = RCodeUtil.Failed_Validation;
                            msg = "不具有权限！";
                        }   else if (ApiAuthorityType.Authority_2 == apiauthority.value()) {
                            // 进行等级2 的验证
                            String apiToken = RequestUtil.getValueByReques(request, CommonString.UserTokenKey );
                            if( !StrUtil.isNullOrEmpty(apiToken) ) {
                                // todo 验证获得的token是否有权限
                                boolean islogin = false;
                                if(islogin){
                                    return true;
                                }
                            }
                            code = RCodeUtil.Failed_Validation;
                            msg = "不具有权限！";
                        }   else {
                            // 验证登录及权限
                            //throw new Exception("");
                            code = RCodeUtil.ERR;
                            msg = "不应该出现的错误！";
                            // return false;
                        }
                    } else {
                        //未设置 authority 注解的，默认为不需要验证。  //dforel 后改为 未设置 authority的，需要验证
                        //return true;
                    }
                    // 未通过验证，返回提示json
                    Map<String, Object> responseMap = new HashMap<String, Object>();
                    responseMap.put("code", code);
                    responseMap.put("msg", msg);
                    responseMap.put("params", "");
                    responseMap.put("rows", "");
                    String json = new Gson().toJson(responseMap);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    response.getWriter().write(json);
                    return false;
                }
            } catch (Exception e) {
                Map<String, Object> responseMap = new HashMap<String, Object>();
                responseMap.put("code", RCodeUtil.ERR);
                responseMap.put("msg", "Api验证异常！");
                responseMap.put("params", "");
                responseMap.put("rows", "");
                String json = new Gson().toJson(responseMap);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().write(json);
            }
        }
        return false;
    }

}
