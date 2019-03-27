package com.fjut;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WgClientSimpleApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(WgClientSimpleApplication.class).headless(false).run(args);
		ViewStart.run();
	}
}
