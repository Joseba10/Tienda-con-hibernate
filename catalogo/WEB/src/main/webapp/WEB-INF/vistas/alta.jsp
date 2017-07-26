<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="usuario" scope="request"
 class="com.ipartek.TIPOS.Usuario" />
 	<%@ include file="includes/cabecera.jsp" %>
	<form action="alta" method="post">
		
<style>
 	
ul{

margin-left: 0px;
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
margin-left: 500px;
margin-top: 22px;

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

margin-left: 280px;

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

margin-left: -80px;
}

}



</style>


		<fieldset>

			<label for="username">Nombre</label> 
			<input id="username" name="username" required minlength="4" value="${usuario.username}" >

		</fieldset>

		<fieldset>

			<label for="password">Contraseña</label>
			<input id="password" name="password" type="password">

		</fieldset>
		<fieldset>

			<label for="pass2">Repetir Contraseña</label>
			<input id="pass2" name="pass2" type="password">

		</fieldset>
			<fieldset>

			<label for="nombre_completo">Nombre Completo</label>
			<input id="nombre_completo" name="nombre_completo" type="text">

		</fieldset>
		<fieldset>

			<input type="submit" value="Alta">
			<p class="errores">${usuario.errores}</p>

		</fieldset>
		
	
	</form>
	
	<%@ include file="includes/pie.jsp" %>



