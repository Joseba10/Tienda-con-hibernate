<%@  include file="includes/cabecera.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>



h2{
text-align: center;
margin-top: 30px;
}
ul{

margin-left: 500px;
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

margin-left: 430px;
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
margin-left:330px;
text-decoration: none;

}


@media screen and (max-width: 1280px)and (orientation: landscape){


ul{

margin-left: 230px;
margin-top: 0px;
font-size: 30px;

}

ul li{

width: 200px;

}

table{

margin-left: 250px;


}

#alta a{


margin-left:150px;


}

}




</style>

<h2>Mantenimiento de usuarios</h2>

<table border=1>

<thead>

<tr>

	<th>Operaciones<th>Usuario</th>
	<th>Contraseña</th><th>Roles</th><th>Nombre Completo</th>
	
	</tr>
	
	</thead>
	
	<tbody>
	
	<c:forEach items="${requestScope.usuarios}" var="usuario">
	<tr>
		<td><a href="?op=modificar&username=${usuario.username}">Modificar</a>
		<a href="?op=borrar&username=${usuario.username}">Borrar</a>
		<td>${usuario.username}</td>
		<td>${usuario.password}</td>
		<td>${usuario.id_roles}</td>
		<td>${usuario.nombre_completo}</td>
		
	</tr>
	</c:forEach>
			
	
	
	</tbody>



</table>

<div id="alta">

<a href="usuariocrud?op=alta">Alta</a>

</div>


<%@  include file="includes/pie.jsp" %>