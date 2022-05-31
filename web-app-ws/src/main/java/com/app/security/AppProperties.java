package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
//Indicates that an annotated class is a "component".Such classes are considered as candidates for auto-detectionwhen using annotation-based configuration and classpath scanning. 
public class AppProperties {

	@Autowired
	//Marks a constructor, field, setter method, or config method as to be autowired bySpring's dependency injection facilities. This is an alternative to the JSR-330 javax.inject.Inject annotation, adding required-vs-optional semantics. 

	Environment env;

	public String getProperties(String propertyName) {
		return env.getProperty(propertyName);
	}
}
