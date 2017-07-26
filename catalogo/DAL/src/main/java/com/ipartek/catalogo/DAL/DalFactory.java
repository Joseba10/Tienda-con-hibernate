package com.ipartek.catalogo.DAL;

public class DalFactory {

	public static UsuariosDAL getUsuariosDAL() {
		// return new UsuarioDalUsuarioUnico();
		// Crea un nuevo usuario cada vez que se ejecute
		return new UsuariosDalColeccion();
	}
}
