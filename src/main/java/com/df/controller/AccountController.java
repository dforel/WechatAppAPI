package com.df.controller;

import com.df.activemq.MessageSender;
import com.df.cache.LoginTokenCache;
import com.df.common.*;
import com.df.interceptor.Authority;
import com.df.interceptor.AuthorityType;
import com.df.model.UserBasis;
import com.df.service.UserBasisService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@Authority(AuthorityType.NoValidate)
//@RequestMapping("/account/*")
public class AccountController {

	@Autowired
	private UserBasisService userBasisService;
	@Autowired
	private MessageSender messageSender;

	@Autowired
	private ValidateCodeUtil validateCodeUtil;

	@Autowired
	private LoginTokenCache loginTokenCache;


	private Logger log = Logger.getLogger(AccountController.class);

	@Authority(AuthorityType.NoValidate)
	@ResponseBody
	@RequestMapping(value = "/web/login", produces = "application/json;charset=UTF-8", method = { RequestMethod.POST })
	public String login( HttpServletResponse response, @RequestParam("phone") String phone, @RequestParam("password") String password,
	@RequestParam("validatecode") String  validatecode ,@CookieValue(CommonString.ValidateCodeKey) String verifysession ) {
		int code = RCodeUtil.ERR;
		String msg = "";
		Map<String, Object> params = new HashMap<String, Object>();

		if (StrUtil.isNullOrEmpty(phone)) {
			msg = "请填写手机号！";
			code = RCodeUtil.PHONE_EMPTY;
		} else if (StrUtil.isNullOrEmpty(password)) {
			msg = "请填写密码！";
			code = RCodeUtil.PASSWORD_EMPTY;
		} else if (StrUtil.isNullOrEmpty(validatecode)||StrUtil.isNullOrEmpty(verifysession)) {
			msg = "验证码异常！";
			code = RCodeUtil.VALIDATE_ERR;
		}  else {
			UserBasis entity = userBasisService.getEntity(phone);
			if(!validateCodeUtil.verifyCode(verifysession,validatecode)){
				msg = "验证码错误！";
				code = RCodeUtil.WRONG_VALIDATE_CODE;
			}
			else if (entity != null && entity.getId() > 0) {
				// System.out.println(MD5Util.GetMD5Code32(password));
				if (entity.getPassword().equals(MD5Util.GetMD5Code32(password))) {
					code = RCodeUtil.SUCCESS;
					msg = "登录成功！";
					messageSender.userLogin(entity.getId(), entity.getName());

					String randomToken = UUIDtil.getUUID();
					//往用户cache中缓存 登录token 和用户id
					loginTokenCache.set( randomToken,entity.getId() );
					Cookie c2 =new Cookie( CommonString.LoginTokenKey,randomToken);
					c2.setPath("/");
					c2.setMaxAge(3600);
					response.addCookie(c2);

					// 登录成功以后返回cookies及登录token
					params.put( CommonString.LoginTokenKey ,randomToken);
				} else {
					msg = "用户密码错误！";
					code = RCodeUtil.WRONG_PASSWORD;
				}
			} else {
				msg = "用户不存在！";
				code = RCodeUtil.USER_NO_EXIST;
			}
		}

		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("code", code);
		responseMap.put("msg", msg);
		responseMap.put("params", params);
		responseMap.put("rows", "");
		return new Gson().toJson(responseMap);
	}

	/**
	 * 注册控制器
	 * @param phone 手机号
	 * @param name 账号
	 * @param password 密码
	 * @param validatecode 验证码
	 * @param verifysession 验证码session号
	 * @return 返回json信息，code返回错误码，错误码可查询RCodeUtil，msg返回相应错误原因
	 */
	@Authority(AuthorityType.NoValidate)
	@ResponseBody
	@RequestMapping(value = "/web/register", produces = "application/json;charset=UTF-8", method = { RequestMethod.POST })
	public String register(@RequestParam("phone") String phone, @RequestParam("name") String name, @RequestParam("password") String password
			,@RequestParam("validatecode") String  validatecode ,@CookieValue(CommonString.ValidateCodeKey) String verifysession ) {
		int code = RCodeUtil.ERR;
		String msg = "";

		if (StrUtil.isNullOrEmpty(phone)) {
			msg = "请填写电话号码！";
			code = RCodeUtil.PHONE_EMPTY;
		} else if (StrUtil.isNullOrEmpty(password)) {
			msg = "请填写密码！";
			code = RCodeUtil.PASSWORD_EMPTY;
		} else if (StrUtil.isNullOrEmpty(name)) {
			msg = "请填写帐号！";
			code = RCodeUtil.NAME_EMPTY;
		} else if (StrUtil.isNullOrEmpty(validatecode)||StrUtil.isNullOrEmpty(verifysession)) {
			msg = "验证码异常！";
			code = RCodeUtil.VALIDATE_ERR;
		}  else {

			//System.out.println(" sads ");
			if( !validateCodeUtil.verifyCode(verifysession,validatecode) ){
				msg = "验证码错误！";
				code = RCodeUtil.WRONG_VALIDATE_CODE;
			}else if( userBasisService.isExistName(name)){
				msg = "名字已存在！";
				code = RCodeUtil.NAME_EXIST;
			} else if( userBasisService.isExistPhone(phone)){
				msg = "电话已存在！";
				code = RCodeUtil.PHONE_EXIST;
			} else{
				UserBasis entity = new UserBasis(StrUtil.DEFAULT_NULL,name,password,phone,1);
				long id = userBasisService.Save(entity);
				if (id > 0) {
					code = RCodeUtil.SUCCESS;
					msg = "注册成功！";
				}else{
					code = RCodeUtil.ERR;
					msg = "注册失败，请稍后再试！";
				}
			}

		}

		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("code", code);
		responseMap.put("msg", msg);
		responseMap.put("params", "");
		responseMap.put("rows", "");
		return new Gson().toJson(responseMap);
	}


}
