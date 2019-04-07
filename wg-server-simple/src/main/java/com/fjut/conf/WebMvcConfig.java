package com.fjut.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//addResourceHandler是指你想在url请求的路径
		//addResourceLocations是图片存放的真实路径
		registry.addResourceHandler("/file/**").addResourceLocations("file:E://FileUpload//");
		super.addResourceHandlers(registry);
	}
}
