package br.com.caelum.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.loja.daos.ProdutoDao;
import br.com.caelum.loja.models.CarrinhoCompras;
import br.com.caelum.loja.models.CarrinhoItem;
import br.com.caelum.loja.models.Produto;
import br.com.caelum.loja.models.TipoPreco;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/carrinho")
public class CarrinhoComprasController {

    @Autowired
    private ProdutoDao produtoDao;

    @Autowired
    private CarrinhoCompras carrinho;

    @RequestMapping("/add")
    public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
	ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
	CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
	carrinho.add(carrinhoItem);
	return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView itens() {
	ModelAndView mv = new ModelAndView("carrinho/itens");
	mv.addObject("carrinhoCompras", this.carrinho);
	return mv;
    }

    private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
	Produto produto = produtoDao.find(produtoId);
	CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
	return carrinhoItem;
    }

    @RequestMapping("/remover")
    public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco) {
	carrinho.remover(produtoId, tipoPreco);

	return new ModelAndView("redirect:/carrinho");
    }
}