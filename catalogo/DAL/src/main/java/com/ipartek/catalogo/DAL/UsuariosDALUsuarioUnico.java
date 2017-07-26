package com.ipartek.catalogo.DAL;

import com.ipartek.TIPOS.Usuario;

public class UsuariosDALUsuarioUnico implements UsuariosDAL {

	private Usuario usuario;

	public void alta(Usuario usuario) {
		if (this.usuario.getNombre_completo().equals(usuario.getNombre_completo()))
			throw new DALException("Ya existe un usuario con ese nombre: " + usuario);

		this.usuario = usuario;
	}

	public boolean validar(Usuario usuario) {
		return this.usuario != null && this.usuario.equals(usuario);
	}

	public void modificar(Usuario usuario) {
		if (!this.usuario.getNombre_completo().equals(usuario.getNombre_completo()))
			throw new DALException("No se ha encontrado el usuario a modificar " + usuario);
		this.usuario = usuario;

	}

	public void borrar(Usuario usuario) {
		if (this.usuario.equals(usuario))
			this.usuario = null;
	}

	public Usuario buscarPorId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario[] buscarTodosLosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

}
