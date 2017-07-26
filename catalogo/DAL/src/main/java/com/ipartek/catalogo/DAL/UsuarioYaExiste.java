package com.ipartek.catalogo.DAL;

public class UsuarioYaExiste extends DALException {

	private static final long serialVersionUID = 1L;

	public UsuarioYaExiste() {
		super();
	}

	public UsuarioYaExiste(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioYaExiste(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioYaExiste(String message) {
		super(message);
	}

	public UsuarioYaExiste(Throwable cause) {
		super(cause);
	}

}
