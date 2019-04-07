package com.fjut;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.fjut.handler.Update;

@SpringBootApplication
public class WgUpdateSimpleApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(WgUpdateSimpleApplication.class).headless(false).run(args);
		Update frame = new Update();
		frame.setVisible(true);
	}
}
