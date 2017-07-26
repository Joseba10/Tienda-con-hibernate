<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="usuario" scope="request"
 class="com.ipartek.TIPOS.Usuario" />
 
 	<%@ include file="includes/cabecera.jsp" %>
 	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 	
 	
	<form action="/admin/usuarioform" method="post">
		
<style>
 	
ul{

margin-left: 490px;
margin-top: 0px;

}

li{
background-color: #EFEFEF;
width:100px;
border:solid #EFEFEF;
-webkit-border-radius: 19px;
-moz-border-radius: 19px;
border-radius: 19px;
color:blue;
text-align:center;
display: inline-block;


}

li a:hover {
	color:red;
}

li a{
text-decoration: none;
}

fieldset{

border: none;}

form{


margin-left: 530px;
margin-top: 50px;}

.errores{

margin-top:20px;
width: 200px;
}
@media screen and (max-width: 1280px)and (orientation: landscape){


form{

margin-left: 250px;

font-size: 35px;
}

form label{

padding-top:80px;
margin-top: -40px;

}

form input {
	
	margin-top: 20px;
	font-size: 30px;
	
}

ul{
font-size: 30px;

margin-left: 250px;
}

ul li{

width: 200px;
}




</style>


		<fieldset>

			<label for="username">Nombre</label> 
			<input id="username" name="username"
			 required minlength="4" value="${usuario.username}"
			 
			<c:if test="${param.op=='modificar' or param.op == 'borrar'}">
			
			 
readonly="readonly"
			  </c:if>
>
		</fieldset>

		<fieldset>

			<label for="password">Contraseña</label>
			<input id="password" name="password" type="password" value="${usuario.password}">

		</fieldset>
		<fieldset>

			<label for="pass2">Contraseña Otra vez</label>
			<input id="pass2" name="pass2" type="password" value="${usuario.password}">

		</fieldset>
		
		<fieldset>

			<label for="id_roles">ID ROLES</label>
			<input id="id_roles" name="id_roles" type="text" value="${usuario.id_roles}">

		</fieldset>
		
			<fieldset>

			<label for="nombre_completo">Nombre Completo</label>
			<input id="nombre_completo" name="nombre_completo" type="text" value="${usuario.nombre_completo}">

		</fieldset>
		<fieldset>

			<input type="submit" value="${fn:toUpperCase(param.op) }">
			<p class="errores">${usuario.errores }</p>
			
			<input type="hidden" name="opform" value="${param.op }">

		</fieldset>
	</form>
	
	<c:if test="${param.op == 'borrar'}">
		<script>
			document.forms[0].onsubmit = confirmarBorrado;
		</script>
	</c:if>
	<%@ include file="includes/pie.jsp" %>



