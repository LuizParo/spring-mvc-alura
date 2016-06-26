package br.com.caelum.loja.daos;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.loja.builder.ProdutoBuilder;
import br.com.caelum.loja.conf.DataSourceConfigurationTest;
import br.com.caelum.loja.conf.JPAConfiguration;
import br.com.caelum.loja.models.Produto;
import br.com.caelum.loja.models.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfiguration.class, DataSourceConfigurationTest.class, ProdutoDao.class})
@ActiveProfiles("test") // Identifica que esse teste deve usar um banco de teste.
public class ProdutoDaoTest {
    
    @Autowired
    private ProdutoDao produtoDao;

    @Test
    @Transactional
    public void deveSomarTodosPrecosPorTipoLivro() {
	List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESO, BigDecimal.TEN)
		.mais(3)
		.buildAll();
	
	List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN)
		.mais(3)
		.buildAll();
	
	livrosImpressos.stream().forEach(this.produtoDao::gravar);
	livrosEbook.stream().forEach(this.produtoDao::gravar);
	
	BigDecimal valor = this.produtoDao.somaPrecosPorTipo(TipoPreco.EBOOK);
	assertEquals(new BigDecimal(40).setScale(2), valor);
    }
}