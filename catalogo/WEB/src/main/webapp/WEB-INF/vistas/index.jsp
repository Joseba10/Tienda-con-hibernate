<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="includes/cabecera.jsp" %>
<title>Pagina Principal</title>
</head>

<style>

nav {

margin-left: 970px;
font-size:21px;
margin-top: 280px;


width: 100px;

}

nav ul li a{

text-transform:uppercase;
text-decoration: none;
text-align: center;



}



nav ul {

text-decoration: none;
list-style: none;


}





h3{

text-align: center;

margin-top: 80px;
}


h1{

margin-top: -320px;

}

img{

margin-top: 0px;
margin-left: 380px;
}


#flecha{

margin-left: 720px;
margin-top: -320px;

}


#texto{

margin-top:-100px;
width: 300px;

}




@media screen and (max-width: 1280px)and (orientation: landscape){

#productos img{

width: 400px;
margin-left: 280px;
padding-bottom: 50px;
}

#texto{


margin-left: -10px;

}

#flecha{

margin-left: 340px;
margin-top:-325px;

}

nav ul li a{

margin-left: -220px;





}
</style>




<body>
<h1>La tienda en Casa</h1>


<h3>Haga aqui click en login para entrar en la web</h3>


<div id="productos">
<img src="productos.jpg" height="400px" width="600px">
</div>


<div id="flecha">
<img src="flecha.png" height="100px" width="200px">
</div>


<div id="texto">

<p>Para comprar los productos llame al numero 941345923</p>



</div>

</body>
</html>