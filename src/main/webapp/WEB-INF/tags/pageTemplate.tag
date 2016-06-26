<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulo" required="true" %>
<%@ attribute name="bodyClass" required="false" %>
<%@ attribute name="extraScripts" fragment="true" %>
<!doctype html>

<html lang="pt-BR">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width">
		<link rel="shortcut icon" href="//cdn.shopify.com/s/files/1/0155/7645/t/187/assets/favicon.ico?13414332408319958314" type="image/ico"/>
		<link href="https://plus.google.com/108540024862647200608" rel="publisher">
		
		<title>${titulo} - Casa do Código</title>
		
		<meta itemprop="image" content="//cdn.shopify.com/s/files/1/0155/7645/t/187/assets/casa-do-codigo.svg?13414332408319958314">
	
	    <script type="text/javascript" src="//cdn.shopify.com/s/assets/themes_support/ga_urchin_forms-668547562549a84f5dfa01ef82607987f85ecbe1c8301faf25059becfa208199.js"></script>
	
	
		<!-- inject:css -->
		<link rel="stylesheet" href="//cdn.shopify.com/s/files/1/0155/7645/t/187/assets/style.css?13414332408319958314">
		<!-- endinject -->
	
		<link rel="canonical" href="http://www.casadocodigo.com.br/" />
	</head>
	<body class="${bodyClass}">
		<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
		<jsp:doBody />
		<%@ include file="/WEB-INF/views/rodape.jsp" %>
		
		<jsp:invoke fragment="extraScripts" />
	</body>
</html>