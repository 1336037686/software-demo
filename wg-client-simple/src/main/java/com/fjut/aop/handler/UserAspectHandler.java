package com.fjut.aop.handler;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fjut.aop.annotation.SystemLogUserAspect;
import com.fjut.aop.constant.UserConst;
import com.fjut.pojo.Session;
import com.fjut.pojo.SystemLog;
import com.fjut.pojo.User;
import com.fjut.service.SystemLogService;
import com.fjut.util.MD5Util;
import com.fjut.util.SystemUtil;

/**
 * 用户日志切面处理
 * @author LGX
 *
 */
@Aspect
@Component
@SuppressWarnings("all")
public class UserAspectHandler {
	
	@Autowired
	private SystemLogService systemLogService;
	
	
	/**
	 * 用户登录切面处理
	 */
	@Pointcut("@annotation(com.fjut.aop.annotation.SystemLogUserAspect)")
	public void userAspect() {}
	
	/**
	 * 切面处理方法
	 * @param joinPoint 切入点
	 * @param annotation 自定义注解
	 * @param result 方法boolean返回值
	 */
	@AfterReturning(pointcut = "userAspect() && @annotation(annotation)", returning = "result")
	public void userAspectReturning(JoinPoint joinPoint, SystemLogUserAspect annotation, boolean result) {
		//获取参数，获取切入方法参数
		Object[] args = joinPoint.getArgs();
		
		//获取切面类型,获取注解值
		int type = annotation.value();
		
		//用户登录,添加日志
		if(UserConst.USERLOGIN == type) {
			if(result == true) {
				User user = (User)Session.getSession().get("user");
				SystemLog sl = new SystemLog(0, new Date(), SystemUtil.getLocalHost(), user.getId(), "[" + user.getUserName() + "] 用户登陆");
				systemLogService.insertLog(sl);
			}	
		}
		
		//用户删除,添加日志
		if(UserConst.USERDELETE == type) {
			User user = (User)Session.getSession().get("user");
			SystemLog sl = new SystemLog(0, new Date(), SystemUtil.getLocalHost(), user.getId(), "[" + user.getUserName() + "] 删除用户：[id=" + args[0] + ", userId=" + args[1] + "]");
			systemLogService.insertLog(sl);
		}
		
		//用户注册,添加日志
		if(UserConst.USERREGISTER == type) {
			User user = (User)Session.getSession().get("user");
			User register = (User) args[0];
			if(result == true) {
				SystemLog sl = new SystemLog(0, new Date(), SystemUtil.getLocalHost(), user.getId(), "[" + user.getUserName() + "] 注册用户：" + register);
				systemLogService.insertLog(sl);
			}
		}
		
		//用户更改,添加日志
		if(UserConst.USERUPDATE == type) {
			User user = (User)Session.getSession().get("user");
			User update = (User) args[0];
			if(result == true) {
				if(update.getPassword().length() != 32) update.setPassword(MD5Util.md5(update.getPassword()));
				SystemLog sl = new SystemLog(0, new Date(), SystemUtil.getLocalHost(), user.getId(), "[" + user.getUserName() + "] 修改用户：" + update);
				systemLogService.insertLog(sl);
			}
		}
	}
}
