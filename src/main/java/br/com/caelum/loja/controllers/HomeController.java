package br.com.caelum.loja.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.loja.daos.ProdutoDao;
import br.com.caelum.loja.daos.UsuarioDAO;
import br.com.caelum.loja.models.Produto;
import br.com.caelum.loja.models.Role;
import br.com.caelum.loja.models.Usuario;

@Controller
public class HomeController {
    
    @Autowired
    private ProdutoDao produtoDao;
    
    @Autowired
    private UsuarioDAO usuarioDao;

    @RequestMapping("/")
    @Cacheable(value = "produtosHome")
    public ModelAndView index() {
	List<Produto> produtos = produtoDao.listar();
	
	ModelAndView mv = new ModelAndView("home");
	mv.addObject("produtos", produtos);
	return mv;
    }
    
    @ResponseBody
    @Transactional(readOnly = false)
    @RequestMapping("/magic-crazy-url-hsfvhruiha5645645ofhabghjdsbhsyuf456456ssbssf")
    public String urlMagicCrazy() {
        Usuario usuario = new Usuario();
        usuario.setNome("Admin");
        usuario.setEmail("admin@casadocodigo.com.br");
        usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyuTRGh/Wbc89FUXxtSMwdAeykeq7mD2M6");
        usuario.setRoles(Arrays.asList(new Role("ADMIN")));
        
        this.usuarioDao.save(usuario);
        
        return "Magic URL executed!";
    }
}