package com.fjut.aop.handler;

import java.util.Date;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fjut.aop.annotation.SystemLogMaterialsAspect;
import com.fjut.pojo.MaterialsSell;
import com.fjut.pojo.MaterialsSellDetail;
import com.fjut.pojo.Session;
import com.fjut.pojo.SystemLog;
import com.fjut.pojo.User;
import com.fjut.service.SystemLogService;
import com.fjut.util.SystemUtil;

/**
 * 进出仓切面处理
 * @author LGX
 *
 */
@Aspect
@Component
@SuppressWarnings("all")
public class WarehouseAspectHandler {
	
	@Autowired
	private SystemLogService systemLogService;
	
	/**
	 * 仓库切面处理
	 */
	@Pointcut("@annotation(com.fjut.aop.annotation.SystemLogWarehouseAspect)")
	public void warehouseAspect() {};
	
	
	@Around("warehouseAspect() && @annotation(annotation)")
	public void WarehouseAspectReturning(ProceedingJoinPoint joinPoint, SystemLogMaterialsAspect annotation) {
//		//获取参数
//		Object[] args = joinPoint.getArgs();
//		
//		//获取切面类型
//		int type = annotation.value();
//		System.out.println("type:" + type);
//		
//		//获取操作用户
//		User user = (User)Session.getSession().get("user");
//		
//		try {
//			boolean result = (boolean) joinPoint.proceed(args);
//			if(result == true) {				
//				MaterialsSell materialsSell = (MaterialsSell) args[0];
//				List<MaterialsSellDetail> materialsSellDetailsList = (List<MaterialsSellDetail>) args[1];
//				StringBuffer sb = new StringBuffer();
//				sb.append(materialsSell.toString());
//				if(materialsSellDetailsList != null) {
//					for (MaterialsSellDetail materialsSellDetail : materialsSellDetailsList) {
//						sb.append(materialsSellDetail + "\n");
//					}
//				}
//				
//				SystemLog sl = new SystemLog(0, new Date(), SystemUtil.getLocalHost(), user.getId(), sb.toString());
//				System.out.println(sl);
//				systemLogService.insertLog(sl);
//			}
//		} catch (Throwable e) {
//			//...
//		} finally {
//			//...
//		}
		

	}
	
	
}
