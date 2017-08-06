package com.df.interceptor;

import com.df.cache.LoginTokenCache;
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
 * 权限认证拦截器
 *
 */
public class AuthorityAnnotationInterceptor extends HandlerInterceptorAdapter {

	//todo:这里使用autowrite会出错，不知道什么原因
	private LoginTokenCache loginTokenCache;

	public void setLoginTokenCache(LoginTokenCache loginTokenCache) {
		this.loginTokenCache = loginTokenCache;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//System.out.println("进行权限认证");
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;

			Class<?> clazz = hm.getBeanType();
			Method m = hm.getMethod();
			try {
				if (clazz != null && m != null) {
					boolean isClzAnnotation = clazz.isAnnotationPresent(Authority.class);
					boolean isMethondAnnotation = m.isAnnotationPresent(Authority.class);
					Authority authority = null;
					// 如果方法和类声明中同时存在这个注解，那么方法中的会覆盖类中的设定。
					if (isMethondAnnotation) {
						authority = m.getAnnotation(Authority.class);
					} else if (isClzAnnotation) {
						authority = clazz.getAnnotation(Authority.class);
					}
					int code = -1;
					String msg = "错误！";
					if (authority != null) {
						//System.out.println("对"+m.getName()+"进行权限认证");
						if (AuthorityType.NoValidate == authority.value()) {
							// 标记为不验证,放行
							return true;
						} else if (AuthorityType.AuthorityLogin == authority.value()) {
							// 不验证权限，验证是否登录
							String logintoken = RequestUtil.getValueByRequesOrCookies(request, CommonString.LoginTokenKey);
							if( !StrUtil.isNullOrEmpty(logintoken) ) {
								boolean islogin = loginTokenCache.isLogin(logintoken);
								if(islogin){
									return true;
								}
							}
							code = RCodeUtil.UNLOGIN;
							msg = "未登录！";
						}   else {
							// 验证登录及权限
							//throw new Exception("");
							code = RCodeUtil.ERR;
							msg = "不应该出现的错误！";
							// return false;
						}
					}else {
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
				responseMap.put("msg", "登录权限异常！");
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
