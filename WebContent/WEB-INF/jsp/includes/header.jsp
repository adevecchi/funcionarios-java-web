<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" crossorigin>
	<link rel="stylesheet" href="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.css" crossorigin>
	<link rel="stylesheet" href="assets/css/styles.css">
	<title>Gerenciameto de Funcionários</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<span class="navbar-brand mb-0 h1">adevecchi</span>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
		  	<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		    	<div class="navbar-nav">
		      		<a id="menu-home" class="nav-link" href="<%= request.getContextPath() %>/">Home <span class="sr-only">(current)</span></a>
		      		<a id="menu-funcionarios" class="nav-link" href="<%= request.getContextPath() %>/funcionarios">Funcionários</a>
		    	</div>
		  	</div>
		</nav>
	</header>