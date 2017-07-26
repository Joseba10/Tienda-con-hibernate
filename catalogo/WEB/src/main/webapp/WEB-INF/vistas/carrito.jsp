<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap.min.css">
<title>Zona de Compra</title>

<style>

h1{
text-align: center;
}

p{
text-align:center;
font-weight: bold;
}

.form-group form{width: 500px;
margin-left: 340px;
}


@media screen and (max-width: 1280px)and (orientation: landscape){

.container{


margin-left: -300px;

}

.container h1{



margin-left: 270px;}

}


</style>



</head>
<body>

<div class="container">
<div class=".row">

<div class="form-group .col-xs-6 .col-md-4  ">

<h1>Zona de Compra</h1>
<form action="/Zonadecompra" method="post">





		<!---manzanas --><!---tomates --><!-- -patatas --><!---mandarinas --><!---naranjas -->
		<!---peras --><!---vinos -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<p>Productos que quieres comprar y la cantidad</p>
			<c:forEach items="${arrayproductos}" var="producto">
				${producto.nombre}
				<input class="form-control"name="${producto.id }" type="number" max="${producto.cantidad}" value="" placeholder="Cantidad" pattern="[0-9]+"><br>
			</c:forEach>	
			

			<input class="btn btn-default " type="submit" value="Enviar">
			
			<c:if test="${param.op==null or param.op=='' }">
		
			</c:if>
			
			
			<p class="errores">${producto.errores }</p>
			
			<input type="hidden" name="op" value="completado">

	</form>
		</div>
		</div>
	</div>
	<c:if test="${param.op == 'borrar'}">
		<script>
			document.forms[0].onsubmit = confirmarBorrado;
		</script>
	</c:if>
	<%@ include file="includes/productopie.jsp" %>



 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
 
    <script src="js/bootstrap.min.js"></script>
</body>
</html>