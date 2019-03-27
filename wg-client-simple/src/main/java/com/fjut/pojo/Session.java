package com.fjut.pojo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 缓存数据
 * @author LGX
 *
 */
@Component
public class Session {
	private static Map<String, Object> session = new HashMap<>();
	
	public static Map<String, Object> getSession(){
		return session;
	}
}
