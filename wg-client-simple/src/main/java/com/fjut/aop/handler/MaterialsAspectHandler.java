package com.fjut.aop.handler;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fjut.aop.annotation.SystemLogMaterialsAspect;
import com.fjut.aop.constant.MaterialsConst;
import com.fjut.pojo.Materials;
import com.fjut.pojo.Session;
import com.fjut.pojo.SystemLog;
import com.fjut.pojo.User;
import com.fjut.service.SystemLogService;
import com.fjut.util.SystemUtil;

/**
 * 物料日志切面处理
 * @author LGX
 *
 */
@Aspect
@Component
@SuppressWarnings("all")
public class MaterialsAspectHandler {

	@Autowired
	private SystemLogService systemLogService;

	/**
	 * 物料切面处理
	 */
	@Pointcut("@annotation(com.fjut.aop.annotation.SystemLogMaterialsAspect)")
	public void materialsAspect() {};
	
	@AfterReturning(pointcut = "materialsAspect() && @annotation(annotation)", returning = "result")
	public void materialsAspectReturning(JoinPoint joinPoint, SystemLogMaterialsAspect annotation, boolean result) {
		//获取参数
		Object[] args = joinPoint.getArgs();
		
		//获取切面类型
		int type = annotation.value();
		
		//获取操作用户
		User user = (User)Session.getSession().get("user");
		
		//物料增加
		if(MaterialsConst.MATERIALSADD == type) {
			Materials materials = (Materials) args[0];
			if(result == true) {
				SystemLog sl = new SystemLog(0, new Date(), SystemUtil.getLocalHost(), user.getId(), "[" + user.getUserName() + "] 添加物料档案: " + materials);
				systemLogService.insertLog(sl);
			}
		}
		
		//物料修改
		if(MaterialsConst.MATERIALSUPDATE == type) {
			Materials materials = (Materials) args[0];
			if(result == true) {
				SystemLog sl = new SystemLog(0, new Date(), SystemUtil.getLocalHost(), user.getId(), "[" + user.getUserName() + "] 修改物料档案: " + materials);
				systemLogService.insertLog(sl);
			}
		}
		
		//物料删除
		if(MaterialsConst.MATERIALSDELETE == type) {
			String id = (String) args[0];
			if(result == true) {
				SystemLog sl = new SystemLog(0, new Date(), SystemUtil.getLocalHost(), user.getId(), "[" + user.getUserName() + "] 删除物料档案: id=" + id);
				systemLogService.insertLog(sl);
			}
		}	
	}
}
