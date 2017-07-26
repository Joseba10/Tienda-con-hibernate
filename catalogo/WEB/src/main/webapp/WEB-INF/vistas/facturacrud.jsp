<%@  include file="includes/cabecera.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>



h2{
text-align: center;
margin-top: 30px;
}
ul{

margin-left:500px;
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

margin-left: 450px;
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
text-decoration: none;

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

#alta a{
padding:15px;
border:solid #EFEFEF;
-webkit-border-radius: 19px;
-moz-border-radius: 19px;
border-radius: 19px;
margin-left:350px;
text-decoration: none;

}

@media screen and (max-width: 1280px)and (orientation: landscape){


table{

margin-left: 200px;

}

table td,th{

font-size: 22px;
}


ul{

margin-left: 200px;

font-size: 22px;




}

ul li{
margin-top: 10px;
width: 250px;
}

#alta a{

margin-left:100px;
font-size: 22px;

}

}


</style>

<h2>Mantenimiento de Facturas</h2>

<table border=1>

<thead>

<tr>

	<th>Operaciones<th>Numero Factura</th>
	<th>Id Usuario</th><th>Fecha</th>
	
	</tr>
	
	</thead>
	
	<tbody>
	
	<c:forEach items="${applicationScope.facturasArr}" var="factura">
	<tr>
		<td><a href="?op=modificar&factura=${factura.id}">Modificar</a>
		<a href="?op=borrar&factura=${factura.id}">Borrar</a>
		<td>${factura.numero_factura}</td>
		<td>${factura.id_usuarios}</td>
		<td>${factura.fecha}</td>
		
		
	</tr>
	</c:forEach>
			
	
	
	</tbody>



</table>

<div id="alta">

<a href="/admin/facturacrud?op=alta">Alta</a>

</div>


<%@  include file="includes/pie.jsp" %>