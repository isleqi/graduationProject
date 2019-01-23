package com.isleqi.graduationproject.component.common;

//管理redis key前缀
public class RedisKeyPrefix {

	/**
	 * 将短信验证码放入redis中
	 */
	public final static String SMS_KEY = "SMS_KEY:";
	/**
	 * 将邮箱验证码放入redis中
	 */
	public final static String EMAIL_KEY = "EMAIL_KEY:";

	/**
	 * 将修改密码的身份验证放入redis
	 */
	public final static String PAY_PASSWORD_KEY = "PAY_PASSWORD_KEY:";
	
	/**
	 * 中台用户登录后的个人信息缓存key
	 */
	public static final String CACHE_MUSER_USERINFO = "cache:muser:userInfo:";

}
