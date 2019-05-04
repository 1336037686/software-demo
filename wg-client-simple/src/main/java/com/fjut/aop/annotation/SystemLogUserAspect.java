package com.fjut.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义用户日志切面注解
 * 使用UserConst类进行基本的行为选择
 * @author LGX
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLogUserAspect {

	/**
	 * 用户具体操作行为，使用UserConst操作
	 * @return
	 */
	public int value();
}
