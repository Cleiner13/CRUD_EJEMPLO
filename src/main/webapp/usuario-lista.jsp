<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CRUD</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: black">
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">USUARIOS</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">


		<div class="container">
			<h3 class="text-center">LISTAR USUARIOS</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New User</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>NOMBRE</th>
						<th>EMAIL</th>
						<th>ACCION</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="user" items="${listarUsuario}">

						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.nombre}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><a href="editar?id=<c:out value='${user.id}' />">EDITAR</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="eliminar?id=<c:out value='${user.id}' />">ELIMINAR</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>