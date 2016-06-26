package br.com.caelum.loja.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.loja.models.Preco;
import br.com.caelum.loja.models.Produto;
import br.com.caelum.loja.models.TipoPreco;

public class ProdutoBuilder {
    private List<Produto> produtos = new ArrayList<>();
    
    private ProdutoBuilder(Produto produto) {
	this.produtos.add(produto);
    }
    
    public static ProdutoBuilder newProduto(TipoPreco tipoPreco, BigDecimal valor) {
	Produto livro = create("Book 1", tipoPreco, valor);
	return new ProdutoBuilder(livro);
    }
    
    public static ProdutoBuilder newProduto() {
	Produto livro = create("Book 1", TipoPreco.COMBO, BigDecimal.TEN);
	return new ProdutoBuilder(livro);
    }
    
    private static Produto create(String titulo, TipoPreco tipoPreco, BigDecimal valor) {
	Produto livro = new Produto();
	livro.setTitulo(titulo);
	livro.setDataLancamento(Calendar.getInstance());
	livro.setPaginas(150);
	livro.setDescricao("Great book about testing");
	
	Preco preco = new Preco();
	preco.setTipoPreco(tipoPreco);
	preco.setValor(valor);
	livro.getPrecos().add(preco);
	
	return livro;
    }
    
    public ProdutoBuilder mais(int quantidade) {
	Produto base = this.produtos.get(0);
	Preco preco = base.getPrecos().get(0);
	for(int i = 0; i < quantidade; i++) {
	    produtos.add(create("Book " + i, preco.getTipoPreco(), preco.getValor()));
	}
	return this;
    }
    
    public Produto buildOne() {
	return this.produtos.get(0);
    }
    
    public List<Produto> buildAll() {
	return this.produtos;
    }
}