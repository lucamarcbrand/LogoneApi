package com.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container.
@Configuration
public class ApplicaionBeanConfiguation {
	@Bean
	public BCryptPasswordEncoder getBcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext getContext() {
		return new SpringApplicationContext();
	}

	
}
