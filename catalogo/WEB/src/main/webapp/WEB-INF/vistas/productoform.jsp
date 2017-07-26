<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="includes/cabecera.jsp" %>
 	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 	
 	
<jsp:useBean id="producto" scope="request"
 class="com.ipartek.TIPOS.Producto" />
 
 	
	
		
<style>
 	
ul{

margin-left: 530px;
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


margin-left: 560px;
margin-top: 50px;}

.errores{

margin-top:20px;
width: 200px;
}

input:FOCUS{

padding:10px;

}


input:hover{

font-weight: bold;
}


@media screen and (max-width: 1280px)and (orientation: landscape){


form{

margin-left: 230px;

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

margin-left: 130px;
}

ul li{

width:300px;}



</style>

<form action="/admin/productoform" method="post">

<ul>
 	
 		
 	</ul>
		<fieldset>

			<label for="id">Id</label> 
			<input id="id" name="id" required="required" value="${producto.id}"
			 
			<c:if test="${param.op=='modificar' or param.op == 'borrar'}">
			
			 
readonly="readonly"
			  </c:if>
>
		</fieldset>
<fieldset>

			<label for="nombre">Nombre</label>
			<input id="nombre" name="nombre" type="text" value="${producto.nombre}">

		</fieldset>
			<fieldset>

			<label for="precio">Precio</label>
			<input id="precio" name="precio" value="${producto.precio}">

		</fieldset>
		<fieldset>

			<label for="descripcion">Descripcion</label>
			<input id="descripcion" name="descripcion" type="text" value="${producto.descripcion}">

		</fieldset>
	
		<fieldset>

			<label for="cantidad">Cantidad</label>
			<input id="cantidad" name="cantidad" type="text" value="${producto.cantidad}">

		</fieldset>
		
			<fieldset>

			<label for="imagen">Imagen</label>
			
			<select name="imagen">
			
			<option value="0">Manzana</option> 
			<option value="1">Tomate</option> 
			<option value="2">Patata</option> 
			<option value="3">Mandarina</option> 
			<option value="4">Naranja</option> 
			<option value="5">Pera</option> 
			<option value="6">Vino</option> 
			
			</select>

		</fieldset>
		<fieldset>

			<input type="submit" value="${fn:toUpperCase(param.op) }"
			
			<c:if test="${param.op==null or param.op=='' }">
			style="display:none"
			</c:if>
			
			>
			<p class="errores">${producto.errores }</p>
			
			<input type="hidden" name="opform" value="${param.op }">

		</fieldset>
	</form>
	
	<c:if test="${param.op == 'borrar'}">
		<script>
			document.forms[0].onsubmit = confirmarBorrado;
		</script>
	</c:if>
	<%@ include file="includes/productopie.jsp" %>



