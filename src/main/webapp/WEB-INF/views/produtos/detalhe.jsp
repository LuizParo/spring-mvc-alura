<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="${produto.titulo}">
	<a href="${s:mvcUrl("CCC#itens").build()}">Meu carrinho (${carrinhoCompras.quantidade})</a>
	<br/><br/>
	<div>
		<label>Título</label>
		<p>${produto.titulo}</p>
	</div>
	<br/><br/>
	<div>
		<label>Descrição</label>
		<p>${produto.descricao}</p>
	</div>
	<br/><br/>
	<div>
		<label>Páginas</label>
		<p>${produto.paginas}</p>
	</div>
	<br/><br/>
	<div>
		<label>Data de Lançamento</label>
		<p><fmt:formatDate value="${produto.dataLancamento.time}" pattern="MM/yyyy"/></p>
	</div>
	<br/><br/>
	<div>
		<label>Tipos de Livro</label>
		<form:form servletRelativeAction="/carrinho/add" method="post" cssClass="container">
			<input type="hidden" name="produtoId" value="${produto.id}">
			<c:forEach items="${produto.precos}" var="preco" varStatus="status">
				<br/>
				<label>${preco.tipoPreco}</label>
				<input type="radio" name="tipoPreco" value="${preco.tipoPreco}" ${status.index == 0 ? 'checked' : ''}>			
			</c:forEach>
			<br/><br/>
			<button type="submit">Compre Agora</button>
			<!--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />-->
		</form:form>
	</div>
</tags:pageTemplate>