package br.com.caelum.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.loja.daos.ProdutoDao;
import br.com.caelum.loja.infra.FileSaver;
import br.com.caelum.loja.models.Produto;
import br.com.caelum.loja.models.TipoPreco;
import br.com.caelum.loja.validators.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoDao dao;
    
    @Autowired
    private FileSaver fileSaver;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
	binder.addValidators(new ProdutoValidation());
    }
    
    @RequestMapping("/form")
    public ModelAndView form(Produto produto) {
	ModelAndView mv = new ModelAndView("produtos/form");
	mv.addObject("tipos", TipoPreco.values());
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @CacheEvict(value = "produtosHome", allEntries = true) // Limpa o cache do home, recarregando a lista inteira de produtos
    public ModelAndView gravar(MultipartFile sumario, Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
	if(result.hasErrors()) return this.form(produto);
	
	String path = this.fileSaver.gravar("arquivos_sumario", sumario);
	produto.setSumarioPath(path);
	
	this.dao.gravar(produto);
	redirectAttributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!");
	
        return new ModelAndView("redirect:produtos");
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar() {
	List<Produto> produtos = dao.listar();
	
	ModelAndView mv = new ModelAndView("produtos/lista");
	mv.addObject("produtos", produtos);
	return mv;
    }
    
    @RequestMapping("/detalhe/{id}")
    public ModelAndView detalhe(@PathVariable Integer id) {
	ModelAndView mv = new ModelAndView("produtos/detalhe");
	mv.addObject("produto", this.dao.find(id));

	return mv;
    }
    
    @RequestMapping("/{id}")
    @ResponseBody // Faz com que o retorno seja o corpo da resposta
    public Produto detalheJson(@PathVariable Integer id) {
	return this.dao.find(id);
    }
}