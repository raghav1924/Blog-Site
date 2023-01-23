package com.blog.userApp;

import com.blog.userApp.filter.CFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class UserAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean<?> filterUrl(){
		System.out.println("inside filterurl");
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new CFilter());
		filterRegistrationBean.addUrlPatterns("/userApp/addBlog");
		filterRegistrationBean.addUrlPatterns("/userApp/updateBlog");
//		filterRegistrationBean.addUrlPatterns("/userApp/updateComment");
		filterRegistrationBean.addUrlPatterns("/userApp/getUser");
		filterRegistrationBean.addUrlPatterns("/userApp/deleteBlog");
		return filterRegistrationBean;
	}
}
