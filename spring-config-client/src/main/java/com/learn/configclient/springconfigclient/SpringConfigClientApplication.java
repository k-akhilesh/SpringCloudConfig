/**
 * Configuration Client
 * @author Akhilesh
 */
package com.learn.configclient.springconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope //This annotation enables actuator to refresh the values of keys at runtime.
public class SpringConfigClientApplication {

	/*Injected at startup from config server.*/
	@Value("${url}") 
	private String welcomeText;
	
	/*Injected at startup from config server.*/
	@Value("${api}") 
	private String api;

	/* Rest Endpoint that reterives values of config properties*/
	@RequestMapping(value = "/") 
	public String welcomeText() {
		return welcomeText + " " + api;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringConfigClientApplication.class, args);
	}

}
