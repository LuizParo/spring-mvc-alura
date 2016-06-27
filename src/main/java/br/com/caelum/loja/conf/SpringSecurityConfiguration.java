package br.com.caelum.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.caelum.loja.daos.UsuarioDAO;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UsuarioDAO usuarioDao;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
	// Primeiro configura os bloqueios, depois as liberações. 
	http.authorizeRequests()
		.antMatchers("/produtos/form").hasRole("ADMIN") // Bloqueio do formulário de produtos.
		.antMatchers("/carrinho/**").permitAll() // Liberação do carrinho de compras e URLs posteriores.
		.antMatchers("/pagamento/**").permitAll()
		.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN") // Bloqueio da listagem de produtos para requisições POST.
		.antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN") // Bloqueio da listagem de produtos para requisições GET.
		//.antMatchers(HttpMethod.GET, "/produtos").permitAll() // Liberação da listagem de produtos para requisições GET.
		.antMatchers("/produtos/**").permitAll() // Liberação de todas as páginas que comecem com a URL produtos/..., com excessão de "/produtos/form".
		.antMatchers("/resources/**").permitAll() // Liberação de todos os requests dos recursos (CSS, Js, etc), para que nenhum seja barrado pelo Spring.
		.antMatchers("/magic-crazy-url-hsfvhruiha5645645ofhabghjdsbhsyuf456456ssbssf").permitAll() // Liberação da inserção de usuário pelo home (Heroku).
		.antMatchers("/").permitAll() // Liberação da Home.
		.anyRequest().authenticated() // Qualquer request deve ser autenticado.
		.and().formLogin().loginPage("/login").permitAll() // Se não estiver autenticado, mande pro formulário de login.
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(this.usuarioDao)
		.passwordEncoder(new BCryptPasswordEncoder());
    }
}