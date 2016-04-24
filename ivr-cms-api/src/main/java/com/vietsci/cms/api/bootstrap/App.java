package com.vietsci.cms.api.bootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring boot application class
 * 
 */

@ComponentScan
@EnableAutoConfiguration
public class App {
	
  /**
   * Spring boot application initialization
   * 
   * @param args the arguments
   */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
