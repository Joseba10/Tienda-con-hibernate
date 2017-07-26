<%@  include file="includes/cabecera.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>



h2{
text-align: center;
margin-top: 30px;
}
ul{

margin-left: 380px;
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

table{

margin-left: 320px;
margin-top: 30px;
border: solid;
background-color: green;

border-color: green;
}
table tr{
background-color: white;
}
table tr td a{

color:blue;
font-weight: bold;


}
table tr td a:hover{

color:red;
background-color: white;

}


table tr th{

height:25px;
background-color: red;

}

table tr td{

text-align: center;}

a{

text-decoration: none;}

@media screen and (max-width: 1280px)and (orientation: landscape){


table{

margin-left: 130px;



}

#carrito{

margin-left:150px;

}

ul{

margin-left: 270px;

}

</style>
<h2>Mantenimiento de Productos</h2>

<table border=1 width="700" height=150px;>

<thead>

<tr>

	<th>Operaciones</th>
	<th>ID</th>
	<th>Producto</th>
	<th>Descripcion</th>
	<th>Precio</th>
	<th>Cantidad</th>
	<th>Imagen</th>
	
	</tr>
	
	</thead>
	
	<tbody>
	
	<c:forEach items="${applicationScope.listaproductos}" var="producto">
	<tr>
		<td><a href="/admin/productocrud?op=modificar&id=${producto.id}">Modificar</a>
		<a href="/admin/productocrud?op=borrar&id=${producto.id}">Borrar</a></td>
		<td>${producto.id}</td>
		<td>${producto.nombre}</td>
		<td>${producto.descripcion}</td>
		<td>${producto.precio} €</td>
		<td>${producto.cantidad}</td>
		
		
		<td><img src="/${pageContext.request.contextPath}${producto.imagen}.jpg" width="80px"></td>
	
	</tr>
	</c:forEach>
			
	
	
	</tbody>



</table>




<%@  include file="includes/productopie.jsp" %>