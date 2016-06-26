<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Livros de Java, Android, iOS, Mobile, e muito mais!">
	<main>
	<section class="vitrineDestaquinho container">
		<h2 class="vitrineDestaquinho-titulo">Últimos lançamentos</h2>
		<ul class="vitrineDestaquinho-lista">
			<c:forEach items="${produtos}" var="produto">
				<li class="livroNaVitrine vitrineDestaquinho-produto"><a
					href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}"
					class="livroNaVitrine-link">
						<div class="livroNaVitrine-imagemContainer" role="presentation">
							<img class="livroNaVitrine-imagem"
								src="//cdn.shopify.com/s/files/1/0155/7645/products/isvvZtPdj_hi28pBwwPJRuQz5bvR9spqTKpO22oGQQ4_size_mode_3_size_800x600_large.jpeg?v=1449257973"
								alt="Livro de Octave" title="Livro de Octave">
						</div> <span class="livroNaVitrine-nome"> ${produto.titulo} </span>
				</a></li>
			</c:forEach>
		</ul>
	</section>
	
	
	<div class="buscaDoRodape container" role="presentation">
		<form role="search" aria-labelledby="rotuloBuscaDoRodape"
			action="/search" method="GET" class="buscaDoRodape-formulario">
			<label id="rotuloBuscaRodape" class="buscaDoRodape-rotuloEscondido"
				for="campoBuscaRodape">Busque por autor, tÃ­tulo,
				conteÃºdo...</label> <label class="buscaDoRodape-rotulo"
				for="campoBuscaRodape">NÃ£o encontrou o seu livro?</label>
			<fieldset class="buscaDoRodape-fieldset">
				<input type="hidden" name="type" value="product"> <input
					id="campoBuscaRodape" class="buscaDoRodape-campo"
					placeholder="O que vocÃª procura?" type="search" name="q" required
					aria-required>
				<button class="buscaDoRodape-enviar" type="submit">Buscar</button>
			</fieldset>
		</form>
	</div>
	</main>
	
	<script type="text/javascript"
		src="//cdn.shopify.com/s/files/1/0155/7645/t/187/assets/trackExternalLinks.js?13414332408319958314"></script>
</tags:pageTemplate>