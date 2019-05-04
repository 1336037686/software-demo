package com.fjut.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义物料日志切面注解
 * 使用MaterialsConst类进行基本的行为选择
 * @author LGX
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLogWarehouseAspect {

	/**
	 * 物料具体操作行为，使用MaterialsConst操作
	 * @return
	 */
	public int value();
}
