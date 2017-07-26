package com.ipartek.catalogo.DAL;

public class ProductoDalFactory {

	public static ProductoDAL getProductoDAL() {
		// return new UsuarioDalUsuarioUnico();
		// Crea un nuevo usuario cada vez que se ejecute
		return new ProductoDalColeccion();
	}
}
