package com.fjut.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 客户端版本信息
 * @author LGX
 *
 */
@Component("ClientMSG")
@Getter@Setter
public class ClientMSG {
	
	/**
	 * 当前版本
	 */
	@Value("${client.version}")
	private String version;
	
	/**
	 * 关于我们
	 */
	@Value("${client.aboutme}")
	private String aboutMe;
}
