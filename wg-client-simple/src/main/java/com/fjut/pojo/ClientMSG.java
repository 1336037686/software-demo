package com.fjut.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component("ClientMSG")
@Getter@Setter
public class ClientMSG {
	@Value("${client.version}")
	private String version;
	
	@Value("${client.aboutme}")
	private String aboutMe;
}
