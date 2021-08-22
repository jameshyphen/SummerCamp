package config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import domain.services.AuthorizationService;
import domain.services.AuthorizationServiceImpl;
import domain.services.CampService;
import domain.services.PostalCodeService;
import domain.services.PostalCodeServiceImpl;
import domain.services.SummerCampServiceImpl;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages= {"controllers", "config"})
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public PostalCodeService postalCodeService() {
		return new PostalCodeServiceImpl();
	}
	
	@Bean
	public AuthorizationService authorizationService() {
		return new AuthorizationServiceImpl();
	}
	
	@Bean
	public CampService campService() {
		return new SummerCampServiceImpl();
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		System.out.println("Config applied.");
		return resolver;
	}
}