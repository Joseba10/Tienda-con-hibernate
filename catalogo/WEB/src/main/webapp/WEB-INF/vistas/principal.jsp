<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 	<%@ include file="includes/cabecera.jsp" %>
 	
<h1>Principal</h1>
<jsp:useBean id="usuario" scope="session"
 class="com.ipartek.TIPOS.Usuario" />
<h2>Bienvenido ${usuario.username} <a href="loginserver?opcion=logout">Logout</a></h2>
<h3><%=new java.util.Date() %></h3>


	<%@ include file="includes/pie.jsp" %>