package com.df.controller;

import com.df.common.CommonString;
import com.df.common.UUIDtil;
import com.df.common.ValidateCode;
import com.df.common.ValidateCodeUtil;
import com.df.interceptor.Authority;
import com.df.interceptor.AuthorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CodeController  {



    @Autowired
    private ValidateCodeUtil validateCodeUtil;
    /**
     * 响应验证码页面
     * @return
     */
    @RequestMapping(value="/web/validateCode")
    @Authority(AuthorityType.NoValidate)
    public void validateCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ValidateCode vCode = new ValidateCode(120,40,4,100);
        // 生成随机id
        String UUID = UUIDtil.generateShortUuid();
        Cookie c=new Cookie( CommonString.ValidateCodeKey,UUID);
        c.setPath("/");
        response.addCookie(c);
        validateCodeUtil.saveToCache(UUID,vCode.getCode());
        vCode.write(response.getOutputStream());
        //return null;
    }


}