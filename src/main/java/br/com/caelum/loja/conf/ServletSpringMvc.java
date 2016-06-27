package br.com.caelum.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMvc extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
	return new Class[]{AppWebConfiguration.class, JPAConfiguration.class, SpringSecurityConfiguration.class, JPAProductionConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
	return new Class[] {};
    }

    @Override
    protected String[] getServletMappings() {
	return  new String[] {"/"};
    }
    
    @Override
    protected Filter[] getServletFilters() {
	CharacterEncodingFilter encoding = new CharacterEncodingFilter();
	encoding.setEncoding("UTF-8");
	
        return new Filter[]{
                encoding,
                // new OpenEntityManagerInViewFilter() - Optional - Pattern OpenEntityManagerInView!
            };
    }
    
    @Override
    protected void customizeRegistration(Dynamic registration) {
	registration.setMultipartConfig(new MultipartConfigElement(""));
    }
    
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//	super.onStartup(servletContext);
//	servletContext.addListener(RequestContextListener.class); // Registra um context listener do Spring.
//	servletContext.setInitParameter("spring.profiles.active", "dev"); // Listener para buscar o profile do banco de produção.
//    }
}