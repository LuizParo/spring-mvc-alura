package br.com.caelum.loja.controllers;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.caelum.loja.conf.AppWebConfiguration;
import br.com.caelum.loja.conf.DataSourceConfigurationTest;
import br.com.caelum.loja.conf.JPAConfiguration;
import br.com.caelum.loja.conf.SpringSecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // Para que o Spring possa rodar o contexto do Spring completo, inclusive acessando as páginas do navegador.
@ContextConfiguration(classes={JPAConfiguration.class, DataSourceConfigurationTest.class, AppWebConfiguration.class, SpringSecurityConfiguration.class})
@ActiveProfiles("test") // Identifica que esse teste deve usar um banco de teste.
public class HomeControllerTest {
    
    @Autowired
    private WebApplicationContext wac;
    
    @Autowired
    private Filter springSecurityFilterChain;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(this.springSecurityFilterChain).build();
    }
    
    @Test
    public void deveRetornarParaHomeComOsLivros() throws Exception {
	this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.model()
			.attributeExists("produtos")) // Verifica se o ModelAndView retornado recebe o parâmetro produtos.
		.andExpect(MockMvcResultMatchers
			.forwardedUrl("/WEB-INF/views/home.jsp")); // Verifica se a URL experada é chamada.
    }
    
    @Test
    public void somenteAdminDeveAcessarProdutosForm() throws Exception {
	this.mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form")
		.with(SecurityMockMvcRequestPostProcessors
			.user("user@casadocodigo.com.br")
			.password("123456")
			.roles("USUARIO"))) // Tenta acessar com esse usuário
		.andExpect(MockMvcResultMatchers.status().is(403));
    }
}