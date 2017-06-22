package org.hrd.kimly.configuration;

import java.util.Locale;

import org.codehaus.groovy.tools.shell.util.MessageSource;
import org.springframework.boot.autoconfigure.web.WebMvcProperties.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/static/");
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		//front end
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/home").setViewName("/home");
		registry.addViewController("/user").setViewName("/user");
		registry.addViewController("/contact").setViewName("/contact");
		registry.addViewController("/about").setViewName("/about");
		
		registry.addViewController("/user/user-list").setViewName("/user/user-list");
		registry.addViewController("/user/user-cu").setViewName("/user/user-cu");
		registry.addViewController("/user/role-list").setViewName("/user/role-list");
		registry.addViewController("/user/role-cu").setViewName("/user/role-cu");
		registry.addViewController("/user/dashboard").setViewName("/user/dashboard");
		
		//back end
		
		registry.addViewController("/admin/").setViewName("/user/dashboard");
		registry.addViewController("/admin/dashboard").setViewName("/user/dashboard");

		}
	
	/*
	 * Internalization i18n
	 */
	
	/*
	 * In order for our application to be able to determine which locale is
	 * currently being used, we need to add a LocaleResolver bean:
	 */
	@Bean
	public org.springframework.web.servlet.LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("kh"));
		resolver.setCookieName("Spring_Locale");
		resolver.setCookieMaxAge(4800);
		return resolver;
	}

	/*
	 * Next, we need to add an interceptor bean that will switch to a new locale
	 * based on the value of the lang parameter appended to a request:
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/*
	 * In order to take effect, this bean needs to be added to the application’s
	 * interceptor registry.
	 *
	 * To achieve this, our @Configuration class has to extend the
	 * WebMvcConfigurerAdapter class and override the addInterceptors() method:
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	/*
	 * Defining the Message Sources
	 */
	@Bean
	public org.springframework.context.MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/i18n/messages/message");
		messageSource.setCacheSeconds(0);
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
