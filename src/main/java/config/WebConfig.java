package config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import domain.services.PostalCodeService;
import domain.services.PostalCodeServiceImpl;

@EnableWebMvc
@Configuration
@ComponentScan("controllers")
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	public PostalCodeService postalCodeService() {
		return new PostalCodeServiceImpl();
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