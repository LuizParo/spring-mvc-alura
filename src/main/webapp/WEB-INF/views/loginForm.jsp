<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<c:url value="/resources/css" var="cssPath" />
		<c:url value="/resources/js" var="jsPath" />
		
		<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		
		<!-- Optional theme -->
		<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="${jsPath}/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Livros de java, Android, Iphone, PHP, Ruby e muito mais - Casa do código</title>
	</head>
	<body>
		<div class="container">
			<h1>Login da Casa do Código</h1>
			
			<form:form servletRelativeAction="/login" method="POST" >
		        <div class="form-group">
		            <label>E-mail</label>
		            <input type="text" name="username" class="form-control"/>
		        </div>
		        <div class="form-group">
		            <label>Senha</label>
		            <input type="password" name="password" class="form-control"/>
		        </div>
		
		        <button type="submit" class="btn btn-primary">
		            Logar
		        </button>
	   		</form:form>
   		</div>
	</body>
</html>