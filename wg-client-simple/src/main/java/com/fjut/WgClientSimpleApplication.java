package com.fjut;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @EnableTransactionManagement(proxyTargetClass = true) 开启Spring事务功能
 * @SpringBootApplication标识当前应用为SpringBoot应用
 * @author LGX
 *
 */
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
public class WgClientSimpleApplication {

	public static void main(String[] args) {
		//初始化Spring
		new SpringApplicationBuilder(WgClientSimpleApplication.class).headless(false).run(args);
		//显示界面
		ViewStart.run();
	}
}
