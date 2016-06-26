<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Erro!">
		<section class="vitrineDestaquinho container">
			<h2>Ocorreu um erro!</h2>
			<br/>
			Mensagem: ${exception.message}
			<br/><br/>
			<c:forEach items="${exception.stackTrace}" var="trace">
				${trace}<br/>
			</c:forEach>
		</section>
</tags:pageTemplate>