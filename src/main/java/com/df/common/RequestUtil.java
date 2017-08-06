package com.df.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-05.
 */
public class RequestUtil {
    public static String getValueByRequesOrCookies(HttpServletRequest request,String tokenKey){
        String stoken=request.getParameter(tokenKey);
        if(StrUtil.isNullOrEmpty(stoken)){
            Cookie tokencookie = getCookieByKey(request,tokenKey);
            if(tokencookie==null){
                return null;
            }
            return tokencookie.getValue();
        }
        return stoken;
    }

    public static String getValueByReques(HttpServletRequest request,String tokenKey){
        String stoken=request.getParameter(tokenKey);
        if(StrUtil.isNullOrEmpty(stoken)){
            return null;
        }
        return stoken;
    }

    /**
     * 根据名字获取cookie
     * @param request
     * @param keyName cookie名字
     * @return
     */
    public static Cookie getCookieByKey(HttpServletRequest request, String keyName){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(keyName)){
            Cookie cookie = (Cookie)cookieMap.get(keyName);
            return cookie;
        }else{
            return null;
        }
    }


    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
