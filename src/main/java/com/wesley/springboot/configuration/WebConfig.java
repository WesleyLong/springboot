package com.wesley.springboot.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.wesley.springboot.filter.TimeFilter;
import com.wesley.springboot.test.ListenerTest;
import com.wesley.springboot.test.ServletTest;
import com.wesley.springboot.test.TimeInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Value("${ds.userName}")
	private String userName;

	@Autowired
	private Environment environment;

	public void show() {
		System.out.println("ds.userName:" + this.userName);
		System.out.println("ds.password:" + this.environment.getProperty("ds.password"));
	}

	@Bean

	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new ServletTest(), "/servletTest");
	}

	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);

		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);

		return registrationBean;
	}

	@Bean
	public ServletListenerRegistrationBean<ListenerTest> servletListenerRegistrationBean() {
		return new ServletListenerRegistrationBean<ListenerTest>(new ListenerTest());
	}
	
	

	@Autowired
	private TimeInterceptor timeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}

}
