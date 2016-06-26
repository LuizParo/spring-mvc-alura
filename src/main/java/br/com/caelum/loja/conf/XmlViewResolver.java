package br.com.caelum.loja.conf;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

public class XmlViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
	MappingJackson2XmlView xmlView = new MappingJackson2XmlView();
	xmlView.setPrettyPrint(true);
	
	return xmlView;
    }
}