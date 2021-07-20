package config.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import config.SecurityConfig;
import config.WebConfig;

public class SpringMvcInitializer implements WebApplicationInitializer{
//	
// BETERE MANIER DAN ABSTRACT??? MET MEER CONTROLE
//
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return new Class[]{SecurityConfig.class, WebConfig.class};
//	}
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return null;
//	}
//	@Override
//	protected String[] getServletMappings() {return new String[]{"/"};}
	
	@Override
	public void onStartup(ServletContext container) throws ServletException{
		AnnotationConfigWebApplicationContext ctx =
	            new AnnotationConfigWebApplicationContext();
	    ctx.register(WebConfig.class); // configuration class for root context
	    ctx.register(SecurityConfig.class);

        ServletRegistration.Dynamic servlet = container.addServlet(
                "dispatcher", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
	    
//	    rootContext.scan("...service", "...dao"); // scan only some packages
//	    servletContext.addListener(new ContextLoaderListener(rootContext));

	    // dispatcher servlet 1
//	    AnnotationConfigWebApplicationContext webContext1 = 
//	            new AnnotationConfigWebApplicationContext();
//	    webContext1.setParent(rootContext);
//	    webContext1.register(SecurityConfig.class); // configuration class for servlet 1
////	    webContext1.scan("...web1");            // scan some other packages
//	    ServletRegistration.Dynamic dispatcher1 =
//	    		servletContext.addServlet("dispatcher1", new DispatcherServlet(webContext1));
//	    dispatcher1.setLoadOnStartup(1);
//	    dispatcher1.addMapping("/");

	}
}