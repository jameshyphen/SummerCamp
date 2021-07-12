package config.core;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import config.WebConfig;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{WebConfig.class};
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
	@Override
protected String[] getServletMappings() {return new String[]{"/"};}
}