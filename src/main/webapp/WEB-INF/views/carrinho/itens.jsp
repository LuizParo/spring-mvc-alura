<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Seu carriho de compras">
	<jsp:attribute name="extraScripts">
		<script>
			// Aqui vem o script dessa página
		</script>
	</jsp:attribute>

	<jsp:body>
		<form role="search" aria-labelledby="rotuloBuscaPrincipal"
			action="/search" method="GET" class="buscaPrincipal">
			<label id="rotuloBuscaPrincipal" class="buscaPrincipal-label"
				for="campoBuscaPrincipal"> Busque por autor, título,
				conteúdo... </label> <input type="hidden" name="type" value="product">
			<input id="campoBuscaPrincipal" class="buscaPrincipal-campo"
				placeholder="O que você procura?" type="search" name="q" required
				aria-required>
			<!-- 
					 -->
			<button class="buscaPrincipal-submit" type="submit">Buscar</button>
		</form>
		</div>
	
		<a tabindex="2"
			class="cabecalhoPrincipal-itemNavegacao cabecalhoPrincipal-mostraCategoriasEBusca"
			href="#navegacaoCabecalho"> <img
			class="cabecalhoPrincipal-iconeItemNavegacao"
			src="//cdn.shopify.com/s/files/1/0155/7645/t/187/assets/search.svg?13414332408319958314"
			alt="Abrir navegação por categorias e busca">
		</a>
	
		<a tabindex="-1"
			class="cabecalhoPrincipal-itemNavegacao cabecalhoPrincipal-escondeCategoriasEBusca"
			href="#"> <img class="cabecalhoPrincipal-iconeItemNavegacao"
			src="//cdn.shopify.com/s/files/1/0155/7645/t/187/assets/search.svg?13414332408319958314"
			alt="Ir para ao topo da página">
		</a>
	
		<a tabindex="3" href="/cart" title="Ir para sacola de compras"
			class="sacola cabecalhoPrincipal-itemNavegacao"> <svg width="28px"
				height="34px" viewBox="0 0 28 34" role="img"
				aria-labelledby="sacolaLabel" class="sacola-icone">
						<title id="sacolaLabel">
							Você tem ${carrinhoCompras.quantidade} itens na sacola
						</title>
						<path fill="#f8965d"
					d="M1.66666667,26.9714355 L18.3333333,26.9714355 C19.2533333,26.9714355 20,26.2247689 20,25.3047689 L20,6.97143555 C20,6.05143555 19.2533333,5.30476888 18.3333333,5.30476888 L14.9916667,5.30476888 C14.9516667,2.53643555 12.735,0.30476888 10,0.30476888 C7.265,0.30476888 5.04833333,2.53643555 5.00833333,5.30476888 L1.66666667,5.30476888 C0.746666667,5.30476888 0,6.05143555 0,6.97143555 L0,25.3047689 C0,26.2247689 0.746666667,26.9714355 1.66666667,26.9714355 L1.66666667,26.9714355 Z M12.5,10.3047689 L7.5,10.3047689 C7.04,10.3047689 6.66666667,9.93143555 6.66666667,9.47143555 C6.66666667,9.01143555 7.04,8.63810221 7.5,8.63810221 L12.5,8.63810221 C12.96,8.63810221 13.3333333,9.01143555 13.3333333,9.47143555 C13.3333333,9.93143555 12.96,10.3047689 12.5,10.3047689 L12.5,10.3047689 Z M10,1.97143555 C11.8116667,1.97143555 13.285,3.45976888 13.325,5.30476888 L6.675,5.30476888 C6.715,3.45976888 8.18833333,1.97143555 10,1.97143555 L10,1.97143555 Z"></path>
						<g>
							<circle cx="19.5" cy="24.8047689" r="8.5" role="presentation"
					fill="#FFF" />
							<text text-decoration="none" text-anchor="middle" x="19.5" y="30"
					class="sacola-contador">${carrinhoCompras.quantidade}</text>
						</g>
					 </svg>
		</a>
		</div>
		</header>
	
	
		<main>
		<section class="infoSection container">
			<h2 class="infoSection-titulo">
				<a href="${s:mvcUrl('CCC#itens').build()}">Seu carrinho
					(${carrinhoCompras.quantidade})</a>
			</h2>
	
			<table class="formularioDoCarrinho-tabela">
				<thead class="formularioDoCarrinho-cabecalho">
					<tr>
						<th></th>
						<th class="formularioDoCarrinho-cabecalho-item">Item</th>
						<th
							class="formularioDoCarrinho-cabecalho-item formularioDoCarrinho-cabecalho-preco">Preço</th>
						<th class="formularioDoCarrinho-cabecalho-item">Qtd</th>
						<th class="formularioDoCarrinho-cabecalho-item">Total</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${carrinhoCompras.itens}" var="item">
						<tr>
							<td class="formularioDoCarrinho-item"><a
								href="/products/livro-javaee"> <img
									class="formularioDoCarrinho-item-imagem"
									src="//cdn.shopify.com/s/files/1/0155/7645/products/G8Odq4Q1TChRM5NMkpWOfJLrIdpMhXpIMt0Qs0vOAJQ_size_mode_2_size_1024x768_small.jpeg?v=1447444684" />
							</a></td>
							<td class="formularioDoCarrinho-item">
								<h2 class="formularioDoCarrinho-item-titulo">${item.produto.titulo}</h2>
							</td>
							<td
								class="formularioDoCarrinho-item formularioDoCarrinho-item-preco">${item.preco}</td>
							<td class="formularioDoCarrinho-item"><input
								class="formularioDoCarrinho-item-quantidade" type="number"
								min="0" id="quantidade" name="quantidade"
								value="${carrinhoCompras.getQuantidade(item)}"></td>
							<td
								class="formularioDoCarrinho-item formularioDoCarrinho-item-precoTotal"
								title="Preço unitário: R$29,90">${carrinhoCompras.getTotal(item)}</td>
							<td class="formularioDoCarrinho-item">
								<form:form
									action="${s:mvcUrl('CCC#remover').arg(0, item.produto.id).arg(1, item.tipoPreco).build()}"
									method="post">
									<input type="image"
										class="formularioDoCarrinho-item-remover-imagem"
										src="http:////cdn.shopify.com/s/files/1/0155/7645/t/187/assets/trash.png?13414332408319958314"
										alt="X" title="Remover">
								</form:form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot class="formularioDoCarrinho-rodape">
					<tr>
						<form:form action="${s:mvcUrl('PC#finalizar').build()}" method="post">
							<td
								class="formularioDoCarrinho-rodape-item formularioDoCarrinho-finalizar"
								colspan="3">
								<button class="formularioDoCarrinho-finalizar-botao"
									type="submit" name="checkout">
									Finalizar<span
										class="formularioDoCarrinho-finalizar-botao-texto"
										role="presentation"> compra</span>
								</button>
							</td>
						</form:form>
						<td class="formularioDoCarrinho-rodape-item">
							${carrinhoCompras.total}</td>
						<td></td>
					</tr>
				</tfoot>
			</table>
			<h2><a href="${s:mvcUrl('HC#index').build()}">Voltar para homepage</a></h2>
		</section>
		<div class="buscaDoRodape container" role="presentation">
			<form role="search" aria-labelledby="rotuloBuscaDoRodape"
				action="/search" method="GET" class="buscaDoRodape-formulario">
				<label id="rotuloBuscaRodape" class="buscaDoRodape-rotuloEscondido"
					for="campoBuscaRodape">Busque por autor, título, conteúdo...</label>
				<label class="buscaDoRodape-rotulo" for="campoBuscaRodape">Não
					encontrou o seu livro?</label>
				<fieldset class="buscaDoRodape-fieldset">
					<input type="hidden" name="type" value="product"> <input
						id="campoBuscaRodape" class="buscaDoRodape-campo"
						placeholder="O que você procura?" type="search" name="q" required
						aria-required>
					<button class="buscaDoRodape-enviar" type="submit">Buscar</button>
				</fieldset>
			</form>
		</div>
		</main>
	</jsp:body>
	
</tags:pageTemplate>