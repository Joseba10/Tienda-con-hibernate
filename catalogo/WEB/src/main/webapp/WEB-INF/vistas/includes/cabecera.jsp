<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
	
	<style type="text/css">
	
	h1,p{
	text-align: center;
}
	
	
	</style>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/${pageContext.request.contextPath}css/estilos.css">
	<title>Mantenimiento de Usuarios</title>

</head>

<body>

	<header>
		
		
	
	</header>
	
	
	<!-- Donde no van a salir los Botones del Menu -->
	
	
	<nav>
		<ul>
			<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
					
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/index.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/facturaform.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/login.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/alta.jsp'}">
					
						<li><a href="/loginserver?opcion=logout">Logout</a></li>
				
					
				</c:if>	</c:if></c:if></c:if>
				
				<!--Mantenimiento de Productos--->	
			   
			   <c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/facturaform.jsp'}">
				<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/facturacrud.jsp'}">
							<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productocrud.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/usuarioform.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/login.jsp'}">
				<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/index.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productocrudusuario.jsp'}">
				<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/alta.jsp'}">
				<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/factura.jsp'}">
						<li><a href="/admin/productocrud">Mantenimiento Productos</a></li>
			
				</c:if></c:if></c:if></c:if></c:if>	</c:if></c:if></c:if></c:if>
	
	<!--Alta Usuarios--->		
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/facturaform.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/facturacrud.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/usuariocrud.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/usuarioform.jsp'}">	
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productoform.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productocrudusuario.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/alta.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productocrud.jsp'}">
		<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/index.jsp'}">
		<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/factura.jsp'}">
				<li><a href="/alta">Alta</a></li>
				
				</c:if></c:if>	</c:if>	</c:if>	</c:if></c:if></c:if></c:if></c:if></c:if>
				
				<!--Alta Productos--->		
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/facturaform.jsp'}">	
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/facturacrud.jsp'}">	
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/usuariocrud.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/usuarioform.jsp'}">	
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productoform.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productocrudusuario.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/alta.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/login.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/index.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/factura.jsp'}">
			
			
			<li><a href="/admin/productocrud?op=alta">Alta Productos</a></li>
			
			
			
				</c:if></c:if>	</c:if>	</c:if>	</c:if></c:if></c:if></c:if></c:if></c:if>
				<!--Mantenimiento de Usuarios--->	
			
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/facturaform.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/facturacrud.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/usuariocrud.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productoform.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/login.jsp'}">	
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productocrudusuario.jsp'}">	
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/alta.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/index.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/factura.jsp'}">
			
					<li><a href="/admin/usuariocrud">Mantenimiento Usuarios</a></li>
								</c:if></c:if></c:if></c:if></c:if></c:if></c:if></c:if></c:if>
								
								
								<!--Login--->	
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/facturaform.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/facturacrud.jsp'}">	
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productocrud.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/usuariocrud.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/usuarioform.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productoform.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productocrudusuario.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/login.jsp'}">
					<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/factura.jsp'}">	
						
						<li><a href="/loginserver">Login</a></li>
		</c:if>	</c:if></c:if></c:if></c:if></c:if></c:if></c:if></c:if>
		
		
		
			<!--Mantenimiento de Facturas--->	
				
		
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/login.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/usuarioform.jsp'}">	
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productoform.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productocrudusuario.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/alta.jsp'}">
			<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/productocrud.jsp'}">
		<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/index.jsp'}">
		<c:if test="${pageContext.request.requestURI != '/WEB-INF/vistas/factura.jsp'}">
		
			
					<li><a href="/admin/facturacrud">Mantenimiento Facturas</a></li>
								</c:if>	</c:if>	</c:if></c:if></c:if></c:if></c:if></c:if>
		
		
		
		
					
						
		</ul>
	</nav>
